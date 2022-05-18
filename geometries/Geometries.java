package geometries;

import primitives.*;

import java.util.*;


public class Geometries implements Intersectable {
    public List<Intersectable> geometries;

    /**
     * Default constructor.
     * Creates an empty list of intersectables.
     */
    public Geometries()
    {
        //בחרתי דווקא ברשימה מקושרת מפני שכמעט ואין לנו מחיקה ולכן מבנה הנתונים הזה יעיל יותר
        geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {

        List<GeoPoint> intersections = null;

        for (Intersectable item : geometries) {

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
