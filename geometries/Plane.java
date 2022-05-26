package geometries;

import primitives.*;
import java.util.List;
import static primitives.Util.isZero;

public class Plane extends Geometry
{
    private final Point q0;
    private final Vector normal;

    /**
     * Creates a new plane by a point on the plane and the plane's normal.
     * @param _q0 A point on the plane.
     * @param _normal The plane's normal.
     */
    public Plane(Point _q0, Vector _normal)
    {
        q0 = _q0;
        normal = _normal;
    }

    /**
     * Creates a new plane by three different points on the plane.
     * @param p1 A point on the plane.
     * @param p2 A point on the plane.
     * @param p3 A point on the plane.
     *@exception IllegalArgumentException When at least two of the given points are equals.
     */
    public Plane(Point p1, Point p2, Point p3)
    {
        if(p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("All points should be different");
        }
        q0 = p1;
        Vector v=p1.substract(p2);
        Vector u=p2.substract(p3);
        normal= v.crossProduct(u).normlize();
    }

    /**
     * Returns a point on the plane.
     * @return A shallow copy of the point.
     */
    public Point getq0()
    {
        return q0;
    }

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
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        //t=n*(q0-Po)/n*v
        Vector v= ray.getDir();
        Point p0=ray.getPoint();
        //Ray on the plane
        if(q0.equals(p0)){
            return null;
        }
        double nqp=normal.dotProduct(q0.substract(p0));
        //Ray on the plane
        if(isZero(nqp)){
            return null;
        }
        double nv= normal.dotProduct(v);
        if(isZero(nv)){
            return null;
        }
        double t=nqp/nv;
        //Ray after the plane
        if(t<0){
            return null;
        }
        Point P=ray.getPointBy(t);
        //Ray crosses the plane
        return List.of(new GeoPoint(this,P));
    }
}
