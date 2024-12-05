package Components.Sidepanel;

import Object.GameObject;

import java.awt.*;

public class ScorePanel extends GameObject {

    private int width;
    private int height;

    private int score = 0;
    private int viewScore = 0;

    public ScorePanel(int width, int height, int tileSize ) {
        super(tileSize, tileSize, tileSize * 4, tileSize * 2);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void subtractScore(int score) {
        this.score -= score;
    }

    public void update() {
        if(viewScore < score) {
            viewScore += (score - viewScore) / 10 + 1;
        } else if(viewScore > score) {
            viewScore -= (viewScore - score) / 2;
        }
    }

    public void draw(Graphics2D g2) {
        // RGB 74, 84, 98, 255
        g2.setColor(new Color(74, 84, 98, 155));
        g2.fillRect(getX(), getY(), getWidth(), getHeight());

        //Draw score
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.BOLD, 100));
        //get width and height of the string
        FontMetrics metrics = g2.getFontMetrics();
        int stringWidth = metrics.stringWidth(String.valueOf(score));
        int stringHeight = metrics.getHeight();
        //Draw the string in the middle of the panel
        g2.drawString(String.valueOf(viewScore), getX() + (getWidth() - stringWidth) / 2, getY() + (getHeight() - stringHeight) / 2 + metrics.getAscent());
    }
}
