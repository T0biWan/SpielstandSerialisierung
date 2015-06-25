package classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InputAndOutput {
	//Methoden
	public void writeSpielfeld(Spielfeld spielfeld) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("output/Latrunculi.spielfeld"));
			output.writeObject(spielfeld);
			output.close();

		} catch (IOException e) {
			System.out.println("\"output/Latrunculi.spielplan\" konnte nicht gefunden werden");
		}
	}

	public Spielfeld readSpielfeld() {
		Spielfeld spielfeld;
		ObjectInputStream input;
		try {
			input = new ObjectInputStream(new FileInputStream("output/Latrunculi.spielfeld"));
			spielfeld = (Spielfeld) input.readObject();
			input.close();
			return spielfeld;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("\"output/Latrunculi.spielplan\" konnte nicht gefunden werden");
			return null;
		}
	}

}
