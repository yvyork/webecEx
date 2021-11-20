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
Ein Gerät kann unterschiedliches Zubehört haben (Tastatur, Maus, etc).
Es können neue Geräte erfasst und gelöscht werden. Weiter ist es möglich, einem bestehenden Gerät einen neuen Standort oder persönlichen Nutzer zuzuweisen oder desses Status zu ändern. Wird ein Gerät auf den Status 'Defekt' gesetzt, soll es automatisch aus der Datenbank gelöscht werden. 

## ERM

![ERM_UML-ERM](https://user-images.githubusercontent.com/61004874/142725725-b693890e-7ce3-4fbd-8fc4-a2c6d3af17aa.png)

## UML

![UML](https://user-images.githubusercontent.com/61004874/142725720-ee98ff70-cc5e-49fe-b232-2b0e3df26b1b.png)

### Extras

1. BEM
2. TBA 

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
