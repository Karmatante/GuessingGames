package se.iths.katharina.guesser;

public class ConsoleHelper {
    public static void printMenu() {
        IO.println("*******************************");
        IO.println("         Välkommen");
        IO.println("*******************************");
        IO.println();
        IO.println("1. Gissa talet");
        IO.println("2. Gissa djuret");
        IO.println("3. Gissa färgen");
        IO.println("4. Avsluta");
        IO.println();
    }

    public static void greeting() {
        IO.println("*******************************");
        IO.println("         Välkommen");
        IO.println("*******************************");
        IO.println();
    }

    public static int readInt(String prompt) {
        boolean running = false;
        int choice = 0;
        do {
            String input = IO.readln(prompt);
            running = true;
            try {
                choice = Integer.parseInt(input);
                running = false;
                IO.println();
            } catch (NumberFormatException e) {
                ConsoleHelper.printMessage("FEL!", "Du måste skriva ett heltal.");
            }

        } while (running);
        return choice;
    }


    public static void printMessage(String title, String message) {
        IO.println(title);
        IO.println(message);
    }

    public static void printMessage(String title, GuessGame g, String message) {
        IO.println(title);
        String rules = g.showRules();
        IO.println(rules);
        IO.println(message);
    }

}
