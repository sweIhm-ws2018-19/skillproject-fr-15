package alexasescape.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HighscoreTest {

    private Highscore score;

    @Before
    public void setUp() {
        score = new Highscore(3, 2, 1);
    }

    @Test
    public void getMinutes() {
        assertEquals(2, score.getMinutes());
    }

    @Test
    public void setMinutes() {
        score.setMinutes(6);
        assertEquals(6, score.getMinutes());
    }

    @Test
    public void getSeconds() {
        assertEquals(1, score.getSeconds());
    }

    @Test
    public void setSeconds() {
        score.setSeconds(6);
        assertEquals(6, score.getSeconds());
    }

    @Test
    public void getTotalGames() {
        assertEquals(3, score.getTotalGames());
    }

    @Test
    public void setTotalGames() {
        score.setTotalGames(6);
        assertEquals(6, score.getTotalGames());
    }
}
