package functionality;

import java.util.LinkedList;

public class Snake
{
    private LinkedList<Vector2d> occupiedPositions; // first el. - end; last el. - head

    private Orientation headOrientation;
    private boolean moveEnd;

    public Snake(Vector2d position)
    {
        occupiedPositions = new LinkedList<>();
        occupiedPositions.add(position);

        this.headOrientation = Orientation.randomOrientation();
        this.moveEnd = true;
    }

    public LinkedList<Vector2d> getOccupiedPositions()
    {
        return this.occupiedPositions;
    }

    public Vector2d getHeadPosition()
    {
        return this.occupiedPositions.getLast();
    }

    public int getLength()
    {
        int length = this.occupiedPositions.size();
        if (!moveEnd)
            length++;
        return length;
    }

    public Vector2d getEndPosition()
    {
        return this.occupiedPositions.getFirst();
    }

    public Orientation getHeadOrientation()
    {
        return this.headOrientation;
    }

    public void rotateHeadRight()
    {
        this.headOrientation = this.headOrientation.rightRotate();
    }

    public void rotateHeadLeft()
    {
        this.headOrientation = this.headOrientation.leftRotate();
    }

    public void updateHeadPositionWithBoundaries(Vector2d bottomLeft, Vector2d topRight)
    {
        Vector2d headPosition = this.occupiedPositions.getLast();
        this.occupiedPositions.removeLast();
        this.occupiedPositions.add(headPosition.updateWithBoundaries(bottomLeft, topRight));
    }

    public void moveForward()
    {
        this.occupiedPositions.add(this.occupiedPositions.getLast().add(this.headOrientation.toVector()));
        if (moveEnd)
            this.occupiedPositions.removeFirst();
        else
            this.moveEnd = true;
    }

    public void eat()
    {
        this.moveEnd = false;
    }
}
