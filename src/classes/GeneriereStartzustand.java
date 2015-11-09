package classes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import enums.Farbe;

public class GeneriereStartzustand {
	//Attribute
	Spielfeld spielfeld = new Spielfeld();
	Spielstein spielstein;
	
	
	public void starteSpiel() throws FileNotFoundException, IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Spielstand.spielfeld"));
		output.writeObject(spielfeld);
		output.flush();
		output.close();
	}
	
	public void fuelleSpielplan() {
		for(int i = 0; i < 7; i++) {
			spielfeld.spielfeld.add(new Spielstein(Farbe.Rot, i, 0));
		}
		
		for(int i = 0; i < 7; i++) {
			spielfeld.spielfeld.add(new Spielstein(Farbe.Blau, i, 6));
		} 
		
	}
	

}