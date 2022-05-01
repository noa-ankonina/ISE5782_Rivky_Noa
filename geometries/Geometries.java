package geometries;

import primitives.*;

import java.util.*;


public class Geometries extends Intersectable
{
    public List<Intersectable> lstIntersectable;

    /**
     * Default constructor.
     * Creates an empty list of intersectables.
     */
    public Geometries()
    {
        //בחרתי דווקא ברשימה מקושרת מפני שכמעט ואין לנו מחיקה ולכן מבנה הנתונים הזה יעיל יותר
        lstIntersectable = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(lstIntersectable, geometries);
    }


    @Override
    public List<Point> findIntersections(Ray ray) {return  null;}

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {

        List<GeoPoint> intersections = null;

        for (Intersectable item : lstIntersectable) {

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

    /**
     * @param ray
     * @param maxDistance
     * @return
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return null;
    }
}
