package classes;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	public void steinZiehen(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yBreiteNeu) {
		
	}
	
	//Methoden
	public String toString() {
		String string = "**************************************************\n";
		for(Spielstein index : spielfeld) {
			string = string + index.toString()+"\n**************************************************\n";
		}
		
		return string;
	}
}
