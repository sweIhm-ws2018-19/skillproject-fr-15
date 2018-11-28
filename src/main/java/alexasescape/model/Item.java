package alexasescape.model;

import java.util.Objects;

public class Item {

    private String name;
    private boolean isUsable;
    private Room room;

    public Item(String name, boolean isUsable, Room room) {
        this.name = name;
        this.isUsable = isUsable;
        this.room = room;
    }

    public void use() {

    }

    public String getName() {
        return name;
    }

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean usable) {
        isUsable = usable;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return isUsable == item.isUsable &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isUsable, room);
    }
}
