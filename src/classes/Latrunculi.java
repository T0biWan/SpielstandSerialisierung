package classes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Latrunculi {
	
	//Methoden
	public static void main(String [] args) {
		//Attribute
		String dateipfad = "output/latrunculi.spielfeld";
		Spielfeld spielfeld;
		InputAndOutput io = new InputAndOutput();
		Scanner scanner = new Scanner(System.in);
		int xBreiteAlt;
		int yHöheAlt;
		int xBreiteNeu;
		int yHöheNeu;

		
		//Spielfeld einlesen
		//Falls es die Datei zum einlesen nicht gibt,
		//wird ein neues Spiel in Startaufstellung konstruiert.
		//Wir haben ja darüber geredet es mit einer if-Abfrage zu machen, allerdings verlangen io-Streams immer Exception Handling...?
		System.out.println("Willkomen bei Latrunculi!");
		try {
			spielfeld = io.readSpielfeld(dateipfad);
		} catch (IOException | ClassNotFoundException e) {
			spielfeld = new Spielfeld();
			spielfeld.setRunden(0);
		}
		
		//Konsolenausgabe
		if (spielfeld.getRunden() > 1) {
			if(spielfeld.getRunden() % 2 == 0) {
				System.out.println(spielfeld.getNameBlau() + " (Blau)\n");
			} else {
				System.out.println(spielfeld.getNameRot() + " (Rot)\n");
			}
		} else {
			if(spielfeld.getRunden() == 0) {
				System.out.print("Spieler 1, deine Steine sind die blauen, wie ist dein Name?\n> ");
				spielfeld.setNameBlau(scanner.nextLine());
				System.out.println();
			}
			if(spielfeld.getRunden() == 1) {
				System.out.print("Spieler 2, deine Steine sind die roten, wie ist dein Name?\n> ");
				spielfeld.setNameRot(scanner.nextLine());
				System.out.println();
			}
		}
		spielfeld.grafischeDarstellung();
		
		//Einlesen der Koordinaten für Zug
		do {
			System.out.println("Stein auf: [x][y]");
			System.out.print("x: ");
			xBreiteAlt = scanner.nextInt();
			System.out.print("y: ");
			yHöheAlt = scanner.nextInt();
			System.out.println("\nNach: [x][y]");
			System.out.print("x: ");
			xBreiteNeu = scanner.nextInt();
			System.out.print("y: ");
			yHöheNeu = scanner.nextInt();
			System.out.println();
			if(!spielfeld.spielzugLegal(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu)) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("# Achtung:\n# Die eingegebenen Werte führen zu einem illegalen Zug!\n#");
				System.out.println("# Da Rules:");
				System.out.println("# 1. Die Koordinaten müssen zwischen 0 und 5 liegen.");
				System.out.println("# 2. Es kann nur auf ein leeres Feld gezogen werden.");
				System.out.println("# 3. Ein Spielstein kann nur ein Feld weitgehen.");
				System.out.println("# 4. Ein Spielstein kann sich nur horizontal oder vertikal bewegen.");
				System.out.println("# 5. Es dürfen nur Spielsteine der eigenen Farbe gezogen werden.\n#");
				System.out.println("# Versuch es noch einmal.");
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println();
			}
		} while(!spielfeld.spielzugLegal(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu));
		scanner.close();
		
		//Zug machen
		spielfeld.steinZiehen(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu);
		
		//Schlagen
		spielfeld.steinSchlagen(xBreiteNeu, yHöheNeu);
		
		spielfeld.grafischeDarstellung();
		System.out.println("\nZug beendet.");
		spielfeld.setRunden(spielfeld.getRunden()+1);
		
		//Speichern
		try {
			io.writeSpielfeld(spielfeld, dateipfad);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
