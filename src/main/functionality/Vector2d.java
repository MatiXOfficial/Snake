package functionality;

import java.util.Random;

public class Vector2d
{
    public final int x;
    public final int y;

    public Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) obj;
        return this.x == that.x && this.y == that.y;
    }

    public String toString()
    {
        return "(" + this.x + "," + this.y + ")";
    }

    public Vector2d add(Vector2d that)
    {
        return new Vector2d(this.x + that.x, this.y + that.y);
    }

    public boolean precedes(Vector2d that)
    {
        return (this.x <= that.x && this.y <= that.y);
    }

    public boolean follows(Vector2d that)
    {
        return (this.x >= that.x && this.y >= that.y);
    }

    public int hashCode()
    {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}
