package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry
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
