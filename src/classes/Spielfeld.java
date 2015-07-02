package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enums.Farbe;
import exceptions.SteinExistiertNichtException;

public class Spielfeld implements Serializable {
	// Attribute
	protected List<Spielstein> spielfeld = new ArrayList<Spielstein>();
	private static final long serialVersionUID = 1L;
	private String nameBlau;
	private String nameRot;
	private int runden;
	private Boolean siegBlau = false;
	private Boolean siegRot = false;

	// Konstruktoren
	public Spielfeld() {
		// Generiert Startzustand.
		for (int i = 0; i < 5; i++) {
			spielfeld.add(new Spielstein(Farbe.Rot, i, 0));
		}

		for (int i = 0; i < 4; i++) {
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

	// Search geht die Collection durch und sucht nach einem Stein der die
	// gegebenen Koordinaten, d.h. seine position enthält.
	public Spielstein searchStein(int x, int y) {
		for (Spielstein stein : spielfeld) {
			if (stein.getXPosition() == x && stein.getYPosition() == y) {
				return stein;
			}
		}
		return null;
	}

	public Boolean spielzugLegal(int xBreiteAlt, int yHöheAlt, int xBreiteNeu,int yHöheNeu) {
		if (imWertebereich(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu)) {
			if (spielerFarbeStimmt(xBreiteAlt, yHöheAlt)) {
				if (feldFrei(xBreiteNeu, yHöheNeu)) {
					if (xBreiteNeu == (xBreiteAlt - 1)
							|| xBreiteNeu == (xBreiteAlt + 1)
							|| yHöheNeu == (yHöheAlt - 1)
							|| yHöheNeu == (yHöheAlt + 1)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean imWertebereich(int xBreiteAlt, int yHöheAlt, int xBreiteNeu, int yHöheNeu) {
		return (xBreiteAlt > -1 && xBreiteAlt < 8 && yHöheAlt > -1 && yHöheAlt < 8
				&& xBreiteNeu > -1 && xBreiteNeu < 8 && yHöheNeu > -1
				&& yHöheNeu < 8);
	}

	public boolean feldFrei(int xBreiteNeu, int yHöheNeu) {
		return searchStein(xBreiteNeu, yHöheNeu) == null;
	}

	public boolean spielerFarbeStimmt(int xBreiteAlt, int yHöheAlt) {
		Spielstein stein = searchStein(xBreiteAlt, yHöheAlt);
		if(stein != null){
			if (runden % 2 == 0) {
				if (stein.getFarbe() == Farbe.Blau) {
					return true;
				}
			} else {
				if (stein.getFarbe() == Farbe.Rot) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean steinZiehen(int xBreiteAlt, int yHöheAlt, int xBreiteNeu,int yHöheNeu) throws SteinExistiertNichtException {
		if (spielzugLegal(xBreiteAlt, yHöheAlt, xBreiteNeu, yHöheNeu)) {
			Spielstein stein = searchStein(xBreiteAlt, yHöheAlt);
			if (stein != null){
				stein.setXPosition(xBreiteNeu);
				stein.setYPosition(yHöheNeu);
				return true;
			}
		}
		return false;
	}

	private int numGeschlagendeSteine(Farbe farbe) {
		int zähler = 0;

		for (Spielstein stein : spielfeld) {
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

	public void grafischeDarstellung() {

		zeigeGeschlageneSteine(Farbe.Blau);
		// Erstelle Spielfeld
		System.out.println("****************");
		System.out.println("****************");
		System.out.println("#|0|1|2|3|4|5|6|");
		for (int j = 0; j < 7; j++) {
			System.out.print(j);
			for (int i = 0; i < 7; i++) {
				Boolean gefunden = false;
				for (Spielstein stein : spielfeld) {
					if (stein.getXPosition() == i && stein.getYPosition() == j&& !stein.isGeschlagen()) {
						String farbe = (stein.getFarbe() == Farbe.Rot) ? "r" : "b";
						System.out.print("|" + farbe);
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

		zeigeGeschlageneSteine(Farbe.Rot);
		System.out.println();
	}

	public void steinSchlagen(int xBreite, int yHöhe) {

		Spielstein stein = searchStein(xBreite, yHöhe);
		
		int x = stein.getXPosition();
		int y = stein.getYPosition();

		ArrayList<Spielstein> steine = umliegendeSteineDerAnderenFarbe(stein);

		for (Spielstein umliegenderStein : steine) {

			ArrayList<Spielstein> naechstenUmliegendeSteine = umliegendeSteineDerAnderenFarbe(umliegenderStein);

			for (Spielstein neuUmliegenderStein : naechstenUmliegendeSteine) {

				if (neuUmliegenderStein != stein) {

					int xNeu = neuUmliegenderStein.getXPosition();
					int yNeu = neuUmliegenderStein.getYPosition();

					if (x - xNeu == 2 || xNeu - x == 2 || y - yNeu == 2
							|| yNeu - y == 2) {
						umliegenderStein.setGeschlagen(true);
					}
				}
			}
		}
	}

	private ArrayList<Spielstein> umliegendeSteineDerAnderenFarbe(
			Spielstein stein) {

		Farbe farbe = stein.getFarbe();
		int x = stein.getXPosition();
		int y = stein.getYPosition();

		ArrayList<Spielstein> spielsteine = new ArrayList<Spielstein>();

		Spielstein steinOben = searchStein(x, y - 1);
		if (steinOben != null) {
			if (steinOben.getFarbe() != farbe) {
				spielsteine.add(steinOben);
			}
		}

		Spielstein steinUnten = searchStein(x, y + 1);
		if (steinUnten != null) {
			if (steinUnten.getFarbe() != farbe) {
				spielsteine.add(steinUnten);
			}
		}

		Spielstein steinLinks = searchStein(x - 1, y);
		if (steinLinks != null) {
			if (steinLinks.getFarbe() != farbe) {
				spielsteine.add(steinLinks);
			}
		}

		Spielstein steinRechts = searchStein(x + 1, y);
		if (steinRechts != null) {
			if (steinRechts.getFarbe() != farbe) {
				spielsteine.add(steinRechts);
			}
		}

		return spielsteine;
	}

	public void partieZuEnde() {
		if (numGeschlagendeSteine(Farbe.Blau) > 5) {
			setSiegBlau(true);
		}

		if (numGeschlagendeSteine(Farbe.Rot) > 5) {
			setSiegRot(true);
		}
	}

	public boolean hatJemandGewonnen() {

		if (getSiegBlau() || getSiegRot()) {

			String name;

			if (getSiegBlau()) {
				name = getNameBlau();
			} else {
				name = getNameRot();
			}
			System.out.println("Nice!");
			System.out.println(name + " du hast gewonnen!\nDas Spiel ging "
					+ getRunden() + ".");
			return true;
		}

		return false;
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
