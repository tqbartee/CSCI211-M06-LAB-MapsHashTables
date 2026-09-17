import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleHashTableTests {

    @Test
    public void testPutAndGet() {
        SimpleHashTable<Integer, String> table = new SimpleHashTable<>(10);
        table.put(1, "One");
        table.put(2, "Two");
        table.put(11, "Eleven"); // Collision with 1 if capacity is 10

        assertEquals("One", table.get(1));
        assertEquals("Two", table.get(2));
        assertEquals("Eleven", table.get(11));
    }

    @Test
    public void testUpdateValue() {
        SimpleHashTable<Integer, String> table = new SimpleHashTable<>(10);
        table.put(1, "One");
        table.put(1, "Updated One");

        assertEquals("Updated One", table.get(1));
    }

    @Test
    public void testGetNonExistentKey() {
        SimpleHashTable<Integer, String> table = new SimpleHashTable<>(10);
        assertNull(table.get(99));
    }

    @Test
    public void testCollisionHandling() {
        // With capacity 10, keys 1, 11, 21 all hash to index 1 (or -1 if negative, but Math.abs is used)
        SimpleHashTable<Integer, String> table = new SimpleHashTable<>(10);
        table.put(1, "One");
        table.put(11, "Eleven");
        table.put(21, "Twenty-One");

        assertEquals("One", table.get(1));
        assertEquals("Eleven", table.get(11));
        assertEquals("Twenty-One", table.get(21));
    }

    @Test
    public void testStringKeys() {
        SimpleHashTable<String, Integer> table = new SimpleHashTable<>(10);
        table.put("apple", 1);
        table.put("banana", 2);
        
        // "apple".length() = 5. Index 5.
        // "banana".length() = 6. Index 6.
        assertEquals(1, table.get("apple"));
        assertEquals(2, table.get("banana"));
    }

    @Test
    public void testNullKey() {
        SimpleHashTable<String, Integer> table = new SimpleHashTable<>(10);
        table.put(null, 0);
        assertEquals(0, table.get(null));
    }

    @Test
    public void testEnterValuesIntoHashTable() {
        SimpleHashTable<Integer, Character> table = new SimpleHashTable<>(11);
        TestHashTable.enterValuesIntoHashTable(table);

        assertEquals('D', table.get(1));
        assertEquals('C', table.get(25));
        assertEquals('F', table.get(3));
        assertEquals('Z', table.get(14));
        assertEquals('A', table.get(6));
        assertEquals('Z', table.get(39));
        assertEquals('Q', table.get(7));
    }

    @Test
    public void testGetTableContent() {
        SimpleHashTable<Integer, String> table = new SimpleHashTable<>(3);
        table.put(0, "Zero");
        table.put(3, "Three");
        table.put(1, "One");

        String content = table.getTableContent();
        assertTrue(content.contains("Bucket 0: [3 : Three] -> [0 : Zero]"));
        assertTrue(content.contains("Bucket 1: [1 : One]"));
        assertTrue(content.contains("Bucket 2: null"));
    }
}
