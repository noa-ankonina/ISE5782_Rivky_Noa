package geometries;

import primitives.*;
import java.util.List;

/**
 * מציאת נקודות חיתוך עם הגופים
 */
public interface Intersectable
{
    /**
     *  מוצא את נקודות החיתוך של הקרן עם הגופים
     * @param ray
     * @return List<Point>
     */
    public List<Point> findIntersections(Ray ray);
}
