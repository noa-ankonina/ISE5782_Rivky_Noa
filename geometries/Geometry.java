package geometries;

import primitives.Point;
import primitives.Vector;

/**
 *
 */
public interface Geometry extends Intersectable
{
    /**
     *
     * @param point
     * @return
     */
    Vector getNormal(Point point);

}
