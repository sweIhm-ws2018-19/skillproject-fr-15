package alexasescape.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    private Item item;

    @Before
    public void setUp() {
        item = new Item("Item", true);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUse() {
        item.use();
    }

    @Test
    public void testGetName() {
        assertEquals("Item", item.getName());
    }

    @Test
    public void testIsUsable() {
        assertTrue(item.isUsable());
    }

    @Test
    public void setUsable() {
        item.setUsable(false);
        assertFalse(item.isUsable());
    }

    @Test
    public void testEquals() {
        assertEquals(new Item("Item", true), item);
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(item, item);
    }

    @Test
    public void testEqualsNull() {
        assertNotEquals(item, "Test");
    }

    @Test
    public void testHashCode() {
        assertEquals(new Item("Item", true).hashCode(), item.hashCode());
    }
}
