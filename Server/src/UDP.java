import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDP {
    //byte[] buffer = new byte[1024];
    public static void main(String[] args) {
        /*
            I want to get information from receiver class.
            Update Grid
            Then send all user co ordinates in a buffer
            Send(buffer);
         */

        //Get message
        //String new message = Read();
    }

    public void Send(String message) throws Exception {
        InetAddress group = InetAddress.getByName("228.5.6.7");
        MulticastSocket socket = new java.net.MulticastSocket(49152);
        socket.joinGroup(group);

        //send message
        DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length()
                , group, 49152);
        socket.send(outPacket);

        //leave group
        socket.leaveGroup(group);
    }
    public String Receive()throws Exception {
        InetAddress group = InetAddress.getByName("228.5.6.7");
        MulticastSocket socket = new java.net.MulticastSocket(49152);
        socket.joinGroup(group);

        //read response
        byte[] buffer = new byte[1024];
        DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(inPacket);
        String newMessage = new String(buffer, 0, buffer.length);

        //leave group
        socket.leaveGroup(group);

        //return message
        return newMessage;
    }
}
