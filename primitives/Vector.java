package primitives;
import primitives.Util;
import primitives.Point;
/**
 * Class Vector is the basic class representing a vector of Euclidean geometry in Cartesian
 * vactor system
 * @author Rivky Shachar and Noa Ankonina
 */

public class Vector extends Point {

    /**
     * constructor- gets 3 numbers and creates a Vector
     *
     * @param x the coordinate x
     * @param y the coordinate y
     * @param z the coordinate z
     */
    public Vector(double x, double y, double z) {
            this(new Double3(x, y, z));
    }

    /**
     * Constructor with parameter double 3
     * @param xyz point
     */
    public Vector(Double3 xyz){
        super(xyz);
        if(xyz.equals(Double3.ZERO))
        {
            throw  new IllegalArgumentException("Vector(0,0,0) is not allowed");
        }
    }

    /**
     *return the length squared of the vector
     */
    public double lengthSquared()
    {
        double u1 = xyz.d1;
        double u2 = xyz.d2;
        double u3 = xyz.d3;
        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    /**
     *return a new vector that add 2 vectors
     */
    public Vector add(Vector v)
    {
        double v1 = v.xyz.d1+this.xyz.d1;
        double v2 = v.xyz.d2+this.xyz.d2;
        double v3 = v.xyz.d3+this.xyz.d3;
        return  new Vector(v1,v2,v3);
    }

    /**
     * Vector product in number - Scalar (returns new vector)
     * @param scalar number to multiplier
     * @return vector
     */
    public Vector scale(double scalar) {
        return new Vector(xyz.scale(scalar));
    }

    /**
     * Vector multiplier
     * @param v A vector.
     * @return the cross product between 2 vectors
     * @exception IllegalArgumentException When parallel vectors.
     */
    public Vector crossProduct(Vector v)
    {
        double ax = xyz.d1;
        double ay = xyz.d2;
        double az = xyz.d3;
        double bx = v.xyz.d1;
        double by = v.xyz.d2;
        double bz = v.xyz.d3;

        double cx = ay * bz - az * by;
        double  cy = az * bx - ax * bz;
        double cz = ax * by - ay * bx;
        return  new Vector(cx, cy, cz);
    }

    /**
     *return the dot product between 2 vectors
     */
    public double dotProduct(Vector v)
    {
        double u1 = this.xyz.d1;
        double u2 = this.xyz.d2;
        double u3 = this.xyz.d3;

        double v1 = v.xyz.d1;
        double v2 = v.xyz.d2;
        double v3 = v.xyz.d3;

        return (u1 * v1 + u2 * v2 + u3 * v3);


    }

    /**
     *return the normalize vector
     */
    public Vector normlize()
    {
        double len = this.length();
        if (len == 0)  //cannot divide by 0
            throw new ArithmeticException("divide by Zero");
        return  new Vector(xyz.reduce(len));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     *return true if zero and false if not.
     */
    public boolean isZero(Vector v){
       return( Util.isZero(v.xyz.d1)& Util.isZero(v.xyz.d2)&Util.isZero(v.xyz.d3));

    }

    /**
     * Rotates the vector around the x axis
     * @param alpha the amount to rotate in degrees
     * @return the current vector
     */
    public Vector rotateX(double alpha) {
        double radianAlpha = alpha * Math.PI / 180;

        double x = getX();
        double y = getY() * Math.cos(radianAlpha) - getZ() * Math.sin(radianAlpha);
        double z = getY() * Math.sin(radianAlpha) + getZ() * Math.cos(radianAlpha);
        return this;
    }


    /**
     * Rotates the vector around the y axis
     * @param alpha the amount to rotate in degrees
     * @return the current vector
     */
    public Vector rotateY(double alpha) {
        double radianAlpha = alpha * Math.PI / 180;

        double x = getX() * Math.cos(radianAlpha) + getZ() * Math.sin(radianAlpha);
        double y = getY();
        double z = -getX() * Math.sin(radianAlpha) + getZ() * Math.cos(radianAlpha);
        return this;
    }

    /**
     * Rotates the vector around the z axis
     * @param alpha the amount to rotate in degrees
     * @return the current vector
     */
    public Vector rotateZ(double alpha) {
        double radianAlpha = alpha * Math.PI / 180;

        double x = getX() * Math.cos(radianAlpha) - getY() * Math.sin(radianAlpha);
        double y = getX() * Math.sin(radianAlpha) + getY() * Math.cos(radianAlpha);
        double z = getZ();
        return this;
    }

    /**
     * This function return a normal Vector to "this" vector
     * @return normalized normal vector
     */
    public Vector createNormal() {
        int min = 1;
        double x = this.getX(), y = this.getY(), z = this.getZ();
        double minCoor = x > 0 ? x : -x;
        if (y < minCoor ||-y < minCoor) {//(Math.abs(y) < minCoor) {
            minCoor = y > 0 ? y : -y;
            min = 2;
        }
        if (z < minCoor ||-z < minCoor) {///Math.abs(z) < minCoor) {
            min = 3;
        }
        switch (min) {
            case 1: {
                return new Vector(0, -z, y).normlize();
            }
            case 2: {
                return new Vector(-z, 0, x).normlize();
            }
            case 3: {
                return new Vector(y, -x, 0).normlize();
            }
            default:
                throw new IllegalArgumentException("Unexpected value: " + min);
        }
    }
}
