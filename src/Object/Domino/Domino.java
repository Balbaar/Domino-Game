package Object.Domino;

import Object.GameObject;
import Util.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Domino extends GameObject {

    private int upValue;
    private int downValue;

    //General info about the domino
    private int sumValue;
    private boolean isDouble;
    private boolean hasBlank;

    BufferedImage viewImage;
    BufferedImage realImage;

    ArrayList<BufferedImage> randomImages = new ArrayList<>();



    public Domino(int x, int y, int width, int height, int upValue, int downValue) {
        super(x, y, width, height);

        this.upValue = upValue;
        this.downValue = downValue;

        setGeneralInfoValues();

        loadImages(upValue, downValue);
    }

    public Domino(int x, int y, int width, int height) {
        super(x, y, width, height);

        Random random = new Random();
        upValue = random.nextInt(7);
        downValue = random.nextInt(7);

        setGeneralInfoValues();

        loadImages(upValue, downValue);
    }



    public void update() {
        super.update();
        doEffects();
    }

    public void draw(Graphics2D g) {
        g.drawImage(viewImage, getViewX(), getViewY(), getWidth(), getHeight(), null);
    }

    private int originalX = getX();
    private int originalY = getY();
    private BufferedImage originalImage;

    private void doEffects() {
        if(shakeEffect) {
            doShakeEffect();
        }

    }



    private void doShakeEffect() {
        if (this.shakeDuration > 0) {
            int shakeAmount = 10;
            int shakeX = (int) (Math.random() * shakeAmount - shakeAmount / 2);
            int shakeY = (int) (Math.random() * shakeAmount - shakeAmount / 2);
            setX(originalX + shakeX);
            setY(originalY + shakeY);
            if(shakeDuration % 5 == 0) viewImage = randomImages.get((int) (Math.random() * randomImages.size()));
            shakeDuration--;
        } else {
            setX(originalX);
            setY(originalY);
            viewImage = realImage;
            shakeEffect = false;
        }
    }

    private void setGeneralInfoValues() {
        this.sumValue = upValue + downValue;
        if(upValue == downValue) isDouble = true;
        if(upValue == 0 || downValue == 0) hasBlank = true;
    }


    private void loadImages(int upValue, int downValue) {
        try {
            realImage = ImageIO.read(getClass().getResourceAsStream("/domino/white/" + upValue + "_" + downValue + ".png"));
            randomImages.add(ImageIO.read(getClass().getResourceAsStream("/domino/white/0_0.png")));
            randomImages.add(ImageIO.read(getClass().getResourceAsStream("/domino/white/2_1.png")));
            randomImages.add(ImageIO.read(getClass().getResourceAsStream("/domino/white/3_0.png")));
            randomImages.add(ImageIO.read(getClass().getResourceAsStream("/domino/white/3_2.png")));
            randomImages.add(ImageIO.read(getClass().getResourceAsStream("/domino/white/4_1.png")));
            randomImages.add(ImageIO.read(getClass().getResourceAsStream("/domino/white/4_4.png")));
            randomImages.add(ImageIO.read(getClass().getResourceAsStream("/domino/white/5_3.png")));
            randomImages.add(ImageIO.read(getClass().getResourceAsStream("/domino/white/6_6.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUpValue(int upValue) {
        this.upValue = upValue;
        sumValue = upValue + downValue;
    }

    public void setDownValue(int downValue) {
        this.downValue = downValue;
        sumValue = upValue + downValue;
    }

    public int getUpValue() {
        return upValue;
    }

    public int getDownValue() {
        return downValue;
    }

    public int getSumValue() {
        return sumValue;
    }






}
