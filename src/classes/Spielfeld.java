package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enums.Farbe;
/*
 * Diese Klasse 
 */
public class Spielfeld implements Serializable {
	// Attribute
	protected List<Spielstein>	spielsteine = new ArrayList<>();
	private static final long	serialVersionUID = 1L;
	private String				nameBlau;
	private String				nameRot;
	private int					runden;
	private Boolean				siegBlau = false;
	private Boolean				siegRot = false;
	private final int 			MAX	= 7;
	/*
	 * Konstruktoren
	 * Hier wird ein neues Spielfeld im Startzustand erzeugt.
	 * Die zwei For-Schleifen füllen das Spielfeld mit 7 blauen und 7 roten Steinen an den richtigen Stellen.
	 */
	public Spielfeld() {
		for (int i = 0; i < 6; i++) {
			spielsteine.add(new Spielstein(Farbe.Rot, i, 0));
		}
		
		spielsteine.add(new Spielstein(Farbe.Rot, 2, 3));

		for (int i = 0; i < 5; i++) {
			spielsteine.add(new Spielstein(Farbe.Blau, i, 6));
		}
		
		spielsteine.add(new Spielstein(Farbe.Blau, 2, 4));
		spielsteine.add(new Spielstein(Farbe.Blau, 3, 2));
	}

	// Methoden mit gettern und settern, um Spieler, Rundenzahl zu benennen etc.
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
	
	/* Oberklasse
	 * Koordinaten, alterStand und neuerStand wird mitgegeben
	 * Suche nach Steinen, die diese alten Koordinaten haben, um diesen dann zu verändern
	 * Nur wenn er ihn findet und der Zug legal ist, wird er ausgeführt sonst wird false zurückgegeben
	 */
	public boolean steinZiehen(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yHöheNeu) {
		if(spielzugLegal(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu)) {
			Spielstein stein = searchStein(xBreiteAlt, yHöheAlt);
			if(stein != null) {
				stein.setXPosition(xBreiteNeu);
				stein.setYPosition(yHöheNeu);
				return true;
			}	
		}
		return false;
	}
	
	/*
	 * Search geht die Collection durch und sucht nach einem Stein der die gegebenen Koordinaten, d.h. seine position enthält.
	 * Diese Methode sucht einen Stein an den gesuchten Koordinaten und gibt ihn, wenn vorhanden, zurück.
	 */
 	public Spielstein searchStein(int x, int y) {
 		if (imWertebereich(x, y)){
			for (Spielstein stein : spielsteine) {
				if (stein.getXPosition() == x && stein.getYPosition() == y) {
					return stein;
				}
			}
 		}
		return null;
	}
	
 	/*
 	 * Kontrolle, ob ein Spielzug legal ist. 
 	 */
	public Boolean spielzugLegal(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yHöheNeu) {
		if (imWertebereich(xBreiteAlt, yHöheAlt) && imWertebereich(xBreiteNeu, yHöheNeu)) {
			if (spielerFarbeStimmt(xBreiteAlt, yHöheAlt)) {
				if (feldFrei(xBreiteNeu, yHöheNeu)) {
					if (xBreiteNeu == (xBreiteAlt - 1) || xBreiteNeu == (xBreiteAlt + 1) || yHöheNeu == (yHöheAlt - 1) || yHöheNeu == (yHöheAlt + 1)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/*
	 * imWertebereich schaut immer, ob die Koordinaten zwischen 0 und 7 liegen.
	 */
	public Boolean imWertebereich(int xBreite, int yHöhe) {
		return (xBreite > -1 && xBreite <= MAX && yHöhe > -1 && yHöhe <= MAX);	
	}
	
	/*
	 * Wir suchen den Stein, der bewegt werden soll, und schauen, ob wir uns in einer geraden oder ungeraden Runde befinden.
	 * Sind wir in einer geraden, muss der Stein blau sein, für ungerade rot. Wir wollen nicht, dass roter Spieler blaue Steine bewegt.
	 */
	public Boolean spielerFarbeStimmt(int xBreiteAlt, int yHöheAlt) {
		Spielstein stein = searchStein(xBreiteAlt, yHöheAlt);
		if(runden % 2 == 0) {
			return (stein.getFarbe() == Farbe.Blau);
		} else {
			return (stein.getFarbe() == Farbe.Rot);
		}
	}
	
	/*
	 * Diese Methode guckt, ob das Feld, auf das der Stein bewegt werden soll, wirklich frei ist.
	 * Die search-Methode gibt null bei leerem Feld zurück. 
	 */
	public Boolean feldFrei (int xBreiteNeu, int yHöheNeu) {
		return (searchStein(xBreiteNeu, yHöheNeu) == null);
	}
	
	/*
	 * Gibt die Anzahl der geschlagenen Steiner der gefragten Farbe zurück
	 */
	private int numGeschlagendeSteine(Farbe farbe) {
		int zähler = 0;

		for (Spielstein stein : spielsteine) {
			if (stein.getFarbe() == farbe) {
				if (stein.isGeschlagen()) {
					zähler++;
				}
			}
		}
		return zähler;
	}
	
	private void zeigeGeschlageneSteine(Farbe farbe) {
		// Erstelle die Tabelle für die geschlagenen Steine
		int zähler = numGeschlagendeSteine(farbe);
		// holt den ersten Buchstaben der Farbe
		String letter = (farbe == Farbe.Blau) ? "R" : "B";
		String sletter = (farbe == Farbe.Blau) ? "b" : "r";
		
		System.out.print(letter);

		for (int i = 0; i <= 6; i++) {
			if (zähler > 0) {
				System.out.print("|" + sletter); // macht einen kleinbuchstaben daraus
				zähler--;
			} else {
				System.out.print("| ");
			}
		}
		System.out.println("|");
	}
	
	/*
	 * Ausgabe der Grafischen Oberfläche des aktuellen Spielstandes 
	 */
	public void grafischeDarstellung() {
		//Erstelle die Tabelle für die geschlagenen Steine
		zeigeGeschlageneSteine(Farbe.Blau);

		// Erstelle Spielfeld
		// die zwei for-Schleifen gehen unsere Liste der Spielsteine durch und setzen die Spielsteine in das Feld.
		// sollte in einem Feld kein Stein sein wird es leer ausgegeben.
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("#|0|1|2|3|4|5|6|"); // x-Koordinate
		for (int j = 0; j < 7; j++) {
			System.out.print(j); // y-Koordinate
			for (int i = 0; i < 7; i++) {
				Boolean gefunden = false;
				for (Spielstein stein : spielsteine) {
					if (stein.getXPosition() == i && stein.getYPosition() == j && !stein.isGeschlagen()) {
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
		
		//Erstelle die Tabelle für die geschlagenen Steine
		zeigeGeschlageneSteine(Farbe.Rot);
		System.out.println();
	}
	
	/*
	 * Wir schauen, ob ein schlagbarer Stein existiert.
	 * Wenn ja, wird der Stein "geschlagen" gesetzt (true) und aus der Spielstein-Liste herausgenommen (remove).
	 *  
	 */
	public void steinSchlagen(int xBreiteNeu, int yHöheNeu) {
		
		Spielstein steinOben = schlagbarOben(xBreiteNeu, yHöheNeu);
		if(steinOben != null) {
			steinOben.setGeschlagen(true);
		}
		
		Spielstein steinUnten = schlagbarUnten(xBreiteNeu, yHöheNeu);
		if(steinUnten != null) {
			steinUnten.setGeschlagen(true);
		}
		
		Spielstein steinLinks = schlagbarLinks(xBreiteNeu, yHöheNeu);
		if(steinLinks != null) {
			steinLinks.setGeschlagen(true);
		}
		
		Spielstein steinRechts = schlagbarRechts(xBreiteNeu, yHöheNeu);
		if(steinRechts != null) {
			steinRechts.setGeschlagen(true);
		}
	}

	/*
	 * Vorgehen: Holen den gesetzten Stein (neue K.) und holen uns seine Farbe.
	 * Dann schauen wir, ob darüber (y = -1) ein Stein der Gegnerfarbe ist.
	 * Sollte dies stimmen, schauen wir, ob es darüber (y = -2) einen Stein unserer Farbe gibt.
	 * Wenn ja, gibt die Methode den schlagbaren Stein zurück. Die anderen Methoden machen dasselbe, nur in die anderen Richtungen. (Unten, Links und Rechts).
	 */
	public Spielstein schlagbarOben(int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		if(stein != null) {
			Farbe steinfarbe = stein.getFarbe();
			Spielstein nebenliegenderStein = searchStein(xBreiteNeu, yHöheNeu - 1);
			if(nebenliegenderStein != null) {
				if(steinfarbe != nebenliegenderStein.getFarbe()) {
					Spielstein nebenNebenliegenderStein = searchStein(xBreiteNeu, yHöheNeu - 2);
					if(nebenNebenliegenderStein != null) {
							if(steinfarbe == nebenNebenliegenderStein.getFarbe()) {
								return nebenliegenderStein;
							}
					}
				}
			}
		}
		return null;
	}
	
	public Spielstein schlagbarUnten(int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		if(stein != null) {
			Farbe steinfarbe = stein.getFarbe();
			Spielstein nebenliegenderStein = searchStein(xBreiteNeu, yHöheNeu + 1);
			if(nebenliegenderStein != null) {
				if(steinfarbe != nebenliegenderStein.getFarbe()) {
					Spielstein nebenNebenliegenderStein = searchStein(xBreiteNeu, yHöheNeu + 2);
					if(nebenNebenliegenderStein != null) {
						if(steinfarbe == nebenNebenliegenderStein.getFarbe()) {
							return nebenliegenderStein;
						}
					}
				}
			}
		}
		return null;
	}
	
	public Spielstein schlagbarLinks(int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		if(stein != null) {
			Farbe steinfarbe = stein.getFarbe();
			Spielstein nebenliegenderStein = searchStein(xBreiteNeu -1 , yHöheNeu);
			if(nebenliegenderStein != null) {
				if(steinfarbe != nebenliegenderStein.getFarbe()) {
					Spielstein nebenNebenliegenderStein = searchStein(xBreiteNeu -2, yHöheNeu);
					if(nebenNebenliegenderStein != null) {
							if(steinfarbe == nebenNebenliegenderStein.getFarbe()) {
								return nebenliegenderStein;
							}
					}
				}
			}
		}
		return null;
	}
	
	public Spielstein schlagbarRechts(int xBreiteNeu, int yHöheNeu) {
		Spielstein stein = searchStein(xBreiteNeu, yHöheNeu);
		if(stein != null) {
			Farbe steinfarbe = stein.getFarbe();
			Spielstein nebenliegenderStein = searchStein(xBreiteNeu +1 , yHöheNeu);
			if(nebenliegenderStein != null) {
				if(steinfarbe != nebenliegenderStein.getFarbe()) {
					Spielstein nebenNebenliegenderStein = searchStein(xBreiteNeu +2, yHöheNeu);
					if(nebenNebenliegenderStein != null) {
							if(steinfarbe == nebenNebenliegenderStein.getFarbe()) {
								return nebenliegenderStein;
							}
					}
				}
			}
		}
		return null;
	}
	/*
	 * Sind die geschlagenen Steine größer als 5?
	 * Wenn ja, wird die Variable siegerRot oder Blau auf true gesetzt. 
	 */
	public void partieZuEnde() {
		if(numGeschlagendeSteine(Farbe.Blau) > 5) {
			setSiegBlau(true);
		}
		
		if(numGeschlagendeSteine(Farbe.Rot)  > 5) {
			setSiegRot(true);
		}
	}
	
	/*
	 * Gibt eine Siegerehrung aus. Nur eine Textnachricht. Ob jemand gewonnen hat, wird in Latrunculi in der Main überprüft.
	 */
	public void siegerehrung() {
		
		String name = null;
		
		System.out.println("--------------------------------------------------");
		System.out.println("--------------------------------------------------\n");
		System.out.print("\tFick die Henne, ");
		if(getSiegBlau()) {
			name = getNameBlau();
		} else if (getSiegRot()) {
			name = getNameRot();
		}
		System.out.println(name + " hat gewonnen!\n\tDas Spiel ging " + getRunden() + " Runden.");
		System.out.println("\n--------------------------------------------------");
		System.out.println("--------------------------------------------------");
	}

	// Methoden, gibt die gesamte Collection aus, also jeden Stein
	// Steine haben auch toString, die Farbe und Position ausgibt
	// im Endeffekt haben wir eine Liste mit jedem Stein und seiner Farbe

	public String toString() {
		String string = "**************************************************\n";
		for (Spielstein index : spielsteine) {
			string = string + index.toString()
					+ "\n**************************************************\n";
		}
		return string;
	}

	/*
	 * schaut ob siegBlau oder Rot true ist, dann hätte dieser gewonnen.
	 */
	public boolean hatJemandGewonnen() {
		return getSiegBlau() | getSiegRot();
	}

}
