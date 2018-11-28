package alexasescape.model;

import java.util.Objects;

public class Item {

    private String name;
    private boolean isUsable;

    public Item(String name, boolean isUsable) {
        this.name = name;
        this.isUsable = isUsable;
    }

    public void use() {
        throw new UnsupportedOperationException("Not implemented yet!");
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
        return Objects.hash(name, isUsable);
    }
}
