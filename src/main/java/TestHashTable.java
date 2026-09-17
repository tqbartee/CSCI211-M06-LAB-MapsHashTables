public class TestHashTable {

    public static void enterValuesIntoHashTable(SimpleHashTable<Integer, Character> thisHashTable) {
        // TODO: put the values into the hash table from the book
        // Figure 10.2.2 - use put method
    }

    public static void test() {
        SimpleHashTable<Integer, Character> hashTable =
                new SimpleHashTable<>(11);
        enterValuesIntoHashTable(hashTable);
        System.out.println(hashTable.getTableContent());
    }

    public static void main(String[] args) {
        test();
    }
}
