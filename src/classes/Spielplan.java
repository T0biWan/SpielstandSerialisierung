package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Spielplan implements Serializable{
	//Attribute
	List<Spielstein> spielfeld = new ArrayList();
	
	public String toString() {
		String string = "**************************************************\n";
		for(Spielstein index : spielfeld) {
			string = string + index.toString()+"\n**************************************************\n";
		}
		
		return string;
	}
}
