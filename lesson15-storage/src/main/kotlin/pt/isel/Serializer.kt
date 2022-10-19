package pt.isel

/**
 * Serializer interface for strings.
 * Notice that serializers can deal with other data types,
 * such as byte[], or other.
 *
 * Given an object obj, parse(write(obj)) == obj (i.e. equals)
 */
interface StringSerializer<T> {
    fun write(obj: T) : String
    fun parse(input: String): T
}
