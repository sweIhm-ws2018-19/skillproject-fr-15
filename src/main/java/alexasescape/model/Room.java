package alexasescape.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Room {

    private String name;
    @EqualsAndHashCode.Exclude
    private List<Item> items;

    public Room(String name, List<Item> items) {
        Objects.requireNonNull(items, "Items must not be null");
        if (items.isEmpty() || items.size() > 4)
            throw new IllegalArgumentException("Room must contain between 1 and 4 items");
        this.name = name;
        this.items = items;
    }

    @JsonIgnore
    public String getDescription() {
        return "Hier ist" + items.stream().map(item -> " ein " + item.getName()).collect(Collectors.joining());
    }

}
