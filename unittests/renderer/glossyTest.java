package unittests.renderer;

import geometries.*;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import renderer.Camera;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;


public class glossyTest {
    @Test
    public void glossyTest() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point(-1000, 0, 90), new Vector(1, 0, -0.09), new Vector(0.09, 0, 1))
                .setViewPlaneSize(500, 500)
                .setDistance(1000);
        scene.setBackground(new Color(3, 3, 3));
        scene.setBackground(new Color(java.awt.Color.red));
        scene.setAmbientLight(Color.BLACK, 0);

        //Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
        //Color trCL = new Color(800, 500, 250); // Triangles test Color of Light

        scene.geometries.add(new Plane(new Point(300, 0, 0), new Vector(1, 0, 0))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Polygon(new Point(0, -15, 120),
                        new Point(0, -90, 120),
                        new Point(0, -90, -20),
                        new Point(0, -15, -20)).setEmission(new Color(10, 10, 10))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1)
                                .setShininess(10).setkT(0.9).setkR(0).setkB(0).setkG(0.1)),
                new Polygon(new Point(0, 15, 120),
                        new Point(0, 90, 120),
                        new Point(0, 90, -20),
                        new Point(0, 15, -20)).setEmission(new Color(10, 10, 10))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1)
                                .setShininess(10).setkT(0.9).setkR(0).setkB(0.5).setkG(0)),
                new Polygon(new Point(-1000, -150, 0),
                        new Point(1500, -150, 0),
                        new Point(1500, 150, 0),
                        new Point(-1000, 150, 0)).setEmission(new Color(10, 10, 10))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setShininess(100).setkT(0).setkR(0.5).setkB(0).setkG(0.1)),
                new Cylinder(new Ray(
                        new Point(90, -65, 0),
                        new Vector(0, 3, 10)),
                        30, 150)
                        .setEmission(new Color(100, 75, 0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(80)),
                new Sphere(new Point(50, 0, 20), 12)
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(new Point(50, 40, 20), 12)
                        .setEmission(new Color(java.awt.Color.red))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(new Point(50, -40, 20), 12)
                        .setEmission(new Color(java.awt.Color.YELLOW))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(new Point(50, -80, 20), 12)
                        .setEmission(new Color(java.awt.Color.green))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(new Point(50, -120, 20), 12)
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(new Point(50, 80, 20), 12)
                        .setEmission(new Color(java.awt.Color.ORANGE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
                new Sphere(new Point(50, 120, 20), 12)
                        .setEmission(new Color(java.awt.Color.MAGENTA))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));

        double i = 250, j = 0, k = 0;
        i = 200;
        j = 0;
        k = 0;
        scene.geometries.add( //
                new Polygon(new Point(10 + i, 0 + j, 0 + k),
                        new Point(115 + i, 45 + j, 0 + k),
                        new Point(70 + i, 150 + j, 0 + k),
                        new Point(-35 + i, 105 + j, 0 + k)).
                        setEmission(new Color(java.awt.Color.red))//front a,b,c,d
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(10 + i, 0 + j, 114.23659658795863 + k),
                        new Point(115 + i, 45 + j, 114.23659658795863 + k),
                        new Point(70 + i, 150 + j, 114.23659658795863 + k),
                        new Point(-35 + i, 105 + j, 114.23659658795863 + k)).
                        setEmission(new Color(java.awt.Color.green)) //back e,f,g,h
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(115 + i, 45 + j, 0 + k),
                        new Point(115 + i, 45 + j, 114.23659658795863 + k),
                        new Point(70 + i, 150 + j, 114.23659658795863 + k),
                        new Point(70 + i, 150 + j, 0 + k)).
                        setEmission(new Color(java.awt.Color.pink)) //right side b,f,g,c
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(10 + i, 0 + j, 0 + k),
                        new Point(10 + i, 0 + j, 114.23659658795863 + k),
                        new Point(-35 + i, 105 + j, 114.23659658795863 + k),
                        new Point(-35 + i, 105 + j, 0 + k)).
                        setEmission(new Color(java.awt.Color.blue)) //left side a,e,h,d
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(-35 + i, 105 + j, 0 + k),
                        new Point(70 + i, 150 + j, 0 + k),
                        new Point(70 + i, 150 + j, 114.23659658795863 + k),
                        new Point(-35 + i, 105 + j, 114.23659658795863 + k)).
                        setEmission(new Color(java.awt.Color.yellow)) //top d,c,g,h
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(10 + i, 0 + j, 0 + k),
                        new Point(115 + i, 45 + j, 0 + k),
                        new Point(115 + i, 45 + j, 114.23659658795863 + k),
                        new Point(10 + i, 0 + j, 114.23659658795863 + k)).
                        setEmission(new Color(java.awt.Color.orange)) //bottom a,b,f,e
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3))
        );

        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 0.1, 0)));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.white), new Point(200, 0, 20), new Vector(0, 0, -1))
                .setKl(0.0000001).setKq(0.0000001));

            ImageWriter imageWriter = new ImageWriter("MP1", 300, 300);
            camera.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene).setGlossinessRays(20))
                    .renderImage();
            camera.writeToImage();
        }
}
