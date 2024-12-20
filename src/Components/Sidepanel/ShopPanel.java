package Components.Sidepanel;

import Object.GameObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ShopPanel extends GameObject {

    private int tileSize;

    private BufferedImage shopTitleText;

    private ArrayList<Item> allPossibleItems = new ArrayList<>(); //All items that can be bought
    private ArrayList<Item> shopItems = new ArrayList<>(); //Items that are currently in the shop

    public ShopPanel(int x, int y, int width, int height, int tileSize) {
        super(x, y, width, height);
        this.tileSize = tileSize;
        shopTitleText = loadImage("/shop/shop_title.png");

        loadAllShopItems();
        rollItems();
    }


    //A method for rolling 4 random items
    private void rollItems() {
        shopItems.clear();
        for (int i = 0; i < 4; i++) {
            int randomIndex = (int) (Math.random() * allPossibleItems.size());
            shopItems.add(allPossibleItems.get(randomIndex));
        }
    }



    private void loadAllShopItems() {
        allPossibleItems.add(new Item("item1", "Description", 100, 1));
        allPossibleItems.add(new Item("item2", "Description", 200, 1));
        allPossibleItems.add(new Item("item3", "Description", 300, 1));
        allPossibleItems.add(new Item("item4", "Description", 400, 1));
        allPossibleItems.add(new Item("item5", "Description", 500, 1));
        allPossibleItems.add(new Item("item6", "Description", 600, 1));
        allPossibleItems.add(new Item("item7", "Description", 700, 1));
        allPossibleItems.add(new Item("item8", "Description", 800, 1));
        allPossibleItems.add(new Item("item9", "Description", 900, 1));
        allPossibleItems.add(new Item("item10", "Description", 1000, 1));
    }

    @Override
    public void update() {
        super.update();
    }

    public void draw(Graphics2D g2) {
        // RGB 51, 57, 65, 255
        //Draw background for sidepanel
        g2.setColor(new Color(13, 44, 94, 100));
        g2.fillRect(getX(), getY(), getWidth(), getHeight());
        g2.drawImage(shopTitleText, getX(), getY(), getWidth(), tileSize, null);

        //Draw shop items
        for (int i = 0; i < shopItems.size(); i++) {
            shopItemObject shopItem = new shopItemObject(getX(), getY() + tileSize + i * tileSize * 2, getWidth(), tileSize * 2);
            shopItem.item = shopItems.get(i);
            shopItem.draw(g2);
        }
    }




    private static class shopItemObject extends GameObject {
        Item item;
        BufferedImage shopItemBackground;
        BufferedImage itemImage;
        public shopItemObject(int x, int y, int width, int height) {
            super(x, y, width, height);
            shopItemBackground = loadImage("/shop/ItemCard.png");
            //itemImage = loadImage("/shop/" + item.name + ".png");
            itemImage = loadImage("/items/" + "cash" + ".png");

        }

        public void draw(Graphics2D g2) {
            g2.setColor(Color.white);
            g2.drawImage(shopItemBackground, getX(), getY(), getWidth(), getHeight(), null);
            g2.drawImage(itemImage, getX() + getWidth()/20, getY() + getHeight()/12, getWidth() / 4, getHeight() / 2, null);
        }
    }



    private static class Item {
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
