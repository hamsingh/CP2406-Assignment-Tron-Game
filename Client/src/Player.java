import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Player extends Object {

    // Player Attributes
    Color color;
    boolean alive = true;
    boolean jetWall = true;

    // Initial Conditions
    int initialVelocity = 0;
    int velocity = 5; //Subject to change

    static int HEIGHT = 5;
    static int WIDTH = 5;

    // Player object's path
    ArrayList<Shape> lines = new ArrayList<Shape>();

    public Player(int x, int y, int velocityX, int velocityY, Color color) {
        super(x, y, velocityX, velocityY, WIDTH, HEIGHT);
        this.color = color;
    }

    // Changes State of Players Jet Wall
    public void setjetWall() {
        jetWall = false;
    }

    public abstract void move();

    // Draws Players and Jetwall
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x - WIDTH/2, y - HEIGHT/2, WIDTH, HEIGHT);
        for (Shape k: lines) {
            k.draw(g);
        }
    }

    public boolean getAlive() {
        return alive;
    }

    public ArrayList<Shape> getPath() {
        return lines;
    }

    // checks if the Player has crashed with a path
    public void crash(Intersection i) {
        if (i == Intersection.UP) {
            velocityX = 0;
            velocityY = 0;
            alive = false;
        }
    }
}

