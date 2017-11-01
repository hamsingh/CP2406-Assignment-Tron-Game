public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws Exception {
        NetworkWorker worker = new NetworkWorker(49152);

        while (running) {
            Thread receive = new Thread(new Runnable(){
                @Override
                public void run() {
                    worker.start();
                }
            });
            receive.start();
        }
    }
}
