package classes;

import java.io.Serializable;

//Klasse soll abstract sein: Skript Seite 11
public class Spielstein implements Serializable {
	// Attribute müssen alle serialisierbar sein
	//Primitive Datentypen sind von Hause aus serialisierbar
	private Farbe farbe;
	private int[] position = new int[2];

	//Konstruktoren
	public Spielstein(Farbe farbe, int xBreite, int yHöhe) {
		setFarbe(farbe);
		this.position[0] = xBreite;
		this.position[1] = yHöhe;
	}
	
	public Spielstein(Farbe farbe, int[] position) {
		setFarbe(farbe);
		setPosition(position);
	}
	
	public Spielstein() {
		
	}
	
	//Getter/Setter
	public Farbe getFarbe() {
		return farbe;
	}

	public void setFarbe(Farbe farbe) {
		this.farbe = farbe;
	}

	public int[] getPosition() {
		return position;
	}
	


	public void setPosition(int[] position) {
		this.position = position;
	}
	
	//toString
	public String toString() {
		return "Spielstein:\nFarbe:\t\t"+getFarbe()+"\nPosition:\t("+position[0]+"|"+position[1]+")";
	}
}
