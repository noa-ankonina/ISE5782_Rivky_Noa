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
        Vector v = ray.getDir();
        Vector v0 = axisRay.getDir();

        // Calculating temp1 = v - v0 * (v,v0)
        Vector temp1 = v;
        double vv0 = v.dotProduct(v0);
        if (!isZero(vv0)) {
            Vector v0vv0 = v0.scale(vv0);
            if (v.equals(v0vv0)) {
                return null;
            }
            temp1 = v.substract(v0vv0);
        }

        // Calculating temp2 = dp - v0 * (dp,v0) where dp = p0 - p
        double temp1DotTemp2 = 0;
        double squaredTemp2 = 0;
        if (!ray.getPoint().equals(axisRay.getPoint())) {
            Vector dp = ray.getPoint().substract(axisRay.getPoint());
            Vector temp2 = dp;
            double dpv0 = dp.dotProduct(v0);
            if (isZero(dpv0)) {
                temp1DotTemp2 = temp1.dotProduct(temp2);
                squaredTemp2 = temp2.lengthSquared();
            }
            else {
                Vector v0dpv0 = v0.scale(dpv0);
                if (!dp.equals(v0dpv0)) {
                    temp2 = dp.substract(v0dpv0);
                    temp1DotTemp2 = temp1.dotProduct(temp2);
                    squaredTemp2 = temp2.lengthSquared();
                }
            }
        }

        // Getting the quadratic equation: at^2 +bt + c = 0
        double a = temp1.lengthSquared();
        double b = 2 * temp1DotTemp2;
        double c = alignZero(squaredTemp2 - radius * radius);

        double squaredDelta = alignZero(b * b - 4 * a * c);
        if (squaredDelta <= 0) {
            return null;
        }

        double delta = Math.sqrt(squaredDelta);
        double t1 = alignZero((-b + delta) / (2 * a));
        double t2 = alignZero((-b - delta) / (2 * a));

        if (t1 > 0 && t2 > 0 ) {
            return List.of(
                    new GeoPoint(this, ray.getPointBy(t1)),
                    new GeoPoint(this, ray.getPointBy(t2))
            );
        }
        if (t1 > 0 ) {
            return List.of(new GeoPoint(this, ray.getPointBy(t1)));
        }
        if (t2 > 0 ) {
            return List.of(new GeoPoint(this, ray.getPointBy(t2)));
        }

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
