package visualization;

import javax.swing.*;
import java.awt.*;

public class ErrorFrame extends JFrame
{
    private int width = 300;
    private int height = 80;

    private JLabel errorLabel;

    public ErrorFrame(String errorMessage)
    {
        super("Wąż");
        setLocation(700, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(this.width, this.height);

        errorLabel = new JLabel("Błąd: " + errorMessage);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        errorLabel.setLocation(5, 5);
        errorLabel.setSize(290, 30);
        add(errorLabel);

        setVisible(true);
    }
}
