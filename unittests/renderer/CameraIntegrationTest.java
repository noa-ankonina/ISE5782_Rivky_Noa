package unittests.renderer;

import org.junit.jupiter.api.Test;
import renderer.Camera;
import geometries.*;
import primitives.*;

/**
 * Integration tests for {@link Camera} class.
 */
public class CameraIntegrationTest {


    /**
     * Integration tests of a camera and a sphere.
     */
    @Test
    public void testSphereAndCamera() {
        Sphere[] spheres = new Sphere[]{
                //TC01: the sphere is in front of the view plane(2).
                new Sphere(new Point(0, 0, -2.5), 1),
                //TC02: the view plane is inside the sphere, all rays should intersect twice(18).
                new Sphere(new Point(0, 0, -2.5), 2.5),
                //TC03: the view plane cross the sphere (10).
                new Sphere(new Point(0, 0, -2), 2),
                //TC04: the camera is inside the sphere,all rays should intersect only once(9).
                new Sphere(new Point(0, 0, -1), 4),
                //TC05: the sphere is behind the camera , no ray should intersect(0).
                new Sphere(new Point(0, 0, 1), 0.5)
        };
    }

    /**
     * Integration tests of a camera and a plane.
     */
    @Test
    public void testPlaneAndCamera() {
        Plane[] planes = new Plane[]{
                //TC01: the plane is parallel with the view plane, all rays should intersect(9).
                new Plane(new Point(0, 0, -2), new Vector(0, 0, 1)),
                //TC02: the plane is in front of the view plane and cross, all rays should intersect(9).
                new Plane(new Point(0, 0, -1.5), new Vector(0, -0.5, 1)),
                //TC03: the plane is above the view plane's third row (6).
                new Plane(new Point(0, 0, -3), new Vector(0, -1, 1))
        };
    }

    /**
     * Integration tests of a camera and a triangle.
     */
    @Test
    public void testTriangleAndCamera() {
        Triangle[] triangles = new Triangle[]{
                //TC01:only the center ray should intersect(1).
                new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2)),
                //TC02: only the center ray and the top-middle ray should intersect(2).
                new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2))
        };
    }
}