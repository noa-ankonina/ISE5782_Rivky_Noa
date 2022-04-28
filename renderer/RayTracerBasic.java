package renderer;

import lighting.LightSource;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;

import java.util.List;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

/**
 *  RayTracerBasic class extends RayTracerBase and implement the abstract function traceRay
 */
public class RayTracerBasic extends RayTracerBase{

    /**
     * A builder
     * @param scene that the ray cross
     */
    public RayTracerBasic(Scene scene){
        super(scene);
    }


    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);

        if (points != null) {
            GeoPoint closePoint = ray.findClosestGeoPoint(points);
            return calcColor(closePoint, ray);
        }
        //no points
        return scene.background;
    }

    /**
     * Calculate the color intensity on the point
     * @param intersection on the geometry
     * @param ray from the camera
     * @return the color intensity
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission()).add(calcLocalEffects(intersection, ray));
// move Emission light into it as well
    }
    /**
     * Calculate the local component of the scene
     * @param point on the geometry
     * @param ray from the camera
     * @return the color intensity
     */
    private Color calcLocalEffects(GeoPoint point, Ray ray) {

        Vector v = ray.getDir();
        Vector n = point.geometry.getNormal(point.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = point.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD;
        double ks = material.kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(point.point);
            double nl = alignZero(n.dotProduct(l));
            double specularN=1;
            if(lightSource instanceof SpotLight)
            {
                specularN=((SpotLight) lightSource).getSpecularN();
            }
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(point.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n,nl, v, nShininess, lightIntensity,specularN));
            }
        }
        return color;
    }
    /**
     *
     * @param ks factor of the specular
     * @param l vector of the
     * @param n normal vector of the geometry point
     * @param nl vector n dot product vector l
     * @param v vector of ray direction
     * @param nShininess factor of the shining
     * @param lightIntensity color of the intensity
     * @param specularN specular component
     * @return calculate color
     */
    private Color calcSpecular(double ks, Vector l, Vector n,double nl, Vector v, int nShininess, Color lightIntensity,double specularN) {
        Vector r=l.substract(n.scale(nl*2));
        double vr=Math.pow(v.scale(-1).dotProduct(r),nShininess);
        return lightIntensity.scale(ks*Math.pow(vr,specularN));
    }

    /**
     * @param kd diffuse factor
     * @param l vector of the
     * @param n normal vector of the geometry point
     * @param lightIntensity color of the intensity
     * @return calculate color
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double ln=Math.abs(n.dotProduct(l));
        return lightIntensity.scale(kd*ln);
    }

}