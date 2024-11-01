import java.util.Scanner;

public class Main {
    public static final int SCREEN_WIDTH = 80;
    public static final int SCREEN_HEIGHT = 50;

    public static void main(String[] args) {
        Game game = new Game(100.0, "Cape Coral");

        boolean play = true;
        Scanner inputScanner = new Scanner(System.in);

        while (play) {
            game.DrawMainScreen();

            // get user input with prompt and sanitize it to lowercase
            String input = game.GetInput("Action:  ");
            input = input.toLowerCase();

            switch (input) {
                case "travel":
                    game.ChangeCity();
                    break;
                case "go":
                    game.ChangeLocation();
                    break;
                case "store":
                    game.OpenStore();
                    break;
                case "exit":
                    play = false;
                    break;
            }
        }
    }
}