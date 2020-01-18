package functionality;

import java.util.Random;

public enum Orientation
{
    UP, RIGHT, DOWN, LEFT;

    private static Orientation[] values = values();
    private static Vector2d[] vectors = {new Vector2d(0, 1), new Vector2d(1, 0), new Vector2d(0, -1), new Vector2d(-1, 0)};
    private static Random generator = new Random();

    public static void setGenerator(Random generator)
    {
        Orientation.generator = generator;
    }

    public Orientation rightRotate()
    {
        return values[(this.ordinal() + 1) % values.length];
    }

    public Orientation leftRotate()
    {
        return values[(this.ordinal() + 3) % values.length];
    }

    public Vector2d toVector()
    {
        return vectors[this.ordinal()];
    }

    public static Orientation randomOrientation()
    {
        return values[generator.nextInt(4)];
    }
}
