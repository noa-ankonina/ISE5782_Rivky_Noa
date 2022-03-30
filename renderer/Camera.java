package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;


public class Camera {
    /**
     * Camera's location.
     */
    private Point p0;
    /**
     * Camera's upper direction.
     */
    private Vector vUp;
    /**
     * Camera's forward direction.
     */
    private Vector vTo;
    /**
     * Camera's right direction
     */
    private Vector vRight;
    /**
     * View plane's width.
     */
    private double width;
    /**
     * View plane's height.
     */
    private double height;
    /**
     * The distance between the camera and the view plane.
     */
    private double distance;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;
    /**
     * Constructs a camera with location, to and up vectors.
     * The right vector is being calculated by the to and up vectors.
     *
     * @param p0  The camera's location.
     * @param vTo The direction to where the camera is looking.
     * @param vUp The direction of the camera's upper direction.
     * @throws IllegalArgumentException When to and up vectors aren't orthogonal.
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("Up vector is not Orthogonal with To vector");
        }
        this.p0 = p0;
        this.vTo = vTo.normlize();
        this.vUp = vUp.normlize();
        vRight = vTo.crossProduct(vUp);
    }

    /**
     * Returns the camera location.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the camera's forward direction.
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * Returns the camera's upper direction.
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * Returns the camera's right direction.
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * Returns the view plane's width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the view plane's height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the distance between the camera and the view plane.
     */
    public double getDistance() {
        return distance;
    }
    
    /**
     * Chaining method for setting the view plane's size.
     *
     * @param width  The new view plane's width.
     * @param height The new view plane's height.
     * @return The camera itself.
     */
    public Camera setVPSize(double width, double height) {
        setWidth(width);
        setHeight(height);
        return this;
    }

    /**
     * Chaining method for setting the distance between the camera and the view plane.
     *
     * @param distance The new distance between the camera and the view plane.
     * @return The camera itself.
     * @throws IllegalArgumentException When distance illegal.
     */
    public Camera setVPDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Illegal value of distance");
        }
        this.distance = distance;
        return this;
    }
    /**
     * set 3 double number of the point
     */
    public Camera setP0(double x, double y, double z) {
        this.p0 = new Point(x, y, z);
        return this;

    }
    /**
     * Set the new view plane's width.
     *
     * @throws IllegalArgumentException When width illegal.
     */
    public Camera setWidth(double width) {
        if (width <= 0) {

            throw new IllegalArgumentException("Illegal value of width");
        }
        this.width = width;
        return this;
    }

    /**
     * Set the new view plane's height.
     *
     * @throws IllegalArgumentException When height illegal.
     */
    public Camera setHeight(double height) {
        if (height <= 0) {

            throw new IllegalArgumentException("Illegal value of width");
        }
        this.height = height;
        return this;
    }


    /**
     * Constructs a ray through a given pixel on the view plane.
     *
     * @param nX Total number of pixels in the x dimension.
     * @param nY Total number of pixels in the y dimension.
     * @param j  The index of the pixel on the x dimension.
     * @param i  The index of the pixel on the y dimension.
     * @return A ray going through the given pixel.
     */
    public Ray constructRay(int nX, int nY, int j, int i)
    {
        Point pIJ = CalculateCenterPointInPixel(nX, nY, j, i);
        Vector vIJ = pIJ.substract(p0);
        return new Ray(p0, vIJ);
    }

    /**
     * The function calculate the center point of the pixel.
     *
     * @param nX Total number of pixels in the x dimension.
     * @param nY Total number of pixels in the y dimension.
     * @param j  The index of the pixel on the x dimension.
     * @param i  The index of the pixel on the y dimension.
     * @return the center point in the pixel.
     */
    private Point CalculateCenterPointInPixel(int nX, int nY, int j, int i) {
        Point pC = (Point) p0.add(vTo.scale(distance));
        Point pIJ = pC;

        double rY = height / nY;
        double rX = width / nX;

        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;

        if (!isZero(xJ)) {
            pIJ = (Point) pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = (Point) pIJ.add(vUp.scale(yI));
        }
        return pIJ;
    }
    /**
     * Adds the given amount to the camera's position
     *
     * @return the current camera
     */
    public Camera move(Vector amount) {
        p0 = (Point) p0.add(amount);
        return this;
    }
    /**
     * Adds x, y, z to the camera's position
     *
     * @return the current camera
     */
    public Camera move(double x, double y, double z) {
        return move(new Vector(x, y, z));
    }
    /**
     * Rotates the camera around the axes with the given angles
     *
     * @param amount vector of angles
     * @return the current camera
     */
    public Camera rotate(Vector amount) {
        return rotate(amount.getX(), amount.getY(), amount.getZ());
    }

    /**
     * Rotates the camera around the axes with the given angles
     *
     * @param x angles to rotate around the x axis
     * @param y angles to rotate around the y axis
     * @param z angles to rotate around the z axis
     * @return the current camera
     */
    public Camera rotate(double x, double y, double z) {
        vTo.rotateX(x).rotateY(y).rotateZ(z);
        vUp.rotateX(x).rotateY(y).rotateZ(z);
        vRight = vTo.crossProduct(vUp);

        return this;
    }
 public void renderImage(){
     if(p0 == null)
         throw new MissingResourceException("po is null","camera","");

     if(vUp== null)
         throw new MissingResourceException("vUp is null","camera","");

     if(vTo== null)
         throw new MissingResourceException("vTo is null","camera","");

     if(vRight== null)
         throw new MissingResourceException("vRight is null","camera","");

     if(width == 0)
         throw new MissingResourceException("width is 0","camera","");

     if(height== 0)
         throw new MissingResourceException("height is 0","camera","");

     if(distance== 0)
         throw new MissingResourceException("distance is 0","camera","");

     if(rayTracerBase== null)
         throw new MissingResourceException("rayTracerBase is null","camera","");

    // throw new UnsupportedOperationException();
 }
}
