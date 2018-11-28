package alexasescape.model;

import java.util.ArrayList;
import java.util.Objects;

public class Room {

    private String name;
    private Game game;
    private ArrayList<Item> items;

    public Room(String name, Game game, ArrayList<Item> items) {
        this.name = name;
        this.game = game;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name) &&
                Objects.equals(game, room.game) &&
                Objects.equals(items, room.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, game, items);
    }
}
