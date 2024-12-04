package Components;

import Object.GameObject;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameButton extends GameObject {
    BufferedImage buttonBackground;

    public GameButton(int screenWidth, int screenHeight ,int tileSize) {
        super(screenWidth / 2 - tileSize, screenHeight - tileSize * 3, tileSize * 2, tileSize * 1);
        try {
            buttonBackground = ImageIO.read(getClass().getResourceAsStream("/buttons/RollButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(buttonBackground, getX(), getY(), getWidth(), getHeight(), null);
    }

    public boolean intersects(int x, int y) {
        return x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight();
    }




}