package pt.isel

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.replaceOneById

class Doc(val _id: String, val obj: String)

class MongoStorage<T>(
    private val collectionName: String,
    private val factory: (String) -> T,
    override val serializer: StringSerializer<T>,
    database: MongoDatabase
) : Storage<String,T>
{
    val collection = database.getCollection(collectionName, Doc::class.java)

    /**
     * Throws Exception if already exists a document with the same Id.
     */
    override fun new(id: String): T {
        /**
         * Validate that id is unique.
         */
        require(load(id) == null) { "There is already a document with given id $id" }
        /**
         * Create a new entity T and save in on file system.
         */
         val obj = factory(id)
         val objStr = serializer.write(obj)
         collection.insertOne(Doc(id, objStr))
         return obj
    }

    override fun load(id: String): T? {
        val doc = collection.findOneById(id) ?: return null
        val objStr = doc.obj
        return serializer.parse(objStr)
    }

    override fun save(id: String, obj: T) {
        require(load(id) != null) { "There is no document with given id $id" }
        val objStr = serializer.write(obj)
        collection.replaceOneById(id, Doc(id, objStr))
    }

    override fun delete(id: String) {
        require(load(id) != null) { "There is no document with given id $id" }
        collection.deleteOneById(id)
    }
}
