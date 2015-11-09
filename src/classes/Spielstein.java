package classes;

import java.io.Serializable;
import enums.Farbe;



/*
 * Spielstein (ADT) hat drei Attribute, ist serializable und enthält int array
 * array ist zuständig für die Koordinaten x und y
 * ToString Methode
 */
//Klasse soll abstract sein: Skript Seite 11
public class Spielstein implements Serializable {
   // Attribute müssen alle serialisierbar sein
   // Primitive Datentypen sind von Hause aus serialisierbar
   private Farbe             farbe;
   private int []            position         = new int [2];
   private boolean           geschlagen       = false;
   private static final long serialVersionUID = 11L;

   // Konstruktor mit Werteingabe für x und y
   public Spielstein(Farbe farbe, int xBreite, int yHoehe) {
      setFarbe(farbe);
      this.position[0] = xBreite;
      this.position[1] = yHoehe;
   }

   // Konstruktor mit Werteingabe für x und y als array
   public Spielstein(Farbe farbe, int [] position) {
      setFarbe(farbe);
      setPosition(position);
   }

   // Kopierkonstruktor
   public Spielstein(Spielstein stein) {
      setFarbe(stein.getFarbe());
      setPosition(stein.getPosition());
      setGeschlagen(stein.isGeschlagen());
   }

   // Standartkonstruktor
   public Spielstein() {

   }

   // Getter/Setter

   public boolean isGeschlagen() {
      return geschlagen;
   }

   public void setGeschlagen(boolean geschlagen) {
      this.geschlagen = geschlagen;
   }

   public Farbe getFarbe() {
      return farbe;
   }

   public void setFarbe(Farbe farbe) {
      this.farbe = farbe;
   }

   public int [] getPosition() {
      return position;
   }

   public int getXPosition() {
      return position[0];
   }

   public int getYPosition() {
      return position[1];
   }

   public void setPosition(int [] position) {
      this.position = position;
   }

   public void setXPosition(int xPosition) {
      this.position[0] = xPosition;
   }

   public void setYPosition(int yPosition) {
      this.position[1] = yPosition;
   }

   // toString
   public String toString() {
      return "Spielstein:\nFarbe:\t\t" + getFarbe() + "\ngeschlagen:\t" + isGeschlagen() + "\nPosition:\t(" + position[0] + "|" + position[1] + ")";
   }
}
