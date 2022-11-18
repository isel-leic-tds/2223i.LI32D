package pt.isel

import kotlinx.coroutines.runBlocking
import pt.isel.ttt.*
import java.io.File
import kotlin.test.*

class StorageAsyncTest {
    class DummyEntity(
        val id: Int,
        val address: String? = null
    )
    object DummySerializer : StringSerializer<DummyEntity> {
        override fun write(obj: DummyEntity) = "${obj.id};${obj.address}"
        override fun parse(input: String): DummyEntity {
            val words = input.split(";")
            return DummyEntity(words[0].toInt(), words[1])
        }
    }

    val testFolder = "out"
    val dummyId = 712535

    @BeforeTest fun setup() {
        val f = File("$testFolder/$dummyId.txt")
        if(f.exists()) f.delete()
    }

    @Test fun `Create an entity with existing id throws exception`() {
        runBlocking {
            val fs = FileStorageAsync(testFolder, ::DummyEntity, DummySerializer)
            fs.new(dummyId)
            val err = assertFailsWith<IllegalArgumentException> { fs.new(dummyId) }
            assertEquals(err.message, "There is already an entity with given id $dummyId")
        }
    }
    @Test fun `Load an entity with unknown id returns null`() {
        runBlocking {
            val fs = FileStorageAsync(testFolder, ::DummyEntity, DummySerializer)
            assertNull(fs.load(7612541))
        }
    }
    @Test fun `Load an existing entity`() {
        runBlocking {
            val fs = FileStorageAsync(testFolder, ::DummyEntity, DummySerializer)
            fs.new(dummyId)
            assertNotNull(fs.load(dummyId))
        }
    }
    @Test fun `Save an entity and load it`() {
        runBlocking {
            val fs = FileStorageAsync(testFolder, ::DummyEntity, DummySerializer)
            fs.new(dummyId)
            fs.save(dummyId, DummyEntity(dummyId, "Rua Rosa"))
            val obj = fs.load(dummyId)
            assertNotNull(obj)
            assertEquals(dummyId, obj.id)
            assertEquals("Rua Rosa", obj.address)
        }
    }
    @Test fun `Save a complex entity and load it`() {
        val serializer = object : StringSerializer<Board> {
            override fun write(obj: Board) = obj.serialize()
            override fun parse(input: String) =  Board.deserialize(input)
        }
        val fs = FileStorageAsync<Int, Board>(testFolder, { BoardRun() }, serializer)
        runBlocking {
            val board = fs
                .new(dummyId)
                .play(Position(1, 1), Player.CROSS)
                .play(Position(2, 0), Player.CIRCLE)
                .play(Position(1, 0), Player.CROSS)
                .play(Position(2, 2), Player.CIRCLE)
                .play(Position(1, 2), Player.CROSS)
            fs.save(dummyId, board)
            val actual = fs.load(dummyId)
            assertNotNull(actual)
            assertNotSame(board, actual)
            assertIs<BoardWinner>(actual)
            val iter = actual.moves.iterator()
            board.moves.forEach { assertEquals(it, iter.next()) }
        }
    }
}
