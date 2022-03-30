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
    /**
     *Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    public void findIntersections() {
        Plane plane = new Plane
                (
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0, 0, 1)
                );
        List<Point> result;

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane (1 points)
        result=plane.findIntersections(new Ray(new Point(0,1,1),new Vector(0,0,-1)));
        assertEquals(result.size(),1,"Wrong number of points");
        assertEquals(new Point(0,1,0),result.get(0).cutTwoNumbers(),
                "Ray intersects the plane");

        // TC02: Ray doesn't intersect the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point(0,1,1),new Vector(0,0,1))),"Ray doesn't intersect the plane");

        // =============== Boundary Values Tests ==================
        //**** Group: Ray is parallel to the plane
        //TC11: Ray is included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(0,0,1), new Vector(1,-1,0))),"Ray is included in the plane. Ray is parallel to the plane");

        //TC12: Ray isn't included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(0,0,2), new Vector(1,-1,0))),"Ray isn't included in the plane. Ray is parallel to the plane");

        //**** Group: Ray is orthogonal to the plane
        //TC13: Ray starts before the plane (1 points)
        result=plane.findIntersections(new Ray(new Point(-1,-1,-1),new Vector(1,1,1)));
        double n = 0.33;    //(1/3)

        assertEquals(result.size(),1,"Wrong number of points");
        assertEquals(new Point(n,n,n), result.get(0).cutTwoNumbers(),
                " Ray starts before the plane. Ray is orthogonal to the plane");

        //TC14: Ray starts inside the plane
        assertNull(plane.findIntersections(new Ray(new Point(0,0,1), new Vector(1,1,1))),
                "Ray starts inside the plane. Ray is orthogonal to the plane");

        //TC15: Ray starts after the plane
        assertNull(plane.findIntersections(new Ray(new Point(2,2,2),new Vector(1,1,1))),
                "Ray starts after the plane. Ray is orthogonal to the plane");

        //**** Group: Special case
        //TC16: Ray begins at the plane (p0 is in the plane, but not the ray)
        assertNull(plane.findIntersections(new Ray(new Point(1,0,0),new Vector(0,0,-1))),
                "Ray begins at the plane (p0 is in the plane, but not the ray)");

        //TC17: Ray begins in the plane's reference point
        assertNull(plane.findIntersections(new Ray(plane.getQ0(),new Vector(1,0,0))),
                "Ray begins in the plane's reference point");
    }
}