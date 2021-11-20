# webeC - Graded Exercise

## Project

### Team members

Bächtiger Yves,
Lehner Rainer


### Description

#### Webapplikation zum verwalten eines Geräteinventars einer Applelandschaft. 

Ausgehend von einem Interface *Device* können folgende Geräte erfasst werden: 
1. MacBook
2. MacMini
3. iPhone
4. iPad

Dabei gehört ein Gerät entweder einer *Person* oder es wird im Betrieb an einem festen *Standort* von mehreren Personen genutzt (unpersönlich).
Weiter kann ein Gerät auch einer *Studie* zugeordnet werden. Wobei jede Studie eine Studienverantwortliche hat.
Die Geräte können unterschiedliche Status haben (zb. Persönlich, Pool, Reparatur, etc.).
Es können neue Geräte erfasst und gelöscht werden. Weiter ist es möglich, einem bestehenden Gerät einen neuen Standort oder persönlichen Nutzer zuzuweisen oder desses Status zu ändern. Wird ein Gerät auf den Status 'Defekt' gesetzt, soll es automatisch aus der Datenbank gelöscht werden. 


![Untitled Diagram (1) drawio](https://user-images.githubusercontent.com/61004874/142723921-2a3e10e1-aa68-4024-9225-ff0725f3aefb.png)

### Extras

BEM\
Vielleicht ein Implementierung von QR-Codes um Geräte schnell zu erfassen. 

## Installation and run instructions

TODO: Add your instructions here if they do not follow the default

### Run application

```
./gradlew bootRun
```

### Run tests

```
./gradlew test
```
