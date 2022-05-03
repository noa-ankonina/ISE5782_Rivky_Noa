package primitives;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in Cartesian
 * ray system
 * @author Rivky Shachar and Noa Ankonina
 */
public class Ray {
    final Point p0;
    final Vector dir;
    private static final double DELTA = 0.1;

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normlize();
    }

    /**
     * Creates a new ray by point,vector direction and normal.
     * @param p0 head point of the ray
     * @param dir direction of the ray
     * @param normal normal of the ray
     */
    public Ray(Point p0, Vector dir, Vector normal) {
        this.dir=dir;
        // make sure the normal and the direction are not orthogonal
        double nv = alignZero(normal.dotProduct(dir));

        // if not orthogonal
        if (!isZero(nv)) {
            Vector moveVector = normal.scale(nv > 0 ? DELTA : -DELTA);
            // move the head of the vector in the right direction
            this.p0= (Point) p0.add(moveVector);
        }
        else
            this.p0=p0;
    }

    public Point getPoint() {
        return p0;
    }

    /**
     * Gets a point on the ray by calculating p0 + t*v.
     *
     * @param t A scalar to calculate the point.
     * @return A point on the ray.
     */
    public Point getPoint(double t)
    {
        try {
            Vector v= dir.scale(t);
            v.isZero();
            return (Point) p0.add(v);
        }
        catch (Exception exception) {
            return p0;
        }
    }

    /**
     * Gets a point on the ray by calculating p0 + t*v.
     *
     * @param t A scalar to calculate the point.
     * @return A point on the ray.
     */
    public Point getPointBy(double t)
    {
        try {
            Vector v= dir.scale(t);
            v.isZero();
            return (Point) p0.add(v);
        }
        catch (Exception exception) {
            return p0;
        }
    }

    /**
     * The function find the closest points to P0 of the ray
     * @param points
     * @return Point the closes point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    /**
     *
     * @param intersections
     * @return
     */
    public Intersectable.GeoPoint findClosestGeoPoint(List<Intersectable.GeoPoint> intersections){
        double minDistance = Double.MAX_VALUE;
        double d;
        Intersectable.GeoPoint closePoint = null;

        if(intersections==null){
            return null;
        }

        for (Intersectable.GeoPoint geoP : intersections) {

            d = geoP.point.distance(p0);
            //check if the distance of p is smaller then minDistance
            if (d < minDistance) {
                minDistance = d;
                closePoint = geoP;
            }
        }
        return closePoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o==null) return false;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "pOrigin=" + p0 +
                ",direction=" + dir +
                '}';
    }
}
