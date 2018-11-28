package alexasescape.model;

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
}
