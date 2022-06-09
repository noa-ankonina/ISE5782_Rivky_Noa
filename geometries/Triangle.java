
package geometries;

import primitives.Point;

/**
 * Triangle class represent a three-dimensional triangle- inherited from Polygon
 *
 * @author Noa & Rivky
 */
public class Triangle extends Polygon
   {
       /**
        * Creates a new triangle from a given vertices of the triangle.
        * @param p1 A point on the plane.
        * @param p2 A point on the plane.
        * @param p3 A point on the plane.
        * @exception IllegalArgumentException When two of the given vertices are equals.
        */
       public Triangle(Point p1,Point p2, Point p3) {
           super(p1,p2,p3);
       }

       @Override
       public String toString() {
           return "Triangle{" +
                   "vertices=" + vertices +
                   ", plane=" + plane +
                   '}';
       }
   }
