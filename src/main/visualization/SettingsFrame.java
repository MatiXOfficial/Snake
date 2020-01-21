package visualization;

import functionality.SnakeGameMap;
import settings.SettingsParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class SettingsFrame extends JFrame implements ActionListener
{
    private int width = 315;
    private int height = 250;

    private JLabel settingsTitleLabel;

    private JLabel sizeLabel;
    private JTextField sizeTextField;

    private JLabel delayLabel;
    private JTextField delayTextField;

    private JLabel obstaclesLabel;
    private JTextField obstaclesTextField;

    private JLabel wrapBoundariesLabel;
    private JCheckBox wrapBoundariesCheckBox;

    private JButton saveButton;
    private JButton defaultsButton;
    private JButton playButton;

    public SettingsFrame() throws FileNotFoundException
    {
        super("Wąż");
        setLocation(600, 390);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(this.width, this.height);

        settingsTitleLabel = new JLabel("USTAWIENIA");
        settingsTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        settingsTitleLabel.setLocation(this.width / 2 - 70, 5);
        settingsTitleLabel.setSize(140, 30);
        add(settingsTitleLabel);

        sizeLabel = new JLabel("Rozmiar:");
        sizeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        sizeLabel.setLocation(10, 40);
        sizeLabel.setSize(120, 30);
        add(sizeLabel);

        sizeTextField = new JTextField(SettingsParser.getSettings().get("size").toString());
        sizeTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        sizeTextField.setLocation(130, 42);
        sizeTextField.setSize(50, 25);
        add(sizeTextField);

        delayLabel = new JLabel("Opóźnienie:");
        delayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        delayLabel.setLocation(10, 70);
        delayLabel.setSize(120, 30);
        add(delayLabel);

        delayTextField = new JTextField(SettingsParser.getSettings().get("delay").toString());
        delayTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        delayTextField.setLocation(130, 72);
        delayTextField.setSize(50, 25);
        add(delayTextField);

        obstaclesLabel = new JLabel("Liczba przeszkód:");
        obstaclesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        obstaclesLabel.setLocation(10, 100);
        obstaclesLabel.setSize(120, 30);
        add(obstaclesLabel);

        obstaclesTextField = new JTextField(SettingsParser.getSettings().get("obstacles").toString());
        obstaclesTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        obstaclesTextField.setLocation(130, 102);
        obstaclesTextField.setSize(50, 25);
        add(obstaclesTextField);

        wrapBoundariesLabel = new JLabel("Zawijanie krawędzi:");
        wrapBoundariesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        wrapBoundariesLabel.setLocation(10, 130);
        wrapBoundariesLabel.setSize(120, 30);
        add(wrapBoundariesLabel);

        wrapBoundariesCheckBox = new JCheckBox("", SettingsParser.getSettings().get("wrapBoundaries") == 1);
        wrapBoundariesCheckBox.setBounds(142, 132, 25, 25);
        add(wrapBoundariesCheckBox);

        saveButton = new JButton("Zapisz");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 12));
        saveButton.setLocation(10, this.height - 75);
        saveButton.setSize(90, 30);
        saveButton.addActionListener(this);
        add(saveButton);

        defaultsButton = new JButton("Domyślne");
        defaultsButton.setFont(new Font("Arial", Font.PLAIN, 12));
        defaultsButton.setLocation(105, this.height - 75);
        defaultsButton.setSize(90, 30);
        defaultsButton.addActionListener(this);
        add(defaultsButton);

        playButton = new JButton("Graj");
        playButton.setFont(new Font("Arial", Font.PLAIN, 12));
        playButton.setLocation(200, this.height - 75);
        playButton.setSize(90, 30);
        playButton.addActionListener(this);
        add(playButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == saveButton)
        {
            HashMap<String, Integer> settingsDict = new HashMap<>();
            try
            {
                if (Integer.parseInt(sizeTextField.getText()) <= 2)
                    throw new IllegalArgumentException("Rozmiar musi być większy od 2!");
                if (Integer.parseInt(delayTextField.getText()) <= 0)
                    throw new IllegalArgumentException("Opóźnienie musi być większe od 0!");
                if (Integer.parseInt(obstaclesTextField.getText()) < 0)
                    throw new IllegalArgumentException("Liczba przeszkód nie może być ujemna!");
                if (Integer.parseInt(obstaclesTextField.getText()) > Integer.parseInt(sizeTextField.getText()) * Integer.parseInt(sizeTextField.getText()) - 2)
                    throw new IllegalArgumentException("Za dużo przeszkód!");
                settingsDict.put("size", Integer.valueOf(sizeTextField.getText()));
                settingsDict.put("delay", Integer.valueOf(delayTextField.getText()));
                settingsDict.put("obstacles", Integer.valueOf(obstaclesTextField.getText()));
                settingsDict.put("wrapBoundaries", wrapBoundariesCheckBox.isSelected() ? 1 : 0);
                try
                {
                    SettingsParser.saveSettings(settingsDict);
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            catch (NumberFormatException ex)
            {
                ErrorFrame errorFrame = new ErrorFrame("Podano złe wartości!");
            }
            catch (IllegalArgumentException ex)
            {
                ErrorFrame errorFrame = new ErrorFrame(ex.getMessage());
            }
        }
        else if (source == defaultsButton)
        {
            HashMap<String, Integer> defaultsDict;
            try
            {
                defaultsDict = SettingsParser.getDefaults();
                sizeTextField.setText(defaultsDict.get("size").toString());
                delayTextField.setText(defaultsDict.get("delay").toString());
                obstaclesTextField.setText(defaultsDict.get("obstacles").toString());
                wrapBoundariesCheckBox.setSelected(defaultsDict.get("wrapBoundaries") == 1);
            }
            catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
        }
        else if (source == playButton)
        {
            try
            {
                var settingsDict = SettingsParser.getSettings();
                SnakeGameMap snakeGameMap = new SnakeGameMap(settingsDict.get("size"), settingsDict.get("obstacles"), settingsDict.get("wrapBoundaries") == 1);
                GameFrame gameFrame = new GameFrame(snakeGameMap, settingsDict.get("delay"));
                dispose();
            }
            catch (FileNotFoundException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
