package classes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Latrunculi {
	
	//Methoden
	public static void main(String [] args) {
		//Attribute
		String dateipfad = "output/latruncul.spielfeld";
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
		try {
			spielfeld = io.readSpielfeld(dateipfad);
		} catch (IOException | ClassNotFoundException e) {
			spielfeld = new Spielfeld();
		}
		
		//Konsolenausgabe
		//Einlesen der Koordinaten für Zug
		System.out.println("Willkomen bei Latrunculi!");
		spielfeld.grafischeDarstellung();
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
		
		//Zug machen
		spielfeld.steinZiehen(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu);
		System.out.println("\nZug beendet.");
		
		try {
			io.writeSpielfeld(spielfeld, dateipfad);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
