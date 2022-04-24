package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {


    @Test
    void testTestGetNormal()
    {
        Plane plane = new Plane
                (
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0, 0, 1)
                );
        double n = Math.sqrt(1d / 3);

        //TC01: There is a simple single test here
        assertEquals(new Vector(n, n, n), plane.getNormal(new Point(0, 0, 1)),
                "Bad normal to plane");
    }
}