public class TestUDP {

    public static void main(String[] args) {
        TestSend();
        TestReceive();
    }
    public static void TestSend(){
        String message = "xPos = 1, yPos = 3";
        UDP sender = new UDP();
        try {
            sender.Send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void TestReceive(){
        UDP reader = new UDP();

        String message = null;
        try {
            message = reader.Receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(message);
    }
}
