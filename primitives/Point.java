package primitives;

/**
 * Class Point is the basic class representing a point of Euclidean geometry in Cartesian
 * point system
 * @author Rivky Shachar and Noa Ankonina
 */
public class Point
{
    public static final Point ZERO = new Point(0,0,0);
    protected final Double3 xyz;

    /**
     * Creates a new point by  double3.
     *
     * @param _xyz
     */
    public Point(Double3 _xyz) {xyz=_xyz;}

    /**
     * Creates a new point by three point.
     *
     * @param x .
     * @param y .
     * @param z .
     */
    public Point(double x, double y, double z) {xyz = new Double3(x,y,z);}

    /**
     *
     * @return x
     */
    public Double getX() { return xyz.d1; }

    /**
     *
     * @return y
     */
    public Double getY() { return xyz.d2; }

    /**
     *
     * @return z
     */
    public Double getZ() { return xyz.d3; }

    /**
     * Add a vector to a vector
     * @param v vector to add to the point 3D of the class
     * @return a new point
     */
    public Object add(Vector v)
    {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * return a vector between 2 points
     */
    public Vector subtract(Point p)
    {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * Distance Squared between the point "other" to the point  of the class
     * @param other point 3D
     * @return double  number of the distant Squared
     */
    public double distanceSquared(Point other)
    {
        final double x1 = xyz.d1;
        final double y1 = xyz.d2;
        final double z1 = xyz.d3;
        final double x2 = other.xyz.d1;
        final double y2 = other.xyz.d2;
        final double z2 = other.xyz.d3;

        return ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1));
    }

    /**
     * Distance between 2 points
     * this func use the func that calculate the Distance Squared
     * @param point point
     * @return  double  number of the distant
     */
    public double distance(Point point)
    {
        return (Math.sqrt( distanceSquared( point))) ;
    }

    @Override
    public String toString()
    {
        return "(" + xyz.d1 + "," + xyz.d2 + "," + xyz.d3+ ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o==null) return false;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    /**
     * @return the coordinates of the point are accurate up to 2 digits after the decimal point
     */
    public Point cutTwoNumbers() {
        return new Point(Util.cutTwoNumber(xyz.d1), Util.cutTwoNumber(xyz.d2), Util.cutTwoNumber(xyz.d3));
    }

    /**
     * check if the point is zero
     */
    public boolean isZero() {
        if (this.equals(Point.ZERO))
            throw new IllegalArgumentException("the vector is the zero vector!!!");
        else return false;
    }
}
