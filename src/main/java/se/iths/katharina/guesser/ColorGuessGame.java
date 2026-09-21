package se.iths.katharina.guesser;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ColorGuessGame implements GuessGame {
    private String color;
    private boolean finished;

    int index;
    List<String> colors = new LinkedList<>();


    public ColorGuessGame() {
        Random rand = new Random();
        colors.add("blå");
        colors.add("svart");
        colors.add("gul");
        colors.add("vit");
        colors.add("rosa");
        colors.add("grön");
        colors.add("lila");
        colors.add("grå");
        colors.add("röd");
        colors.add("turkos");
        colors.add("orange");
        colors.add("beige");
        colors.add("brun");

        index = rand.nextInt(colors.size());
        this.color = colors.get(index);
        finished = false;
    }

    @Override
    public String showRules() {
        return """
                Gissa färgen!
                Det här gissningsspelet går ut på att gissa rätt färg.
                Färgerna är på svenska och stavningen är viktig.
                I dagsläget finns det enbart vanliga färger, inga färgnyanser.
                """;
    }

    @Override
    public String makeGuess(String guess) {
        if (!guess.equalsIgnoreCase(this.color)) {
            finished = false;
            return "Nej, fel färg. Gissa igen.";
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
