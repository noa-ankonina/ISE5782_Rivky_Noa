package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 *
 */
public abstract class  Geometry extends Intersectable
{
    /**
     * The emission light at first is black
     */
    protected Color emission =Color.BLACK;

    Material material;
    /**
     * Returns the emission
     * @return return A shallow copy of the emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     *
     * @param emission set the emission
     * @return the geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     *
     * @param point
     * @return
     */
    abstract public Vector getNormal(Point point);

}
