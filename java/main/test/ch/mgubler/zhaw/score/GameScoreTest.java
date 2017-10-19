package ch.mgubler.zhaw.score;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameScoreTest {

    private GameScore gameScoreTestee;

    @Before
    public void setUp() throws Exception {
        this.gameScoreTestee = new GameScore();
    }

    @Test
    public void updateScore() throws Exception {
        gameScoreTestee.updateScore(20);
        assertEquals(20, gameScoreTestee.getGameScore());
    }

}