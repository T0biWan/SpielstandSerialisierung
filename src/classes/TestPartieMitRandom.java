package classes;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TestPartieMitRandom {
	// Im folgenden soll eine Partie zwischen zwei Spielern simuliert werden.
	public static void main(String[] args) {
		// Attribute
		String dateipfad = "output/latrunculi.spielfeld";
		Spielfeld spielfeld;
		InputAndOutput io = new InputAndOutput();
		Scanner scanner = new Scanner(System.in);
		int xBreiteAlt;
		int yHöheAlt;
		int xBreiteNeu;
		int yHöheNeu;
		Random random = new Random();

		// xBreiteAlt = random.nextInt(7);
		// yHöheAlt = random.nextInt(7);
		// xBreiteNeu = random.nextInt(7);
		// yHöheNeu = random.nextInt(7);

		System.out.println("Willkomen bei Latrunculi!");
		// Spielstand laden
		try {
			spielfeld = io.readSpielfeld(dateipfad);
		} catch (IOException | ClassNotFoundException e) {
			spielfeld = new Spielfeld();
			spielfeld.setRunden(0);
		}
		
		//Dauerschleife um Spiel zu simulieren
		do {

			if (spielfeld.getRunden() > 1) {
				System.out.println("Runde: #" + spielfeld.getRunden());
				if(spielfeld.getRunden() % 2 == 0) {
					System.out.println(spielfeld.getNameBlau() + " (Blau)\n");
				} else {
					System.out.println(spielfeld.getNameRot() + " (Rot)\n");
				}
			} else {
				if(spielfeld.getRunden() == 0) {
					System.out.print("Spieler 1, deine Steine sind die blauen, wie ist dein Name?\n> ");
					spielfeld.setNameBlau("Tobias");
					System.out.println();
				}
				if(spielfeld.getRunden() == 1) {
					System.out.print("Spieler 2, deine Steine sind die roten, wie ist dein Name?\n> ");
					spielfeld.setNameRot("Pamela");
					System.out.println();
				}
			}
			
				spielfeld.grafischeDarstellung();
				
				do {
					 xBreiteAlt = random.nextInt(7);
					 yHöheAlt = random.nextInt(7);
					 xBreiteNeu = random.nextInt(7);
					 yHöheNeu = random.nextInt(7);
				} while(!spielfeld.spielzugLegal(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu));
			
				//Zug machen
				spielfeld.steinZiehen(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu);
				
				//Schlagen
				spielfeld.steinSchlagen(xBreiteNeu, yHöheNeu);
				
				spielfeld.grafischeDarstellung();
				System.out.println("\nZug beendet.\n\n---------------------------------------------------\n");
				spielfeld.setRunden(spielfeld.getRunden()+1);
				
				//Hat ein Spieler gesiegt?
				spielfeld.partieZuEnde();
				
				if(spielfeld.getSiegBlau() || spielfeld.getSiegRot()) {
					//Siegerehrung;
					System.out.print("Nice, ");
					if(spielfeld.getSiegBlau()) {
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