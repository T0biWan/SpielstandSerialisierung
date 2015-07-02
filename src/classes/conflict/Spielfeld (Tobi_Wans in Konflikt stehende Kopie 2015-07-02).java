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

	
	public Spielstein searchStein(int x, int y) {
		for (Spielstein stein : spielfeld) {
			if (stein.getXPosition() == x && stein.getYPosition() == y) {
				return stein;
			}
		}
		return null;
	}
	
	// Methoden
	public void steinZiehen(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yHöheNeu) {	
		Spielstein stein = searchStein(xBreiteAlt, yHöheAlt);
		stein.setXPosition(xBreiteNeu);
		stein.setYPosition(yHöheNeu);
	}
	

	public void grafischeDarstellung() {
		System.out.println("***************\n***************");
		System.out.println("#|0|1|2|3|4|5|6");
		for (int j = 0; j < 7; j++) {
			System.out.print(j);
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

	
	public void schlagen(int xBreiteNeu, int yHöheNeu) {
		
		
		
		
		
	}
	
	//Hier muss noch die Exception gefangen werden, falls kein Stein auf fraglichem Feld liegt.
	public boolean steinOberhalbSchlagbar(int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		Farbe steinfarbe = stein.getFarbe();
		Spielstein steinOberhalb = searchStein(xBreiteNeu, yHöheNeu - 2);
		if(steinfarbe == steinOberhalb.getFarbe()) {
			Spielstein steinDrüber = searchStein(xBreiteNeu, yHöheNeu - 1);
			if(steinfarbe != steinDrüber.getFarbe()) {
				System.out.println("MATCH!");
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String string = "**************************************************\n";
		for (Spielstein index : spielfeld) {
			string = string + index.toString()
					+ "\n**************************************************\n";
		}

		return string;
	}
}
