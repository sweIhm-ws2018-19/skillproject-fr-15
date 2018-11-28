package alexasescape.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Room", new ArrayList<>()));
        game = new Game(rooms);
    }

    @Test
    public void testGetRooms() {
        assertNotNull(game.getRooms());
        assertEquals(1, game.getRooms().size());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullAsRoom() {
        new Game(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyRooms() {
        new Game(new ArrayList<>());
    }

    @Test
    public void testGetStartTime() {
        assertTrue(game.getStartTime() > 0);
        assertTrue(game.getStartTime() <= System.currentTimeMillis());
    }

    @Test
    public void testEquals() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Room", new ArrayList<>()));
        final Game secondGame = new Game(rooms);

        assertEquals(secondGame, game);
    }

    @Test
    public void testHashCode() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Room", new ArrayList<>()));
        final Game secondGame = new Game(rooms);

        assertEquals(secondGame.hashCode(), game.hashCode());
    }
}
