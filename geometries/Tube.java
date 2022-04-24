package geometries;

import  primitives.Ray;
import primitives.Vector;
import primitives.Point;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


public class Tube extends Geometry
{
    final Ray axisRay;
    final double radius;

    /**
     * constructor who get Ray and number of radius
     *  @param axisRay
     * @param radius

     */
    public Tube(Ray axisRay, double radius)
    {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point)
    {
        Vector v = point.substract(axisRay.getP0());
        double t = axisRay.getDir().dotProduct(v);//finding scaler for the projection of point on axisRay
        Point O = (Point) axisRay.getP0().add(axisRay.getDir().scale(t));
        Vector N = point.substract(O);
        return N.normlize();
    }

    @Override
    public List<Point> findIntersections(Ray ray)
    {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }

        @Override
        public String toString ()
        {
            return "Tube{" +
                    "axisRay=" + axisRay +
                    ", radius=" + radius +
                    '}';
        }
}
