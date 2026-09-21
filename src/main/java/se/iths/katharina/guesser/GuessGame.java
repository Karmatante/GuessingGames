package se.iths.katharina.guesser;

public interface GuessGame {

    String showRules();

    String makeGuess(String guess);

    boolean finished();

}
