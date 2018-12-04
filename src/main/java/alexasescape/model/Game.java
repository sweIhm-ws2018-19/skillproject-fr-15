package alexasescape.model;

import java.time.Instant;
import java.util.*;

public class Game {
    private final Instant startTime;
    private int failedAttempts;
    private final int maxFailedAttempts;
    private Queue<Room> rooms;
    private final Player player;
    private GameStatus gameStatus;

    public Game(int maxFailedAttempts, List<Room> rooms, Player player) {
        Objects.requireNonNull(rooms, "rooms must not be null");
        if(rooms.size() != 3)
            throw new IllegalArgumentException("A game has to contain 3 rooms");
        this.maxFailedAttempts = maxFailedAttempts;
        this.rooms = new ArrayDeque<>();
        this.rooms.addAll(rooms);
        this.player = player;
        startTime = Instant.now();
    }

    public Room getCurrentRoom(){
        return rooms.peek();
    }

    public Player getPlayer() {
        return player;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean failed(){
        return failedAttempts++ < maxFailedAttempts;
    }


    public boolean finishRoom(){
        return rooms.poll() == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return failedAttempts == game.failedAttempts &&
                maxFailedAttempts == game.maxFailedAttempts &&
                Objects.equals(startTime, game.startTime) &&
                Objects.equals(rooms, game.rooms) &&
                Objects.equals(player, game.player) &&
                Objects.equals(gameStatus, game.gameStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, failedAttempts, maxFailedAttempts, rooms, player, gameStatus);
    }
}
