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
    private int height = 200;

    private JLabel settingsTitleLabel;

    private JLabel sizeLabel;
    private JTextField sizeTextField;

    private JLabel delayLabel;
    private JTextField delayTextField;

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
        sizeLabel.setSize(80, 30);
        add(sizeLabel);

        sizeTextField = new JTextField(SettingsParser.getSettings().get("size").toString());
        sizeTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        sizeTextField.setLocation(90, 42);
        sizeTextField.setSize(50, 25);
        add(sizeTextField);

        delayLabel = new JLabel("Opóźnienie:");
        delayLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        delayLabel.setLocation(10, 70);
        delayLabel.setSize(80, 30);
        add(delayLabel);

        delayTextField = new JTextField(SettingsParser.getSettings().get("delay").toString());
        delayTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        delayTextField.setLocation(90, 72);
        delayTextField.setSize(50, 25);
        add(delayTextField);

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
                if (Integer.valueOf(sizeTextField.getText()) < 2)
                    throw new IllegalArgumentException("Rozmiar nie może być mniejszy od 2!");
                if (Integer.valueOf(delayTextField.getText()) <= 0)
                    throw new IllegalArgumentException("Opóźnienie musi być większe od 0!");
                settingsDict.put("size", Integer.valueOf(sizeTextField.getText()));
                settingsDict.put("delay", Integer.valueOf(delayTextField.getText()));
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
                SnakeGameMap snakeGameMap = new SnakeGameMap(settingsDict.get("size"));
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
