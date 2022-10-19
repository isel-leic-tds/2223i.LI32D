package pt.isel

/**
 * param K type of T id
 * param T type of the domain entity
 */
interface Storage<K, T> {
    val serializer: StringSerializer<T>
    fun new(id: K) : T
    fun load(id: K) : T?
    fun save(id: K, obj: T)
    fun delete(id: K)
}
