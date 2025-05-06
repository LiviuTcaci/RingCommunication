import java.io.*;
import java.net.*;

public class Node {
    private int localPort;
    private String nextIP;
    private int nextPort;
    private boolean isInitiator;

    public Node(int localPort, String nextIP, int nextPort, boolean isInitiator) {
        this.localPort = localPort;
        this.nextIP = nextIP;
        this.nextPort = nextPort;
        this.isInitiator = isInitiator;
    }

    public void start() {
        try {
            // Server: listen on localPort
            ServerSocket serverSocket = new ServerSocket(localPort);
            System.out.println("Node started on port " + localPort + ", next hop: " + nextIP + ":" + nextPort);

            // If this is the initiator, start by sending value 1
            if (isInitiator) {
                sendValue(1);
            }

            while (true) {
                // Accept a connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted from " + clientSocket.getInetAddress());

                // Read the value from the client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String valueStr = in.readLine();
                int value = Integer.parseInt(valueStr);
                System.out.println("Received value: " + value);

                // Increment the value
                value++;
                System.out.println("Incremented value: " + value);

                // If value < 100, send to next node
                if (value < 100) {
                    sendValue(value);
                } else {
                    System.out.println("Value reached 100, stopping communication.");
                }

                // Close the connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendValue(int value) {
        try {
            // Connect to the next node
            Socket socket = new Socket(nextIP, nextPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(value);
            System.out.println("Sent value " + value + " to " + nextIP + ":" + nextPort);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Usage: java Node <local_port> <next_ip> <next_port> <is_initiator>");
            System.exit(1);
        }
        int localPort = Integer.parseInt(args[0]);
        String nextIP = args[1];
        int nextPort = Integer.parseInt(args[2]);
        boolean isInitiator = Integer.parseInt(args[3]) == 1;
        Node node = new Node(localPort, nextIP, nextPort, isInitiator);
        node.start();
    }
} 