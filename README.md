# Kurzbeschreibung 
Unser Programm „Optimal Heist” soll Schüler:innen das Rucksackproblem näher bringen,
was dieses genau ist und wie man dieses Problem auf mit Hilfe des Greedy- und
Backtracking-Algorithmus lösen kann. Das ganze funktioniert auf eine spielerische Art
und Weise, indem man in die Rolle von Räubern (Gieriger Ganove, Backtracking Bandit und
Doktor Meta) schlüpft, welcher mit einem Rucksack Gegenstände aus einem Museum
stehlen möchte.
Unsere Vision ist es, dass diese Software im Unterricht benutzt wird, um Algorithmen (hier:
Greedy und Backtracking) oder Optimierungsprobleme (insbesondere das
Rucksackproblem) den Schüler:innen näherzubringen. Dabei ist angedacht, dass die
Schüler:innen selbst Hand an das Programm anlegen und sich selbst mit der Thematik des
Rucksackproblems auseinandersetzen, während die Lehrkraft den Lernprozess von außen
mit unterstützt.
Unser Programm ist auch Open Source auf GitHub vertreten und kann frei genutzt und
modifiziert werden.

# Ausführung
Das Programm wird auf Linux, MacOS und Windows unterstützt.

## Linux (Ubuntu)

### Benötigte Programme installieren.
$ sudo apt update && sudo apt upgrade -y

$ sudo apt install git -y

$ sudo apt install maven -y

$ sudo apt install openjdk-17-jre-headless -y

$ sudo apt-get install openjdk-17-jre -y

falls:
$ mvn --version
eine andere java version als jdk 17 anzeigt, wähle über

$ sudo update-alternatives --config java
jdk 17 aus

### Das Projekt klonen und ausführen

$ git clone https://github.com/CaroReutin/Teamprojekt directory
(wobei directory der Ordnerpfad ist an dem das Projekt gespeichert werden soll)
(wenn beim Einloggen in GitHub nach dem passwort gefragt wird, muss statdessen ein PersonalAccesToken eingegeben werden,
https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token#creating-a-personal-access-token-classic)

Das Projekt kann nun bearbeitet werden.
Um das programm auszuführen öffne das Terminal in directory und gebe ein:

$ mvn clean 

$ mvn package

$ java -jar target/jarname
(wobei jarname der name der jar mit den dependencies ist, bsp. 
optimalheist-1.0-SNAPSHOT-jar-with-dependencies.jar)

## Windows
### Benötigte Programme installieren
- git
- maven
- openjdk-17

### Das Projekt klonen und ausführen

$ git clone https://github.com/CaroReutin/Teamprojekt directory
(wobei directory der Ordnerpfad ist an dem das Projekt gespeichert werden soll)
(wenn beim Einloggen in GitHub nach dem passwort gefragt wird, muss statdessen ein PersonalAccesToken eingegeben werden,
https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token#creating-a-personal-access-token-classic)

Das Projekt kann nun bearbeitet werden.
Um das programm auszuführen öffne das Terminal in directory und gebe ein:

$ mvn clean 

$ mvn package

$ java -jar target/jarname
(wobei jarname der name der jar mit den dependencies ist, bsp. 
optimalheist-1.0-SNAPSHOT-jar-with-dependencies.jar)




## MacOS




# Quelle Bilder Räuber
Bild von <a href="https://pixabay.com/de/users/clker-free-vector-images-3736/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=308858">Clker-Free-Vector-Images</a> auf <a href="https://pixabay.com/de//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=image&amp;utm_content=308858">Pixabay</a>
