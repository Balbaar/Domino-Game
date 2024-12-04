package Object;

import java.awt.*;

public class GameObject {
    private Dimension size;

    private int x;
    private int y;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.size = new Dimension(width, height);
    }

    public void update() {}

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(x, y, size.width, size.height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return size.width;
    }

    public int getHeight() {
        return size.height;
    }
}
