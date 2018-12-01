package alexasescape.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    private Item item;

    @Before
    public void setUp() {
        item = new Item("Item", "test", true);
    }

    @Test
    public void testGetName() {
        assertEquals("Item", item.getName());
    }

    @Test
    public void testIsUsable() {
        assertTrue(item.isKey());
    }

    @Test
    public void testEquals() {
        assertEquals(new Item("Item", "test", true), item);
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(item, item);
    }

    @Test
    public void testEqualsOtherType() {
        assertNotEquals(item, "Test");
    }
    @Test
    public void testEqualsNull() {
        assertNotEquals(item, null);
    }

    @Test
    public void testHashCode() {
        assertEquals(new Item("Item", "test", true).hashCode(), item.hashCode());
    }
}
