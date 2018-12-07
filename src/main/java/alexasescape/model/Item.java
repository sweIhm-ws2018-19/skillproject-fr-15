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
    private boolean key;

    public Item matches(String input){
        if(name.toLowerCase().contains(input.toLowerCase()))
            return this;
        else
            return null;
    }

}
