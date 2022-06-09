package primitives;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

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

    /**
     * this function generate beam of rays when radius is bigger our beam spread on
     * more area
     *
     * @param n         normal vector of the point where beam start
     * @param radius    radius of virtual circle
     * @param distance  Distance between The intersection point to the virtual
     *                  circle
     * @param numOfRays The number of the rays of the beam
     * @return beam of rays
     */
    public List<Ray> generateBeam(Vector n, double radius, double distance, int numOfRays) {
        List<Ray> rays = new LinkedList<Ray>();
        rays.add(this);// Including the main ray
        if (numOfRays == 1 || isZero(radius))// The component (glossy surface /diffuse glass) is turned off
            return rays;

        // the 2 vectors that create the virtual grid for the beam
        Vector nX = dir.createNormal();
        Vector nY = dir.crossProduct(nX);

        Point centerCircle = this.getPoint(distance);
        Point randomPoint;
        double x, y, d;
        double nv = alignZero(n.dotProduct(dir));

        // the number of rows and columns
        int nYX = (int) Math.sqrt(numOfRays);
        // the height and width of the virtual grid
        double r = (radius * 2) / nYX;
        // the radius for each circle for each point in the grid
        double rad = radius / nYX;

        // for each point in the grid get random point in circle around this point
        for (int i = 0; i < nYX; ++i) {
            for (int j = 0; j < nYX; ++j) {
                double yI = -((i - (nYX - 1) / 2) * r);
                double xJ = (j - (nYX - 1) / 2) * r;
                Point pIJ = centerCircle;
                if (xJ != 0)
                    pIJ = (Point) pIJ.add(nX.scale(xJ));
                if (yI != 0)
                    pIJ = (Point) pIJ.add(nY.scale(yI));
                x = random(-1, 1);
                y = Math.sqrt(1 - x * x);
                d = random(-rad, rad);
                x = alignZero(x * d);
                y = alignZero(y * d);
                randomPoint = pIJ;
                if (x != 0)
                    randomPoint = (Point) randomPoint.add(nX.scale(x));
                if (y != 0)
                    randomPoint = (Point) randomPoint.add(nY.scale(y));
                Vector tPoint = randomPoint.subtract(p0);
                double nt = alignZero(n.dotProduct(tPoint));
                if (nv * nt > 0) {
                    rays.add(new Ray(p0, tPoint));
                }
            }
        }
        return rays;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "pOrigin=" + p0 +
                ",direction=" + dir +
                '}';
    }
}
