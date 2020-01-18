package visualization;

import functionality.OrientationChange;
import functionality.Snake;
import functionality.SnakeGameMap;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener
{
    private int width;
    private int height;
    private SnakeGameMap gameMap;
    private double mult;
    private int delay;

    private MapPanel mapPanel;

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
        this.height = 680;
        this.mult = 600 / this.gameMap.getSize();
        this.delay = delay;
        this.orientationChange = OrientationChange.FORWARD;

        setSize(width, height);
        addKeyListener(this);

        mapPanel = new MapPanel(width - 40, height - 80, gameMap, mult);
        mapPanel.setSize(width - 40, height - 80);
        mapPanel.setLocation(10, 10);
        add(mapPanel);

        this.timer = new Timer(delay, e -> mapUpdate());
        this.timer.restart();

        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent evt)
    {
        int event = evt.getKeyCode();
        if (event == KeyEvent.VK_LEFT)
            this.orientationChange = orientationChange.LEFT;
        else if (event == KeyEvent.VK_RIGHT)
            this.orientationChange = orientationChange.RIGHT;
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
        this.gameMap.moveSnake(this.orientationChange);
        this.orientationChange = OrientationChange.FORWARD;
        this.mapPanel.repaint();
    }
}