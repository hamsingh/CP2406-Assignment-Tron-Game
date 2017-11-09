import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

@SuppressWarnings("serial")
public class LocalMultiPlayer extends JComponent {

    // the player and all other players
    LocalPlayer player1, player2, player3;
//    LocalPlayer player1 = new LocalPlayer
//            (100, 100, 0, 5, Color.CYAN, "Player1", "DOWN", true);
//    LocalPlayer player2 = new LocalPlayer
//            (200, 100, 0, 5, Color.RED, "Player2", "DOWN", true);
//    LocalPlayer player3 = new LocalPlayer
//            (300, 100, 0, 5, Color.YELLOW, "Player3", "DOWN", true);
    LocalPlayer[] players;
    Color[] colors = {Color.CYAN, Color.PINK, Color.WHITE, Color.YELLOW,
            Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN};

    Random rand = new Random();

    // court dimensions
    int MAPWIDTH = 800;
    int MAPHEIGHT = 800;

    // initial velocity
    int VELOCITY = 5;

    // score and score labels
    int i = 0;
    int j = 0;
    int k = 0;
    JLabel score;
    JLabel score2;
    JLabel score3;

    // the game timer and speed at which tick() is called
    int interval = 20;
    Timer timer;
    boolean run = true;

    // outcome of the match
    boolean p1 = false;
    boolean p2 = false;
    boolean p3 = false;
    boolean tie = false;

    // constructor adds KeyListeners and initializes fields
    public LocalMultiPlayer(JLabel sco1) {
        setBackground(Color.WHITE);
        //if (p > 8) { p = 8; }
        //this.players = new LocalPlayer[p];
        this.score = sco1;
//        this.score2 = sco2;
//        this.score3 = sco3;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        // timer that runs the game
        timer = new Timer(interval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();

        // Player Controls

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!player1.getAlive()) {
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player1.setVelocityX(-VELOCITY);
                    player1.setVelocityY(0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player1.setVelocityX(VELOCITY);
                    player1.setVelocityY(0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    player1.setVelocityY(-VELOCITY);
                    player1.setVelocityX(0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player1.setVelocityY(VELOCITY);
                    player1.setVelocityX(0);
                }
                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    player1.changeJetWall();
                }
                else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    player1.accelerate(1);
                }
                else if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    player1.accelerate(-1);
                }
                if (!player2.getAlive()) {
                }
                else if (e.getKeyCode() == KeyEvent.VK_A && player2.getDirection() != "RIGHT") {
                    player2.setVelocityX(-5);
                    player2.setVelocityY(0);
                    player2.setDirection("LEFT");
                }
                else if (e.getKeyCode() == KeyEvent.VK_D && player2.getDirection() != "LEFT") {
                    player2.setVelocityX(5);
                    player2.setVelocityY(0);
                    player2.setDirection("RIGHT");
                }
                else if (e.getKeyCode() == KeyEvent.VK_W && player2.getDirection() != "DOWN") {
                    player2.setVelocityX(0);
                    player2.setVelocityY(-5);
                    player2.setDirection("UP");
                }
                else if (e.getKeyCode() == KeyEvent.VK_S && player2.getDirection() != "UP") {
                    player2.setVelocityX(0);
                    player2.setVelocityY(5);
                    player2.setDirection("DOWN");
                }
                else if (e.getKeyCode() == KeyEvent.VK_E) {
                    player2.changeJetWall();
                }
                else if (e.getKeyCode() == KeyEvent.VK_R) {
                    player2.accelerate(1);
                }
                else if (e.getKeyCode() == KeyEvent.VK_F) {
                    player2.accelerate(-1);
                }
                if (!player3.getAlive()) {
                }
                else if (e.getKeyCode() == KeyEvent.VK_J && player3.getDirection() != "RIGHT") {
                    player3.setVelocityX(-5);
                    player3.setVelocityY(0);
                    player3.setDirection("LEFT");
                }
                else if (e.getKeyCode() == KeyEvent.VK_L && player3.getDirection() != "LEFT") {
                    player3.setVelocityX(5);
                    player3.setVelocityY(0);
                    player3.setDirection("RIGHT");
                }
                else if (e.getKeyCode() == KeyEvent.VK_I && player3.getDirection() != "DOWN") {
                    player3.setVelocityX(0);
                    player3.setVelocityY(-5);
                    player3.setDirection("UP");
                }
                else if (e.getKeyCode() == KeyEvent.VK_K && player3.getDirection() != "UP") {
                    player3.setVelocityX(0);
                    player3.setVelocityY(5);
                    player3.setDirection("DOWN");
                }
                else if (e.getKeyCode() == KeyEvent.VK_O) {
                    player2.changeJetWall();
                }
                else if (e.getKeyCode() == KeyEvent.VK_U) {
                    player2.accelerate(1);
                }
                else if (e.getKeyCode() == KeyEvent.VK_H) {
                    player2.accelerate(-1);
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
        int xnew = 50 + rand.nextInt(700);
        int ynew = 50 + rand.nextInt(700);
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

    // moves the game by one timestamp
    void tick() {

        player1.setBoundary(getWidth(), getHeight());
        player1.move();
        player1.clip();
        player2.setBoundary(getWidth(), getHeight());
        player2.move();
        player2.clip();
        player3.setBoundary(getWidth(), getHeight());
        player3.move();
        player3.clip();

        player1.crash(player1.intersects(player2));
        player1.crash(player1.intersects(player1));
        player1.crash(player1.intersects(player3));
        player2.crash(player2.intersects(player1));
        player2.crash(player2.intersects(player2));
        player2.crash(player2.intersects(player3));
        player3.crash(player3.intersects(player1));
        player3.crash(player3.intersects(player2));
        player3.crash(player3.intersects(player3));

        if (!player1.getAlive() && !player2.getAlive() || !player2.getAlive() && !player3.getAlive() || !player1.getAlive() && !player3.getAlive()) {
            timer.stop();
            run = false;
            addScore();
        }
        setScore();
        repaint();
    }

    // initializes all new characters and restarts the timer
    public void reset() {
        p1 = false;
        p2 = false;
        p3 = false;
        tie = false;
        int[] start1 = getRandomStart();
        String randomDirection = "";
        if (start1[4] == 1)
            randomDirection = "RIGHT";
        else if (start1[4] == 2)
            randomDirection = "LEFT";
        else if (start1[4] == 3)
            randomDirection = "UP";
        else if (start1[4] == 4)
            randomDirection = "DOWN";
        player1 = new LocalPlayer
                (start1[0], start1[1], start1[2], start1[3], Color.CYAN, "Player1", randomDirection, true);
        //players[0] = player1;
        int[] start2 = getRandomStart();
        String randomDirection2 = "";
        if (start2[4] == 1)
            randomDirection2 = "RIGHT";
        else if (start2[4] == 2)
            randomDirection2 = "LEFT";
        else if (start2[4] == 3)
            randomDirection2 = "UP";
        else if (start2[4] == 4)
            randomDirection2 = "DOWN";
        player2 = new LocalPlayer
                (start2[0], start2[1], start2[2], start2[3], Color.PINK, "Player2", randomDirection2, true);
        //players[1] = player2;
        int[] start3 = getRandomStart();
        String randomDirection3 = "";
        if (start3[4] == 1)
            randomDirection3 = "RIGHT";
        else if (start3[4] == 2)
            randomDirection3 = "LEFT";
        else if (start3[4] == 3)
            randomDirection3 = "UP";
        else if (start3[4] == 4)
            randomDirection3 = "DOWN";
        player3 = new LocalPlayer
                (start3[0], start3[1], start3[2], start3[3], Color.RED, "Player3", randomDirection3, true);
        //players[2] = player3;
        timer.start();
        requestFocusInWindow();
    }

    // changes the score being displayed
    public void setScore(){
        score.setText
                ("   Player 1: " + i + "   Player 2: " + j + "   Player 3: " + k);
    }

    // adds scores to high scores or sets the score after a level
    public void addScore(){
        if (!run) {
            if (player1.getAlive()) {
                p1 = true;
                i++;
            } else if (player2.getAlive()) {
                p2 = true;
                j++;
            } else if (player3.getAlive()) {
                p3 = true;
                k++;
            } else {
                tie = true;
            }
        }
        score.repaint();
//        score2.repaint();
//        score3.repaint();
    }

    // draws the Player objects
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, MAPWIDTH, MAPHEIGHT);
        player1.draw(g);
        player2.draw(g);
        player3.draw(g);
//        for (Player p: players) {
//            if (p != null) {
//                p.draw(g);
//            }
//        }
    }

    // sets the dimensions of the court
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAPWIDTH, MAPHEIGHT);
    }
}