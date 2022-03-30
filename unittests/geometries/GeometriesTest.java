package unittests.geometries;

import geometries.Geometries;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntersections()
    {
        Geometries geo = new Geometries();

        //TC11: Empty list
        assertNull(  geo.findIntersections(new Ray(new Point(3, 1, 0.5), new Vector(1, 1, 0))),"list Empty");

        geo.lstIntersectable.add(new Triangle(new Point(-2, 0, 0), new Point(0, -4, 0), new Point(2, 0, 0)));
        geo.lstIntersectable.add(new Plane(new Point(0, 0, 6), new Point(-8, 0, 0),new Point(0, 6, 0)));
        geo.lstIntersectable.add(new Sphere(new Point(0, 0, 2), 1));

        //TC12: No shape cut
        assertEquals( null, geo.findIntersections(new Ray(new Point(-4,0 , 0), new Vector(-2, -4, 0))),"Ray not cut any shape");

        //TC13: One shape cut
        List<Point> l = geo.findIntersections(new Ray(new Point(-4, 0, 0), new Vector(-6, 6, 0)));
        assertEquals( 1, l.size(),"Ray cut One shape ");


        //TC14: All shapes cut
        l = geo.findIntersections(new Ray(new Point(0.05, -2.5, -1), new Vector(-0.05, 4.5, 5)));
        assertEquals( 3, l.size(),"Ray cut all shape");

        //TC15: more then 1 shapes cut but no all of them
        l = geo.findIntersections(new Ray(new Point(0, 0, 4), new Vector(0, -2, -5)));
        assertEquals( 2, l.size(),"Ray cut more then 1 shapes  but no all of them");
    }
}