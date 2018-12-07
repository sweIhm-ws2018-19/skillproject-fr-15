package alexasescape.model;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Item {

    private String name;
    private String description;
    private String solveDescription = "";  //Only set if this item is Key otherwise empty
    private boolean key;

    public Item(String name, String description, boolean key) {
        this.name = name;
        this.description = description;
        this.key = key;
    }

    public Item matches(String input){
        if(name.toLowerCase().contains(input.toLowerCase()))
            return this;
        else
            return null;
    }

}
