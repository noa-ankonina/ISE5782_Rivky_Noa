package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Sphere extends Geometry {
    final Point center;
    final double radius;

    public Sphere(double radius, Point center )
    {
        this.center = center;
        this.radius = radius;
    }

    /**
     * creates a new sphere by a given center point and radius
     *
     * @param c The center point.
     * @param r The sphere's radius
     * @throws IllegalArgumentException When the radius smaller or equals 0..
     */
    public Sphere(Point c, double r)
    {
        if (r <= 0) {
            throw new IllegalArgumentException("The radius can't be  0 or less ");
        }
        this.center = c;
        this.radius = r;
    }


    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point)
    {
        Vector n=point.substract(center);
        return n.normlize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getPoint();
        Vector v = ray.getDir();

        if (p0.equals(center)) {
            return List.of(ray.getPointBy(radius));
        }

        Vector u = center.substract(p0);
        double tm =v.dotProduct(u);
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
            return List.of(ray.getPointBy(t1), ray.getPointBy(t2));
        }

        if (t1 > 0) {
            return List.of(ray.getPointBy(t1));
        }

        if (t2 > 0) {
            return List.of(ray.getPointBy(t2));
        }

        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        Point p0 = ray.getPoint();
        Vector v = ray.getDir();

        if (p0.equals(center)) {
            return List.of(new GeoPoint(this,ray.getPointBy(radius)));
        }

        Vector u = center.substract(p0);
        double tm =v.dotProduct(u);
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
            return List.of(new GeoPoint(this, ray.getPointBy(t1)),new GeoPoint(this,ray.getPointBy(t2)));
        }

        if (t1 > 0) {
            return List.of(new GeoPoint(this,ray.getPointBy(t1)));
        }

        if (t2 > 0) {
            return List.of(new GeoPoint(this,ray.getPointBy(t2)));
        }

        return null;
    }

    }

