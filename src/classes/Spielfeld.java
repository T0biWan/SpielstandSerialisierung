package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enums.Farbe;

public class Spielfeld implements Serializable {
	// Attribute
	protected List<Spielstein>	spielfeld = new ArrayList();
	private static final long	serialVersionUID = 1L;
	private String				nameBlau;
	private String				nameRot;
	private int					runden;
	private Boolean				siegBlau = false;
	private Boolean				siegRot = false;
	protected List<Spielstein>	geschlageneSteineBlau = new ArrayList();
	protected List<Spielstein>	geschlageneSteineRot = new ArrayList();

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
	public String getNameBlau() {
		return nameBlau;
	}

	public void setNameBlau(String nameBlau) {
		this.nameBlau = nameBlau;
	}

	public String getNameRot() {
		return nameRot;
	}

	public void setNameRot(String nameRot) {
		this.nameRot = nameRot;
	}

	public int getRunden() {
		return runden;
	}

	public void setRunden(int runden) {
		this.runden = runden;
	}
	
	public Boolean getSiegBlau() {
		return siegBlau;
	}
	
	public void setSiegBlau(Boolean siegreich) {
		this.siegBlau = siegreich;
	}
	
	public Boolean getSiegRot() {
		return siegRot;
	}
	
	public void setSiegRot(Boolean siegreich) {
		this.siegRot = siegreich;
	}
	
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
		try {
			if (imWertebereich(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu)) {
				if (spielerFarbeStimmt(xBreiteAlt, yHöheAlt)) {
					if (feldFrei(xBreiteNeu, yHöheNeu)) {
						if (xBreiteNeu == (xBreiteAlt - 1) || xBreiteNeu == (xBreiteAlt + 1) || yHöheNeu == (yHöheAlt - 1) || yHöheNeu == (yHöheAlt + 1)) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public Boolean imWertebereich(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yHöheNeu) {
		if(xBreiteAlt > -1 && xBreiteAlt < 8 && yHöheAlt > -1 && yHöheAlt < 8 && xBreiteNeu > -1 && xBreiteNeu < 8 && yHöheNeu > -1 && yHöheNeu < 8) {
			return true;
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

	public Boolean spielerFarbeStimmt(int xBreiteAlt, int yHöheAlt) {
		Spielstein stein = searchStein(xBreiteAlt, yHöheAlt);
		if(runden % 2 == 0) {
			if(stein.getFarbe() == Farbe.Blau) {
				return true;
			}
		} else {
			if(stein.getFarbe() == Farbe.Rot) {
				return true;
			}
		}
		return false;
	}
	
	/*Oberklasse
	 * Koordinaten, alterStand und neuerStand wird mitgegeben
	 * Suche nach Steinen, die diese alten Koordinaten haben, um diesen dann zu verändern
	 * Nur wenn er ihn findet, wird ausgeführt
	 */
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
		//Erstelle die Tabelle für die geschlagenen Steine
		int zähler = 0;
		System.out.print("R");
		for (Spielstein index : geschlageneSteineBlau) {
			Boolean gefunden = false;
			if (index.getFarbe() == Farbe.Blau) {
				System.out.print("|b");
				gefunden = true;
			}
			if (!gefunden) {
				System.out.print("| ");
			}
			zähler++;
		}
		while (zähler < 7) {
			System.out.print("| ");
			zähler++;
		}
		if (zähler > 6) {
			zähler = 0;
		}
		System.out.println("|");
		
		//Erstelle Spielfeld
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("#|0|1|2|3|4|5|6|");
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
		System.out.println("****************");
		System.out.println("****************");
		
		// Erstelle die Tabelle für die geschlagenen Steine
		System.out.print("B");
		for (Spielstein index : geschlageneSteineRot) {
			Boolean gefunden = false;
			if (index.getFarbe() == Farbe.Rot) {
				System.out.print("|r");
				gefunden = true;
			}
			if (!gefunden) {
				System.out.print("| ");
			}
			zähler++;
		}
		while (zähler < 7) {
			System.out.print("| ");
			zähler++;
		}
		if (zähler > 6) {
			zähler = 0;
		}
		System.out.println("|");
		System.out.println();
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
			if(steinOben.getFarbe() == Farbe.Blau) {
				geschlageneSteineBlau.add(steinOben);
			} else {
				geschlageneSteineRot.add(steinOben);
			}
			spielfeld.remove(steinOben);
		}
		
		if(schlagbarUnten(xBreiteNeu, yHöheNeu)) {
			Spielstein steinUnten = searchStein(xBreiteNeu, yHöheNeu + 1);
			if(steinUnten.getFarbe() == Farbe.Blau) {
				geschlageneSteineBlau.add(steinUnten);
			} else {
				geschlageneSteineRot.add(steinUnten);
			}
			spielfeld.remove(steinUnten);
		}
		
		if(schlagbarLinks(xBreiteNeu, yHöheNeu)) {
			Spielstein steinLinks = searchStein(xBreiteNeu - 1, yHöheNeu);
			if(steinLinks.getFarbe() == Farbe.Blau) {
				geschlageneSteineBlau.add(steinLinks);
			} else {
				geschlageneSteineRot.add(steinLinks);
			}
			spielfeld.remove(steinLinks);
		}
		
		if(schlagbarRechts(xBreiteNeu, yHöheNeu)) {
			Spielstein steinRechts = searchStein(xBreiteNeu + 1, yHöheNeu);
			if(steinRechts.getFarbe() == Farbe.Blau) {
				geschlageneSteineBlau.add(steinRechts);
			} else {
				geschlageneSteineRot.add(steinRechts);
			}
			spielfeld.remove(steinRechts);
		}
	}
	
	public void partieZuEnde() {
		if(geschlageneSteineBlau.size() > 5) {
			setSiegBlau(true);
		}
		
		if(geschlageneSteineRot.size() > 5) {
			setSiegRot(true);
		}
	}
	
	public Boolean siegerehrung() {
		partieZuEnde();
		if(getSiegBlau() || getSiegRot()) {
			System.out.println("--------------------------------------------------");
			System.out.println("--------------------------------------------------\n");
			System.out.print("\tFick die Henne, ");
			if(getSiegBlau()) {
				System.out.print(getNameBlau());
			} else if (getSiegRot()) {
				System.out.print(getNameRot());
			}
			System.out.println(" du hast gewonnen!\n\tDas Spiel ging " + getRunden() + " Runden.");
			System.out.println("\n--------------------------------------------------");
			System.out.println("--------------------------------------------------");
			return true;
		}
		return false;
	}

	// Methoden, gibt die gesamte Collection aus, also jeden Stein
	// Steine haben auch toString, die Farbe und Position ausgibt
	// im Endeffekt haben wir eine Liste mit jedem Stein und seiner Farbe

	public String toString() {
		String string = "**************************************************\n";
		for (Spielstein index : spielfeld) {
			string = string + index.toString()
					+ "\n**************************************************\n";
		}
		return string;
	}

}
