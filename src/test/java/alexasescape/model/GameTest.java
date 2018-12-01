package alexasescape.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        List<Room> rooms = new LinkedList<>();
        final int maxAttempts = 5;
        List<Item> items = new ArrayList<>();
        items.add(new Item("Test", "xy",false));
        rooms.add(new Room("Room", items));
        rooms.add(new Room("Room", items));
        rooms.add(new Room("Room", items));
        game = new Game(maxAttempts,rooms, new Player("test", new Highscore()));
    }


    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullAsRoom() {
        new Game(5,null, new Player("test", new Highscore()));
}

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyRooms() {
        new Game(5, new ArrayList<>(), new Player("test", new Highscore()));
    }




    @Test
    public void testEqualsSameObject() {
        assertEquals(game, game);
    }

    @Test
    public void testEqualsOtherType() {
        assertNotEquals(game, "Test");
    }

    @Test
    public void testEqualsNull() {
        assertNotEquals(game, null);
    }

}
