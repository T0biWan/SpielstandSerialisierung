****************************************************************************************************
****************************************************************************************************
****************************************************************************************************

Willkommen bei Latrunculi!


Es folgt eine kurze Anleitung zu unserem Spiel.

Unser Spiel ist Eclipse basiert,
um spielen zu k�nnen muss die Klasse Latrunculi ausgef�hrt werden.

Auf dem Spielfeld liegen Steine von zwei verschiedenen Farben.
Jede Farbe wird einem der zwei Spieler zugeordnet.

Pro Zug darf ein Stein um ein Feld horizontal oder vertikal bewegt werden.
Vorausgesetzt, das neue Feld ist noch nicht von einem anderen Stein belegt.

Ziel des Spiels ist es, dem Gegner alle Steine wegzunehmen.
Ein Stein kann genommen werden, wenn er in eine Zange aus zwei andersfarbigen Steinen ger�t.
Das folgende "grafische" Beispiel soll das verdeutlichen:

	1.	X O
		    X

	2. 	X O X --> X  X

Eine solche Zange darf auch vertikal sein.
Wird ein Stein in eine solche Zange gesetzt, darf er nicht aus dem Spiel entfernt werden:

	1.	X   X
		  O

	2.	X O X

Um einen Stein zu ziehen, m�ssen die Koordinaten seiner Position eingegeben werden [X|Y].
Anschlie�end werden die Koordinaten der gew�nschten neuen Position angegeben [X|Y].

Unser Spiel wird serialisiert, dadurch wird erm�glicht, dass auf zwei Computern gespielt werden kann.
Dies ist allerdings nicht zwingend notwenidg.
Nach jedem Spielzug wird eine Datei mit dem aktuellen Spielstand erstellt.
Diese Datei wird beim erneuten ausf�hren der Klasse eingelesen.
Diese Datei (zu finden im Ordner "/output") k�nnen sich die Spieler, z.B.: Per Mail zu kommen lassen.
Daraus folgt f�r die Spieler folgendes, pro Programmausf�hrung kann nur ein Spielzug gemacht werden.
Nach einem regelkonformen Spielzug wird das Programm beendet.


Wir w�nschen viel Spa� mit unserem Spiel! :)

� SchwaBaKla 2015

****************************************************************************************************
****************************************************************************************************
****************************************************************************************************