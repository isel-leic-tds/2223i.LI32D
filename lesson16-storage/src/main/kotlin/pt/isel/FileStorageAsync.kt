package pt.isel

import org.javaync.io.readText
import org.javaync.io.writeText
import kotlin.io.path.Path
import kotlin.io.path.exists


class FileStorageAsync<K, T>(
    private val folder: String,
    private val factory: (K) -> T,
    override val serializer: StringSerializer<T>
) : StorageAsync<K,T>
{
    private fun path(id: K) = "$folder/$id.txt"

    /**
     * Throws Exception if already exists an entity T with the same Id.
     */
    override suspend fun new(id: K): T {
        /**
         * Validate that id is unique.
         */
        val file = Path(path(id))
        require(!file.exists()) { "There is already an entity with given id $id" }
        /**
         * Create a new entity T and save in on file system.
         */
         val obj = factory(id)
         val objStr = serializer.write(obj)
         file.writeText(objStr)
         return obj
    }

    override suspend fun load(id: K): T? {
        val file = Path(path(id))
        if(!file.exists()) return null
        val objStr = file.readText();
        return serializer.parse(objStr)
    }

    override suspend fun save(id: K, obj: T) {
        val file = Path(path(id))
        require(file.exists()) { "There no entity with given id $id" }
        val objStr = serializer.write(obj)
        file.writeText(objStr)
    }
}
