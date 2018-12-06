package alexasescape.model;

import java.util.Objects;

public class Item {

    private final String name;
    private final String description;
    private final boolean key;

    public Item(String name, String description, boolean key) {
        this.name = name;
        this.description = description;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isKey() {
        return key;
    }



    public Item matches(String input){
        if(name.toLowerCase().contains(input.toLowerCase()))
            return this;
        else
            return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return isKey() == item.isKey() &&
                Objects.equals(getName(), item.getName()) &&
                Objects.equals(getDescription(), item.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), isKey());
    }
}
