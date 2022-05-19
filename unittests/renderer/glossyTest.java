package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import renderer.Camera;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;


public class glossyTest {
    private Scene scene1 = new Scene("Test scene");
    private Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(150, 150) //
            .setDistance(1000);


    private static Geometry triangle1 = new Triangle( //
            new Point(-50, 0, -150), new Point(50, 0, -150), new Point(0, 30, -150))
            .setEmission(new Color(java.awt.Color.RED))
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0.3));
    private static Geometry polygon1 = new Polygon( //
            new Point(-50, 0, -150), new Point(50, 0, -150), new Point(50, -50, -150),new Point(-50, -50, -150))
            .setEmission(new Color(java.awt.Color.RED))
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkR(0.6));
    private static Geometry polygon2 = new Polygon( //
            new Point(-10, -30, -150), new Point(10,-30, -150), new Point(10, -50, -150),new Point(-10, -50, -150))
            .setEmission(new Color(java.awt.Color.GREEN))
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0.5));
    private static Geometry sphere = new Sphere(new Point(0, 0, -50), 20) //
            .setEmission(new Color(java.awt.Color.BLUE)) //
            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.7));

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void HomeDirectional() {
        scene1.geometries.add(triangle1,polygon1,polygon2,sphere);
        scene1.lights.add(new SpotLight(new Color(700, 400, 400), new Point(0, 0, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));
        scene1.setAmbientLight(new AmbientLight(new Color(java.awt.Color.green), 2));


        ImageWriter imageWriter = new ImageWriter("house", 500, 500);
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))//
                .setImageWriter(imageWriter) //
                .setCamera(camera1) //
                .setRayTracer(new RayTracerBasic(scene1));
        camera.renderImage();
        camera.writeToImage();
    }

    /**
     * Produce a picture of a sphere that reflected on glossy surface
     */
    @Test
    public void glossySurfaceTest() {
        Scene scene = new Scene("Test scene");
        Camera camera=new Camera(new Point(-1000, 0, 90), new Vector(1, 0, -0.09), new Vector(0.09, 0, 1))
                .setViewPlaneSize(150,150)
                .setDistance(1000);
        scene.setBackground(new Color(3, 3, 3));
        scene.setBackground(new Color(java.awt.Color.red));
        scene.setAmbientLight(new AmbientLight( new Color(java.awt.Color.black), 0));

        scene.geometries.add(new Plane(new Point(300, 0, 0), new Vector(1, 0, 0))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100))
                ,new Sphere(new Point(50, 0, 20), 20)
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Polygon(new Point(-700, -75, 0), new Point(900, -75, 0), new Point(900, 75, 0),
                        new Point(-700, 75, 0)).setEmission(new Color(10, 10, 10))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0).setkR(0.5).setkG(0.1)));

        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 0.1, 0)));

        ImageWriter imageWriter = new ImageWriter("power_s300B", 1000, 1000);
        Camera camera2 = new Camera((Point.ZERO), new Vector(0, 0, -1), new Vector(0, 1, 0))//
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        camera2.renderImage();
        camera2.writeToImage();
    }
}
