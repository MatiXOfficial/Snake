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
}