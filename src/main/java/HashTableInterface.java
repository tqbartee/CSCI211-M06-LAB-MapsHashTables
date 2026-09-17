public interface HashTableInterface<K, V> {
    void put(K key, V value);
    V get(K key);
    String getTableContent();
}