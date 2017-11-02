import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Player extends Object {

    // Player States
    Color color;
    boolean alive = true;
    boolean jetWall = true;

    // Initial Conditions
    int initialVelocity = 5;

    // Player Height and Width
    static int HEIGHT = 5;
    static int WIDTH = 5;

    // Player Path
    ArrayList<Shape> lines = new ArrayList<Shape>();

    // Default Constructor
    public Player(int randX, int randY, int velocityX, int velocityY, Color color, String DIR) {
        super(randX, randY, velocityX, velocityY, WIDTH, HEIGHT, DIR);
        initialVelocity = Math.max(Math.abs(velocityX), Math.abs(velocityY));
        this.color = color;
    }

    // Changes State of Players Jet Wall
    public void setjetWall() {
        if (jetWall) {
            jetWall = false;
        }
        if (!jetWall) {
            jetWall = true;
        }
    }

    // Accelerates player
    /*public void accelerate() {
        if (!(this.velocityX < 0 || this.velocityX > 10))
            this.velocityX = this.velocityX++;
        else if (!(this.velocityY < 0 || this.velocityY > 10))
            this.velocityY = this.velocityY++;
        else if (this.velocityX > 10)
            this.velocityX = 10;
        else if (this.velocityY > 10)
            this.velocityY = 10;
    }*/

    // Changes Player State if it Exits Map Boundary
    public void clip() {
        if (x < 0 || x > width) {
            velocityX = 0;
            alive = false;
        }
        if (y < 0 || y > height) {
            velocityY = 0;
            alive = false;
        }
    }

    // Move Player, Local and Online Players differ so method is not initiated here
    public abstract void move();

    // Draws Players and Jetwall if true
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x - WIDTH/2, y - HEIGHT/2, WIDTH, HEIGHT);
        if (jetWall){
            for (Shape k: lines) {
                k.draw(g);
            }
        }
    }

    public void setDirection(String Direction) {
        DIR = Direction;
    }

    public String getDirection(){
        return this.DIR;
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

    // adds Player objects to the field
    abstract void addPlayers(Player[] players);
}

