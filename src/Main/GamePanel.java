package Main;

import Components.Sidepanel.SidePanel;
import Object.Domino.Domino;
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
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable {

    private int FPS = 60;
    private BufferedImage background;

    private int tileSize;

    public Dimension screenSize;

    DominoManager dominoManager;
    GameButton rollButton;
    SidePanel sidePanel;

    MouseEventHandler mouseEventHandler;

    SoundPlayer clonkSound = new SoundPlayer("/sounds/clonk.wav");

    //Events used in calculating score.
    ArrayList<Event> events = new ArrayList<>();

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
        sidePanel = new SidePanel(tileSize, (int) screenSize.getHeight());


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
    private boolean rollInProgress = false;
    public void update() {
        if(isPressed(rollButton) && canRoll && !Arrays.stream(dominoManager.getAllDominos()).allMatch(GameObject::getShakeEffect)) {
            rollInProgress = true;
            clonkSound.play();
            dominoManager.rollBoard();
            for(Domino d: dominoManager.getAllDominos()) {
                d.playShakeEffect();
            }
            canRoll = false;
            addBasicEventAllDominos();
        }

        dominoManager.updateDominos();
        sidePanel.update();

        if(rollInProgress) {
            calculateScore();
        }

    }

    /*Add all dominos to the events list with their sum value as score. Always happens first in all rolls*/
    private void addBasicEventAllDominos() {
        for(Domino d: dominoManager.getAllDominos()) {
            events.add(new Event(d, d.getSumValue(), 10));
        }
    }


    private int duration = 0;
    private void calculateScore() {
        /*
        * Events list is a list containing all events that should occur during score calculation.
        * Trigger one event at a time and apply playLiftUpEffect on the domino passed in each event.
        * When all events are triggered, set rollInProgress to false and canRoll to true.
        */

        //If any of the dominos are still shaking, wait until they are done.
        if(Arrays.stream(dominoManager.getAllDominos()).anyMatch(GameObject::getShakeEffect)) return;

        if(duration == 0) {
            if(events.size() == 0) {
                rollInProgress = false;
                canRoll = true;
                duration = 0;
                for(Domino d: dominoManager.getAllDominos()) {
                    d.playLiftDownEffect();
                }
                return;
            }
            Event e = events.get(0);
            e.domino.playLiftUpEffect();
            sidePanel.getScorePanel().addScore(events.get(0).score);
            events.remove(0);
            duration = e.duration;
        } else {
            duration--;
        }


    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Draw things
        g2d.drawImage(background, 0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight(), null); // Draw background
        dominoManager.draw(g2d); // Draw dominoes
        rollButton.draw(g2d); // Draw button
        sidePanel.draw(g2d); // Draw side panel

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
                new Color[]{new Color(255, 255, 255, 50), new Color(0, 0, 0, 140)}
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }

    public boolean isPressed(GameButton obj) {
        return mouseEventHandler.leftPressed && obj.intersects(mouseEventHandler.mouseX, mouseEventHandler.mouseY);
    }


    private class Event {
        private final Domino domino; //Domino effected by the event
        private final int score; //Score added by the event
        private final int duration; //Duration of the event

        public Event(Domino domino, int score, int duration) {
            this.domino = domino;
            this.score = score;
            this.duration = duration;
        }

    }

}
