import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Network {

    private DatagramSocket sendSocket;
    private MulticastSocket receiveSocket;

    Network(int port) throws IOException {
        sendSocket = new DatagramSocket(port);
        receiveSocket = new MulticastSocket(port);
    }

    void sendRequest(String name, String message, int port) throws IOException {
        InetAddress address = InetAddress.getLocalHost();
        String request = name + " " + message;
        DatagramPacket packet = new DatagramPacket(request.getBytes(), request.length(), address, port);
        sendSocket.send(packet);
    }

    String receiveCommand(int port) throws IOException {
        byte[] buffer = new byte[1024];//TODO: check if this will receive from multicast server
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        //MulticastSocket socket = new MulticastSocket(port);
        receiveSocket.joinGroup(InetAddress.getByName("238.254.254.254"));
        receiveSocket.receive(packet);

        return new String(buffer).trim(); //TODO: see if i can access this by just saying print receiveCommand(PORT) etc.
    }

    void close() {
        sendSocket.close();
        receiveSocket.close();
    }
}
