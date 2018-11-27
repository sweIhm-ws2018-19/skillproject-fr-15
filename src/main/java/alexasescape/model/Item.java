package alexasescape.model;

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
}
