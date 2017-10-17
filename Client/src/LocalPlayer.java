import java.awt.Color;

public class LocalPlayer extends Player {

    public LocalPlayer(int x, int y, int velocityX, int velocityY, Color color){
        super(x, y, velocityX, velocityY, color);
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
            jetWall = false;
        }

    }
}