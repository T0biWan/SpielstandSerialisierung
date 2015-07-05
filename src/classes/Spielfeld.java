package classes;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enums.Farbe;


/*
 * Generiert Startzustand
 * Liste ist Reihe von Steinen mit x und y Werten
 */

public class Spielfeld implements Serializable{
	//Attribute
	List<Spielstein> spielfeld = new ArrayList();
	
	//Konstruktoren
	public Spielfeld (int xBreite) {
		for(int i = 0; i < xBreite; i++) {
			spielfeld.add(new Spielstein(Farbe.Rot, i, 0));
		}
		
		for(int i = 0; i < xBreite; i++) {
			spielfeld.add(new Spielstein(Farbe.Blau, i, 6));
		} 
	}
	
	public Spielfeld() {
		
	}
	
	/*Oberklasse
	 * Koordinaten, alterStand und neuerStand wird mitgegeben
	 * Suche nach Steinen, die diese alten Koordinaten haben, um diesen dann zu verändern
	 * Nur wenn er ihn findet, wird ausgeführt
	 */
	public void steinZiehen(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yHöheNeu) {
		for(Spielstein stein : spielfeld) {
			if(stein.getXPosition() == xBreiteAlt && stein.getYPosition() == yHöheAlt) {
				stein.setXPosition(xBreiteNeu);
				stein.setYPosition(yHöheNeu);
			}
		}
	}
	
	// Methoden, gibt die gesamte Collection aus, also jeden Stein
	// Steine haben auch toString, die Farbe und Position ausgibt
	// im Endeffekt haben wir eine Liste mit jedem Stein und seiner Farbe
	public String toString() {
		String string = "**************************************************\n";
		for(Spielstein index : spielfeld) {
			string = string + index.toString()+"\n**************************************************\n";
		}
		
		return string;
	}
}
