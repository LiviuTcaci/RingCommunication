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
        // TODO: Implement server and client logic
        // Server: listen on localPort, receive value, increment, send to next node
        // Client: connect to nextIP:nextPort, send value
        System.out.println("Node started on port " + localPort + ", next hop: " + nextIP + ":" + nextPort);
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