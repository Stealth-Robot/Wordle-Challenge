import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordleTests {

    WordleModel model;

    @Before
    public void setUp() {
        WordleModel.setDailyWord("GHOST");
        model = new WordleModel(5, new WordleGUI(5, false));
    }

    @Test
    public void testBadGuess() {
        System.out.println("running testIncorrectLatter");
        model.validateGuess("GHOVS");

        assertFalse(model.guessIsCorrect());
    }

    @Test
    public void testGoodGuess() {
        System.out.println("running testIncorrectLatter");
        model.validateGuess("GHOST");

        assertTrue(model.guessIsCorrect());
    }

    @Test
    public void testLetterNotInWord() {
        System.out.println("running testIncorrectLatter");
        model.makeGuess("GHOVS");
        assertFalse(model.letterInWord('V'));
    }

    @Test
    public void testLetterInWrongPosition() {
        System.out.println("running testIncorrectLatter");
        model.makeGuess("GHOVS");
        assertTrue(model.letterInWord('S'));
    }

    @Test
    public void testLetterInCorrectPosition() {
        System.out.println("running testIncorrectLatter");
        model.makeGuess("GHOVS");
        assertTrue(model.letterInWord('G'));
    }

    @After
    public void tearDown() {
        System.out.println("test finished\n");
    }
}