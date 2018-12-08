package alexasescape.model;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private Game game2;
    private Game game3;

    @Before
    public void setUp() {
        List<Room> rooms = new LinkedList<>();
        final int maxAttempts = 2;
        List<Item> items = new ArrayList<>();
        List<Item> items2 = new ArrayList<>();
        items.add(new Item("Test", "xy",false));
        items.add(new Item("Test1", "xyz", true));
        items2.add(new Item("Room2","xyza", true));
        rooms.add(new Room("Room1", items));
        rooms.add(new Room("Room2", items2));
        rooms.add(new Room("Room3", items));
        game = new Game(maxAttempts,rooms, new Player("test", new Highscore()));
        Queue rooms2 = new ArrayDeque<>();
        rooms2.addAll(rooms);
        game2 = new Game(new Date(),0, 3, rooms2, new Player("test2"),GameStatus.DESCRIBE);
        game3 = new Game(new Date(),0, 3, rooms2, new Player("test2"),GameStatus.DESCRIBE);
    }

    @Test
    public void testToString(){
        assertTrue(game2.toString().contains(game3.toString()));
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
    public void testMaxFailedAttempts(){
        assertTrue(game.failed());
        assertTrue(game.failed());
        assertFalse(game.failed());
    }

    @Test
    public void testFailedTurn(){
        assertTrue(game.nextTurn("falsch").contains("Wie bitte"));
        game.nextTurn("Test");
        game.nextTurn("Test");
        assertTrue(game.getFailedAttempts()==2);
        assertTrue(game.nextTurn("Test").contains("Spiel zu Ende"));
    }

    @Test
    public void testNextTurn(){
        assertTrue(game.nextTurn("falscherInput").contains("Wie bitte"));
        assertTrue(game.nextTurn("Test").contains("Test"));
        assertTrue(game.nextTurn("Test1").contains("Room2"));
        assertTrue(game.nextTurn("Room2").contains("Test"));
        assertTrue(game.nextTurn("Test1").contains("Danke! Ich bin frei! Und jetzt schalte dein Eco aus"));
    }


    @Test
    public void testfinishRoom(){
        final List<Item> items = new ArrayList<>();
        items.add(new Item("Test", "xy",false));
        items.add(new Item("Test1", "xyz", true));
        final Room firstRoom = new Room("Room1", items);
        final Room thirdRoom = new Room("Room3", items);
        assertEquals(firstRoom ,game.getCurrentRoom());
        game.finishRoom();
        game.finishRoom();
        assertEquals(thirdRoom, game.getCurrentRoom());
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

    @Test
    public void testHashCode(){
        assertEquals(game3.hashCode(), game2.hashCode());
    }

}
