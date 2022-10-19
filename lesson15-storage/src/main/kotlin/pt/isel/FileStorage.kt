package pt.isel

import java.io.File

class FileStorage<K, T>(
    private val folder: String,
    private val factory: (K) -> T,
    override val serializer: StringSerializer<T>
) : Storage<K,T>
{
    private fun path(id: K) = "$folder/$id.txt"

    /**
     * Throws Exception if already exists an entity T with the same Id.
     */
    override fun new(id: K): T {
        /**
         * Validate that id is unique.
         */
        val file = File(path(id))
        require(!file.exists()) { "There is already an entity with given id $id" }
        /**
         * Create a new entity T and save in on file system.
         */
         val obj = factory(id)
         val objStr = serializer.write(obj)
         file.writeText(objStr)
         return obj
    }

    override fun load(id: K): T? {
        val file = File(path(id))
        if(!file.exists()) return null
        val objStr = file.readText();
        return serializer.parse(objStr)
    }

    override fun save(id: K, obj: T) {
        val file = File(path(id))
        require(file.exists()) { "There no entity with given id $id" }
        val objStr = serializer.write(obj)
        file.writeText(objStr)
    }

    override fun delete(id: K) {
        TODO("Not yet implemented")
    }
}
