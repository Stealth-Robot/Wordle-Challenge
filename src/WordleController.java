import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleController implements ActionListener {
    WordleModel model;
    WordleGUI view;
    int size;

    public WordleController(WordleModel model, WordleGUI view, int size) {
        this.model = model;
        this.view = view;
        this.size = size;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String userName = view.getInput();
        String inputText = userName.replaceAll("\\s+",""); //remove whitespace from input

        //dont take input if less than <size> letters
        if(inputText.length() < size) {
            return;
        }

        String formattedInput = inputText.substring(0, size).toUpperCase();
        System.out.println("guess is: " + formattedInput);  // Output user input
        model.makeGuess(formattedInput);
    }
}
