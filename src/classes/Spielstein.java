package classes;

import java.io.Serializable;

import enums.Farbe;

//Klasse soll abstract sein: Skript Seite 11
public class Spielstein implements Serializable {
	// Attribute müssen alle serialisierbar sein
	//Primitive Datentypen sind von Hause aus serialisierbar
	private Farbe farbe;
	private int[] position = new int[2];
	private static final long serialVersionUID = 11L;

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
	
	public int getXPosition() {
		return position[0];
	}
	
	public int getYPosition() {
		return position[1];
	}

	public void setPosition(int[] position) {
		this.position = position;
	}
	
	public void setXPosition(int xPosition) {
		this.position[0] = xPosition;
	}
	
	public void setYPosition(int yPosition) {
		this.position[1] = yPosition;
	}
	
	//toString
	public String toString() {
		return "Spielstein:\nFarbe:\t\t"+getFarbe()+"\nPosition:\t("+position[0]+"|"+position[1]+")";
	}
}
