package geometries;

import primitives.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * מציאת נקודות חיתוך עם הגופים
 */
public abstract class  Intersectable
{
    /**
     *
     */
    public static class GeoPoint {
        public  final Geometry geometry;
        public  final Point point;

        /**
         *
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point point) {

            this.geometry = geometry;
            this.point=point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }

    /**
     * @param ray
     * @return
     */
    List<GeoPoint> findGeoIntersections (Ray ray){
      return findGeoIntersectionsHelper(ray);
    }

    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        return null;
    }
    /**
     * @param ray intersection in geometries
     * @return list of intersectables the the ray intersecte in geometries
     */
    /**
     *  מוצא את נקודות החיתוך של הקרן עם הגופים
     * @param ray
     * @return List<Point>
     */
    public List<Point> findIntersections(Ray ray){
        return null;
    }
}
