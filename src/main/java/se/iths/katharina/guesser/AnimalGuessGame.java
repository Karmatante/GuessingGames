package se.iths.katharina.guesser;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AnimalGuessGame implements GuessGame {
    private String animal;
    private boolean finished;

    @Override
    public String showRules() {
        return """
                Gissa djuret!
                Det här gissningsspelet går ut på att gissa rätt djur. 
                Djuren är på svenska och stavningen är viktig. 
                Det kan vara både vanliga och exotiska djur.""";
    }

    int index;
    List<String> animals = new LinkedList<>();


    public AnimalGuessGame() {
        Random rand = new Random();
        animals.add("Tiger");
        animals.add("Lejon");
        animals.add("Elefant");
        animals.add("Giraff");
        animals.add("Zebra");
        animals.add("Apa");
        animals.add("Panda");
        animals.add("Varg");
        animals.add("Räv");
        animals.add("Björn");
        animals.add("Kanin");
        animals.add("Häst");
        animals.add("Delfin");
        animals.add("Pingvin");
        animals.add("Uggla");
        index = rand.nextInt(animals.size());
        this.animal = animals.get(index);
        finished = false;
    }

    @Override
    public String makeGuess(String guess) {
        if (!guess.equalsIgnoreCase(this.animal)) {
            finished = false;
            return "Nej, fel djur. Gissa igen.";
        } else {
            finished = true;
            return "Ja, du gissade rätt";
        }
    }


    @Override
    public boolean finished() {
        return finished;
    }
}
