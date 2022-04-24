package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Cylinder extends Tube
{
    double height;

    public Cylinder(Ray axisRay, double radius,double height)
    {
        super(axisRay, radius);
        this.height=height;
    }

    public double getHeight() { return height;}

    @Override
    public Vector getNormal(Point point)
    {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        // projection of P-po on the ray:
        double t;
        try {
            t = alignZero(point.substract(p0).dotProduct(v));//אורך בין p0 למרכז חושב: תחילת הקרן פחות המרכז והכפלנו עם הוקטור כוון V
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        p0 = (Point) p0.add(v.scale(t));
        return point.substract(p0).normlize();
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
