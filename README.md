# Ring Communication

Acest proiect implementează o comunicare în inel (Ring Communication) folosind socket-uri TCP în Java. Fiecare nod primește o valoare, o incrementează și o trimite mai departe, până când valoarea atinge 100.

## Structura proiectului
- `src/` - Codul sursă Java
- `captures/` - Capturi Wireshark pentru analiză

## Instrucțiuni de rulare
1. Compilează codul: `javac src/*.java`
2. Pornește fiecare nod cu parametri: `java -cp src Main <port_local> <ip_next> <port_next> <is_initiator>`
   - `is_initiator` este 1 pentru primul nod, 0 pentru restul.

## Exemplu de parametri pentru 3 noduri (localhost):
- Nod 1: `java -cp src Main 5001 127.0.0.2 5002 1`
- Nod 2: `java -cp src Main 5002 127.0.0.3 5003 0`
- Nod 3: `java -cp src Main 5003 127.0.0.1 5001 0`

## Capturi Wireshark
Capturile de trafic sunt salvate în folderul `captures/`. 