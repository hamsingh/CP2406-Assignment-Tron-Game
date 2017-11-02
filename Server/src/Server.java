import java.util.ArrayList;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Server {
    private boolean running = true;
    public static Network server;
    NetworkReader read = new NetworkReader(); // Initialise receiver thread class
    NetworkSender send = new NetworkSender(); // Initialise broadcaster thread class
    //Network network = new Network("238.254.254.254", 45565);
    //NetworkReader reader = new NetworkReader("238.254.254.254", 45565); // Initialise receiver thread class
    //NetworkSender sender = new NetworkSender("238.254.254.254", 45565); // Initialise broadcaster thread class
    final ArrayList<String> players = new ArrayList<>();

    public void main(String[] args) throws Exception {
        server = new Network("238.254.254.254", 45565);
        int numberPlayers = Integer.parseInt(JOptionPane.showInputDialog("Input Number of Players"));
        for (int i = 0; i < numberPlayers; i++) {
            String message = server.read();
            if (message.startsWith("Add Player")) {
                String[] attributes = message.split(",");
                String name = attributes[1];
                String x = attributes[2];
                String y = attributes[3];
                String jetWall = attributes[4];
                if (players.contains(name)) {
                    server.send(server.getLatestReadIP(), server.getLatestReadPort(), "Error: Name Already Taken");
                }
                else {
                    String player = name + "," + x + "," + y + "," + jetWall;
                    players.add(player);
                    server.send(server.getLatestReadIP(), server.getLatestReadPort(), "JOINED");
                }
            }
        }
        server.broadcast("START");
        System.out.println("Game Running");
        read.start();
        send.start();
    }

    public class NetworkReader implements Runnable {
        private boolean running;

        private synchronized void start() {
            if(running) return;

            Thread broadcastThread = new Thread(this);
            broadcastThread.start();
            running = true;
        }

        void stop() throws Exception {
            if (!running) return;

            server.close();
            running = false;
        }

        public void run() {
            int index = 0;
            while(running){
                try {
                    String message = server.read();
                    String[] attributes = message.split(",");
                    String name = attributes[0];
                    if (!(players.contains(name)))
                        players.add(message);

                    else
                        index = players.indexOf(name);
                    players.set(index,message);
                }
                catch(Exception error) {

                }
            }
        }
    }

    public class NetworkSender implements Runnable{
        private boolean running;

        private synchronized void start() {
            if(running) return;

            Thread broadcastThread = new Thread(this);
            broadcastThread.start();
            running = true;
        }

        void stop() throws Exception {
            if (!running) return;

            server.close();
            running = false;
        }

        public void run() {
            while(running) {
                try {
                    server.broadcast(players.toString());
                }
                catch (Exception error){
                    error.printStackTrace();
                }
            }
        }
    }
}