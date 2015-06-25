package classes;

public class Main {

	public static void main(String[] args) {
		//Attribute
		Spielfeld spielfeld1 = new Spielfeld(2);
		Spielfeld spielfeld2 = new Spielfeld();
//		GeneriereStartzustand g		= new GeneriereStartzustand();
		InputAndOutput io			= new InputAndOutput();
		
		//Startzustand
//		g.fuelleSpielplan();
		
		//Schreiben
//		io.writeSpielfeld(spielfeld1);
		
		//Lesen
		spielfeld2 = io.readSpielfeld();
		
//		//Ausgeben
//		System.out.println(spielfeld1);
//		System.out.println("\n\n************************************************************\n************************************************************\n************************************************************\n************************************************************\n************************************************************\n\n");
		System.out.println(spielfeld2);
		
		spielfeld2.steinZiehen(1, 0, 3, 1);
		
		io.writeSpielfeld(spielfeld2);
	}

}
