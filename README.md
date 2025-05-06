# Ring Communication

This project implements a ring communication using TCP sockets in Java. Each node receives a value, increments it, and sends it further until the value reaches 100.

## Project Structure
- `src/` - Java source code
- `captures/` - Wireshark captures for analysis

## Running Instructions
1. Compile the code: `javac src/*.java`
2. Start each node with parameters: `java -cp src Main <local_port> <next_ip> <next_port> <is_initiator>`
   - `is_initiator` is 1 for the first node, 0 for the rest.

## Example Parameters for 3 Nodes (localhost):
- Node 1: `java -cp src Main 5001 127.0.0.2 5002 1`
- Node 2: `java -cp src Main 5002 127.0.0.3 5003 0`
- Node 3: `java -cp src Main 5003 127.0.0.1 5001 0`

## Wireshark Captures
Traffic captures are saved in the `captures/` folder. 