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

## Wireshark Analysis

### Packet Analysis
1. **Payload vs Application Traffic Ratio**
   - Total payload delivered: ~100 bytes (values 1-100)
   - Total application traffic: ~300 bytes (including TCP headers and control packets)
   - Ratio: ~1:3 (payload:total_traffic)

2. **Packet Length Analysis**
   - Data packet length: 54 bytes (TCP header: 20 bytes + IP header: 20 bytes + data: 14 bytes)
   - Control packet length: 54 bytes (TCP header: 20 bytes + IP header: 20 bytes + no data)
   - Ratio: ~1:1 (data_packets:control_packets)

### TCP Header Analysis
1. **Connection Establishment (3-way handshake)**
   - SYN flag set (Synchronize sequence numbers)
   - SYN-ACK flags set (Synchronize-Acknowledge)
   - ACK flag set (Acknowledge)

2. **Data Transfer**
   - ACK flag set for each data packet
   - PSH flag set when sending data (Push data to application)

3. **Connection Termination**
   - FIN flag set (Finish) by the closing node
   - ACK flag set in response
   - FIN-ACK flags set by the other node
   - Final ACK to complete the connection closure

### TCP Flags for Connection Closure
When a socket connection is closed, the following TCP flags are set in sequence:
1. FIN flag is set by the node initiating the closure
2. ACK flag is set in response to the FIN
3. FIN-ACK flags are set by the other node
4. Final ACK is sent to complete the closure

This four-way handshake ensures a graceful connection termination where both parties acknowledge the closure. 