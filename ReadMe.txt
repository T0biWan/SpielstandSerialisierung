****************************************************************************************************
****************************************************************************************************
****************************************************************************************************

Willkommen bei Latrunculi!


Es folgt eine kurze Anleitung zu unserem Spiel.

Unser Spiel ist Eclipse basiert,
um spielen zu können muss die Klasse Latrunculi ausgeführt werden.

Auf dem Spielfeld liegen Steine von zwei verschiedenen Farben.
Jede Farbe wird einem der zwei Spieler zugeordnet.

Pro Zug darf ein Stein um ein Feld horizontal oder vertikal bewegt werden.
Vorausgesetzt, das neue Feld ist noch nicht von einem anderen Stein belegt.

Ziel des Spiels ist es, dem Gegner alle Steine wegzunehmen.
Ein Stein kann genommen werden, wenn er in eine Zange aus zwei andersfarbigen Steinen gerät.
Das folgende "grafische" Beispiel soll das verdeutlichen:

	1.	X O
		    X

	2. 	X O X --> X  X

Eine solche Zange darf auch vertikal sein.
Wird ein Stein in eine solche Zange gesetzt, darf er nicht aus dem Spiel entfernt werden:

	1.	X   X
		  O

	2.	X O X

Um einen Stein zu ziehen, müssen die Koordinaten seiner Position eingegeben werden [X|Y].
Anschließend werden die Koordinaten der gewünschten neuen Position angegeben [X|Y].

Unser Spiel wird serialisiert, dadurch wird ermöglicht, dass auf zwei Computern gespielt werden kann.
Dies ist allerdings nicht zwingend notwenidg.
Nach jedem Spielzug wird eine Datei mit dem aktuellen Spielstand erstellt.
Diese Datei wird beim erneuten ausführen der Klasse eingelesen.
Diese Datei (zu finden im Ordner "/output") können sich die Spieler, z.B.: Per Mail zu kommen lassen.
Daraus folgt für die Spieler folgendes, pro Programmausführung kann nur ein Spielzug gemacht werden.
Nach einem regelkonformen Spielzug wird das Programm beendet.


Wir wünschen viel Spaß mit unserem Spiel! :)

© SchwaBaKla 2015

****************************************************************************************************
****************************************************************************************************
****************************************************************************************************