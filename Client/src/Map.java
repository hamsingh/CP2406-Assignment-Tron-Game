import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Map extends JComponent{
    LocalPlayer player; //= new LocalPlayer(200,200,0,0,Color.RED);
    //Player[] players;
    ArrayList<Player> players = new ArrayList<>();
    Color[] colors = {Color.CYAN, Color.PINK, Color.WHITE, Color.YELLOW,
            Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN};

    Random rand = new Random();

    // UPD
    int PORT = 49152;
    String ADDRESS = "228.5.6.7";
    String NAME = "Harmon"; //TODO: get name input before joining game

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
    public Map(JLabel sco1) {
        setBackground(Color.WHITE);

        //this.players = new OnlinePlayer[p]; TODO: PUT BACK IN
        this.score1 = sco1;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        // timer that runs the game
        time = new Timer(interval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tick();
                }
                catch(Exception error){
                    System.out.println("Error with tick Exception");
                }
            }
        });
        time.start();


        // player one controls
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!player.getAlive()) {
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    //player.accelerate();
                    player.setVelocityX(-1);
                    player.setVelocityY(0);

                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    //player.accelerate();
                    player.setVelocityX(1);
                    player.setVelocityY(0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    //player.accelerate();
                    player.setVelocityX(0);
                    player.setVelocityY(-1);
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    //player.accelerate();
                    player.setVelocityX(0);
                    player.setVelocityY(1);
                }
                else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    player.setjetWall();
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }




    // returns an array of velocities and dimensions for a Player
    // ensures that the Player moves toward the center initially
    public int[] getRandomStart() {
        int[] start = new int[4];
        int xnew = 50 + rand.nextInt(600);
        int ynew = 50 + rand.nextInt(600);
        int ra = rand.nextInt(2);
        int velx = 0;
        int vely = 0;
        if (ra == 0) {
            if (xnew < 250) {
                velx = VELOCITY;
            } else {
                velx = -VELOCITY;
            }
        } else {
            if (ynew < 250) {
                vely = VELOCITY;
            } else {
                vely = -VELOCITY;
            }
        }
        start[0] = xnew;
        start[1] = ynew;
        start[2] = velx;
        start[3] = vely;
        return start;
    }

    // returns the velocity
    public int getVelocity() {
        return VELOCITY;
    }

    // moves the game by one timestamp
    void tick() throws Exception{
        if(players.isEmpty()) {
            startGame();
        }
        for (int i = 0; i < players.size(); ++i) {
            players.get(i).setBoundary(getWidth(), getHeight());
            players.get(i).move();
            //addPlayers();
            //player.clip();
        }
        /*for (Player k1: players) {
            for (Player k2: players) {
                //k1.crash(k1.intersects(k2));
            }
        }
        if (!localPlayer.getAlive()) {
            timer.stop();
            run = false;
            //addScore();
            setScore();
        }
        else {
            int check = 0;
            for (Player k: players) {
                if (!k.getAlive()) {
                    check++;
                }
            }
            if (check == players.length - 1) {
                run = false;
                timer.stop();
                //addScore();
                setScore();
            } else {
                run = true;
                setScore();
            }
        }*/
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
        int[] start = getRandomStart();
        player = new LocalPlayer(start[0], start[1], start[2], start[3], colors[0]);
        //players[0] = player; TODO: PUT BACK IN
        players.add(player);
        /*Network multi = new Network(PORT);
        multi.sendRequest(NAME,"READY", PORT);
        while (!run)
            multi.receiveCommand(PORT);
            //if (buffer == "START") TODO: try get buffer from network to process string
*/
    }

    // changes the score being displayed
    public void setScore(){
//        score1.setText("     Score: " + i +
//                "    Level: " + (players.length - 1));
//        //score2.setText("             Boost: " + localPlayer.getBoostsLeft());
//        score1.repaint();
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
        player.draw(g);
        for (Player p: players) {
            if (p != null) {
                p.draw(g);
            }
        }
    }

    public void addPlayers() {
//        int[] start = getRandomStart();
//        LocalPlayer player = new LocalPlayer(
//                start[0], start[1], start[2], start[3], colors[0]);
//        players[0] = player;
//        for (int j = 1; j < players.length; j++) {
//            start = getRandomStart();
//            players[j] = new OnlinePlayer(start[0], start[1],
//                    start[2], start[3], colors[j]);
//        }
//        for (Player p: players) {
//            p.addPlayers(players);
//        }
    }

    public void ProcessCommands(){
        //Message Format: name,x,y,wall

    }

    // sets the dimensions of the court
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAPWIDTH, MAPHEIGHT);
    }
}