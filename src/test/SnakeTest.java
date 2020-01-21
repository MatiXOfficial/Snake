import functionality.Orientation;
import functionality.Snake;
import functionality.Vector2d;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class SnakeTest
{
    @Test
    public void snakeTest()
    {
        Orientation.setGenerator(new Random(1));
        Snake snake = new Snake(new Vector2d(2, 1));
        snake.rotateHeadLeft();
        snake.rotateHeadLeft();
        snake.moveForward();
        snake.eat();
        snake.rotateHeadRight();
        snake.moveForward();
        snake.moveForward();
        snake.eat();
        snake.rotateHeadLeft();
        snake.moveForward();
        snake.moveForward();
        snake.eat();
        snake.moveForward();
        snake.rotateHeadLeft();
        snake.moveForward();
        snake.moveForward();
        snake.eat();
        snake.rotateHeadLeft();
        snake.moveForward();

        Assert.assertEquals(new LinkedList<>(Arrays.asList(new Vector2d(4, 4), new Vector2d(4, 5), new Vector2d(3, 5), new Vector2d(2, 5), new Vector2d(2, 4))), snake.getOccupiedPositions());
        Assert.assertEquals(new Vector2d(2, 4), snake.getHeadPosition());
        Assert.assertEquals(5, snake.getLength());
        Assert.assertEquals(new Vector2d(4, 4), snake.getEndPosition());
        Assert.assertEquals(Orientation.DOWN, snake.getHeadOrientation());
    }
}
