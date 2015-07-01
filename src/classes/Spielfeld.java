package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enums.Farbe;

public class Spielfeld implements Serializable {
	// Attribute
	List<Spielstein> spielfeld = new ArrayList();
	private static final long serialVersionUID = 1L;

	// Konstruktoren
	public Spielfeld() {
		// Generiert Startzustand.
		for (int i = 0; i < 7; i++) {
			spielfeld.add(new Spielstein(Farbe.Rot, i, 0));
		}

		for (int i = 0; i < 7; i++) {
			spielfeld.add(new Spielstein(Farbe.Blau, i, 6));
		}
	}

	public void steinZiehen(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yHöheNeu) {
		
		for (Spielstein stein : spielfeld) {
			if (stein.getXPosition() == xBreiteAlt && stein.getYPosition() == yHöheAlt) {
				stein.setXPosition(xBreiteNeu);
				stein.setYPosition(yHöheNeu);
			}
		}
	}

	public void grafischeDarstellung() {
		System.out.println("***************\n***************");
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 7; i++) {
				Boolean gefunden = false;
				for (Spielstein stein : spielfeld) {
					if (stein.getXPosition() == i && stein.getYPosition() == j) {
						String farbe = (stein.getFarbe() == Farbe.Rot) ? "r" : "b";
						System.out.print("|"+ farbe);
						gefunden = true;
					}
				}
				if (!gefunden) {
					System.out.print("| ");
				}
			}
			System.out.print("|\n");
		}
		System.out.println("***************\n***************");
	}

	// Methoden
	public String toString() {
		String string = "**************************************************\n";
		for (Spielstein index : spielfeld) {
			string = string + index.toString()
					+ "\n**************************************************\n";
		}

		return string;
	}
}
