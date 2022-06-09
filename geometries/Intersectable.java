package geometries;

import primitives.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Gives interface for an object that is instersectable.
 *
 * @author Noa & Rivky
 */
public interface Intersectable {

    /**
     * class of geometry's points
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructor with two parameters
         * @param geometry
         * @param point on the geometry
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
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }

    /**
     * @param ray intersection in geometries
     * @return list of intersectables the the ray intersecte in geometries
     */
    default List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }

    /**
     * @param ray from the camera
     * @return list of geometry's points
     */
    List<GeoPoint> findGeoIntersections (Ray ray);
}
