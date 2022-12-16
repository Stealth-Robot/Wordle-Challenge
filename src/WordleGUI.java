import java.awt.*;

import javax.swing.*;

public class WordleGUI {

    private int width = 600;

    private JFrame TicTacToeFrame;

    private JFormattedTextField statusMessage;
    private JButton[][] wordleSpace;

    /**
     * Constructs the view and initializes the GUI
     */
    public WordleGUI(int size, boolean visible) {
        this.TicTacToeFrame = new JFrame("Tic Tac Toe - Marcus Kubilius 101114872");

        this.statusMessage = new JFormattedTextField();
        this.wordleSpace = new JButton[size][size];

        initializeGUI(size, visible);
    }

    /**
     * Instantiates and draws the GUI
     */
    public void initializeGUI(int size, boolean isVisible) {

        //add the status message text to top of frame
        JPanel statusMessagePanel = new JPanel(new FlowLayout());
        statusMessagePanel.add(statusMessage);
        statusMessage.setPreferredSize(new Dimension((int)(width*0.7), 50));

        TicTacToeFrame.add(statusMessagePanel, BorderLayout.NORTH);

        //add the TicTacToe board with 3x3 grid to middle of frame
        JPanel TicTacToeBoardPanel = new JPanel(new FlowLayout());
        JPanel TicTacToeBoardGrid = new JPanel(new GridLayout(size,size));
        TicTacToeBoardPanel.add(TicTacToeBoardGrid, BorderLayout.CENTER);
        for(int row = 0; row < size; row++) {
            for(int column = 0; column < size; column++) {
                wordleSpace[row][column] = new JButton();
                wordleSpace[row][column].setPreferredSize(new Dimension(80,80));
                wordleSpace[row][column].setText("");
                wordleSpace[row][column].setEnabled(false);
                TicTacToeBoardGrid.add(wordleSpace[row][column]);

            }
        }
        TicTacToeFrame.add(TicTacToeBoardPanel, BorderLayout.CENTER);

        // make the gui visible as the final step
        TicTacToeFrame.setSize(new Dimension(width, 800));
        TicTacToeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TicTacToeFrame.setVisible(isVisible);
    }

    /**
     * Adds ActionListener to each button
     *
     * @param c controller that listens for actions
     */
    public void addActionListenerToTextField(WordleController c) {
        statusMessage.addActionListener(c);
    }

    /**
     * Updates a grid space
     *
     * @param row of grid to update
     * @param column of grid to update
     * @param displayCharacter to put on grid space
     */
    public void updateSpace(int row, int column, char displayCharacter, Color color) {
        wordleSpace[row][column].setText(Character.toString(displayCharacter));
        wordleSpace[row][column].setBackground(color);
    }

    public String getInput() {
        return this.statusMessage.getText();
    }

    public void clearInput() {
        statusMessage.setText("");
    }
}
