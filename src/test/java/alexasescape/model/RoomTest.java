package alexasescape.model;

import org.junit.Before;
import org.junit.Ignore;
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
        items.add(new Item("test", "test", true));
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
        itemsTest.add(new Item("test", "test", true));
        itemsTest.add(new Item("test1", "test", true));
        itemsTest.add(new Item("test2", "test", true));
        itemsTest.add(new Item("test3", "test", true));
        itemsTest.add(new Item("test4", "test", true));
        new Room("Test", itemsTest);
    }

    @Test
    public void testGetName() {
        assertEquals("test", room.getName());
    }

    @Ignore
    public void testGetDescription(){assertEquals("Hier ist und test. ",room.getDescription());}

    @Ignore
    public void testMultipleDescription(){
        List<Item> testItems = new ArrayList<>();
        testItems.add(new Item("ein test","test",true));
        testItems.add(new Item("ein test1","test",true));
        Room testRoom =new Room("test", testItems);
        assertEquals("Hier ist ein test1, und ein test. ",testRoom.getDescription());

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
    public void testEqualsOtherName() {
        final Room secondRoom = new Room("test1", items);
        assertNotEquals(room,secondRoom);
    }

    @Test
    public void testEqualsOtherItems() {
        final List<Item> itemsTest = new ArrayList<>();
        itemsTest.add(new Item("test", "test", true));
        itemsTest.add(new Item("test1", "test", false));
        final Room secondRoom = new Room("test2", itemsTest);
        assertNotEquals(room,secondRoom);
    }

    @Test
    public void testHashCode() {
        final Room secondRoom = new Room("test", items);
        assertEquals(room.hashCode(), secondRoom.hashCode());
    }
}
