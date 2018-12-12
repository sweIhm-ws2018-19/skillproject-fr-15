package alexasescape.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    public void testWinGame_WithEmptyScore() {
        final Highscore score = new Highscore();
        score.winGame(1, 2);
        assertEquals(new Highscore(1, 1, 2), score);
    }

    @Test
    public void testWinGame_WithExistingBetterScore() {
        final Highscore score = new Highscore(1, 1, 1);
        score.winGame(2, 2);
        assertEquals(new Highscore(2, 1, 1), score);
    }

    @Test
    public void testWinGame_WithExistingScore_MinutesBetter() {
        final Highscore score = new Highscore(1, 2, 2);
        score.winGame(1, 3);
        assertEquals(new Highscore(2, 1, 3), score);
    }

    @Test
    public void testWinGame_WithExistingScore_SecondsBetter() {
        final Highscore score = new Highscore(1, 2, 2);
        score.winGame(2, 1);
        assertEquals(new Highscore(2, 2, 1), score);
    }

    @Test
    public void getSeconds() {
        assertEquals(1, score.getSeconds());
    }

    @Test
    public void getTotalGames() {
        assertEquals(3, score.getTotalGames());
    }

    @Test
    public void testEquals() {
        assertEquals(new Highscore(3, 2, 1), score);
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(score, score);
    }

    @Test
    public void testEqualsOtherType() {
        assertNotEquals(score, "Test");
    }

    @Test
    public void testEqualsNull() {
        assertNotEquals(score, null);
    }

    @Test
    public void testHashCode() {
        assertEquals(new Highscore(3, 2, 1).hashCode(), score.hashCode());
    }
}
