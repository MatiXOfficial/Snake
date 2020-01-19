package visualization;

import functionality.OrientationChange;
import functionality.Snake;
import functionality.SnakeGameMap;
import functionality.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class GameFrame extends JFrame implements KeyListener
{
    private int width;
    private int height;
    private SnakeGameMap gameMap;
    private double mult;
    private int delay;

    private MapPanel mapPanel;
    private JLabel currentResult;

    private Timer timer;
    private OrientationChange orientationChange;

    public GameFrame(SnakeGameMap gameMap, int delay)
    {
        super("Wąż");
        setLocation(300, 90);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        this.gameMap = gameMap;
        this.width = 640;
        this.height = 700;
        this.mult = 600.0 / this.gameMap.getSize();
        this.delay = delay;
        this.orientationChange = OrientationChange.FORWARD;

        setSize(width, height);
        addKeyListener(this);

        mapPanel = new MapPanel(width - 40, height - 100, gameMap, mult);
        mapPanel.setSize(width - 40, height - 100);
        mapPanel.setLocation(10, 10);
        add(mapPanel);

        currentResult = new JLabel("Twój wynik: " + gameMap.getResult());
        currentResult.setFont(new Font("Arial", Font.PLAIN, 20));
        currentResult.setLocation(10, height - 80);
        currentResult.setSize(200, 30);
        add(currentResult);

        this.timer = new Timer(delay, e -> mapUpdate());
        this.timer.restart();

        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent evt)
    {
        int event = evt.getKeyCode();
        if (event == KeyEvent.VK_LEFT)
            this.orientationChange = OrientationChange.LEFT;
        else if (event == KeyEvent.VK_RIGHT)
            this.orientationChange = OrientationChange.RIGHT;
    }

    @Override
    public void keyReleased(KeyEvent evt)
    {
    }

    @Override
    public void keyTyped(KeyEvent evt)
    {
    }

    private void mapUpdate()
    {
        try
        {
            this.gameMap.moveSnake(this.orientationChange);
            this.orientationChange = OrientationChange.FORWARD;
            this.currentResult.setText("Twój wynik: " + gameMap.getResult());
            this.mapPanel.repaint();
        }
        catch(IllegalStateException ex)
        {
            GameOverFrame gameOverFrame = new GameOverFrame(this.gameMap.getResult());
            this.timer.stop();
            dispose();
        }
    }
}