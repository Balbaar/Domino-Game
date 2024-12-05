package Components.Sidepanel;

import Object.GameObject;

import java.awt.*;

public class SidePanel extends GameObject {

    private ScorePanel scorePanel;

    private int width;
    private int height;
    private int tileSize;

    public SidePanel(int tileSize, int screenHeight) {
        super(0, 0, tileSize * 6, screenHeight);
        this.tileSize = tileSize;
        this.width = tileSize * 6;
        this.height = screenHeight;
        scorePanel = new ScorePanel(tileSize, tileSize*4, height);
    }

    public void draw(Graphics2D g2) {
        // RGB 51, 57, 65, 255
        //Draw background for sidepanel
        g2.setColor(new Color(51, 57, 65, 255));
        g2.fillRect(getX(), getY(), getWidth(), getHeight());
        g2.setColor(new Color(111, 57, 65, 255));
        g2.fillRect(getX() + tileSize, getY(), getWidth() - tileSize*2, getHeight());
        scorePanel.draw(g2);
    }

    public void update() {
        scorePanel.update();
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }

}
