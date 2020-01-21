import functionality.Vector2d;
import org.junit.Assert;
import org.junit.Test;

public class Vector2dTest
{
    @Test
    public void testAdd()
    {
        Assert.assertEquals(new Vector2d(12, 7), new Vector2d(-2, 17).add(new Vector2d(14, -10)));
    }

    @Test public void testPrecedes()
    {
        Assert.assertTrue(new Vector2d(3, 4).precedes(new Vector2d(3, 4)));
        Assert.assertTrue(new Vector2d(2, 7).precedes(new Vector2d(8, 19)));
        Assert.assertFalse(new Vector2d(-5, 16).precedes(new Vector2d(-6, 17)));
        Assert.assertFalse(new Vector2d(2, 5).precedes(new Vector2d(1, 3)));
    }

    @Test public void testFollows()
    {
        Assert.assertTrue(new Vector2d(3, 4).follows(new Vector2d(3, 4)));
        Assert.assertFalse(new Vector2d(2, 7).follows(new Vector2d(8, 19)));
        Assert.assertFalse(new Vector2d(-5, 16).follows(new Vector2d(-6, 17)));
        Assert.assertTrue(new Vector2d(2, 5).follows(new Vector2d(1, 3)));
    }

    @Test
    public void testUpdateWithBoundaries()
    {
        Assert.assertEquals(new Vector2d(5, 6), new Vector2d(5, 6).updateWithBoundaries(new Vector2d(2, 2), new Vector2d(7, 8)));
        Assert.assertEquals(new Vector2d(2, 2), new Vector2d(13, 13).updateWithBoundaries(new Vector2d(2, 2), new Vector2d(12, 12)));
        Assert.assertEquals(new Vector2d(12, 12), new Vector2d(1, 1).updateWithBoundaries(new Vector2d(2, 2), new Vector2d(12, 12)));
        Assert.assertEquals(new Vector2d(2, 8), new Vector2d(8, 8).updateWithBoundaries(new Vector2d(2, 1), new Vector2d(7, 10)));
        Assert.assertEquals(new Vector2d(14, 3), new Vector2d(10, 3).updateWithBoundaries(new Vector2d(11, -1), new Vector2d(14, 5)));
        Assert.assertEquals(new Vector2d(2, -5), new Vector2d(2, -13).updateWithBoundaries(new Vector2d(0, -12), new Vector2d(16, -5)));
        Assert.assertEquals(new Vector2d(5, 10), new Vector2d(5, -1).updateWithBoundaries(new Vector2d(0, 0), new Vector2d(10, 10)));
    }
}