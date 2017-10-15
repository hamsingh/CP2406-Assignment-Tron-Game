import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Player extends Object {

    // Player Attributes
    Color color = Color.RED;
    boolean alive = true;
    boolean jetWall = true;

    // Initial Conditions
    int initialVelocity = 0;
    int velocity = 5; //Subject to change

    // Player object's path
    ArrayList<Shape> lines = new ArrayList<Shape>();

    public Player(int x, int y, int velocityX, int velocityY, int width, int height) {
        super(x, y, velocityX, velocityY, width, height);
        this.color = color;
    }

    // Changes State of Players Jet Wall
    public void setjetWall() {
        jetWall = false;
    }

    public void move() {
        int a = x;
        int b = y;

        if (jetWall) {
            x += velocityX;
            y += velocityY;
            if (lines.size() > 1) {
                Shape l1 = lines.get(lines.size() - 2);
                Shape l2 = lines.get(lines.size() - 1);
                if (a == l1.getStartX() && l1.getEndY() == l2.getStartY()) {
                    lines.add(new Line(l1.getStartX(), l1.getStartY(), l2.getEndX(), l2.getEndY()));
                    lines.remove(lines.size() - 2);
                    lines.remove(lines.size() - 2);
                }
                else if (b == l1.getStartY() && l1.getEndX() == l2.getStartX()) {
                    lines.add(new Line(l1.getStartX(), l1.getStartY(), l2.getEndX(), l2.getEndY()));
                    lines.remove(lines.size() - 2);
                    lines.remove(lines.size() - 2);
                }
            }
            lines.add(new Line(a, b, x, y));
        }
        else {
            jetWall = true;
        }

    }

    public static void Accelerate() {

    }

    public static void Velocity() {

    }

    public void keyPressed(KeyEvent e) {
        if (alive) {
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            setVelocityX(-5);
            setVelocityY(0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setVelocityX(5);
            setVelocityY(0);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            setVelocityX(0);
            setVelocityY(5);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            setVelocityX(0);
            setVelocityY(-5);
        }
        else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            setjetWall();
        }
    }
    public void keyReleased(KeyEvent e) {
    }
}

