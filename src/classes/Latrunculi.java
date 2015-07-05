package classes;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Latrunculi {
	
	/*
	 * Die Klasse Latrunculi fungiert als Main-Klasse. Unsere Attribute sind alle global, damit wir in allen Methoden darauf zugreifen können.
	 * In der Main-Methode wird zuerst die Methode leseSpielfeldEin aufgerufen. Zuerst wird immer versucht, die Datei einzulesen, um mit den Daten arbeiten zu können.
	 * Wenn im ersten Durchlauf noch nichts zum Einlesen da ist, wird ein neues Spielfeld im Startzustand generiert. Danach wird durch das Einlesen der Spielstand in der Datei gespeichert.
	 * Dann wird überprüft, ob jemand das Spiel gewonnen hat, wenn das nicht der Fall ist, werden die Methoden rundeBeginnen und speicherSpielstand aufgerufen. 
	 * Hat ein Spieler jedoch gewonnen, findet die Siegerehrung statt. 
	 * Die Idee ist es, per Ausführung dieser Klasse immer nur jeweils einen Zug zu machen. Dieser wird dann in einer Datei gespeichert und dem anderen Spieler zugängig gemacht.
	 * 
	 */
	
	//Attribute
	private static String dateipfad = "src/output/Latrunculi.spielfeld";
	private static Spielfeld spielfeld;
	private static InputAndOutput io = new InputAndOutput();
	private static Scanner scanner = new Scanner(System.in);
	private static int xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu;
	
	
	//Methoden
	public static void main(String [] args) {
		leseSpielfeldEin();

		// Hat einer der Spieler bereits gewonnen?
		if(spielfeld.hatJemandGewonnen()) {
			spielfeld.siegerehrung();
		}else{
			rundeBeginnen();
			speicherSpielstand();
		}
		
		System.out.println(spielfeld); // gibt eine Liste der aktuellen Steine an
	}

	
	/*
	 * Eine Willkommensnachricht wird zuerst ausgegeben. Wenn die Rundenanzahl kleiner oder gleich 1 ist, wird in der 0. Runde Spieler 1 nach seinem Namen gefragt und in der 1. Runde Spieler 2.
	 * Im Else-Zweig wird die Rundenanzahl ausgegeben, und wer dran ist, und welche Farbe er hat (Gerade Rundenanzahl: Blau), (Ungerade Rundenanzahl: Rot)
	 * Danach wird die Methode rundeAusführen ausgeführt.
	 */
	private static void rundeBeginnen() {
		//Konsolenausgabe
		System.out.println("Willkomen bei Latrunculi!");
		//In den ersten beiden Runden werden die Spieler nach ihrem Namen gefragt und sie bekommen ihre Farbe zugewiesen
		if (spielfeld.getRunden() <= 1) {
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
		} else {
			System.out.println("Runde: #" + spielfeld.getRunden());
			if(spielfeld.getRunden() % 2 == 0) {
				System.out.println(spielfeld.getNameBlau() + " (Blau)\n");
			} else {
				System.out.println(spielfeld.getNameRot() + " (Rot)\n");
			}
		}
		
		rundeAusführen();
	}

	/*
	 * Zuerst wird der aktuelle Spielstand grafisch ausgegeben. Wenn die Züge legal sind, wird die while Schleife durch den Boolean nochmal abgebrochen.
	 * Do-while Schleife bedeutet, Do wird immer beim ersten Mal ausgeführt und danach werden erst die Konditionen überprüft. Die While Schleife fängt bei DO an.
	 * Nur wenn ein illegaler Zug gemacht wurde, wird die Schleife erneut durchlaufen, wenn ein legaler Zug gemacht wurde, wird die Schleife abgebrochen.
	 * Wenn while endlich true ist, also ein legaler Zug gemacht wurde, dann werden die Spielfeld-Methoden aufgerufen. Dort wird der Stein auf die neuen Koordinaten gesetzt,
	 * es wird überprüft, ob ein Stein geschlagen werden kann, die aktuelle grafische Darstellung mit dem aktuellsten Zug, führt dies zum Ende der Partie? Und die Runden werden gezählt.
	 * Es wird nochmal überprüft, ob jemand gewonnen hat und ob es zu einer Siegerehrung kommt. Danach ist der Zug beendet und der Scanner wird geschlossen. 
	 */
	private static void rundeAusführen() {
		
		//Das Spielfeld wird ausgegeben.
		spielfeld.grafischeDarstellung();
		
		boolean nochmal;
		
		//Einlesen der Koordinaten für Zug
		do {
			// user wird bestimmt was richtiges eingeben
			nochmal = false;
			
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
			
			//Wurde ein regelwiedriger Zug getätigt, bekommt der Nutzer noch einmal die Spielregeln zum Lesen.
			if(!spielfeld.steinZiehen(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu)) {
				getRules();
				nochmal = true;
			}
			
		} while(nochmal);

		//Schlagen
		spielfeld.steinSchlagen(xBreiteNeu, yHöheNeu);
		
		spielfeld.grafischeDarstellung();
		
		spielfeld.partieZuEnde();

		spielfeld.setRunden(spielfeld.getRunden()+1);
		
		//Hat ein Spieler gesiegt?
		if(spielfeld.hatJemandGewonnen()){
			spielfeld.siegerehrung();
		}

		System.out.println("Zug beendet.\n");
		scanner.close();
	}


	/*
	 * Wenn ein illegaler Spielzug gemacht wurde, wird diese Methode aufgerufen, die dem Spieler die Regeln des Spiels anzeigt.
	 */
	private static void getRules() {
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
	

	/*
	 * Spielfeld einlesen Falls es die Datei zum einlesen nicht gibt, wird ein
	 * neues Spiel in Startaufstellung konstruiert. Wir haben ja darüber geredet
	 * es mit einer if-Abfrage zu machen, allerdings verlangen io-Streams immer
	 * Exception Handling...? Das ist richtig aber die exception sollte nur
	 * geschmissen werden, wenn eine Datei da ist aber nicht eingelesen werden
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
	
	/*
	 * Speichert unseren aktuellen Spielstand bzw. schreibt ihn in eine Datei. Falls dies nicht möglich ist (z.B. keine Schreibrechte), wird Exception geworfen.
	 */
	private static void speicherSpielstand() {
		
		try {
			io.writeSpielfeld(spielfeld, dateipfad);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
