import javax.swing.*;
import java.awt.*;

public class WordleModel {
    private static String dailyWord = "";
    private boolean[] correctGuess;
    private boolean[] closeGuess;

    private final int size;
    private int turnsRemaining;
    String input;

    WordleGUI view;
    WordleController controller;

    private char[][] grid;

    private static String getDailyWord() {
        return dailyWord;
    }

    public static void setDailyWord(String word) {
        dailyWord = word;
    }

    /**
     * Constructs model and initializes values
     */
    public WordleModel(int size, WordleGUI view) {
        this.size = size;
        this.turnsRemaining = size;

        this.grid = new char[size][size];
        this.correctGuess = new boolean[size];
        this.closeGuess = new boolean[size];
        this.resetModel();

        this.view = view;
        this.controller = new WordleController(this, view, size);
        this.view.addActionListenerToTextField(controller);

        if(dailyWord.equals("")) {
            setDailyWord(JOptionPane.showInputDialog("Please enter a " + size + " length word").toUpperCase());
        }
        System.out.println("Daily Word: " + getDailyWord());
    }

    /**
     * returns whether a letter is in a word or not
     *
     * @param letter letter to check
     * @return true if letter is in word and it wasnt already accounted for, false otherwise
     */
    public boolean letterInWord(char letter) {
        for(int i = 0; i < size; i++) {

            //if letter has already been accounted for, we do not want to reuse it
            if(this.correctGuess[i]) {
                continue;
            }
            if(this.closeGuess[i]) {
                continue;
            }

            if(letter == getDailyWord().charAt(i)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Validates the user's guess and updates the view
     *
     * @param guess input guess string
     */
    public void validateGuess(String guess) {
        //first check if any letters are in correct position
        for(int i = 0; i < size; i++) {
            if(guess.charAt(i) == getDailyWord().charAt(i)) {
                this.correctGuess[i] = true;
            }
        }
        //next check if any letters are correct but in wrong position
        for(int i = 0; i < size; i++) {
            if(letterInWord(guess.charAt(i))) {
                this.closeGuess[i] = true;
            }
        }
    }

    /**
     * updates the view
     */
    public void updateView() {
        Color color;
        //update the view
        for(int i = 0; i < size; i++) {
            if(correctGuess[i]) {
                color = Color.GREEN;
            } else if (closeGuess[i]) {
                color = Color.YELLOW;
            } else {
                color = Color.GRAY;
            }

            view.updateSpace(size-turnsRemaining, i, input.charAt(i), color);
        }
        view.clearInput();
    }

    /**
     * Checks if all letters are guessed and in correct spaces
     *
     * @return true if all letters are correctly placed
     */
    public boolean guessIsCorrect() {
        for(int i = 0; i < size; i++) {
            if(!correctGuess[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the game is won or lost
     */
    public void checkForGameEnd() {
        //handle game over
        if(guessIsCorrect()) {
            JOptionPane.showInputDialog("You Win!");
        } else if (this.turnsRemaining < 1) {
            JOptionPane.showInputDialog("You Lose - you ran out of guesses");
        }
    }

    /**
     * Resets the model's values to the initial state
     */
    public void resetModel() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
            correctGuess[i] = false;
            closeGuess[i] = false;
        }
        this.input = "";
    }

    /**
     * resets the letter usage tracking variables
     */
    private void resetAccountedFor() {
        for (int i = 0; i < size; i++) {
            correctGuess[i] = false;
            closeGuess[i] = false;
        }
    }

    /**
     * Handles updating the model state when a guess is made
     *
     * @param input guess made
     */
    public void makeGuess(String input) {
        this.input = input;
        this.validateGuess(input);
        this.updateView();
        turnsRemaining--;
        checkForGameEnd();
        resetAccountedFor();
    }
}
