package alexasescape.model;

import java.util.Objects;

public class Player {

    private final String name;
    private final Highscore score;

    public Player(String name) {
        this.name = name;
        this.score = new Highscore();
    }

    public Player(String name, Highscore score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Highscore getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
