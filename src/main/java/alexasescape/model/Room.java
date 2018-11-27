package alexasescape;

import java.util.ArrayList;

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
}
