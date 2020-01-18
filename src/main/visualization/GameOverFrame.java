package visualization;

import functionality.SnakeGameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverFrame extends JFrame implements ActionListener
{
    private int result;
    private int width = 310;
    private int height = 170;

    private JLabel gameOverLabel;
    private JLabel gameResult;
    private JButton againButton;
    private JButton exitButton;

    public GameOverFrame(int result)
    {
        super("Wąż");
        setLocation(600, 390);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(this.width, this.height);

        this.result = result;

        gameOverLabel = new JLabel("KONIEC");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gameOverLabel.setLocation(this.width / 2 - 70, 10);
        gameOverLabel.setSize(140, 30);
        add(gameOverLabel);

        gameResult = new JLabel("Wynik: " + this.result);
        gameResult.setFont(new Font("Arial", Font.PLAIN, 22));
        gameResult.setLocation(this.width / 2 - 50, 45);
        gameResult.setSize(100, 30);
        add(gameResult);

        againButton = new JButton("Graj ponownie");
        againButton.setFont(new Font("Arial", Font.PLAIN, 14));
        againButton.setLocation(10, 80);
        againButton.setSize(130, 40);
        againButton.addActionListener(this);
        add(againButton);

        exitButton = new JButton("Wyjście");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 14));
        exitButton.setLocation(this.width / 2, 80);
        exitButton.setSize(130, 40);
        exitButton.addActionListener(this);
        add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == againButton)
        {
            SnakeGameMap gameMap = new SnakeGameMap(20);
            GameFrame gameFrame = new GameFrame(gameMap, 150);
        }
        else if (source == exitButton)
        {
            System.exit(0);
        }
    }
}
