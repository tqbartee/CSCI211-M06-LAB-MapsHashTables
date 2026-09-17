public class SimpleHashTable<K, V> implements HashTableInterface<K, V> {

    // Definition of a hash table entry (nested class)
    static class HashEntry<K, V> {
        K key;
        V value;
        // Below is pointer to next entry (linked list)
        HashEntry<K, V> next;

        // Constructor for a single entry
        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Definition of storage (use array of buckets here)
    // Buckets are actually linked lists
    HashEntry<K, V>[] arrayOfEntries;
    int capacity;

    @SuppressWarnings("unchecked")
    public SimpleHashTable(int capacity) {
        this.capacity = capacity;
        // Create a raw array and cast it for generics
        this.arrayOfEntries = (HashEntry<K, V>[]) new HashEntry[capacity];
    }

    // The Put Method (Insert or Update)
    @Override
    public void put(K key, V value) {
        int index = hashFunctionGetIndex(key);
        HashEntry<K, V> head = arrayOfEntries[index];

        // Check if the key already exists in the bucket
        while (head != null) {
            if ((head.key == null && key == null) || (head.key != null && head.key.equals(key))) {
                head.value = value; // Update the value
                return;
            }
            head = head.next;
        }

        // Key not found: Insert new node at the "head" of the bucket list
        HashEntry<K, V> newNode = new HashEntry<>(key, value);
        newNode.next = arrayOfEntries[index];
        arrayOfEntries[index] = newNode;
    }

    // The Get Method (Retrieval)
    @Override
    public V get(K key) {
        int index = hashFunctionGetIndex(key);
        HashEntry<K, V> head = arrayOfEntries[index];
        // Walk through the linked list in the bucket
        while (head != null) {
            if ((head.key == null && key == null) || (head.key != null && head.key.equals(key))) {
                return head.value;
            }
            head = head.next;
        }
        return null; // Key not found
    }

    // The Hash Function itself
    private int hashFunctionGetIndex(K key) {
        if (key == null) {
            return 0;
        }
        if (key instanceof Integer) {
            int inputkey = (int) key;
            // TODO: put the hash function here
            // Hashfunction - use the key itself mod the capacity
            // to prevent array overrun
            // use Math.abs to allow negative numbers
        }
        if (key instanceof String) {
            int thishashcode;
            // Cast to String to make sure
            String thisString = (String) key;
            // Next line computes the hashcode
            thishashcode = thisString.length() % capacity;
            return thishashcode;
        }
        // DEFAULT: use built-in object hashCode()
        // hashCode() is built into every Java object
        // Math.abs handles negative hash codes, % capacity ensures it fits in the array
        return Math.abs(key.hashCode()) % capacity;
    }

    // Supporting methods below

    public int getCapacity() {
        return capacity;
    }

    public HashEntry<K, V>[] getBuckets() {
        return arrayOfEntries;
    }

    // Get a single string of the table contents for print
    @Override
    public String getTableContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("SimpleHashTable Contents (Capacity: ").append(capacity).append("):\n");
        for (int i = 0; i < capacity; i++) {
            sb.append("Bucket ").append(i).append(": ");
            HashEntry<K, V> current = arrayOfEntries[i];
            if (current == null) {
                sb.append("null");
            } else {
                while (current != null) {
                    sb.append("[").append(current.key).append(" : ").append(current.value).append("]");
                    if (current.next != null) {
                        sb.append(" -> ");
                    }
                    current = current.next;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}