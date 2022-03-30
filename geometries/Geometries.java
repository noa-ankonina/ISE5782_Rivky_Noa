package geometries;

import primitives.*;

import java.util.*;


public class Geometries implements Intersectable
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
    public List<Point> findIntersections(Ray ray)
    {
        List<Point> result = null;
        for (Intersectable item : lstIntersectable) {
            //get intersections points of a particular item from _intersectables
            List<Point> itempoints = item.findIntersections(ray);
            if(itempoints!= null){
                //first time initialize result to new LinkedList
                if(result== null){
                    result= new LinkedList<>();
                }
                //add all item points to the resulting list
                result.addAll(itempoints);
            }
        }

        return result;
    }
}
