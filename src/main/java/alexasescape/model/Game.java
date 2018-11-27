package alexasescape.model;

import java.util.ArrayList;

public class Game {

    private ArrayList<Room> rooms;
    private long startTime;

    public Game(ArrayList<Room> rooms) {
        this.rooms = rooms;
        startTime = System.currentTimeMillis();
    }

    public void stop() {

    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public long getStartTime() {
        return startTime;
    }
}
