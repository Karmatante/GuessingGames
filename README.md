# Guesser

**Guesser** är ett konsolbaserat gissningsspel skrivet i Java.

Projektet innehåller flera olika gissningsspel som använder samma `GuessGame`-interface. Syftet med projektet är framför allt att träna på objektorienterad programmering och att förstå hur ett interface kan användas för att låta flera olika klasser följa samma gemensamma struktur.

---

## Funktioner

När programmet startar får spelaren ange sitt namn.

Därefter visas en meny där spelaren kan välja mellan flera olika gissningsspel:

1. **Gissa talet**
2. **Gissa djuret**
3. **Gissa färgen**
4. **Avsluta**

När ett spel har avslutats kan spelaren återvända till huvudmenyn och välja ett nytt spel.

---

# GuessGame-interface

Alla gissningsspel implementerar samma interface:

```java
public interface GuessGame {

    String showRules();

    String makeGuess(String guess);

    boolean finished();
}
```

`GuessGame` fungerar som ett gemensamt kontrakt för alla spel.

Det betyder att varje klass som implementerar `GuessGame` måste kunna:

- beskriva sina regler
- ta emot en gissning
- tala om när spelet är färdigt

---

## showRules()

```java
String showRules();
```

`showRules()` returnerar en `String` som beskriver spelets mål och regler.

Varje spel har sin egen implementation eftersom reglerna skiljer sig mellan spelen.

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

`makeGuess()` tar emot spelarens gissning som en `String`.

Varje spel bestämmer själv hur gissningen ska kontrolleras.

Metoden returnerar sedan ett meddelande som kan visas för spelaren.

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

`finished()` berättar om den aktuella spelomgången är färdig.

Så länge metoden returnerar:

```java
false
```

fortsätter spelet.

När spelaren har gissat rätt ändras spelets `finished`-värde till:

```java
true
```

och spelomgången kan avslutas.

---

# Varför används ett interface?

En central del av projektet är att flera olika spel kan behandlas som samma typ.

Programmet kan deklarera:

```java
GuessGame g;
```

och därefter låta `g` innehålla olika konkreta spel.

Till exempel:

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

Alla tre klasserna implementerar `GuessGame`.

Det innebär att programmet kan använda samma kod oavsett vilket spel användaren väljer:

```java
String answer = g.makeGuess(guess);
```

och:

```java
g.finished();
```

Programmet behöver alltså inte ha en helt separat spel-loop för varje spel.

Detta är ett exempel på **polymorfism**.

---

# Projektstruktur

Projektet består av flera klasser med olika ansvarsområden.

```text
Guesser
│
├── Main
├── GameController
├── GuessGame
├── NumberGuessGame
├── AnimalGuessGame
├── ColorGuessGame
└── ConsoleHelper
```

---

# Main

`Main` är programmets startpunkt.

Dess huvudsakliga uppgift är att starta programmet och låta `GameController` hantera själva spelflödet.

På så sätt hålls `Main` liten och enkel.

---

# GameController

`GameController` ansvarar för programmets huvudsakliga flöde.

Här hanteras bland annat:

- spelarens namn
- huvudmenyn
- spelarens spelval
- skapandet av rätt `GuessGame`
- visning av regler
- själva spelomgången
- återgång till huvudmenyn

Det övergripande flödet kan beskrivas så här:

```text
Starta programmet
        ↓
Fråga efter spelarens namn
        ↓
Visa huvudmenyn
        ↓
Läs spelarens val
        ↓
Skapa rätt GuessGame
        ↓
Visa spelets regler
        ↓
Låt spelaren gissa
        ↓
Är spelet färdigt?
     ↙       ↘
   Nej        Ja
    ↓          ↓
Gissa igen   Tillbaka
             till menyn
```

`GameController` använder en variabel av typen:

```java
GuessGame g;
```

Den variabeln kan sedan få olika spel beroende på användarens val.

Exempel:

```java
case 1:
    g = new NumberGuessGame();
    break;

case 2:
    g = new AnimalGuessGame();
    break;

case 3:
    g = new ColorGuessGame();
    break;
```

---

# NumberGuessGame

`NumberGuessGame` ansvarar för spelet **Gissa talet** och implementerar `GuessGame`.

När ett nytt `NumberGuessGame` skapas genereras ett slumpmässigt heltal mellan **1 och 100**.

```java
Random rand = new Random();
secret = rand.nextInt(100) + 1;
```

Det hemliga talet lagras i:

```java
private int secret;
```

Klassen har även:

```java
private boolean finished;
```

som håller reda på om spelaren har gissat rätt.

---

## Hur en gissning kontrolleras

Spelarens gissning kommer in till:

```java
makeGuess(String guess)
```

Eftersom gissningen kommer in som en `String` omvandlas den till ett heltal:

```java
int tal = Integer.parseInt(guess);
```

Därefter jämförs spelarens tal med det hemliga talet.

Om talet är för stort får spelaren ett meddelande om att gissningen är för hög.

Om talet är för litet får spelaren ett meddelande om att gissningen är för låg.

När spelaren gissar rätt sätts:

```java
finished = true;
```

Då kan spel-loopen avslutas.

---

## Regler för Gissa talet

`NumberGuessGame` implementerar också:

```java
showRules()
```

Metoden beskriver spelets mål och berättar att spelaren får veta om en gissning är för hög eller för låg.

---

# AnimalGuessGame

`AnimalGuessGame` ansvarar för spelet **Gissa djuret** och implementerar `GuessGame`.

Klassen innehåller en lista med olika djur:

```java
List<String> animals = new LinkedList<>();
```

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

När ett nytt spel skapas väljs ett slumpmässigt index:

```java
index = rand.nextInt(animals.size());
```

Därefter hämtas djuret som finns på den platsen:

```java
this.animal = animals.get(index);
```

Det slumpmässigt valda djuret sparas alltså i fielden:

```java
private String animal;
```

---

## Hur en gissning kontrolleras

Spelarens gissning jämförs med det hemliga djuret med:

```java
guess.equalsIgnoreCase(this.animal)
```

Det betyder att stora och små bokstäver inte spelar någon roll.

Till exempel behandlas:

```text
elefant
Elefant
ELEFANT
```

som samma svar.

Om spelaren gissar fel fortsätter spelet.

Om spelaren gissar rätt sätts:

```java
finished = true;
```

---

# ColorGuessGame

`ColorGuessGame` ansvarar för spelet **Gissa färgen** och implementerar `GuessGame`.

Klassen fungerar på liknande sätt som `AnimalGuessGame`, men använder färger i stället för djur.

Färgerna lagras i en lista:

```java
List<String> colors = new LinkedList<>();
```

Exempel på färger i spelet är:

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

När ett nytt spel skapas slumpas ett index fram:

```java
index = rand.nextInt(colors.size());
```

Därefter hämtas färgen på den platsen:

```java
this.color = colors.get(index);
```

---

## Hur en gissning kontrolleras

Spelarens gissning jämförs med den hemliga färgen.

Även här används:

```java
equalsIgnoreCase()
```

så att stora och små bokstäver inte påverkar resultatet.

När spelaren gissar rätt sätts:

```java
finished = true;
```

och spelomgången avslutas.

---

# ConsoleHelper

`ConsoleHelper` innehåller hjälpmetoder för programmets konsolgränssnitt.

Klassen används bland annat för att:

- visa välkomstmeddelandet
- visa huvudmenyn
- läsa heltal från användaren
- hantera felaktig inmatning
- skriva ut vanliga meddelanden
- skriva ut regler för det valda spelet

Exempel på huvudmenyn:

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

## printMessage()

Projektet använder `printMessage()` för att samla utskrifter på ett ställe.

En version kan användas för vanliga meddelanden, exempelvis fel:

```text
FEL!
Du måste skriva ett heltal.
```

En annan version kan ta emot ett `GuessGame` och använda:

```java
g.showRules();
```

för att visa reglerna för just det spel som användaren har valt.

Detta är ett exempel på **method overloading**, där flera metoder kan ha samma namn men olika parameters.

---

# Input validation

När användaren ska välja ett alternativ i menyn används en metod som läser en `String` och försöker omvandla den till ett heltal.

```java
Integer.parseInt(input);
```

Om användaren exempelvis skriver:

```text
hej
```

går texten inte att omvandla till ett heltal.

Då uppstår:

```java
NumberFormatException
```

Programmet fångar felet med `try/catch` och kan visa:

```text
FEL!
Du måste skriva ett heltal.
```

Programmet kan därefter fortsätta i stället för att krascha.

---

# Loopar

Projektet använder två olika typer av upprepning i `GameController`.

---

## Menyloop

Den yttre loopen håller programmet igång.

Den gör att spelaren kan:

```text
välja spel
↓
spela klart
↓
gå tillbaka till menyn
↓
välja ett nytt spel
```

När spelaren väljer alternativ **4. Avsluta** avslutas programmet.

---

## Spelloop

Inuti menyloopen finns en separat loop för själva spelomgången.

Principen är:

```java
do {
    String guess = IO.readln("Gissa: ");
    String answer = g.makeGuess(guess);
    IO.println(answer);

} while (!g.finished());
```

Så länge:

```java
g.finished()
```

returnerar `false` får spelaren fortsätta gissa.

När rätt svar har angetts returnerar `finished()` `true`, och loopen avslutas.

---

# Hur interface-kopplingen fungerar

Projektets viktigaste koppling kan beskrivas så här:

```text
                    GuessGame
                        │
        ┌───────────────┼───────────────┐
        │               │               │
        ▼               ▼               ▼
NumberGuessGame   AnimalGuessGame   ColorGuessGame
```

Alla tre spelklasserna:

```java
implements GuessGame
```

Därför vet programmet att alla tre har:

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

`GameController` behöver därför inte veta detaljerna om hur varje spel fungerar.

Den behöver bara arbeta mot `GuessGame`.

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

När spelet är färdigt visas huvudmenyn igen.

---

# Java-koncept som används

Projektet tränar flera Java-koncept.

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
- `@Override`
- polymorphism
- `List`
- `LinkedList`
- `Random`
- index
- `switch`
- `if/else`
- `do-while`
- `continue`
- `return`
- `try/catch`
- `NumberFormatException`
- `String`
- `equalsIgnoreCase()`
- method overloading
- input validation

---

# Möjlig vidareutveckling

Eftersom spelen använder `GuessGame` går projektet att bygga ut med fler gissningsspel.

Exempel:

```text
CountryGuessGame
CapitalGuessGame
FruitGuessGame
WordGuessGame
ProgrammingTermGuessGame
```

Ett nytt spel behöver implementera:

```java
GuessGame
```

och därför skapa egna versioner av:

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
- maximalt antal försök
- poängsystem
- ledtrådar
- svårighetsgrader
- statistik
- fler färger
- fler djur
- flera rundor av samma spel

---

# Syfte

Projektet är skapat som en övning i Java och objektorienterad programmering.

Det huvudsakliga målet är att förstå hur flera olika klasser kan implementera samma interface och därefter behandlas som samma typ.

Den centrala idén är:

```java
GuessGame g;
```

Variabeln `g` behöver inte veta exakt vilken konkret klass den innehåller.

Så länge objektet implementerar `GuessGame` kan resten av programmet använda samma metoder.

Det gör programmet lättare att bygga ut och visar hur interfaces kan minska beroendet mellan olika delar av ett program.
