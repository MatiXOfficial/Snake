package visualization;

import functionality.SnakeGameMap;
import functionality.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MapPanel extends JPanel
{
    private SnakeGameMap gameMap;
    private int width;
    private int height;
    private double mult;
    private double eyeMult;

    public MapPanel(int width, int height, SnakeGameMap gameMap, double mult)
    {
        this.width = width;
        this.height = height;
        this.gameMap = gameMap;
        this.mult = mult;
        this.eyeMult = mult / 6;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintChessboard(g2d);
        paintSnake(g2d);
        paintFood(g2d);
    }

    private void paintChessboard(Graphics2D g2d)
    {
        for (double x = 0; x < width; x += mult)
        {
            for (double y = 0; y < height; y += mult)
            {
                if ((x + y) / mult % 2 == 0)
                    g2d.setPaint(new Color(204, 204, 204));
                else
                    g2d.setPaint(new Color(179, 179, 179));
                g2d.fill(new Rectangle2D.Double(x, y, mult, mult));
            }
        }
    }

    private void paintSnake(Graphics2D g2d)
    {
        g2d.setPaint(new Color(77, 136, 255));
        for (int i = 0; i < gameMap.getSnakeOccupiedPositions().size() - 1; i++)
        {
            Vector2d vec = gameMap.getSnakeOccupiedPositions().get(i);
            double x = vec.x * mult;
            double y = height - (vec.y + 1) * mult;
            g2d.fill(new Rectangle2D.Double(x, y, mult, mult));
        }
        g2d.setPaint(new Color(0, 85, 255));
        Vector2d vec = gameMap.getSnakeOccupiedPositions().getLast();
        double x = vec.x * mult;
        double y = height - (vec.y + 1) * mult;
        g2d.fill(new Rectangle2D.Double(x, y, mult, mult));
        paintSnakeEyes(g2d, x, y);
    }

    private void paintSnakeEyes(Graphics2D g2d, double x0, double y0)
    {
        g2d.setPaint(new Color(255, 255, 255));
        double x1, x2, y1, y2;
        switch(gameMap.getSnakeOrientation())
        {
            case UP:
                x1 = x0 + eyeMult;
                x2 = x0 + mult - 2*eyeMult;
                y1 = y0;
                y2 = y0;
                break;
            case RIGHT:
                x1 = x0 + mult - eyeMult;
                x2 = x0 + mult - eyeMult;
                y1 = y0 + eyeMult;
                y2 = y0 + mult - 2*eyeMult;
                break;
            case DOWN:
                x1 = x0 + eyeMult;
                x2 = x0 + mult - 2*eyeMult;
                y1 = y0 + mult - eyeMult;
                y2 = y0 + mult - eyeMult;
                break;
            case LEFT:
                x1 = x0;
                x2 = x0;
                y1 = y0 + eyeMult;
                y2 = y0 + mult - 2*eyeMult;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + gameMap.getSnakeOrientation());
        }
        g2d.fill(new Rectangle2D.Double(x1, y1, eyeMult, eyeMult));
        g2d.fill(new Rectangle2D.Double(x2, y2, eyeMult, eyeMult));
    }

    private void paintFood(Graphics2D g2d)
    {
        g2d.setPaint(new Color(0, 204, 0));
        Vector2d vec = gameMap.getFoodPosition();
        double x = vec.x * mult;
        double y = height - (vec.y + 1) * mult;
        g2d.fill(new Ellipse2D.Double(x, y, mult, mult));
    }
}
