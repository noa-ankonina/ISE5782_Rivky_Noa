package primitives;

import java.util.List;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in Cartesian
 * ray system
 * @author Rivky Shachar and Noa Ankonina
 */
public class Ray {
    final Point p0;
    final Vector dir;

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

        double minDistance = Double.MAX_VALUE;
        double d;
        Point closePoint = null;

        if(points==null){
            return null;
        }

        for (Point p : points) {

            d = p.distance(p0);
            //check if the distance of p is smaller then minDistance
            if (d < minDistance) {
                minDistance = d;
                closePoint = p;
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
