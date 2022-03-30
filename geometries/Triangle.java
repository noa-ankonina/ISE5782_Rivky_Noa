
package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

public class Triangle extends Polygon
   {
      public  Triangle(Point p1, Point p2, Point p3)
       {
           super(p1, p2, p3);
       }

       public List<Point> findIntsersections(Ray ray)
       {
           return super.findIntersections(ray);
       }
       @Override
       public String toString() {
           return "Triangle{" +
                   "vertices=" + vertices +
                   ", plane=" + plane +
                   '}';
       }
   }