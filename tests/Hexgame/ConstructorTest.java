package Hexgame;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aprile on 6/08/2015.
 */
public class ConstructorTest {

    private static final int COUNT = 1;

    @Test
    public void testConstructor() {
        String[] games = new String[COUNT];
        for (int i = 0; i < COUNT; i++) {
            games[i] = new HexGame().toString();
            assertTrue("Bad game returned by constructor: " + games[i], HexGame.legitimateGame(games[i]));
            for (int j = 0; j < i; j++) {
                assertFalse("Same game returned twice by constructor: " + games[j], games[i].equals(games[j]));
            }
        }
    }
}
