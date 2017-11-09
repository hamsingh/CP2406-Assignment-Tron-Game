import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Map extends JComponent{
    LocalPlayer player;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<OnlinePlayer> onlinePlayers = new ArrayList<>();
    Color[] colors = {Color.CYAN, Color.PINK, Color.WHITE, Color.YELLOW,
            Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN};

    Random rand = new Random();

    String NAME;
    Network server;
    NetworkReader read = new NetworkReader(); // Initialise receiver thread class
    NetworkSender send = new NetworkSender(); // Initialise broadcaster thread class

    // court dimensions
    int MAPWIDTH = 800;
    int MAPHEIGHT = 800;

    // initial velocity
    int VELOCITY = 5;

    // score and score labels
    int i = 0;

    JLabel score1;

    // the game timer and speed at which tick() is called
    int interval = 20;
    Timer time;
    boolean run = true;

    // constructor adds KeyListeners and initializes fields
    public Map(JLabel sco1, String NAME) {
        setBackground(Color.WHITE);

        this.score1 = sco1;
        this.NAME = NAME;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        // Timer
        time = new Timer(interval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tick();
                }
                catch(Exception error){
                    System.out.println(error);
                }
            }
        });
        time.start();

        // Local Player Controls
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!player.getAlive()) {
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT && player.getDirection() != "RIGHT") {
                    player.setVelocityX(-5);
                    player.setVelocityY(0);
                    player.setDirection("LEFT");
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT && player.getDirection() != "LEFT") {
                    player.setVelocityX(5);
                    player.setVelocityY(0);
                    player.setDirection("RIGHT");
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP && player.getDirection() != "DOWN") {
                    player.setVelocityX(0);
                    player.setVelocityY(-5);
                    player.setDirection("UP");
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN && player.getDirection() != "UP") {
                    player.setVelocityX(0);
                    player.setVelocityY(5);
                    player.setDirection("DOWN");
                }
                else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    player.changeJetWall();
                }
                else if (e.getKeyCode() == KeyEvent.VK_W) {
                    player.accelerate(1);
                }
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                    player.accelerate(-1);
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }


    // returns an array of velocities and dimensions for a Player
    // ensures that the Player moves toward the center initially
    public int[] getRandomStart() {
        int[] start = new int[5];
        int direction;
        int xnew = 50 + rand.nextInt(800);
        int ynew = 50 + rand.nextInt(800);
        int ra = rand.nextInt(2);
        int velx = 0;
        int vely = 0;
        if (ra == 0) {
            if (xnew < 400) {
                velx = VELOCITY;
                direction = 1;
            } else {
                velx = -VELOCITY;
                direction = 2;
            }
        } else {
            if (ynew < 400) {
                vely = VELOCITY;
                direction = 3;
            } else {
                vely = -VELOCITY;
                direction = 4;
            }
        }
        start[0] = xnew;
        start[1] = ynew;
        start[2] = velx;
        start[3] = vely;
        start[4] = direction;
        return start;
    }

    // returns the velocity
    public int getVelocity() {
        return VELOCITY;
    }

    // Tick is the Games Unit of Time
    void tick() throws Exception{
        if(players.isEmpty()) {
            startGame();
        }
        for (int i = 0; i < players.size(); ++i) {
            if (players.get(i).getAlive()) {
                players.get(i).setBoundary(getWidth(), getHeight());
                players.get(i).move();
                players.get(i).clip();
            }
            else if (!(players.get(i).getAlive())) {
                server.send("238.254.254.254", 45565, NAME + "DEAD");
            }
        }
        for (int i = 0; i < onlinePlayers.size(); ++i) {
            onlinePlayers.get(i).setBoundary(getWidth(), getHeight());
            int x = onlinePlayers.get(i).getX();
            int y = onlinePlayers.get(i).getY();
            onlinePlayers.get(i).changePosition(x, y);
            onlinePlayers.get(i).clip();
        }
//        for (Player k1: players) {
//            for (Player k2: players) {
//                //k1.crash(k1.intersects(k2));
//            }
//        }
//        if (!localPlayer.getAlive()) {
//            timer.stop();
//            run = false;
//            //addScore();
//            setScore();
//        }
//        else {
//            int check = 0;
//            for (Player k: players) {
//                if (!k.getAlive()) {
//                    check++;
//                }
//            }
//            if (check == players.length - 1) {
//                run = false;
//                timer.stop();
//                //addScore();
//                setScore();
//            } else {
//                run = true;
//                setScore();
//            }
//        }
        repaint();
    }

    // initializes all new characters and restarts the timer
    public void reset() {
        //int[] start = getRandomStart();
        //player = new LocalPlayer(start[0], start[1], start[2], start[3], colors[0]);
        /*int[] start = getRandomStart();
        LocalPlayer player;
        players[0] = player;
            i = 0;
            time.start();
            requestFocusInWindow();
        */
    }

    // Starts game add players etc.
    public void startGame() throws Exception{
        String randomDirection = "";
        int[] start = getRandomStart();
        if (start[4] == 1)
            randomDirection = "RIGHT";
        else if (start[4] == 2)
            randomDirection = "LEFT";
        else if (start[4] == 3)
            randomDirection = "UP";
        else if (start[4] == 4)
            randomDirection = "DOWN";
        player = new LocalPlayer(start[0], start[1], start[2], start[3], colors[7], NAME, randomDirection,true);
        players.add(player);
        server = new Network("238.254.254.254", 45565);
        String serverInfo = server.listen();
        String [] serverInformation = serverInfo.split(",");
        String serverIP = serverInformation[0];
        int serverPort = Integer.parseInt(serverInformation[1]);
        String request = NAME + "," + player.getX() + "," + player.getY() + "," + player.isJetWall();
        server.send(serverIP, serverPort, "Add Player," + request);
        String message = server.read();
        System.out.println(message);
        boolean gameStart = false;
        while (!(gameStart)) {
            message = server.read();
            if (message.equals("JOINED")) {
                message = server.read();
                addPlayers(message);
            }
            else if (message.equals("START")) {
                read.start();
                send.start();
                gameStart = true;
            }
        }
    }

    // changes the score being displayed
    public void setScore(){

    }

    // updates the player's score after successfully completing a level
    public void addScore() {
        /*if (player.getAlive()) {
            i += 50 * (players.length - 1);
            //win = true;
            time = new Timer(interval, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addPlayers();
                }
            });
            time.start();
        } else {
            //over = true;
        }*/
    }

    // draws the Player objects
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, MAPWIDTH, MAPHEIGHT);
        if (player != null) {
            player.draw(g);
        }
        if (onlinePlayers != null) {
            for (Player p: onlinePlayers) {
                if (p != null) {
                    p.draw(g);
                }
            }
        }
    }

    public void addPlayers(String playerString) {
        String [] playersArray = playerString.split(" ");
        String name;
        int x;
        int y;
        boolean jetwallStatus;
        for (int i = 0; i < playersArray.length; i++) {
            if (!(playersArray[i].equals(NAME))) {
                String[] playerAttributes = playersArray[i].split(",");
                name = playerAttributes[0];
                x = Integer.parseInt(playerAttributes[1]);
                y = Integer.parseInt(playerAttributes[2]);
                jetwallStatus = true;
                OnlinePlayer player = new OnlinePlayer(x, y, 0, 0, colors[i], "", name, jetwallStatus); // TODO: finish this maybe change player attributes a little
                onlinePlayers.add(player);
                //server.send
            }
        }
    }

    // sets the dimensions of the court
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAPWIDTH, MAPHEIGHT);
    }

    public class NetworkSender implements Runnable{
        private boolean running;

        private synchronized void start() {
            if(running) return;
            System.out.println("Network Sender Started");
            Thread sendThread = new Thread(this);
            sendThread.start();
            running = true;
        }

        void stop() throws Exception {
            if (!running) return;

            server.close();
            running = false;
        }

        public void run() {
            while(running) {
                try {
                    String request = NAME + "," + player.getX() + "," + player.getY() + "," + player.isJetWall();
                    server.send("238.254.254.254", 45565, request);
                }
                catch (Exception error){
                    error.printStackTrace();
                }
            }
        }
    }

    public class NetworkReader implements Runnable {
        private boolean running;

        private synchronized void start() {
            if(running) return;
            System.out.println("Network Reader Started");
            Thread broadcastThread = new Thread(this);
            broadcastThread.start();
            running = true;
        }

        void stop() throws Exception {
            if (!running) return;

            server.close();
            running = false;
        }

        public void run() {
            int index = 0;

            while(running){
                try {
                    String command = server.listen();
                    String[] newPlayers = command.split(" ");
                    String[] attributes = {};
                    for (int p = 0; p < onlinePlayers.size(); p++) {
                        attributes = newPlayers[p].split(",");
                        int x = Integer.parseInt(attributes[1]);
                        int y = Integer.parseInt(attributes[2]);
                        boolean jetwallStatus;
                        if (attributes[3] == "yes")
                            jetwallStatus = true;
                        else
                            jetwallStatus = false;
                        onlinePlayers.get(p).setX(x);
                        onlinePlayers.get(p).setY(y);
                        onlinePlayers.get(p).setjetWall(jetwallStatus);
                    }
                }
                catch(IOException error) {

                }
            }
        }
    }
}