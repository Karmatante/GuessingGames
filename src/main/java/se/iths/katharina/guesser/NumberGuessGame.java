package se.iths.katharina.guesser;

import java.util.Random;

public class NumberGuessGame implements GuessGame {
    private int secret;
    private boolean finished;

    public NumberGuessGame() {
        Random rand = new Random();
        secret = rand.nextInt(100) + 1;
        finished = false;
    }

    @Override
    public String showRules() {
        return """
                Gissa talet!
                Det här gissningsspelet går ut på att gissa rätt tal.
                Talet är slumpat och ligger inom ett bestämt intervall.
                Efter varje gissning får du veta om ditt tal är för högt eller för lågt.
                """;
    }


    public String makeGuess(String guess) {
        int tal = Integer.parseInt((guess));
        if (tal > secret) {
            return "Too big";
        } else if (tal < secret) {
            return "Too small";
        } else {
            finished = true;
            return "Right!";
        }
    }

    public boolean finished() {
        return finished;
    }
}


// Skapa slumptal
// Random rand = new Random();
// int slumpTal = rand.nextInt(100);
// ger tal 0-99

// Extrauppgift, lägg till en gissningsräknare
// och kanske en maxgräns för antal gissningar

// lägg till en loop i main så man kan spela fler ggr