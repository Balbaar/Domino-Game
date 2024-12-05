package Components.Sidepanel;

import Object.GameObject;
import java.awt.*;

public class ShopPanel extends GameObject {

    public ShopPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics2D g2) {
        // RGB 51, 57, 65, 255
        //Draw background for sidepanel
        g2.setColor(new Color(13, 44, 94, 100));
        g2.fillRect(getX(), getY(), getWidth(), getHeight());
    }




    private class shopItemObject extends GameObject {
        Item item;
        public shopItemObject(int x, int y, int width, int height) {
            super(x, y, width, height);
        }

        public void draw(Graphics2D g2) {
            g2.setColor(Color.white);
            g2.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    private class Item {
        private String name;
        private String description;
        private int price;
        private int priority;
        public Item(String name, String description, int price, int priority) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.priority = priority;
        }

    }

}
