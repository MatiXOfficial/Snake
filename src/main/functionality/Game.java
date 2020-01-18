package functionality;

import visualization.GameFrame;

public class Game
{
    public static void main(String[] args)
    {
        SnakeGameMap gameMap = new SnakeGameMap(25);
        GameFrame gameFrame = new GameFrame(gameMap, 200);
    }
}
