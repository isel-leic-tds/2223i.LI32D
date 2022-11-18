package pt.isel

/**
 * param K type of T id
 * param T type of the domain entity
 */
interface StorageAsync<K, T> {
    val serializer: StringSerializer<T>
    suspend fun new(id: K) : T
    suspend fun load(id: K) : T?
    suspend fun save(id: K, obj: T)
}
