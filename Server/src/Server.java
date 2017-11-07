import java.util.ArrayList;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Server{
    private boolean running = true;
    public static Network server;
//    ServerInfo BroadcastServerInfo;
//    NetworkReader read = new NetworkReader(); // Initialise receiver thread class
//    NetworkSender send = new NetworkSender(); // Initialise broadcaster thread class
    //Network network = new Network("238.254.254.254", 45565);
    //NetworkReader reader = new NetworkReader("238.254.254.254", 45565); // Initialise receiver thread class
    //NetworkSender sender = new NetworkSender("238.254.254.254", 45565); // Initialise broadcaster thread class

    //public static Server serverConsole;

    public Server () throws IOException {
        //ServerInfo myServerInfo = new ServerInfo();
    }

    public static void main(String[] args) throws Exception {
        //serverConsole = new Server();
        server = new Network("238.254.254.254", 45565);
        final ArrayList<String> players = new ArrayList<>();
        final ArrayList<String> playerIPs = new ArrayList<>();
        final ArrayList<String> playerPorts = new ArrayList<>();
        (new Thread(new ServerInfo())).start();
//        Thread t = new Thread(Runnable);
//        myServerInfo.start();
        ServerInfo myServerInfo = new ServerInfo();
        Thread thread1 = new Thread(myServerInfo);
        thread1.start();

        int numberPlayers = Integer.parseInt(JOptionPane.showInputDialog("Input Number of Players"));
        int i;
        for (i = 0; i < numberPlayers; i+=1) {
            String message = server.read();
            System.out.println(message);
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
                    playerIPs.add(server.getLatestReadIP());
                    playerPorts.add(Integer.toString(server.getLatestReadPort()));
                }
            }
        }
        boolean playersConnecting = true;
        while (playersConnecting) {
            if (i == numberPlayers) {
                myServerInfo.stop();
                for (int x = 0; x < numberPlayers; x++) {
                    server.send(playerIPs.get(x),Integer.parseInt(playerPorts.get(x)),players.toString());
                    String reply = server.read();
                    if (reply == "PLAYERS ADDED"){
                        break;
                    }
                    else {
                        server.send(playerIPs.get(x),Integer.parseInt(playerPorts.get(x)),players.toString());
                    }
                }
            }
            playersConnecting = false;
            for (int wait = 0; wait < 1000; wait++);
        }

        for (int x = 0; x < numberPlayers; x++) {
            server.send(playerIPs.get(x),Integer.parseInt(playerPorts.get(x)),"START");
            //String reply = server.read();
        }

        //server.broadcast("START");
        System.out.println("Game Running");

        Thread readClientInfo = new Thread(() -> {
            try {
                int index = 0;
                System.out.println("Reader Started");
                while (true) {
                    String message = server.read();
                    String[] attributes = message.split(",");
                    String name = attributes[0];
                    if (!(players.contains(name)))
                        players.add(message);

                    else
                        index = players.indexOf(name);
                        players.set(index,message);
                }
            } catch (IOException ioE) {
                ioE.printStackTrace();
                System.out.println(ioE.toString());
            }
        });
        readClientInfo.start();

        Thread broadcastPlayerInfo = new Thread(() -> {
            try {
                System.out.println("Sender Started");
                while (true) {
                    server.broadcast(players.toString());
                }
            } catch (IOException ioE) {
                ioE.printStackTrace();
                System.out.println(ioE.toString());
            }
        });
        broadcastPlayerInfo.start();
    }

    public static class ServerInfo implements Runnable{
        private boolean running;
        //Server server;
        private volatile boolean playersConnecting = true;


//        public ServerInfo() throws IOException{
//
//           // server.broadcastServer = new Network("238.254.254.254", 45565);
//
//        }

        private synchronized void start() {
            if(playersConnecting) return;
            Thread broadcastThread = new Thread(this);
            broadcastThread.start();
            running = true;
        }

        public void stop() {
//            if (!playersConnecting) return;
            System.out.println("Server Information Broadcast Stopped");
            //server.server.close();
            playersConnecting = false;
        }

        public void run() {
            while(playersConnecting) {
                try {
                    server.broadcast(server.getIP() + "," + server.getPort());
                }
                catch (IOException ioE) {
                    ioE.printStackTrace();
                    System.out.println(ioE.toString());
                }
            }
        }
    }
}