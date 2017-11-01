import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDP {
    private int port;
    private String address;

    public UDP(int port, String address)throws Exception{
        this.port = port;
        this.address = address;
        InetAddress group = InetAddress.getByName(this.address);
        MulticastSocket socket = new java.net.MulticastSocket(this.port);
        socket.joinGroup(group);
    }

    public String ReadCommand(MulticastSocket socket)throws Exception {
        //read response
        byte[] messageBuffer = new byte[1024];
        DatagramPacket inPacket = new DatagramPacket(messageBuffer, messageBuffer.length);
        socket.receive(inPacket);
        String Command = new String(messageBuffer, 0, messageBuffer.length);

        //leave group
        //socket.leaveGroup(group);

        //return message
        return Command;
    }

    public void SendRequest(InetAddress group, MulticastSocket socket, String message)throws Exception {
        //send message
        DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), group, this.port);
        socket.send(outPacket);

        //leave group
        //socket.leaveGroup(group);
    }
}
