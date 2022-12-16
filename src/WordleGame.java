public class WordleGame {

    public WordleGame() {
        int size = 5;

        WordleGUI view = new WordleGUI(size, true);
        WordleModel model = new WordleModel(size, view);
    }

    public static void main(String[] args) {
        new WordleGame();
    }
}
