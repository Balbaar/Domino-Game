package Object;

import Util.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameObject {
    private Dimension size;

    private int x;
    private int y;

    private int viewX;
    private int viewY;

    private BufferedImage image;

    //Effects
    public boolean shakeEffect = false;
    public int shakeDuration = 60;

    public boolean liftUpEffect = false;
    public int liftUpDuration = 20;

    public boolean liftDownEffect = false;
    public int liftDownDuration = 20;

    public boolean hoverEffect = false;
    public boolean isHovered = false;
    public int hoverDuration = 20;
    public int hoverHeight = 10;

    SoundPlayer whooshSound = new SoundPlayer("/sounds/clonk.wav");

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.viewX = x;
        this.y = y;
        this.viewY = y;
        this.size = new Dimension(width, height);
    }

    public void update() {
        if(viewX < x) {
            viewX += Math.max(1, (x - viewX) / 10);
        } else if(viewX > x) {
            viewX -= Math.max(1, (viewX - x) / 10);
        }

        if(viewY < y) {
            viewY += Math.max(1, (y - viewY) / 10);
        } else if(viewY > y) {
            viewY -= Math.max(1, (viewY - y) / 10);
        }

        if(hoverEffect) {
            if(hoverDuration > 0 && !isHovered) {
                playLiftUpEffect(hoverHeight);
                isHovered = true;
                hoverDuration--;
            }
            else if(hoverDuration == 0 && isHovered) {
                playLiftDownEffect(hoverHeight);
                hoverEffect = false;
                isHovered = false;
            }
            hoverDuration--;
        }

    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(viewX, viewY, size.width, size.height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getViewX() {
        return viewX;
    }

    public int getViewY() {
        return viewY;
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


    public void playShakeEffect() {
        shakeDuration = 60;
        shakeEffect = true;
    }

    public boolean getShakeEffect() {
        return shakeEffect;
    }


    public void playLiftUpEffect(int height) {
        setY(getY() - height);
        System.out.println("Lift up effect");
    }

    public boolean getLiftUpEffect() {
        return liftUpEffect;
    }


    public void playLiftDownEffect(int height) {
        setY(getY() + height);
        System.out.println("Lift down effect");
    }

    public boolean getLiftDownEffect() {
        return liftDownEffect;
    }

    public void playHoverEffect(int height, int duration) {
        hoverEffect = true;
        hoverHeight = height;
        hoverDuration = duration;
        whooshSound.play();
    }

    public BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
