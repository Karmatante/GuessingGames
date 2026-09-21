package se.iths.katharina.Guesser;

public interface GuessGame {

    String showRules();

    String makeGuess(String guess);

    boolean finished();

}
