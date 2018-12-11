package alexasescape.model;

import alexasescape.constants.SpeechText;
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
        String retVal = SpeechText.NOT_PLAYED;
        if (totalGames > 0) {
            retVal = String.format(SpeechText.OF_TRIES, totalGames);
            if (minutes > 0 || seconds > 0) {
                retVal += String.format(SpeechText.BEST_ROUND, minutes, seconds);
            } else {
                retVal += SpeechText.NOT_WON;
            }
        }
        return retVal;
    }
}
