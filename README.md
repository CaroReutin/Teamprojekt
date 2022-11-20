# Optimal Heist

Jedes Backlogitem hat einen eigenen Branch und jeder entwickelt auf dem Branch für das entsprechende item.
Sobald ein Item abgeschlossen und getestet ist auf diesem Branch , dann wird auf Github eine Pull-Request auf dev geöffnet.
Dann prüft ein anderer eure Pull Request und kann diese approven und in dev über Github mergen. Dev und Main werden in den Projekt
einstellungen protected, das bedeutet, niemand kann lokal auf diese branches pushen / commiten. Auf dev werden alle features bis zu einem
Release gesammelt. For dem release wird der dev branch getestet und wenn er fehlerfrei ist wird der dev branch auf main gemerged. Main
repräsentiert also das produktiv / öffentliche Umfeld was die Kunden nutzen.

## Kurzbeschreibung 
...

## Ausführung
...

## Erklärung zum Code
...