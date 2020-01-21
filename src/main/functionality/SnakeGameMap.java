package functionality;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGameMap
{
    private int size;
    private Vector2d bottomLeft;
    private Vector2d topRight;

    private Snake snake;

    private HashSet<Vector2d> freePositions;
    private LinkedList<Vector2d> obstaclesPositions;
    private Vector2d foodPosition;
    private boolean wrapBoundaries;

    private static Random generator;

    private SnakeGameMap(int size, int obstaclesNumber, boolean wrapBoundaries, Random generator)
    {
        if (size <= 2)
            throw new IllegalArgumentException("Size has to be greater than 2!");
        if (obstaclesNumber > size * size - 2)
            throw new IllegalArgumentException("Too many obstacles!");
        if (obstaclesNumber < 0)
            throw new IllegalArgumentException("Number of obstacles can not be negative!");

        SnakeGameMap.generator = generator;
        Orientation.setGenerator(generator);

        this.size = size;
        this.bottomLeft = new Vector2d(0, 0);
        this.topRight = new Vector2d(size - 1, size - 1);
        this.wrapBoundaries = wrapBoundaries;

        this.freePositions = new HashSet<>();
        for (int x = bottomLeft.x; x <= topRight.x; x++)
            for (int y = bottomLeft.y; y <= topRight.y; y++)
                freePositions.add(new Vector2d(x, y));

        this.obstaclesPositions = new LinkedList<>();
        for (int i = 0; i < obstaclesNumber; i++)
            this.obstaclesPositions.add(getRandomFreePosition());

        Vector2d snakeVector = new Vector2d((size - 1) / 2, (size - 1) / 2);
        this.snake = new Snake(snakeVector);
        freePositions.remove(snakeVector);
        this.foodPosition = getRandomFreePosition();
    }

    public SnakeGameMap(int size, int obstaclesNumber, boolean wrapBoundaries)
    {
        this(size, obstaclesNumber, wrapBoundaries, new Random());
    }

    public LinkedList<Vector2d> getSnakeOccupiedPositions()
    {
        return snake.getOccupiedPositions();
    }

    public Vector2d getFoodPosition()
    {
        return this.foodPosition;
    }

    public LinkedList<Vector2d> getObstaclesPositions()
    {
        return this.obstaclesPositions;
    }

    public void moveSnake(OrientationChange orientationChange)
    {
        if (orientationChange == OrientationChange.LEFT)
            snake.rotateHeadLeft();
        else if (orientationChange == OrientationChange.RIGHT)
            snake.rotateHeadRight();
        Vector2d previousEndPosition = snake.getEndPosition();
        snake.moveForward();
        if (wrapBoundaries)
            snake.updateHeadPositionWithBoundaries(this.bottomLeft, this.topRight);
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
        return this.size;
    }

    private Vector2d getRandomFreePosition()
    {
        Vector2d position;
        do {
            int i = generator.nextInt(this.freePositions.size());
            position = (Vector2d) freePositions.toArray()[i];
        }
        while (position.equals(new Vector2d((this.size - 1) / 2, (this.size - 1) / 2)));
        this.freePositions.remove(position);
        return position;
    }

    public Orientation getSnakeOrientation()
    {
        return snake.getHeadOrientation();
    }
}
