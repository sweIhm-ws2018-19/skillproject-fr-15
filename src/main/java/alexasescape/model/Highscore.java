package alexasescape.model;

import java.util.Objects;

public class Highscore {
    private int totalGames;
    private int minutes;
    private int seconds;

    public Highscore() {
        totalGames = -1;
        minutes = -1;
        seconds = -1;
    }

    public Highscore(int totalGames, int minutes, int seconds) {
        super();
        this.totalGames = totalGames;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void looseGame() {
        totalGames++;
    }

    public void winGame(int minutes, int seconds) {
        totalGames++;
        if (minutes < this.minutes) {
            this.minutes = minutes;
            this.seconds = seconds;
        }
        if (minutes == this.minutes && seconds < this.seconds) {
            this.seconds = seconds;
        }
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

    @Override
    public String toString() {
        String retVal = "Du hast noch keine Runde gespielt. Also los gehts!";
        if (totalGames > 0) {
            retVal = String.format("Von insgesamt %d versuchen ", totalGames);
            if (minutes > 0 || seconds > 0) {
                retVal += String.format("liegt die beste Runde bei %d Minuten und %d Sekunden.", minutes, seconds);
            } else {
                retVal += "hast du noch keinen erfolgreich beendet!";
            }
        }
        return retVal;
    }
}
