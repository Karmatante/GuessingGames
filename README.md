# Guesser

**Guesser** är ett konsolbaserat gissningsspel skrivet i Java.

Projektet innehåller flera olika gissningsspel som använder samma `GuessGame`-interface. Syftet med projektet är framför allt att träna på objektorienterad programmering och att förstå hur ett interface kan användas för att låta olika klasser följa samma gemensamma struktur.

## Funktioner

När programmet startar får spelaren ange sitt namn och därefter välja vilket gissningsspel som ska spelas.

Programmet innehåller tre olika spel:

1. **Gissa talet**
2. **Gissa djuret**
3. **Gissa färgen**
4. **Avsluta**

När ett spel har avslutats kan spelaren återvända till menyn och välja ett nytt spel.

---

## Spelen

### Gissa talet

`NumberGuessGame` skapar ett slumpmässigt tal mellan 1 och 100.

Spelaren skriver in en gissning och får sedan veta om talet är:

- för högt
- för lågt
- rätt

Spelet fortsätter tills spelaren har gissat rätt tal.

---

### Gissa djuret

`AnimalGuessGame` innehåller en lista med olika djur på svenska.

När ett nytt spel skapas väljs ett slumpmässigt djur från listan.

Exempel på djur är:

- Tiger
- Lejon
- Elefant
- Giraff
- Zebra
- Apa
- Panda
- Varg
- Räv
- Björn
- Kanin
- Häst
- Delfin
- Pingvin
- Uggla

Ett slumpmässigt index skapas:

```java
index = rand.nextInt(animals.size());
```

Därefter hämtas djuret på den platsen:

```java
this.animal = animals.get(index);
```

Spelarens gissning jämförs med det hemliga djuret med hjälp av:

```java
equalsIgnoreCase()
```

Det innebär att stora och små bokstäver inte spelar någon roll.

Till exempel behandlas:

```text
elefant
Elefant
ELEFANT
```

som samma svar.

---

### Gissa färgen

`ColorGuessGame` fungerar på liknande sätt som `AnimalGuessGame`.

Spelet innehåller en lista med svenska färger och väljer slumpmässigt en av dem.

Exempel på färger är:

- blå
- svart
- gul
- vit
- rosa
- grön
- lila
- grå
- röd
- turkos
- orange
- beige
- brun

Spelaren fortsätter att gissa tills rätt färg har angetts.

---

# GuessGame-interface

Alla spel implementerar samma interface:

```java
public interface GuessGame {

    String showRules();

    String makeGuess(String guess);

    boolean finished();
}
```

`GuessGame` fungerar som ett gemensamt kontrakt för alla gissningsspel.

Alla klasser som implementerar `GuessGame` måste därför innehålla dessa tre metoder.

---

## showRules()

```java
String showRules();
```

Metoden returnerar en text som beskriver spelets mål och regler.

Varje spel kan ha sin egen implementation.

Exempel:

```java
@Override
public String showRules() {
    return """
            Gissa djuret!
            Det här gissningsspelet går ut på att gissa rätt djur.
            Djuren är på svenska och stavningen är viktig.
            Det kan vara både vanliga och exotiska djur.
            """;
}
```

---

## makeGuess()

```java
String makeGuess(String guess);
```

Metoden tar emot spelarens gissning som en `String`.

Varje spel bestämmer själv hur gissningen ska kontrolleras.

Metoden returnerar sedan ett meddelande till spelaren.

Exempel:

```text
Nej, fel djur. Gissa igen.
```

eller:

```text
Ja, du gissade rätt!
```

---

## finished()

```java
boolean finished();
```

Metoden berättar om den aktuella spelomgången är färdig.

Så länge metoden returnerar:

```java
false
```

fortsätter spelet.

När spelaren har gissat rätt ändras värdet till:

```java
true
```

och spelomgången avslutas.

---

# Varför används ett interface?

En viktig del av projektet är att programmet inte behöver ha en separat spel-loop för varje spel.

I stället kan programmet använda en gemensam variabel:

```java
GuessGame g;
```

Den variabeln kan innehålla olika typer av spel:

```java
g = new NumberGuessGame();
```

eller:

```java
g = new AnimalGuessGame();
```

eller:

```java
g = new ColorGuessGame();
```

Alla dessa klasser implementerar `GuessGame`.

Det gör att resten av programmet kan använda samma kod:

```java
String answer = g.makeGuess(guess);
```

och:

```java
g.finished();
```

utan att behöva veta exakt vilket spel som körs.

Detta är ett exempel på **polymorfism**.

---

# Projektstruktur

Projektet består av flera klasser med olika ansvarsområden.

```text
Guesser
│
├── Main
│
├── GameController
│
├── GuessGame
│
├── NumberGuessGame
│
├── AnimalGuessGame
│
├── ColorGuessGame
│
└── ConsoleHelper
```

---

## Main

`Main` är programmets startpunkt.

Dess uppgift är att starta programmet och låta `GameController` hantera själva spelflödet.

---

## GameController

`GameController` ansvarar för programmets huvudsakliga flöde.

Den hanterar bland annat:

- spelarens namn
- spelmenyn
- användarens spelval
- skapandet av rätt `GuessGame`
- spelomgången
- återgång till huvudmenyn

Det huvudsakliga flödet kan beskrivas så här:

```text
Starta programmet
        ↓
Fråga efter spelarens namn
        ↓
Visa spelmenyn
        ↓
Läs användarens val
        ↓
Skapa rätt GuessGame
        ↓
Visa spelets regler
        ↓
Låt användaren gissa
        ↓
Är spelet färdigt?
     ↙       ↘
   Nej        Ja
    ↓          ↓
Gissa igen   Tillbaka
             till menyn
```

---

## ConsoleHelper

`ConsoleHelper` innehåller hjälpmetoder för programmets konsolgränssnitt.

Klassen används bland annat för att:

- visa huvudmenyn
- visa välkomstmeddelanden
- läsa heltal från användaren
- hantera felaktig inmatning
- skriva ut meddelanden
- skriva ut regler för det valda spelet

Exempel på meny:

```text
*******************************
         Välkommen
*******************************

1. Gissa talet
2. Gissa djuret
3. Gissa färgen
4. Avsluta
```

---

# Input validation

När användaren ska välja ett alternativ från menyn används en metod som försöker omvandla texten till ett heltal.

```java
Integer.parseInt(input);
```

Om användaren till exempel skriver:

```text
hej
```

i stället för ett heltal uppstår en `NumberFormatException`.

Programmet fångar detta med `try/catch` och visar ett felmeddelande:

```text
FEL!
Du måste skriva ett heltal.
```

Programmet fortsätter därefter i stället för att krascha.

---

# Loopar

Projektet använder flera loopar för olika syften.

## Menyloop

En yttre `do-while`-loop används för att hålla programmet igång och låta spelaren välja ett nytt spel efter en avslutad spelomgång.

```text
Visa meny
↓
Spela
↓
Tillbaka till meny
↓
Spela igen
```

---

## Spelloop

Varje spel körs i en egen `do-while`-loop.

Principen är:

```java
do {
    String guess = IO.readln("Gissa: ");
    String answer = g.makeGuess(guess);
    IO.println(answer);

} while (!g.finished());
```

Loopen fortsätter alltså så länge:

```java
g.finished()
```

är `false`.

---

# Java-koncept som används

Projektet tränar flera grundläggande och objektorienterade Java-koncept.

Bland annat:

- classes
- objects
- fields
- constructors
- methods
- parameters
- arguments
- return values
- interfaces
- `implements`
- method overriding
- polymorphism
- `List`
- `LinkedList`
- `Random`
- `switch`
- `if/else`
- `do-while`
- `try/catch`
- `NumberFormatException`
- `String`
- `equalsIgnoreCase()`
- method overloading
- input validation

---

# Exempel på körning

```text
*******************************
         Välkommen
*******************************

Vad heter du? Katharina

1. Gissa talet
2. Gissa djuret
3. Gissa färgen
4. Avsluta

Vad vill du spela, Katharina? 2

******* Välkommen till Gissa djuret *******

Gissa djuret!

Det här gissningsspelet går ut på att gissa rätt djur.
Djuren är på svenska och stavningen är viktig.
Det kan vara både vanliga och exotiska djur.

Är du redo, Katharina?

Gissa: Tiger

Nej, fel djur. Gissa igen.

Gissa: Häst

Nej, fel djur. Gissa igen.

Gissa: Elefant

Ja, du gissade rätt!
```

Efter avslutat spel visas huvudmenyn igen.

---

# Möjlig vidareutveckling

Projektet kan byggas ut med fler spel som implementerar `GuessGame`.

Exempel:

- `CountryGuessGame`
- `CapitalGuessGame`
- `FruitGuessGame`
- `WordGuessGame`
- `ProgrammingTermGuessGame`

Eftersom alla spel använder samma interface behöver ett nytt spel framför allt implementera:

```java
showRules()
```

```java
makeGuess(String guess)
```

och:

```java
finished()
```

Andra möjliga förbättringar är:

- gissningsräknare
- maximalt antal gissningar
- poängsystem
- ledtrådar
- svårighetsgrader
- statistik
- fler kategorier
- möjlighet att spela flera rundor av samma spel

---

# Syfte

Projektet är skapat som en övning i Java och objektorienterad programmering.

Det huvudsakliga målet är att förstå hur olika klasser kan implementera samma interface och därefter behandlas som samma typ.

Den centrala idén i projektet är:

```java
GuessGame g;
```

Programmet behöver inte veta exakt vilken konkret spelklass som används.

Så länge klassen implementerar `GuessGame` kan samma spelkod användas.

Det gör programmet enklare att bygga ut och visar hur interfaces kan användas för att minska kopplingen mellan olika delar av ett program.
