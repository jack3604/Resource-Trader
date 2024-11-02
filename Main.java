////////////////////////////////////////////
//                 TO-DO                  //
//                                        //
//  Done - Add buying and selling         //
//                                        //
//  Done - Add price variation            //
//  ____ - Add discount shop items        //
//  ____ - Add some features for the      //
//         home location                  //
//                                        //
//                                        //
//                                        //
//                                        //
////////////////////////////////////////////

public class Main {
    public static void main(String[] args) {
        Game game = new Game(100.0, "Cape Coral");

        boolean play = true;

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