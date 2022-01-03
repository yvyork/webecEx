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

![ermLatest-Page-1](https://user-images.githubusercontent.com/61004874/143672588-b30974d7-c612-488d-9c40-df62149a20d6.jpg)

## UML

![UML_V3](https://user-images.githubusercontent.com/71099031/147847159-f22ef16c-ba9d-402d-8d7a-1db5186f6867.jpg)


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
