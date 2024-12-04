package Object.Domino;

import Object.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Domino extends GameObject {

    private int upValue;
    private int downValue;

    BufferedImage image;
    ArrayList<BufferedImage> randomImages = new ArrayList<>();

    public Domino(int x, int y, int width, int height, int upValue, int downValue) {
        super(x, y, width, height);

        this.upValue = upValue;
        this.downValue = downValue;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/domino/white/" + upValue + "_" + downValue + ".png"));
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

    public Domino(int x, int y, int width, int height) {
        super(x, y, width, height);

        Random random = new Random();
        upValue = random.nextInt(7);
        downValue = random.nextInt(7);

        if(upValue < downValue) {
            int temp = upValue;
            upValue = downValue;
            downValue = temp;
        }

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/domino/white/" + upValue + "_" + downValue + ".png"));
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
    }

    public void setDownValue(int downValue) {
        this.downValue = downValue;
    }

    public int getUpValue() {
        return upValue;
    }

    public int getDownValue() {
        return downValue;
    }

    private int shakeDuration = 60;
    private int originalX = getX();
    private int originalY = getY();
    private BufferedImage originalImage;
    public void update() {
        originalImage = image;
        if (shakeDuration > 0) {
            int shakeAmount = 10;
            int shakeX = (int) (Math.random() * shakeAmount - shakeAmount / 2);
            int shakeY = (int) (Math.random() * shakeAmount - shakeAmount / 2);
            setX(originalX + shakeX);
            setY(originalY + shakeY);
            if(shakeDuration % 5 == 0) image = randomImages.get((int) (Math.random() * randomImages.size()));
            shakeDuration--;
        } else {
            setX(originalX);
            setY(originalY);
            image = originalImage;
        }


    }

    public void draw(Graphics2D g) {
        g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }




}
