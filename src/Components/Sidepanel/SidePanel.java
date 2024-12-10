package Components.Sidepanel;

import Object.GameObject;

import java.awt.*;

public class SidePanel extends GameObject {

    private ScorePanel scorePanel;
    private ShopPanel shopPanel;


    private int width;
    private int height;
    private int tileSize;

    public SidePanel(int screenHeight, int tileSize) {
        super(0, 0, tileSize * 6, screenHeight);
        this.tileSize = tileSize;
        this.width = tileSize * 6;
        this.height = screenHeight;

        scorePanel = new ScorePanel(tileSize*4, height, tileSize);
        shopPanel = new ShopPanel(tileSize, tileSize*4, tileSize*4, tileSize*10, tileSize);
    }

    @Override
    public void draw(Graphics2D g2) {
        // RGB 51, 57, 65, 255
        //Draw background for sidepanel
        g2.setColor(new Color(51, 57, 65, 255));
        g2.fillRect(getX(), getY(), getWidth(), getHeight());
        g2.setColor(new Color(111, 57, 65, 255));
        g2.fillRect(getX() + tileSize, getY(), getWidth() - tileSize*2, getHeight());
        scorePanel.draw(g2);
        shopPanel.draw(g2);
    }

    @Override
    public void update() {
        super.update();
        scorePanel.update();
        shopPanel.update();
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }

    public ShopPanel getShopPanel() {
        return shopPanel;
    }

}
