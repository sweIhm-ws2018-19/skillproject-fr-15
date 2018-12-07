package alexasescape.model;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Player {

    private String name;
    private Highscore score;

    public Player(String name) {
        this.name = name;
        score = new Highscore();
    }

}
