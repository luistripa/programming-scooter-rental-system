# IP_TP2 - Passing 29 of 29 tests! Yay!

## Second programming project for the IP subject

To run the tests, navigate to the project directory and run:
```
python TestParser.py
```
The program will then run all tests and stop whenever an error is found

The tests output will be saved inside tests/TK/myOut with K being the number ID of the test

## Available commands

All commands are case insensitive

`nif` identifies the clients while `scooterID` or `id` identifies the scooters

All params are Strings except phone (int), latitude (double) and longitude (double)

```
adCliente <nif> <email> <phone> <name> - Inserts a client in the system

remCliente <nif> - Removes a client from the system

adTrot <id> <licensePlate> - Inserts a scooter in the system

dadosCliente <nif> - Prints client information

trot <nif> - Get scooter info that was rented by the client

dadosTrot <id> - Get scooter information

cliente <id> - Get client info that rented the scooter

carrSaldo <nif> <amount> - Inserts <amount> into client balance

alugar <nif> <id> - Rents a scooter with id <id> by client <nif>

libertar <id> <minutes> - Releases scooter <id> after <minutes>

estadoSistema - Prints the system state

desTrot <id> - Deactivates a scooter

reacTrot <id> - Reactivates a scooter

listTrot - Lists all scooters in insetion order

lisCliente - List all clients in <nif> lexicographic order

listDev - List clients with negative balance in descending order

libLoc <id> <minutes> <latitude> <longitude> - Release a scooter with location

locTrot <latitude> <longitude> - Lists scooters by distance (ascending order)

sair - Exits
```
