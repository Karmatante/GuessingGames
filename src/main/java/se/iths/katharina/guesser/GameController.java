package se.iths.katharina.Guesser;

public class GameController {

    private GuessGame g;

    public GameController(GuessGame game) {
        g = game;
    }

    public static void run() {
        GuessGame g;
        int choice;
        boolean running = true;

        ConsoleHelper.greeting();

        String name = IO.readln("Vad heter du?");

        ConsoleHelper.printMenu();
        do {
            ConsoleHelper.greeting();

            choice = ConsoleHelper.readInt("Vad vill du spela, " + name + "?");


            if (choice < 1 || choice > 4) {
                ConsoleHelper.printMessage("FEL!", "Välj ett alternativ mellan 1 och 4.");
                continue;
            } else {
                switch (choice) {
                    case 1:
                        g = new NumberGuessGame();
                        ConsoleHelper.printMessage(
                                "******* Välkommen till Gissa talet *******", g,
                                "Är du redo, " + name + "?");
                        break;

                    case 2:
                        g = new AnimalGuessGame();
                        ConsoleHelper.printMessage(
                                "******* Välkommen till Gissa djuret *******", g,
                                "Är du redo, " + name + "?");
                        break;

                    case 3:
                        g = new ColorGuessGame();
                        ConsoleHelper.printMessage(
                                "******* Välkommen till Gissa färgen *******", g,
                                "Är du redo, " + name + "?");
                        break;
                    case 4:
                        return;

                    default:
                        continue;
                }
                do {
                    String guess = IO.readln("Gissa: ");
                    String answer = g.makeGuess(guess);
                    IO.println(answer);

                } while (!g.finished());
            }
        } while (running);
    }

}
