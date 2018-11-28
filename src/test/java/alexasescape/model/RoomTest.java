package alexasescape.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RoomTest {
    private Room room;
    private List<Item> items;

    @Before
    public void setUP(){
        items = new ArrayList<>();
        items.add(new Item("test", true));
        room = new Room("test", items);
    }

    @Test(expected = NullPointerException.class)
    public void testItemsNull(){
        new Room("Test", new ArrayList<>(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroItems(){
        new Room("Test", new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooMuchItems(){
        final List<Item> itemsTest = new ArrayList<>();
        itemsTest.add(new Item("test", true));
        itemsTest.add(new Item("test1", true));
        itemsTest.add(new Item("test2", true));
        itemsTest.add(new Item("test3", true));
        itemsTest.add(new Item("test4", true));
        new Room("Test", itemsTest);
    }

    @Test
    public void testGetName() {
        assertEquals("test", room.getName());
    }

    @Test
    public void testGetItems() {
        assertEquals(items, room.getItems());
    }

    @Test
    public void testEquals() {
        final Room secondRoom = new Room("test", items);
        assertEquals(room,secondRoom);
    }
    @Test
    public void testEqualsSame() {
        assertEquals(room,room);
    }
    @Test
    public void testEqualsOtherType() {
        assertNotEquals(room,"test");
    }
    @Test
    public void testEqualsNull() {
        assertNotEquals(room,null);
    }

    @Test
    public void testEqualsOtherItems() {
        final List<Item> itemsTest = new ArrayList<>();
        itemsTest.add(new Item("test", true));
        itemsTest.add(new Item("test1", false));
        final Room secondRoom = new Room("test", itemsTest);
        assertNotEquals(room,secondRoom);
    }

    @Test
    public void testHashCode() {
        final Room secondRoom = new Room("test", items);
        assertEquals(room.hashCode(), secondRoom.hashCode());
    }
}