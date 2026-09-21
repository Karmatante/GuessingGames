# Guesser

**Guesser** är ett konsolbaserat gissningsspel skrivet i Java. Projektet innehåller flera olika gissningsspel som använder samma `GuessGame`-interface.

Syftet med projektet är framför allt att träna på objektorienterad programmering i Java och att förstå hur ett **interface kan användas för att låta olika klasser följa samma struktur**.

## Spel

Användaren kan välja mellan tre olika spel:

- **Gissa talet** – ett slumpmässigt tal väljs och spelaren får veta om gissningen är för hög eller för låg.
- **Gissa djuret** – ett djur slumpas fram från en lista och spelaren försöker gissa vilket djur det är.
- **Gissa färgen** – en färg slumpas fram från en lista och spelaren försöker gissa rätt färg.

Efter att ett spel är avslutat kan användaren återvända till spelmenyn och välja ett nytt spel.

## GuessGame-interface

Alla gissningsspel implementerar samma interface:

```java
public interface GuessGame {

    String showRules();

    String makeGuess(String guess);

    boolean finished();
}
