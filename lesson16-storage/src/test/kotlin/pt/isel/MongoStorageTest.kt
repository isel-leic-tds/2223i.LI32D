package pt.isel

import com.mongodb.ConnectionString
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.deleteOneById
import pt.isel.ttt.*
import kotlin.random.Random
import kotlin.test.*

class MongoStorageTest {
    class Student(val _id: String, val name: String, val address: String)

    val connectionString =
        ConnectionString("mongodb+srv://gamboa:GOs5fi7me3sZE299@cluster0.mosya9y.mongodb.net/?retryWrites=true&w=majority")
    val client = KMongo.createClient(connectionString)
    val database: MongoDatabase = client.getDatabase("TicTacToe")

    @BeforeTest fun setup() {
        database.getCollection("Console-Ui").deleteOneById("super")
    }

    @Test fun `Check Cloud Mongo DB connection`() {
        /**
         * Weakly typed.
         */
        // val collection: MongoCollection<Document> = database.getCollection("test")
        val collection: MongoCollection<Student> = database.getCollection("test", Student::class.java)
        collection.insertOne(Student(Random.nextInt().toString(), "Ze Manel", "Rua Rosa"))
    }
        @Test fun `Save a complex entity and load it`() {
        val serializer = object : StringSerializer<Board> {
            override fun write(obj: Board) = obj.serialize()
            override fun parse(input: String) =  Board.deserialize(input)
        }
        val fs = MongoStorage<Board>("Console-Ui", { BoardRun() }, serializer, database)
        val board = fs
            .new("super")
            .play(Position(1, 1), Player.CROSS)
            .play(Position(2, 0), Player.CIRCLE)
            .play(Position(1, 0), Player.CROSS)
            .play(Position(2, 2), Player.CIRCLE)
            .play(Position(1, 2), Player.CROSS)
        fs.save("super", board)
        val actual = fs.load("super")
        assertNotNull(actual)
        assertNotSame(board, actual)
        assertIs<BoardWinner>(actual)
        val iter = actual.moves.iterator()
        board.moves.forEach { assertEquals(it, iter.next()) }
    }
}
