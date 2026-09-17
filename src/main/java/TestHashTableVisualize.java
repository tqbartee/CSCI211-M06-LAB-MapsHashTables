public class TestHashTableVisualize {

    public static void enterValuesIntoHashTable(SimpleHashTable<Integer, Character> thisHashTable) {
        thisHashTable.put(1, 'D');
        thisHashTable.put(25, 'C');
        thisHashTable.put(3, 'F');
        thisHashTable.put(14, 'Z');
        thisHashTable.put(6, 'A');
        thisHashTable.put(39, 'Z');
        thisHashTable.put(7, 'Q');
    }

    public static void test() {
        SimpleHashTable<Integer, Character> hashTable =
                new SimpleHashTable<>(11);
        enterValuesIntoHashTable(hashTable);
        HashTableVisualizer.display(hashTable);
    }

    public static void main(String[] args) {
        test();
    }
}
