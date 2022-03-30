package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements Geometry
{
    private final Point q0;
    private final Vector normal;


    public Plane(Point _q0, Vector _normal)
    {
        q0 = _q0;
        normal = _normal;
    }

    public Plane(Point p1, Point p2, Point p3)
    {
        q0 = p1;

        Vector v=p1.substract(p2);
        Vector u=p2.substract(p3);

        normal= v.crossProduct(u).normlize();
    }

    public Point getQ0() { return q0;}

    /**
     * Returns the plane's normal.
     * @return A shallow copy of the plane's normal.
     */
    public Vector getNormal()
    {
        return normal;
    }


    @Override
    public Vector getNormal(Point p)
    {
        return normal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return q0.equals(plane.q0) && normal.equals(plane.normal);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector n = normal;

        if (q0.equals(P0)) {
            return null;
        }

        Vector P0_Q0 = q0.substract(P0);

        //numerator
        double nP0Q0 = alignZero(n.dotProduct(P0_Q0));

        //
        if (isZero(nP0Q0)) {
            return null;
        }

        //denominator
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }

        double t = alignZero(nP0Q0 / nv);

        if (t <= 0) {
            return null;
        }

        Point point = ray.getPoint(t);

        return List.of(point);
    }
}