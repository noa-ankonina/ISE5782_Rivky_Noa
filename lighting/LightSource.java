package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface of all the light
 *
 * @author Noa & Rivky
 */
public interface LightSource {

    /**
     * @param p point on the geometry
     * @return intensity color on that point
     */
    public Color getIntensity(Point p);

    /**
     * @param p point on the geometry
     * @return the vector between p and position point
     */
    public Vector getL(Point p);

    /**
     * @param point on the geometry
     * @return the distance between two points.
     */
    double getDistance(Point point);
}
