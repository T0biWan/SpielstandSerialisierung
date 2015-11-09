package classes;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;



public class TestPartieMitRandom {
   // Im folgenden soll eine Partie zwischen zwei Spielern simuliert werden.
   public static void main(String [] args) {
      // Attribute
      String dateipfad = "output/latrunculi.spielfeld";
      Spielfeld spielfeld;
      InputAndOutput io = new InputAndOutput();
      Scanner scanner = new Scanner(System.in);
      int xBreiteAlt;
      int yHoeheAlt;
      int xBreiteNeu;
      int yHoeheNeu;
      Random random = new Random();

      // xBreiteAlt = random.nextInt(7);
      // yHoeheAlt = random.nextInt(7);
      // xBreiteNeu = random.nextInt(7);
      // yHoeheNeu = random.nextInt(7);

      System.out.println("Willkomen bei Latrunculi!");
      // Spielstand laden
      try {
         spielfeld = io.readSpielfeld(dateipfad);
      } catch (IOException | ClassNotFoundException e) {
         spielfeld = new Spielfeld();
         spielfeld.setRunden(0);
      }

      // Dauerschleife um Spiel zu simulieren
      do {

         if (spielfeld.getRunden() > 1) {
            System.out.println("Runde: #" + spielfeld.getRunden());
            if (spielfeld.getRunden() % 2 == 0) {
               System.out.println(spielfeld.getNameBlau() + " (Blau)\n");
            } else {
               System.out.println(spielfeld.getNameRot() + " (Rot)\n");
            }
         } else {
            if (spielfeld.getRunden() == 0) {
               System.out.print("Spieler 1, deine Steine sind die blauen, wie ist dein Name?\n> ");
               spielfeld.setNameBlau("Tobias");
               System.out.println();
            }
            if (spielfeld.getRunden() == 1) {
               System.out.print("Spieler 2, deine Steine sind die roten, wie ist dein Name?\n> ");
               spielfeld.setNameRot("Pamela");
               System.out.println();
            }
         }

         spielfeld.grafischeDarstellung();

         do {
            xBreiteAlt = random.nextInt(7);
            yHoeheAlt = random.nextInt(7);
            xBreiteNeu = random.nextInt(7);
            yHoeheNeu = random.nextInt(7);
         } while (!spielfeld.spielzugLegal(xBreiteAlt, yHoeheAlt, xBreiteNeu, yHoeheNeu));

         // Zug machen
         spielfeld.steinZiehen(xBreiteAlt, yHoeheAlt, xBreiteNeu, yHoeheNeu);

         // Schlagen
         spielfeld.steinSchlagen(xBreiteNeu, yHoeheNeu);

         spielfeld.grafischeDarstellung();
         System.out.println("\nZug beendet.\n\n---------------------------------------------------\n");
         spielfeld.setRunden(spielfeld.getRunden() + 1);

         // Hat ein Spieler gesiegt?
         spielfeld.partieZuEnde();

         if (spielfeld.getSiegBlau() || spielfeld.getSiegRot()) {
            // Siegerehrung;
            System.out.print("Nice, ");
            if (spielfeld.getSiegBlau()) {
               System.out.print(spielfeld.getNameBlau());
            } else if (spielfeld.getSiegRot()) {
               System.out.print(spielfeld.getNameRot());
            }
            System.out.println(" du hast gewonnen!\nDas Spiel ging " + spielfeld.getRunden() + " Runden.");
         }

      } while (!spielfeld.getSiegBlau() || !spielfeld.getSiegRot());

      // Speichern
      try {
         io.writeSpielfeld(spielfeld, dateipfad);
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
   }

}