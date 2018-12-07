package alexasescape.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Highscore {
    private int totalGames;
    private int minutes;
    private int seconds;

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
