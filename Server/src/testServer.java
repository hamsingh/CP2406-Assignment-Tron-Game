/**
 * Created by jc321560 on 2/11/17.
 */
public class testServer {
    public static void main(String[] args) throws Exception{
        System.out.println("START");
        String message = "";
        String testMessage = "Harmon,200,300,yes";
        Network network = new Network("238.254.254.254", 45565);
        String IP = network.getIP();
        int PORT = network.getPort();
        network.send(IP, PORT,testMessage);
        message = network.listen();
        System.out.println(message);
    }
}
