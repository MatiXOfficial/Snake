package functionality;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGameMap
{
    private Vector2d bottomLeft;
    private Vector2d topRight;

    private Snake snake;

    private HashSet<Vector2d> freePositions;
    private Vector2d foodPosition;

    private static Random generator;

    public SnakeGameMap(int size, Random generator)
    {
        if (size <= 2)
            throw new IllegalArgumentException("Size has to be greater than 2!");

        SnakeGameMap.generator = generator;
        Orientation.setGenerator(generator);

        this.bottomLeft = new Vector2d(0, 0);
        this.topRight = new Vector2d(size - 1, size - 1);

        this.freePositions = new HashSet<>();
        for (int x = bottomLeft.x; x <= topRight.x; x++)
            for (int y = bottomLeft.y; y <= topRight.y; y++)
                freePositions.add(new Vector2d(x, y));

        this.snake = new Snake(getRandomFreePosition());
        int which = generator.nextInt(2);
        while(!this.freePositions.contains(this.snake.getHeadPosition().add(this.snake.getHeadOrientation().toVector()).add(this.snake.getHeadOrientation().toVector())))
        {
            if (which == 0)
                this.snake.rotateHeadRight();
            else
                this.snake.rotateHeadLeft();
        }
        this.foodPosition = getRandomFreePosition();
    }

    public SnakeGameMap(int size)
    {
        this(size, new Random());
    }

    public LinkedList<Vector2d> getSnakeOccupiedPositions()
    {
        return snake.getOccupiedPositions();
    }

    public Vector2d getFoodPosition()
    {
        return this.foodPosition;
    }

    public void moveSnake(OrientationChange orientationChange)
    {
        if (orientationChange == OrientationChange.LEFT)
            snake.rotateHeadLeft();
        else if (orientationChange == OrientationChange.RIGHT)
            snake.rotateHeadRight();
        Vector2d previousEndPosition = snake.getEndPosition();
        snake.moveForward();
        if (snake.getHeadPosition().equals(foodPosition))
        {
            snake.eat();
            if (!previousEndPosition.equals(snake.getEndPosition()))
                this.freePositions.add(previousEndPosition);
            this.foodPosition = getRandomFreePosition();
        }
        else if (!freePositions.contains(snake.getHeadPosition()))
        {
            throw new IllegalStateException("Game Over!");
        }
        else
        {
            if (!previousEndPosition.equals(snake.getEndPosition()))
                this.freePositions.add(previousEndPosition);
            this.freePositions.remove(snake.getHeadPosition());
        }
    }

    public int getResult()
    {
        return this.snake.getLength();
    }

    public int getSize()
    {
        return topRight.x - bottomLeft.x + 1;
    }

    private Vector2d getRandomFreePosition()
    {
        int i = generator.nextInt(freePositions.size());
        Vector2d position = (Vector2d) freePositions.toArray()[i];
        freePositions.remove(position);
        return position;
    }

    public Orientation getSnakeOrientation()
    {
        return snake.getHeadOrientation();
    }
}
