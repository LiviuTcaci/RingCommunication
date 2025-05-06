public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Usage: java Main <local_port> <next_ip> <next_port> <is_initiator>");
            System.err.println("  <is_initiator> is 1 for the first node, 0 for the rest.");
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