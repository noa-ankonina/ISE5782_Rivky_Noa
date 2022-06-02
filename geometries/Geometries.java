package geometries;

import primitives.*;
import java.util.*;

public class Geometries implements Intersectable {
    public List<Intersectable> geometriesLst;

    /**
     * Default constructor.
     * Creates an empty list of intersectables.
     */
    public Geometries()
    {
        geometriesLst = new LinkedList<>();
    }

    /**
     * Adds a list of given intersectables to the current list.
     *
     * @param intersectables List of intersectables to add
     */
    public void add(Intersectable... intersectables) {
        this.geometriesLst.addAll(Arrays.asList(intersectables));
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {

        List<GeoPoint> intersections = null;

        for (Intersectable item : geometriesLst) {

            // if there are elements in geoIntersections – add them to intersections
            List<GeoPoint> geoIntersections = item.findGeoIntersections(ray);

            if (geoIntersections != null) {

                if (intersections == null) {
                    intersections = new LinkedList<>();
                }

                intersections.addAll(geoIntersections);
            }
        }
        return intersections;
    }

}
