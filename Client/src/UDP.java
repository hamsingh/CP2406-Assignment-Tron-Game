import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDP {
    private int port;
    private String address;

    public UDP(){
        this.port = port;
        this.address = address;
    }

    public String ReadCommand()throws Exception {
        InetAddress group = InetAddress.getByName(this.address);
        MulticastSocket socket = new java.net.MulticastSocket(this.port);
        socket.joinGroup(group);

        //read response
        byte[] messageBuffer = new byte[1024];
        DatagramPacket inPacket = new DatagramPacket(messageBuffer, messageBuffer.length);
        socket.receive(inPacket);
        String Command = new String(messageBuffer, 0, messageBuffer.length);
        //leave group
        socket.leaveGroup(group);
        //return message
        return Command;
    }
    public void SendRequest(String message)throws Exception {
        InetAddress group = InetAddress.getByName(this.address);
        MulticastSocket socket = new java.net.MulticastSocket(this.port);
        socket.joinGroup(group);

        //send message
        DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), group, this.port);
        socket.send(outPacket);

        //leave group
        socket.leaveGroup(group);
    }
}
