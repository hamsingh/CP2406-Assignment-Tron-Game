import java.util.ArrayList;

public class Server {
    private boolean running = true;

    public void main(String[] args) throws Exception {
        Network network = new Network("238.254.254.254", 45565);
        String message = "";
        final ArrayList<String> players = new ArrayList<>();
        String[] attributes = message.split(",");
        String name;
        int index = 0;
        NetworkSender sender = new NetworkSender("238.254.254.254", 45565);
        while (running)

            /*
            message = network.read();
            name = attributes[0];
            if (!(players.contains(name)))
                players.add(message);

            else
                index = players.indexOf(name);
                players.set(index,message);

            network.broadcast(players.toString());
            */
            network.broadcast("REQUEST");
    }
}