package classes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GeneriereStartzustand {
	//Attribute
	Spielplan spielplan = new Spielplan();
	Spielstein spielstein;
	
	
	public void starteSpiel() throws FileNotFoundException, IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Spielstand.spielfeld"));
		output.writeObject(spielplan);
		output.flush();
		output.close();
	}
	
	public void fuelleSpielplan() {
		for(int i = 0; i < 7; i++) {
			spielplan.spielfeld.add(new Spielstein(Farbe.Rot, i, 0));
		}
		
		for(int i = 0; i < 7; i++) {
			spielplan.spielfeld.add(new Spielstein(Farbe.Blau, i, 6));
		} 
		
		System.out.println(spielplan);
	}
}
