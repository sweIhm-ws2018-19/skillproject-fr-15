package alexasescape.model;

import java.util.Objects;

public class Highscore {
    private int totalGames;
    private int minutes;
    private int seconds;

    public Highscore() {

    }

    public Highscore(int totalGames, int minutes, int seconds) {
        super();
        this.totalGames = totalGames;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Highscore highscore = (Highscore) o;
        return totalGames == highscore.totalGames &&
                minutes == highscore.minutes &&
                seconds == highscore.seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalGames, minutes, seconds);
    }
}
