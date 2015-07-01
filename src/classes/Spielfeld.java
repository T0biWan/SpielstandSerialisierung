package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enums.Farbe;

public class Spielfeld implements Serializable {
	// Attribute
	List<Spielstein> spielfeld = new ArrayList();
	private static final long serialVersionUID = 1L;

	// Konstruktoren
	public Spielfeld() {
		// Generiert Startzustand.
		for (int i = 0; i < 7; i++) {
			spielfeld.add(new Spielstein(Farbe.Rot, i, 0));
		}

		for (int i = 0; i < 7; i++) {
			spielfeld.add(new Spielstein(Farbe.Blau, i, 6));
		}
	}

	// Methoden	
	//Search geht die Collection durch und sucht nach einem Stein der die gegebenen Koordinaten, d.h. seine position enthält.
	public Spielstein searchStein(int x, int y) {
		for (Spielstein stein : spielfeld) {
			if (stein.getXPosition() == x && stein.getYPosition() == y) {
				return stein;
			}
		}
		return null;
	}
	
	public Boolean spielzugLegal(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yHöheNeu) {
		if(xBreiteAlt > -1 && xBreiteAlt < 8 && yHöheAlt > -1 && yHöheAlt < 8 && xBreiteNeu > -1 && xBreiteNeu < 8 && yHöheNeu > -1 && yHöheNeu < 8) {
			if(feldFrei(xBreiteNeu, yHöheNeu)) {
				if(xBreiteNeu == (xBreiteAlt - 1) || xBreiteNeu == (xBreiteAlt + 1) || yHöheNeu == (yHöheAlt - 1) || yHöheNeu == (yHöheAlt + 1)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Boolean feldFrei (int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		if(stein == null) {
			return true;
		}
		return false;
	}

	public void steinZiehen(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yHöheNeu) {
		if(spielzugLegal(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu)) {
			Spielstein stein = searchStein(xBreiteAlt, yHöheAlt);
			if(stein != null) {
				stein.setXPosition(xBreiteNeu);
				stein.setYPosition(yHöheNeu);
			}
			
		}
	}

	public void grafischeDarstellung() {
		System.out.println("***************\n***************");
		System.out.println("#|0|1|2|3|4|5|6");
		for (int j = 0; j < 7; j++) {
			System.out.print(j);
			for (int i = 0; i < 7; i++) {
				
				Boolean gefunden = false;
				for (Spielstein stein : spielfeld) {
					if (stein.getXPosition() == i && stein.getYPosition() == j) {
						String farbe = (stein.getFarbe() == Farbe.Rot) ? "r" : "b";
						System.out.print("|"+ farbe);
						gefunden = true;
					}
				}
				if (!gefunden) {
					System.out.print("| ");
				}
			}
			System.out.print("|\n");
		}
		System.out.println("***************\n***************");
	}

	//Es gibt sicherlich eine elegantere Lösung, zum Beispiel innerhalb der Search Methode darauf reagieren, das kein Stein gefunden wurde...
	public boolean schlagbarOben(int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		if(stein != null) {
			Farbe steinfarbe = stein.getFarbe();
			Spielstein steinOben = searchStein(xBreiteNeu, yHöheNeu - 1);
			if(steinOben != null) {
				Spielstein steinObenOben = searchStein(xBreiteNeu, yHöheNeu - 2);
				if(steinObenOben != null) {
					if(steinfarbe != steinOben.getFarbe()) {
						if(steinfarbe == steinObenOben.getFarbe()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean schlagbarUnten(int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		if(stein != null) {
			Farbe steinfarbe = stein.getFarbe();
			Spielstein steinUnten = searchStein(xBreiteNeu, yHöheNeu + 1);
			if(steinUnten != null) {
				Spielstein steinUntenUnten = searchStein(xBreiteNeu, yHöheNeu + 2);
				if(steinUntenUnten != null) {
					if(steinfarbe != steinUnten.getFarbe()) {
						if(steinfarbe == steinUntenUnten.getFarbe()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean schlagbarLinks(int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		if(stein != null) {
			Farbe steinfarbe = stein.getFarbe();
			Spielstein steinLinks = searchStein(xBreiteNeu - 1, yHöheNeu);
			if(steinLinks != null) {
				Spielstein steinLinksLinks = searchStein(xBreiteNeu - 2, yHöheNeu);
				if(steinLinksLinks != null) {
					if(steinfarbe != steinLinks.getFarbe()) {
						if(steinfarbe == steinLinksLinks.getFarbe()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean schlagbarRechts(int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		if(stein != null) {
			Farbe steinfarbe = stein.getFarbe();
			Spielstein steinRechts = searchStein(xBreiteNeu + 1, yHöheNeu);
			if(steinRechts != null) {
				Spielstein steinRechtsRechts = searchStein(xBreiteNeu + 2, yHöheNeu);
				if(steinRechtsRechts != null) {
					if(steinfarbe != steinRechts.getFarbe()) {
						if(steinfarbe == steinRechtsRechts.getFarbe()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void steinSchlagen(int xBreiteNeu, int yHöheNeu) {
		if(schlagbarOben(xBreiteNeu, yHöheNeu)) {
			Spielstein steinOben = searchStein(xBreiteNeu, yHöheNeu - 1);
			spielfeld.remove(steinOben);
		}
		
		if(schlagbarUnten(xBreiteNeu, yHöheNeu)) {
			Spielstein steinUnten = searchStein(xBreiteNeu, yHöheNeu + 1);
			spielfeld.remove(steinUnten);
		}
		
		if(schlagbarLinks(xBreiteNeu, yHöheNeu)) {
			Spielstein steinLinks = searchStein(xBreiteNeu - 1, yHöheNeu);
			spielfeld.remove(steinLinks);
		}
		
		if(schlagbarRechts(xBreiteNeu, yHöheNeu)) {
			Spielstein steinRechts = searchStein(xBreiteNeu + 1, yHöheNeu);
			spielfeld.remove(steinRechts);
		}
	}
	
	public String toString() {
		String string = "**************************************************\n";
		for (Spielstein index : spielfeld) {
			string = string + index.toString()
					+ "\n**************************************************\n";
		}

		return string;
	}
}
