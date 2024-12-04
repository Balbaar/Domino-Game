package Main;

import Object.GameObject;
import Components.GameButton;
import Object.Domino.DominoManager;
import Util.MouseEventHandler;
import Util.SoundPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private int FPS = 60;
    private BufferedImage background;

    private int tileSize;

    public Dimension screenSize;

    DominoManager dominoManager;
    GameButton rollButton;

    MouseEventHandler mouseEventHandler;

    SoundPlayer clonkSound = new SoundPlayer("/sounds/clonk.wav");


    public GamePanel() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setPreferredSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        tileSize = (int) screenSize.getWidth() / 1080 * 48;

        try {
            background = ImageIO.read(getClass().getResourceAsStream("/backgrounds/blue-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Objects on screen
        dominoManager = new DominoManager((int) screenSize.getWidth(), (int)screenSize.getHeight(), tileSize);
        rollButton = new GameButton((int) screenSize.getWidth(), (int)screenSize.getHeight(), tileSize);



        mouseEventHandler = new MouseEventHandler();
        this.addMouseListener(mouseEventHandler);
        this.addMouseMotionListener(mouseEventHandler);


        startGameThread();
    }

    Thread gameThread;
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double drawInterval = 1E9/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private boolean canRoll = true;
    public void update() {
        if(isPressed(rollButton) && canRoll) {
            clonkSound.play();
            dominoManager.rollBoard();
            canRoll = false;
        } else if(!isPressed(rollButton)) {
            canRoll = true;
        }

        dominoManager.updateDominos();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Draw things
        g2d.drawImage(background, 0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight(), null); // Draw background
        dominoManager.draw(g2d); // Draw dominoes
        rollButton.draw(g2d); // Draw button

        //Retro overlay effect
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        applyRetroOverlay(g2d, getWidth(), getHeight());
    }

    private void applyRetroOverlay(Graphics2D g2d, int width, int height) {
        // Save the current composite state
        Composite originalComposite = g2d.getComposite();

        // Set transparency for the overlay
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.15f));
        g2d.setColor(Color.WHITE);

        // Draw horizontal scan lines
        for (int y = 0; y < height; y += 5) { // Adjust spacing for effect
            g2d.drawLine(0, y, width, y);
        }

        // Restore the original composite state
        g2d.setComposite(originalComposite);

        // Optional: Add additional retro effects like a gradient glow
        RadialGradientPaint gradient = new RadialGradientPaint(
                width / 2f, height / 2f, Math.max(width, height) / 2f,
                new float[]{0f, 1f},
                new Color[]{new Color(255, 255, 255, 50), new Color(0, 0, 0, 200)}
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }

    public boolean isPressed(GameButton obj) {
        return mouseEventHandler.leftPressed && obj.intersects(mouseEventHandler.mouseX, mouseEventHandler.mouseY);

    }

}
