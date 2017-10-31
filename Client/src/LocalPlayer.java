import java.awt.Color;

public class LocalPlayer extends Player {

    public LocalPlayer(int randX, int randY, int velocityX, int velocityY, Color color){
        super(randX, randY, velocityX, velocityY, color);
    }

    // does nothing because human players can see screen
    // only needed for AI, but required for abstract class
    public void addPlayers(Player[] players) {
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
            if (velocityX > 0) {
                //x += JUMPHEIGHT;
            } else if (velocityX < 0) {
                //x -= JUMPHEIGHT;
            } else if (velocityY > 0) {
                //y += JUMPHEIGHT;
            } else if (velocityY < 0) {
                //y -= JUMPHEIGHT;
            }
            jetWall = true;
        }

    }
}