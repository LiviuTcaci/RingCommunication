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
Traffic captures are saved in the `captures/` folder:
- `ring-communication-localhost.pcapng` - TCP traffic capture between the 3 nodes on localhost (lo0 interface)

### How to View the Capture
1. Open Wireshark
2. Open the file `captures/ring-communication-localhost.pcapng`
3. Apply the filter: `tcp.port == 5001 || tcp.port == 5002 || tcp.port == 5003`

### What You Can Observe
- TCP communication between nodes on ports 5001, 5002, and 5003
- Data packets containing the incremented values
- TCP handshake when establishing connections
- Connection termination when the value reaches 100 