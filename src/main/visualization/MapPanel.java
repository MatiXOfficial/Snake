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

    public MapPanel(int width, int height, SnakeGameMap gameMap, double mult)
    {
        this.width = width;
        this.height = height;
        this.gameMap = gameMap;
        this.mult = mult;
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
