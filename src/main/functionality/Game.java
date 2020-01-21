package functionality;

import visualization.SettingsFrame;
import java.io.FileNotFoundException;

public class Game
{
    public static void main(String[] args) throws FileNotFoundException
    {
        SnakeGameMap gameMap = new SnakeGameMap(20);
        SettingsFrame settingsFrame = new SettingsFrame();
    }
}
