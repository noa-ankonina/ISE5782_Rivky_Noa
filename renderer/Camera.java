package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.MissingResourceException;

import static primitives.Util.isZero;
import static primitives.Util.random;


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
    RayTracerBasic rayTracerBasic;

    private int numOfRays = 0; //num of rays in every pixel(default = 1)

    /**
     * The camera in the scene
     */
    Camera camera;

    /**
     * @param rayTracerBasic from the camera
     * @return this render
     */
    public Camera setRayTracer(RayTracerBasic rayTracerBasic) {
        this.rayTracerBasic = rayTracerBasic;
        return this;
    }

    /**
     * @param camera of the scene
     * @return this render
     */
    public Camera setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

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
     *
     * @param imageWriter
     * @return
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return  this;
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

    /**
     * Make the image from the elements
     */
    public void renderImage(){
        //check that all the parameters OK
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");}

            if (rayTracerBasic == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");}

            //Rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            LinkedList<Ray> rays;
            // pass through each pixel and calculate the color
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    rays=this.constructRayPixel(nX,nY,j,i);
                    imageWriter.writePixel(j,i,rayTracerBasic.AverageColor(rays));}}}

        catch (MissingResourceException e){
            throw new UnsupportedOperationException("Not implemented yet " + e.getClassName());}
    }

    /**
     * Adds a grid to the image.
     *
     * @param interval num of the grid's lines
     * @param color    the color of the grid's lines
     */
    public void printGrid(int interval, Color color) {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);}}}
    }

    //Turn on the function of the imageWriter writeToImage
    public void writeToImage(){

        if(imageWriter==null) {
            throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");}
        imageWriter.writeToImage();
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
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point pIJ=CalculatCenterPointInPixel(nX,nY,j,i);
        Vector vIJ=pIJ.substract(p0);

        return new Ray(p0,vIJ);
    }

    /**
     * Chaining method for setting the view plane's size.
     *
     * @param width  The new view plane's width.
     * @param height The new view plane's height.
     * @return The camera itself.
     */
    public Camera setViewPlaneSize(double width, double height) {
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
    public Camera setDistance(double distance) {
        if (distance <= 0) {
            throw new IllegalArgumentException("Illegal value of distance");}

        this.distance = distance;
        return this;
    }


    public LinkedList<Ray> constructRayPixel(int nX, int nY, int j, int i) {
        if (isZero(distance))
            throw new IllegalArgumentException("distance can't be 0");

        LinkedList<Ray> rays = new LinkedList<>();

        double rX = width / nX;
        double rY = height / nY;

        double  randX,randY;

        Point pCenterPixel = CalculatCenterPointInPixel(nX,nY,j,i);
        rays.add(new Ray(p0, pCenterPixel.substract(p0)));

        Point pInPixel;
        for (int k = 0; k < numOfRays; k++) {
            randX= random(-rX/2,rX/2);
            randY =  random(-rY/2,rY/2);
            pInPixel = new Point(pCenterPixel.getX()+randX,pCenterPixel.getY()+randY,pCenterPixel.getZ());
            rays.add(new Ray(p0, pInPixel.substract(p0)));}
        return rays;
    }

    /**
     *
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    private Point CalculatCenterPointInPixel(int nX, int nY, int j, int i) {
        Point pC = (Point) p0.add(vTo.scale(distance));
        Point pIJ=pC;

        double rY = height / nY;
        double rX = width / nX;

        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;

        if(!isZero(xJ)){
            pIJ = (Point) pIJ.add(vRight.scale(xJ));}
        if(!isZero(yI)){
            pIJ = (Point) pIJ.add(vUp.scale(yI));}
        return pIJ;
    }

    /**
     *
     * @param numOfRays
     * @return
     */
    public Camera setNumOfRays(int numOfRays) {
        this.numOfRays = numOfRays;
        return this;
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component coef
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     * @author Dan Zilberstein
     * The diffuse component is that dot product nâ€¢L that we discussed in class. It approximates light, originally
     * from light source L, reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossy
     * surface is paper. In general, you'll also want this to have a non-gray color value,
     * so this term would in general be a color defined as: [rd,gd,bd](nâ€¢L)
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0) {
            nl = -nl; }

        return ip.scale(nl * kd);
    }
}
