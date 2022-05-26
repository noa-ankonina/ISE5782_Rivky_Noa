package unittests.finalproject;
import geometries.Plane;
import lighting.PointLight;
import scene.Scene;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    private Scene scene = new Scene("Test scene");


    @Test
    public void Bonus() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(600, 450).setDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.white), 0.15));

        scene.geometries.add( //
                new Polygon(new Point(1, 0, 0),
                        new Point(120, 0, 0),
                        new Point(120, 10, 1),
                        new Point(1, 10, 1)).
                        setEmission(new Color(java.awt.Color.blue)) //front a,b,c,d
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(1, 0, 40),
                        new Point(120, 0, 40),
                        new Point(120, 10, 41),
                        new Point(1, 10, 41)).
                        setEmission(new Color(java.awt.Color.blue)) //back e,f,g,h
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(120, 0, 0),
                        new Point(120, 0, 40),
                        new Point(120, 10, 41),
                        new Point(120, 10, 1)).
                        setEmission(new Color(java.awt.Color.blue)) //right side b,f,g,c
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(1, 0, 0),
                        new Point(1, 0, 40),
                        new Point(1, 10, 41),
                        new Point(1, 10, 1)).
                        setEmission(new Color(java.awt.Color.blue)) //left side a,e,h,d
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(1, 10, 1),
                        new Point(120, 10, 1),
                        new Point(120, 10, 41),
                        new Point(1, 10, 41)).
                        setEmission(new Color(java.awt.Color.blue)) //top d,c,g,h
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(1, 0, 0),
                        new Point(120, 0, 0),
                        new Point(120, 0, 40),
                        new Point(1, 0, 40)).
                        setEmission(new Color(java.awt.Color.blue)) //bottom a,b,f,e
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),


                new Polygon(new Point(-9, 15, 0),
                        new Point(110, 15, 0),
                        new Point(110, 25, 1),
                        new Point(-9, 25, 1)).
                        setEmission(new Color(java.awt.Color.blue)) //next
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),

                new Plane(new Point(120,0,0),new Point(120,0,40),new Point(120,10,41)).
                        setEmission(new Color(java.awt.Color.red)) //right side b,f,g,c
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6))

        );
        scene.lights.add(new SpotLight(new Color(700, 400, 400),
                new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKq(2E-7)); //.setKq(0.000005));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400),
                new Point(-750, -750, -150), new Vector(-1, -1, -4)));
        scene.lights.add(new PointLight(new Color(700,400,400),new Point(-200,50,100)));
        int frames = 16;
        double angle = 360d / frames;
        double angleRadians = 0.5 * Math.PI / frames;

        double radius = camera.getP0().substract(Point.ZERO).length();

        for (int i = 0; i < frames; i++) {
            System.out.println("Start frame " + (i + 1));

            camera.rotate(0, angle, 0);
            camera.setP0(
                    Math.sin(angleRadians * (i + 1)) * radius,
                    0,
                    Math.cos(angleRadians * (i + 1)) * radius
            );


            ImageWriter imageWriter = new ImageWriter("Project" + (i + 1), 600, 450);
            camera.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene)) //
                    .renderImage(); //
            camera.writeToImage();


        }
    }

    @Test
    public void Bonus2() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(600, 450).setDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.white), 0.15));
        double i=250,j=0,k=0;
        scene.geometries.add( //
                new Polygon(new Point(10+i,0+j,0+k),
                        new Point(115+i,45+j,0+k),
                        new Point(70+i,150+j,0+k),
                        new Point(-35+i,105+j,0+k)).
                        setEmission(new Color(java.awt.Color.red))//front a,b,c,d
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(10+i, 0+j, 114.23659658795863+k),
                        new Point(115+i, 45+j, 114.23659658795863+k),
                        new Point(70+i, 150+j, 114.23659658795863+k),
                        new Point(-35+i, 105+j, 114.23659658795863+k)).
                        setEmission(new Color(java.awt.Color.green)) //back e,f,g,h
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(115+i,45+j,0+k),
                        new Point(115+i, 45+j, 114.23659658795863+k),
                        new Point(70+i, 150+j, 114.23659658795863+k),
                        new Point(70+i,150+j,0+k)).
                        setEmission(new Color(java.awt.Color.pink)) //right side b,f,g,c
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(10+i,0+j,0+k),
                        new Point(10+i, 0+j, 114.23659658795863+k),
                        new Point(-35+i, 105+j, 114.23659658795863+k),
                        new Point(-35+i,105+j,0+k)).
                        setEmission(new Color(java.awt.Color.blue)) //left side a,e,h,d
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(-35+i,105+j,0+k),
                        new Point(70+i,150+j,0+k),
                        new Point(70+i, 150+j, 114.23659658795863+k),
                        new Point(-35+i, 105+j, 114.23659658795863+k)).
                        setEmission(new Color(java.awt.Color.yellow)) //top d,c,g,h
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(10+i,0+j, 0+k),
                        new Point(115+i,45+j,0+k),
                        new Point(115+i, 45+j, 114.23659658795863+k),
                        new Point(10+i, 0+j, 114.23659658795863+k)).
                        setEmission(new Color(java.awt.Color.orange)) //bottom a,b,f,e
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6))




        );
        i=0;
        scene.geometries.add( //
                new Polygon(new Point(10+i,0+j,0+k),
                        new Point(115+i,45+j,0+k),
                        new Point(70+i,150+j,0+k),
                        new Point(-35+i,105+j,0+k)).
                        setEmission(new Color(java.awt.Color.red))//front a,b,c,d
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(10+i, 0+j, 114.23659658795863+k),
                        new Point(115+i, 45+j, 114.23659658795863+k),
                        new Point(70+i, 150+j, 114.23659658795863+k),
                        new Point(-35+i, 105+j, 114.23659658795863+k)).
                        setEmission(new Color(java.awt.Color.green)) //back e,f,g,h
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(115+i,45+j,0+k),
                        new Point(115+i, 45+j, 114.23659658795863+k),
                        new Point(70+i, 150+j, 114.23659658795863+k),
                        new Point(70+i,150+j,0+k)).
                        setEmission(new Color(java.awt.Color.pink)) //right side b,f,g,c
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(10+i,0+j,0+k),
                        new Point(10+i, 0+j, 114.23659658795863+k),
                        new Point(-35+i, 105+j, 114.23659658795863+k),
                        new Point(-35+i,105+j,0+k)).
                        setEmission(new Color(java.awt.Color.blue)) //left side a,e,h,d
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(-35+i,105+j,0+k),
                        new Point(70+i,150+j,0+k),
                        new Point(70+i, 150+j, 114.23659658795863+k),
                        new Point(-35+i, 105+j, 114.23659658795863+k)).
                        setEmission(new Color(java.awt.Color.yellow)) //top d,c,g,h
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(10+i,0+j, 0+k),
                        new Point(115+i,45+j,0+k),
                        new Point(115+i, 45+j, 114.23659658795863+k),
                        new Point(10+i, 0+j, 114.23659658795863+k)).
                        setEmission(new Color(java.awt.Color.orange)) //bottom a,b,f,e
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6))
        );

        i=17;
        j=-100;
        k=40;
        scene.geometries.add( //
                new Polygon(new Point(10+i,0+j,0+k),
                        new Point(115+i,45+j,0+k),
                        new Point(70+i,150+j,0+k),
                        new Point(-35+i,105+j,0+k)).
                        setEmission(new Color(java.awt.Color.red))//front a,b,c,d
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(10+i, 0+j, 114.23659658795863+k),
                        new Point(115+i, 45+j, 114.23659658795863+k),
                        new Point(70+i, 150+j, 114.23659658795863+k),
                        new Point(-35+i, 105+j, 114.23659658795863+k)).
                        setEmission(new Color(java.awt.Color.green)) //back e,f,g,h
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(115+i,45+j,0+k),
                        new Point(115+i, 45+j, 114.23659658795863+k),
                        new Point(70+i, 150+j, 114.23659658795863+k),
                        new Point(70+i,150+j,0+k)).
                        setEmission(new Color(java.awt.Color.pink)) //right side b,f,g,c
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(10+i,0+j,0+k),
                        new Point(10+i, 0+j, 114.23659658795863+k),
                        new Point(-35+i, 105+j, 114.23659658795863+k),
                        new Point(-35+i,105+j,0+k)).
                        setEmission(new Color(java.awt.Color.blue)) //left side a,e,h,d
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(-35+i,105+j,0+k),
                        new Point(70+i,150+j,0+k),
                        new Point(70+i, 150+j, 114.23659658795863+k),
                        new Point(-35+i, 105+j, 114.23659658795863+k)).
                        setEmission(new Color(java.awt.Color.yellow)) //top d,c,g,h
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6)),

                new Polygon(new Point(10+i,0+j, 0+k),
                        new Point(115+i,45+j,0+k),
                        new Point(115+i, 45+j, 114.23659658795863+k),
                        new Point(10+i, 0+j, 114.23659658795863+k)).
                        setEmission(new Color(java.awt.Color.orange)) //bottom a,b,f,e
                        .setMaterial(new Material().setKd(1).setKs(0.2).setShininess(30).setkT(0.6))




        );



        scene.lights.add(new SpotLight(new Color(700, 400, 400),
                new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKq(2E-7)); //.setKq(0.000005));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400),
                new Point(-750, -750, -150), new Vector(-1, -1, -4)));
        scene.lights.add(new PointLight(new Color(700,400,400),new Point(-200,50,100)));
        scene.lights.add(new SpotLight(new Color(700, 400, 400),
                new Point(-400, 50, -200), new Vector(500, -100, 220)) //
                .setKq(2E-7)); //.setKq(0.000005));
        int frames = 16;
        double angle = 360d / frames;
        double angleRadians = 0.5 * Math.PI / frames;

        double radius = camera.getP0().substract(Point.ZERO).length();

        for (int m = 0; m < frames; m++) {
            System.out.println("Start frame " + (m + 1));

            camera.rotate(0, angle, 0);
            camera.setP0(
                    Math.sin(angleRadians * (m + 1)) * radius,
                    0,
                    Math.cos(angleRadians * (m + 1)) * radius
            );


            ImageWriter imageWriter = new ImageWriter("Project2" + (m + 1), 600, 450);
            camera.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene)) //
                    .renderImage(); //
            camera.writeToImage();


        }
    }
}