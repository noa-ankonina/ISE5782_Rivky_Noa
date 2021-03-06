package unittests.finalproject;
import geometries.Polygon;
import geometries.Sphere;
import lighting.DirectionalLight;
import lighting.PointLight;
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

       // Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
      //  Color trCL = new Color(800, 500, 250); // Triangles test Color of Light


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

        scene.geometries.add( //
                new Polygon(new Point(210, 0, 0),
                        new Point(315, 45, 0),
                        new Point(270, 150, 0),
                        new Point(165, 105, 0)).
                        setEmission(new Color(java.awt.Color.red))//front a,b,c,d
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(220, 0, 114.23659658795863),
                        new Point(315, 45, 114.23659658795863),
                        new Point(270, 150, 114.23659658795863),
                        new Point(165, 105, 114.23659658795863)).
                        setEmission(new Color(java.awt.Color.green)) //back e,f,g,h
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(315, 45 , 0),
                        new Point(315, 45 , 114.23659658795863),
                        new Point(270, 150 , 114.23659658795863),
                        new Point(270, 150 , 0)).
                        setEmission(new Color(java.awt.Color.pink)) //right side b,f,g,c
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(210, 0 , 0),
                        new Point(210, 0 , 114.23659658795863 ),
                        new Point(165, 105, 114.23659658795863),
                        new Point(165, 105, 0 )).
                        setEmission(new Color(java.awt.Color.blue)) //left side a,e,h,d
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(165, 105, 0),
                        new Point(270, 150, 0 ),
                        new Point(270, 150 , 114.23659658795863),
                        new Point(165, 105 , 114.23659658795863 )).
                        setEmission(new Color(java.awt.Color.yellow)) //top d,c,g,h
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3)),

                new Polygon(new Point(210, 0 , 0),
                        new Point(315, 45, 0 ),
                        new Point(315, 45, 114.23659658795863),
                        new Point(210, 0 , 114.23659658795863)).
                        setEmission(new Color(java.awt.Color.orange)) //bottom a,b,f,e
                        .setMaterial(new Material().setKd(0.6).setKs(0.2).setShininess(30).setkT(0.3))
        );

        scene.lights.add(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 0.1, 0)));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.white), new Point(200, 0, 20), new Vector(0, 0, -1))
                .setKl(0.0000001).setKq(0.0000001));

        ImageWriter imageWriter = new ImageWriter("MP1", 1000, 1000);
        camera.setImageWriter(imageWriter) //
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
                .setNumOfRays(81);

        Scene scene = new Scene("Test Scene");
        setLights(scene);
        setGeometries(scene);

        camera.setMultithreading(5)
                .setRayTracer(new RayTracerBasic(scene).setGlossinessRays(1));

        ImageWriter imageWriter = new ImageWriter("MP2", 600, 450);
        camera.setImageWriter(imageWriter);
        camera.renderImage();
        camera.writeToImage();
    }

    private void setLights(Scene scene) {
        //scene.lights.add(new DirectionalLight(new Color(230,0,230),new Vector(0,-1,0)));
        scene.lights.add(new PointLight(new Color(88,0,88),new Point(-20,-30,400)));
        scene.lights.add(new SpotLight(
                        new Color(400,400,400),
                        new Point(-50, 100, 100),
                        new Vector(-0.5, -1, -0.5))
                        .setKl(0.004)
                        .setKq(0.000006));
        scene.lights.add(new SpotLight(
                        new Color(180,110,180),
                        new Point(-50, 100, 100),
                        new Vector(-0.5, -1, -0.5))
                        .setKl(0.004)
                        .setKq(0.000006));
        scene.lights.add(new SpotLight(
                new Color(0,250,350),
                new Point(-200, 100, 0),
                new Vector(1, 1, -2)).setSpecularN(40) //
                .setKl(0.00000005).setKq(0.000000005));
        scene.lights.add(new SpotLight(
                new Color(0,250,350),
                new Point(-200, 50, 0),
                new Vector(1, 0.5, -2)).setSpecularN(20) //
                .setKl(0.00000005).setKq(0.000000005));
        scene.lights.add(new SpotLight(
                new Color(0,250,350),
                new Point(-200, 55, 0),
                new Vector(1, 1, -2)).setSpecularN(10) //
                .setKl(0.00000005).setKq(0.000000005));
    }

    private void setGeometries(Scene scene) {
        //triangles
        int xd=-50,yd=-50,xt=10,yt=-30,xr=80,yr=80;
        Point h = new Point(60+xt, -50, 30+yt);
        Point g = new Point(45+xt, -30, 0+yt);
        Point a = new Point(30+xt, -50, 30+yt);
        Point b = new Point(40+xt, 0, 15+yt);
        Point W_1 = new Point(69.2705098312485+xd, 16.810981864696302,42.7597621252806+yd);
        Point V_1 = new Point(60+xd, 16.810981864696302,71.2914576141351+yd);
        Point U_1 = new Point(30+xd, 16.810981864696302,71.2914576141351+yd);
        Point T_1 = new Point(20.729490168751497+xd, 16.810981864696302,42.7597621252806+yd);
        Point S_1 = new Point(45+xd, 16.810981864696302,25.126204556506494+yd);
        Point R_1 = new Point(84.2705098312485+xd, -8.708542385864902,37.885966681787096+yd);
        Point Q_1 = new Point(69.2705098312485+xd, -8.708542385864902,84.05121973941601+yd);
        Point P_1 = new Point(20.729490168751497+xd, -8.708542385864902,84.05121973941601+yd);
        Point O_1 = new Point(5.729490168751497+xd, -8.708542385864597,37.885966681787096+yd);
        Point N_1 = new Point(45+xd, -8.708542385864902,9.354271192932604+yd);
        Point M_1 = new Point(69.2705098312485+xd, -24.4804757494388,17.240237874719405+yd);
        Point L_1 = new Point(84.2705098312485+xd, -24.4804757494388,63.405490932348314+yd);
        Point K_1 = new Point(45+xd, -24.4804757494388,91.9371864212028+yd);
        Point J_1 = new Point(5.729490168751497+xd, -24.4804757494388,63.405490932348314+yd);
        Point I_1 = new Point(20.729490168751497+xd, -24.4804757494388,17.240237874719405+yd);
        Point A_6 = new Point(30+xd, -50, 30+yd);
        Point E_1 = new Point(60+xd, -50, 30+yd);
        Point H_1 =new Point(69.27050983124842+xd, -50,58.53169548885461+yd);
        Point G_1 =new Point(44.99999999999999+xd, -50,76.16525305762879+yd);
        Point F_1 =new Point (20.72949016875158+xd, -50,58.531695488854595+yd);
        int S=80;

        scene.geometries.add(
                //sphere
               /*new Sphere(new Point(80, -28, 0), 22)
                        .setEmission(new Color(java.awt.Color.pink))
                        .setMaterial(new Material()
                                .setkR(0.8)),*/
                new Sphere(new Point(-45, -45, -5), 5)
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material()
                                .setkR(0.8).setkG(0.95)),
/*
                new Sphere(new Point(-20, -45, -5), 5)
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material()
                                .setkR(0.8).setkG(0.95)),/*
                new Sphere(new Point(-45, -30, -5), 5)
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material()
                                .setkR(0.8).setkG(0.95)),
                new Sphere(new Point(-45, -20, -5), 5)
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material()
                                .setkR(0.8).setkG(0.95)),*/

                //triangles
                new Triangle(a, g, h)
                        .setEmission(new Color(255,128,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkT(0.1)
                                .setShininess(80)),
                new Triangle(a, b, h)
                        .setEmission(new Color(255,128,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkT(0.1)
                                .setShininess(80)),
                new Triangle(a, b, g)
                        .setEmission(new Color(255,128,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkT(0.1)
                                .setShininess(80)),
                new Triangle(g, b, h)
                        .setEmission(new Color(255,128,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkT(0.1)
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

                //else
                new Sphere(new Point(-60, -40, 10), 10)
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setkT(1.0).setkG(0.8)),


                //square
                //1
               new Polygon(new Point(-25+xr, -50, -30+yr),
                        new Point(-25+xr, -50, 30+yr),
                        new Point(15+xr, -50, 30+yr),
                        new Point(15+xr, -50, -30+yr))
                        .setEmission(new Color(128,128,255))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(-25+xr, -25, -30+yr),
                        new Point(-25+xr, -25, 30+yr),
                        new Point(15+xr, -25, 30+yr),
                        new Point(15+xr, -25, -30+yr))
                        .setEmission(new Color(128,128,255))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(-25+xr, -50, -30+yr),
                        new Point(-25+xr, -50, 30+yr),
                        new Point(-25+xr, -25, 30+yr),
                        new Point(-25+xr, -25, -30+yr))
                        .setEmission(new Color(128,128,255))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(15+xr, -50, -30+yr),
                        new Point(15+xr, -50, 30+yr),
                        new Point(15+xr, -25, 30+yr),
                        new Point(15+xr, -25, -30+yr))
                        .setEmission(new Color(128,128,255))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(-25+xr, -50, 30+yr),
                        new Point(15+xr, -50, 30+yr),
                        new Point(15+xr, -25, 30+yr),
                        new Point(-25+xr, -25, 30+yr))
                        .setEmission(new Color(128,128,255))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(-25+xr, -50, -30+yr),
                        new Point(15+xr, -50, -30+yr),
                        new Point(15+xr, -25, -30+yr),
                        new Point(-25+xr, -25, -30+yr))
                        .setEmission(new Color(128,128,255))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                //2
                new Polygon(new Point(-15+xr, -25, -20+yr),
                        new Point(-15+xr, -25, 20+yr),
                        new Point(5+xr, -25, 20+yr),
                        new Point(5+xr, -25, -20+yr))
                        .setEmission(new Color(255,255,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(-15+xr, -15, -20+yr),
                        new Point(-15+xr, -15, 20+yr),
                        new Point(5+xr, -15, 20+yr),
                        new Point(5+xr, -15, -20+yr))
                        .setEmission(new Color(255,255,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(-15+xr, -25, -20+yr),
                        new Point(-15+xr, -25, 20+yr),
                        new Point(-15+xr, -15, 20+yr),
                        new Point(-15+xr, -15, -20+yr))
                        .setEmission(new Color(255,255,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(5+xr, -25, -20+yr),
                        new Point(5+xr, -25, 20+yr),
                        new Point(5+xr, -15, 20+yr),
                        new Point(5+xr, -15, -20+yr))
                        .setEmission(new Color(255,255,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(-15+xr, -25, 20+yr),
                        new Point(5+xr, -25, 20+yr),
                        new Point(5+xr, -15, 20+yr),
                        new Point(-15+xr, -15, 20+yr))
                        .setEmission(new Color(255,255,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),
                new Polygon(new Point(-15+xr, -25, -20+yr),
                        new Point(5+xr, -25, -20+yr),
                        new Point(5+xr, -15, -20+yr),
                        new Point(-15+xr, -15, -20+yr))
                        .setEmission(new Color(255,255,128))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(50)),

                // surface
                /*new Polygon(
                        new Point(-100, -50, -150),
                        new Point(-100, -50, 200),
                        new Point(100, -50, 200),
                        new Point(100, -50, -150))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material()
                                .setKd(0.5).setKs(0.5)
                                .setShininess(100)),*/
                //back

                new Polygon(
                        new Point(-100, -50, -150),
                        new Point(-100, -50, 150),
                        new Point(100, -50, 150),
                        new Point(100, -50, -150))

                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material()
                                .setKd(0.5).setKs(0.5)
                                .setShininess(100)),

                new Polygon(
                        new Point(-100, -50, -150),
                        new Point(-100, 75, -150),
                        new Point(100, 75, -150),
                        new Point(100, -50, -150))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material()
                                .setkR(0.8).setkG(0.95)),
                //front block
                /*new Polygon(
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
                        new Point(80, 30, 75),
                        new Point(80, -50, 75))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material()
                                .setkT(1.0).setkG(0.8)),*/
                //dodeandrom
                new Polygon(W_1,S_1,N_1,M_1,R_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                        .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                        .setShininess(S)),
                new Polygon(Q_1,V_1,W_1,R_1,L_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(Q_1,L_1,H_1,G_1,K_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(U_1,V_1,Q_1,K_1,P_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(N_1,I_1,A_6,E_1,M_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(H_1,E_1,A_6,F_1,G_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(R_1,M_1,E_1,H_1,L_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(T_1,U_1,P_1,J_1,O_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(S_1,T_1,O_1,I_1,N_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(W_1,V_1,U_1,T_1,S_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(P_1,K_1,G_1,F_1,J_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S)),
                new Polygon(O_1,J_1,F_1,A_6,I_1)
                        .setEmission(new Color(255,128,0))
                        .setMaterial(new Material()
                                .setKd(0.6).setKs(0.4).setkR(0.1).setkG(0.8)
                                .setShininess(S))


        );
    }
}

