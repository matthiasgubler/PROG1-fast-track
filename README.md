# PROG1, fast-track
IT17a_WIN, Matthias Gubler

## Spiel
Es wird ein Snake Game implementiert, welches Konsolebasiert ist.
Die Konsole wird mittels Lanterna implementiert, da die normale Java-Console relativ schwierig zu benutzen ist für non-blocking userinput.
Die Schlange lässt sich mittels WSAD steuern.

## Setup
Das Projekt is in folgendem Git-Repo zu finden:
https://github.engineering.zhaw.ch/PROG1-axa/fast-track

Es werden Unittests mittels JUnit 4 und Mockito verwendet und wo möglich wird der Ansatz von TDD implementiert.

Folgende Designpatterns wurden implementiert:
- Composite-Pattern: Für Snake und die "Körperteile"
- Observer-Pattern: Anzeige des Punktestandes
- Behaviour-Pattern: Verschiedene Verhalten von Kollisionen

## Ideen für Erweiterungen

- Die Geschwindigkeit erhöht sich entweder alle x Zeit oder alle x Punkte
- Es gibt verschiedene Arten von Objekten auf dem Feld:
    - Done: "Normales Essen"
    - Done: Super Food (gibt mehr Punkte)
    - Poisioned Food (zieht Punkte ab und erhöht die Geschwindigkeit)

## Benötigte Libraries
```
JUnit 4.12
Mockito 1.9.5
Hamcrest Core 1.3
com.googlecode.lanterna:lanterna:3.0.0
org.apache.commons:commons-lang3:3.3
```

