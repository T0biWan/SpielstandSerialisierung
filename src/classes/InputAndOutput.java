package classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InputAndOutput {
	//Methoden
	public void writeSpielfeld(Spielfeld spielfeld, String dateipfad) throws FileNotFoundException, IOException {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(dateipfad));
			output.writeObject(spielfeld);
			output.close();
	}

	public Spielfeld readSpielfeld(String dateiname) throws FileNotFoundException, IOException, ClassNotFoundException {
		Spielfeld spielfeld;
		ObjectInputStream input;
			input = new ObjectInputStream(new FileInputStream(dateiname));
			spielfeld = (Spielfeld) input.readObject();
			input.close();
			return spielfeld;
	}

}
