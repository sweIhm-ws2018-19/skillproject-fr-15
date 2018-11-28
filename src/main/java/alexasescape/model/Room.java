package alexasescape.model;

import java.util.List;
import java.util.Objects;

public class Room {

    private String name;
    private Game game;
    private List<Item> items;

    public Room(String name, Game game, List<Item> items) {
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

    public List<Item> getItems() {
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
