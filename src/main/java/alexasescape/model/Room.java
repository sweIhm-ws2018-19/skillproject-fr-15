package alexasescape.model;

import java.util.List;
import java.util.Objects;

public class Room {

    private String name;
    private List<Item> items;

    public Room(String name, List<Item> items) {
        Objects.requireNonNull(items,"Items must not be null");
        if(items.isEmpty() || items.size() > 4)
            throw new IllegalArgumentException("Room must contain between 1 and 4 items");
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getDescription(){
        String output = "Hier ist";
        for (Item item: items){
            output = output + " ein " +item.getName();
        }
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name) &&
                Objects.equals(items, room.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, items);
    }
}
