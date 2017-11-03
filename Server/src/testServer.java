import java.util.ArrayList;

/**
 * Created by jc321560 on 2/11/17.
 */
public class testServer {
    public static void main(String[] args) throws Exception{
        System.out.println("START");
        String message = "";
        String testMessage = "Harmon,200,300,yes";
        String testMessage1 = "Jack,200,300,yes";
        String testMessage2 = "John,200,300,yes";
        final ArrayList<String> players = new ArrayList<>();
        final ArrayList<String> players2 = new ArrayList<>();
        players.add(testMessage);
        players.add(testMessage1);
        players.add(testMessage2);
        //Network network = new Network("238.254.254.254", 45565);
        //String IP = network.getIP();
        //int PORT = network.getPort();
        //network.send(IP, PORT,testMessage);
        //message = network.listen();
        //System.out.println(players);
        //System.out.println(players.toString());
        message = players.toString();
        players2.add(message);
        System.out.println(players2);
        String [] players3 = message.split(" ");
        String testplayers = players3.toString();
        System.out.println(testplayers);
        String Player1 = players3[1].toString();
        System.out.println(Player1);
        String [] attributes = Player1.split(",");
        String name = attributes[3].toString();
        System.out.println(name);

    }
}
