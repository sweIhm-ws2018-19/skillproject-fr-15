package alexasescape.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Room {

    private String name;
    @EqualsAndHashCode.Exclude
    private List<Item> items;
    private String solveDescription = "";

    public Room(String name, List<Item> items) {
        Objects.requireNonNull(items, "Items must not be null");
        if (items.isEmpty() || items.size() > 4)
            throw new IllegalArgumentException("Room must contain between 1 and 4 items");
        this.name = name;
        this.items = items;
    }

    private Item findKey(List<Item> items) {
        Item key = null;
        for (Item item : items) {
            if (item.isKey()) {
                key = item;
            }
        }
        return key;
    }

    @JsonIgnore
    public String getDescription() {
        //return "Hier ist" + items.stream().map(item -> " ein " + item.getName()).collect(Collectors.joining());

        StringBuilder output = new StringBuilder("Hier ist ");
        boolean first = true;
        String last = "";
        for (Item item : items) {
            if (first) {
                last = item.getName();
                first = false;
            } else {
                output.append(item.getName()).append(" ");
            }
        }
        output.append("und ");
        output.append(last);
        return output.toString();
    }

}
