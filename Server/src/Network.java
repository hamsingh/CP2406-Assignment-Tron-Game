import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Network {

    private MulticastSocket sendSocket;
    private DatagramSocket receiveSocket;

    Network(int port) throws IOException {
        sendSocket = new MulticastSocket(port);
        receiveSocket = new DatagramSocket(port);
    }

    void sendCommand(String message, int port) throws IOException {
        InetAddress address = InetAddress.getByName("238.254.254.254");
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), address, port);
        sendSocket.send(packet);
    }

    String receiveRequest() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        receiveSocket.receive(packet);

        return new String(buffer).trim();
    }

    void close() {
        sendSocket.close();
        receiveSocket.close();
    }
}
