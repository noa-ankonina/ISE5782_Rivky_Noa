package unittests.finalproject;
import geometries.Polygon;
import geometries.Sphere;
import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;
import geometries.*;
import primitives.*;

class ProjectTest {
    public void glossyTest() {
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point(-1000, 0, 90), new Vector(1, 0, -0.09), new Vector(0.09, 0, 1))
                .setViewPlaneSize(500, 500)
                .setDistance(1000);
        scene.setBackground(new Color(3, 3, 3));
        scene.setBackground(new Color(java.awt.Color.red));
        scene.setAmbientLight(Color.BLACK, 0);

        Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
        Color trCL = new Color(800, 500, 250); // Triangles test Color of Light


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

        ImageWriter imageWriter = new ImageWriter("MP1", 1000, 1000);
        camera.setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage();
        camera.writeToImage();
    }
    @Test
    public void project() {
        Camera camera = new Camera(
                new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                .setViewPlaneSize(200, 125)
                .setDistance(800)
                .setNumOfRays(70);

        Scene scene = new Scene("Test Scene");
        setLights(scene);
        setGeometries(scene);

                camera.setCamera(camera) //
               // .setMultithreading(3)
                .setRayTracer(new RayTracerBasic(scene).setGlossinessRays(20));

       // int frames = 16;
        //double angle = 360d / frames;
        //double angleRadians = 2 * Math.PI / frames;

        double radius = camera.getP0().subtract(Point.ZERO).length();
          /*
        for (int i = 0; i < frames; i++) {
            System.out.println("Start frame " + (i + 1));

            camera.rotate(0, angle, 0);
            camera.setP0(
                    Math.sin(angleRadians * (i + 1)) * radius,
                    0,
                    Math.cos(angleRadians * (i + 1)) * radius
            );
            */

            //ImageWriter imageWriter = new ImageWriter("MP1", 1000, 1000);
            ImageWriter imageWriter = new ImageWriter("MP2", 600, 450);
            camera.setImageWriter(imageWriter);
            camera.renderImage();
            camera.writeToImage();
       // }
    }

    private void setLights(Scene scene){
        scene.lights.add(
                new SpotLight(
                        new Color(400, 400, 400),
                        new Point(-50, 100, 100),
                        new Vector(-0.5, -1, -0.5))
                        .setKl(0.004)
                        .setKq(0.000006)
        );
        scene.lights.add(new SpotLight(new Color(0,250,350),new Point(-200, 100, 0), new Vector(1, 1, -2)).setSpecularN(40) //
                .setKl(0.00000005).setKq(0.000000005));
        scene.lights.add(new SpotLight(new Color(0,250,350),new Point(-200, 50, 0), new Vector(1, 0.5, -2)).setSpecularN(20) //
                .setKl(0.00000005).setKq(0.000000005));
        scene.lights.add(new SpotLight(new Color(0,250,350),new Point(-200, 55, 0), new Vector(1, 1, -2)).setSpecularN(10) //
                .setKl(0.00000005).setKq(0.000000005));
    }

    private void setGeometries(Scene scene) {
        //triangles
        Point h=new Point(60,-50,30);
        Point g=new Point(45,-30,0);
        Point a=new Point(30,-50,30);
        Point b=new Point(40,0,15);

        scene.geometries.add(
                //sphere
                new Sphere(new Point(80, -28, 0), 22)
                        .setEmission(new Color(java.awt.Color.pink))
                        .setMaterial(new Material()
                                .setkR(0.8)),
                new Sphere(new Point(-45, -45, -5), 5)
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material()
                                .setkR(0.8).setkG(0.95)),

                //triangles
                new Triangle(a,g,h)
                        .setEmission(new Color(java.awt.Color.YELLOW))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkT(0.6)
                                .setShininess(80)),
                new Triangle(a,b,h)
                        .setEmission(new Color(java.awt.Color.YELLOW))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkT(0.6)
                                .setShininess(80)),
                new Triangle(a,b,g)
                        .setEmission(new Color(java.awt.Color.YELLOW))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkT(0.6)
                                .setShininess(80)),
                new Triangle(g,b,h)
                        .setEmission(new Color(java.awt.Color.YELLOW))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkT(0.6)
                                .setShininess(80)),
                //cylinder
                new Cylinder(new Ray(
                        new Point(-80, -45, 0),
                        new Vector(60, 85, 0)),
                        13, 50)
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(50))
                ,

                //square
                //1
                new Polygon(new Point(-25,-50,-30),
                        new Point(-25,-50,30),
                        new Point(15,-50,30),
                        new Point(15,-50,-30))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(50)),
                new Polygon(new Point(-25,-25,-30),
                        new Point(-25,-25,30),
                        new Point(15,-25,30),
                        new Point(15,-25,-30))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(50)),
                new Polygon(new Point(-25,-50,-30),
                        new Point(-25,-50,30),
                        new Point(-25,-25,30),
                        new Point(-25,-25,-30))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(50)),
                new Polygon(new Point(15,-50,-30),
                        new Point(15,-50,30),
                        new Point(15,-25,30),
                        new Point(15,-25,-30))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(50)),
                new Polygon(new Point(-25,-50,30),
                        new Point(15,-50,30),
                        new Point(15,-25,30),
                        new Point(-25,-25,30))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(50)),
                new Polygon(new Point(-25,-50,-30),
                        new Point(15,-50,-30),
                        new Point(15,-25,-30),
                        new Point(-25,-25,-30))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(50)),
                //2
                new Polygon(new Point(-15,-25,-20),
                        new Point(-15,-25,20),
                        new Point(5,-25,20),
                        new Point(5,-25,-20))
                        .setEmission(new Color(java.awt.Color.MAGENTA))
                        .setMaterial(new Material()
                                .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                .setShininess(60)),
                new Polygon(new Point(-15,-15,-20),
                        new Point(-15,-15,20),
                        new Point(5,-15,20),
                        new Point(5,-15,-20))
                        .setEmission(new Color(java.awt.Color.MAGENTA))
                        .setMaterial(new Material()
                                .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                .setShininess(60)),
                new Polygon(new Point(-15,-25,-20),
                        new Point(-15,-25,20),
                        new Point(-15,-15,20),
                        new Point(-15,-15,-20))
                        .setEmission(new Color(java.awt.Color.MAGENTA))
                        .setMaterial(new Material()
                                .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                .setShininess(60)),
                new Polygon(new Point(5,-25,-20),
                        new Point(5,-25,20),
                        new Point(5,-15,20),
                        new Point(5,-15,-20))
                        .setEmission(new Color(java.awt.Color.MAGENTA))
                        .setMaterial(new Material()
                                .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                .setShininess(60)),
                new Polygon(new Point(-15,-25,20),
                        new Point(5,-25,20),
                        new Point(5,-15,20),
                        new Point(-15,-15,20))
                        .setEmission(new Color(java.awt.Color.MAGENTA))
                        .setMaterial(new Material()
                                .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                .setShininess(30)),
                new Polygon(new Point(-15,-25,-20),
                        new Point(5,-25,-20),
                        new Point(5,-15,-20),
                        new Point(-15,-15,-20))
                        .setEmission(new Color(java.awt.Color.MAGENTA))
                        .setMaterial(new Material()
                                .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                .setShininess(60)),

                // surface
                new Polygon(
                        new Point(-100, -50, -150),
                        new Point(-100, -50, 200),
                        new Point(100, -50, 200),
                        new Point(100, -50, -150))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material()
                                .setKd(0.5).setKs(0.5)
                                .setShininess(100)),
                //front block
                new Polygon(
                        new Point(0, -50, 75),
                        new Point(0, 30, 75),
                        new Point(38, 30, 75),
                        new Point(38, -50, 75))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setkT(1.0).setkG(0.8)),
                new Polygon(
                        new Point(42, -50, 75),
                        new Point(42, 30, 75),
                        new Point(80 ,30, 75),
                        new Point(80, -50, 75))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setkT(1.0).setkG(0.8))
        );
    }
/*
    @Test
    public void project2() {
        Camera camera = new Camera(
                new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                .setViewPlaneSize(225, 150)
                .setDistance(800)
                .setNumOfRays(10)
                .setFocus(new Point(0, 0, 0), 500);

        Scene scene = new Scene("Test Scene");
        scene.lights.add(

                new SpotLight(
                        new Color(500, 500, 500),
                        new Point(-50, 100, 100),
                        new Vector(-0.5, -1, -0.5))
                        .setKl(0.004)
                        .setKq(0.000006));
        scene.geometries.add(
                new Sphere(new Point(50, 0, 0), 50)
                        .setEmission(new Color(5, 5, 5))
                        .setMaterial(new Material()
                                .setkR(1.0).setkG(0.8)),
                new Cylinder(new Ray(
                        new Point(-90, -35, 0),
                        new Vector(60, 85, 0)),
                        25, 100)
                        .setEmission(new Color(100, 75, 0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKd(0.4)
                                .setShininess(80)),
                new Polygon(
                        new Point(-100, -50, -150),
                        new Point(-100, -50, 150),
                        new Point(100, -50, 150),
                        new Point(100, -50, -150))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(50)),
                new Polygon(
                        new Point(-100, -50, -150),
                        new Point(-100, 75, -150),
                        new Point(100, 75, -150),
                        new Point(100, -50, -150))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4)
                                .setShininess(50))
        );

       // int frames = 16;
       // double angle = 360d / frames;
       // double angleRadians = 2 * Math.PI / frames;

        double radius = camera.getP0().substract(Point.ZERO).length();
/*
        for (int i = 0; i < frames; i++) {
            System.out.println("Start frame " + (i + 1));

            camera.rotate(0, angle, 0);
            camera.setP0(
                    Math.sin(angleRadians * (i + 1)) * radius,
                    0,
                    Math.cos(angleRadians * (i + 1)) * radius
            );
*/
            // Render render = new Render()
    /*
            camera.setImageWriter(
                            new ImageWriter("Project2.", 750, 500))
                    .setCamera(camera)
                    //.setMultithreading(5)
                    .setRayTracer(new RayTracerBasic(scene).setGlossinessRays(20));
            camera.renderImage();
            camera.writeToImage();
        }

    //}
    */
}


