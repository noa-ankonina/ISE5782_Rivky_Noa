package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {



    @Test
    void testSubstract()
    {
        // ============ Equivalence Partitions Tests ==============
        Point p0 = new Point(1, 3, -2);
        Point p1 = new Point(-4, 2, -7);

        assertEquals(new Vector(5, 1, 5), p0.substract(p1),
                "Subtract() wrong result of sub");
    }

}