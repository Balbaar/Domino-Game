package Object;

import Util.SoundPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    private Dimension size;

    private int x;
    private int y;

    private BufferedImage image;

    //Effects
    public boolean shakeEffect = false;
    public int shakeDuration = 60;

    public boolean liftUpEffect = false;
    public int liftUpDuration = 20;

    public boolean liftDownEffect = false;
    public int liftDownDuration = 20;

    SoundPlayer whooshSound = new SoundPlayer("/sounds/clonk.wav");

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.size = new Dimension(width, height);
    }

    public void update() {

    }

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


    public void playShakeEffect() {
        shakeDuration = 60;
        shakeEffect = true;
    }

    public boolean getShakeEffect() {
        return shakeEffect;
    }


    public void playLiftUpEffect() {
        liftUpDuration = 10;
        liftUpEffect = true;
        whooshSound.play();
        System.out.println("Lift up effect" + this);
    }

    public boolean getLiftUpEffect() {
        return liftUpEffect;
    }


    public void playLiftDownEffect() {
        liftUpDuration = 10;
        liftDownEffect = true;
    }

    public boolean getLiftDownEffect() {
        return liftDownEffect;
    }


}
