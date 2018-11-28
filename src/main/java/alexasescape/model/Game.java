package alexasescape.model;

import java.util.List;
import java.util.Objects;

public class Game {

    private List<Room> rooms;
    private long startTime;

    public Game(List<Room> rooms) {
        this.rooms = rooms;
        startTime = System.currentTimeMillis();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return startTime == game.startTime &&
                Objects.equals(rooms, game.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rooms, startTime);
    }
}
