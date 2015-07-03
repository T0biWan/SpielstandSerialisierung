package classes;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import exceptions.SteinExistiertNichtException;

public class Latrunculi {

	// Attribute
	private static String dateipfad = "src/output/Latrunculi.spielfeld";
	private static Spielfeld spielfeld;
	private static InputAndOutput io = new InputAndOutput();
	private static Scanner scanner = new Scanner(System.in);
	private static int xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu;

	// Methoden
	public static void main(String[] args) {

		leseSpielfeldEin();

		if (!hatJemandGewonnen()) {
			initRunde();
			speicherSpielstand();
		}
	}

	private static void initRunde() {
		// Konsolenausgabe
		// In den ersten beiden Runden werden die Spieler nach ihrem Namen
		// gefragt und sie bekommen ihre Farbe zugewiesen
		String name = "", farbe;
		String[] farben = {"Blau","Rot"}; 
		int runde = spielfeld.getRunden();
		farbe = farben[(runde % 2)];
		
		System.out.println("Willkommen bei Latrunculi!");
		
		if (runde > 1) {
			if (runde % 2 == 0) {
				name = spielfeld.getNameBlau();
			} else {
				name = spielfeld.getNameRot();
			}
			
			System.out.println("Hallo " + name + ", deine Farbe ist "+ farbe +"\n");
			
		} else {

			
			System.out.print("Spieler " + (runde+1) + ", deine Farbe ist "+ farbe +", wie ist dein Name?\n> ");
			
			if (runde == 0) {
				
				name = scanner.nextLine();
				spielfeld.setNameBlau(name);
				System.out.println();
			}
			if (runde == 1) {
				name = scanner.nextLine();
				spielfeld.setNameRot(name);
				System.out.println();
			}
			
			System.out.println("Willkommen " + name + ", setze deinen ersten Stein.\n");
		}

		// Das Spielfeld wird ausgegeben.
		spielfeld.grafischeDarstellung();
		
		// wir beginnen das spielen
		spiele();

	}

	private static void spiele() {
		// unsere Kontrollvariable die unsere Schleife abbricht
		boolean spiele = false;
		// Einlesen der Koordinaten für Zug
		do {
			// wenn wir wieder von vorne anfangen spielen wieder auf false
			// setzen
			spiele = false;
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

			try {
				// überprüft auch das legale ziehen des Steines
				if (spielfeld.steinZiehen(xBreiteAlt, yHöheAlt, xBreiteNeu,
						yHöheNeu)) {
					// kann geschlagen werden
					spielfeld.steinSchlagen(xBreiteNeu, yHöheNeu);
					// Hat ein Spieler gesiegt?
					spielfeld.partieZuEnde();
					spielfeld.setRunden(spielfeld.getRunden() + 1);
					
				} else {
					// Wurde ein regelwiedriger Zug getätigt, bekommt der Nutzer
					// noch einmal die Spielregeln zum Lesen.
					printRules();
					spiele = true;
				}
			} catch (SteinExistiertNichtException e) {
				System.out.println(e.getMessage());
			}

			// Wurde ein regelwiedriger Zug befohlen, startet die
			// do-while-Schleife erneut.
			// Der Spieler kann einen neuen Zug befehlen.
		} while (spiele);

		spielfeld.grafischeDarstellung();
		hatJemandGewonnen();

		scanner.close();
		System.out.println("\nZug beendet.");

	}

	private static void speicherSpielstand() {
		// Speichern
		try {
			io.writeSpielfeld(spielfeld, dateipfad);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static boolean hatJemandGewonnen() {

		if (spielfeld.getSiegBlau() || spielfeld.getSiegRot()) {

			String name;

			if (spielfeld.getSiegBlau()) {
				name = spielfeld.getNameBlau();
			} else {
				name = spielfeld.getNameRot();
			}
			System.out.println("Nice!");
			System.out.println(name + " du hast gewonnen!\nDas Spiel ging "
					+ spielfeld.getRunden() + ".");
			return true;
		}

		return false;
	}
	
	private static void printRules() {
		System.out
				.println("--------------------------------------------------------------------------------");
		System.out
				.println("# Achtung:\n# Die eingegebenen Werte führen zu einem illegalen Zug!\n#");
		System.out.println("# Da Rules:");
		System.out
				.println("# 1. Die Koordinaten müssen zwischen 0 und 5 liegen.");
		System.out
				.println("# 2. Es kann nur auf ein leeres Feld gezogen werden.");
		System.out.println("# 3. Ein Spielstein kann nur ein Feld weitgehen.");
		System.out
				.println("# 4. Ein Spielstein kann sich nur horizontal oder vertikal bewegen.");
		System.out
				.println("# 5. Es dürfen nur Spielsteine der eigenen Farbe gezogen werden.\n#");
		System.out.println("# Versuch es noch einmal.");
		System.out
				.println("--------------------------------------------------------------------------------");
		System.out.println();
	}

	/*
	 * Spielfeld einlesen Falls es die Datei zum einlesen nicht gibt, wird ein
	 * neues Spiel in Startaufstellung konstruiert. Wir haben ja darüber geredet
	 * es mit einer if-Abfrage zu machen, allerdings verlangen io-Streams immer
	 * Exception Handling...? Das ist richtig aber die exception sollte nur
	 * geschmissen weden, wenn eine Datei da ist aber nicht eingelesen werden
	 * kann.
	 */
	private static void leseSpielfeldEin() {

		File f = new File(dateipfad);

		if (f.exists() && !f.isDirectory()) {
			try {
				spielfeld = io.readSpielfeld(dateipfad);
			} catch (IOException | ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			spielfeld = new Spielfeld();
			spielfeld.setRunden(0);
		}
	}

}
