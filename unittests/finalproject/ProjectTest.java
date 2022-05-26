/**package unittests.finalproject;

import lighting.*;
import scene.Scene;
import org.junit.jupiter.api.Test;
import renderer.*;
import primitives.*;
import geometries.*;



class ProjectTest {
    private Scene scene = new Scene("Test scene");


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

            /** render = new Render() //
                    .setCamera(camera) //
                    .setMultithreading(3)
                    .setRayTracer(new RayTracerBasic(scene).setGlossinessRays(20));**/
/**
            int frames = 16;
            double angle = 360d / frames;
            double angleRadians = 2 * Math.PI / frames;

            double radius = camera.getP0().substract(Point.ZERO).length();

            for (int i = 0; i < frames; i++) {
                System.out.println("Start frame " + (i + 1));

                camera.rotate(0, angle, 0);
                camera.setP0(
                        Math.sin(angleRadians * (i + 1)) * radius,
                        0,
                        Math.cos(angleRadians * (i + 1)) * radius
                );

                ImageWriter imageWriter = new ImageWriter("project" + (i + 1), 600, 450);
                camera.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage();
                camera.writeToImage();
            }
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
            scene.lights.add(new SpotLight(new Color(0,250,350),
                    new Point(-200, 100, 0),
                    new Vector(1, 1, -2)).setSpecularN(40) ///
                    .setKl(0.00000005).setKq(0.000000005));
            scene.lights.add(new SpotLight(new Color(0,250,350),
                    new Point(-200, 50, 0),
                    new Vector(1, 0.5, -2)).setSpecularN(20) //
                    .setKl(0.00000005).setKq(0.000000005));
            scene.lights.add(new SpotLight(new Color(0,250,350),
                    new Point(-200, 55, 0),
                    new Vector(1, 1, -2)).setSpecularN(10) //
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
                            .setEmission(new Color(30,40,50))
                            .setMaterial(new Material()
                                    .setkR(0.8)),

                    new Sphere(new Point(-45, -45, -5), 5)
                            .setEmission(new Color(0,60,0))
                            .setMaterial(new Material()
                                    .setkR(0.8).setkG(0.95)),

                    //triangles

                    new Triangle(a,g,h)
                            .setEmission(new Color(0, 75, 66))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4).setkT(0.6)
                                    .setShininess(80)),
                    new Triangle(a,b,h)
                            .setEmission(new Color(0, 75, 66))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4).setkT(0.6)
                                    .setShininess(80)),
                    new Triangle(a,b,g)
                            .setEmission(new Color(0, 75, 66))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4).setkT(0.6)
                                    .setShininess(80)),
                    new Triangle(g,b,h)
                            .setEmission(new Color(0, 75, 66))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4).setkT(0.6)
                                    .setShininess(80)),

                    //cylinder
                    new Cylinder(new Ray(
                            new Point(-80, -45, 0),
                            new Vector(60, 85, 0)),
                            13, 50)
                            .setEmission(new Color(0,100,70))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4)
                                    .setShininess(50))
                    ,
                    new Cylinder(new Ray(
                            new Point(-70, -61, 0),
                            new Vector(1, 0, 0)),
                            11, 140)
                            .setEmission(new Color(0,51,102))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4).setkG(0.9)
                                    .setShininess(50)),


                    //square
                    //1
                    new Polygon(new Point(-25,-50,-30),
                            new Point(-25,-50,30),
                            new Point(15,-50,30),
                            new Point(15,-50,-30))
                            .setEmission(new Color(0,75,100))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4)
                                    .setShininess(50)),
                    new Polygon(new Point(-25,-25,-30),
                            new Point(-25,-25,30),
                            new Point(15,-25,30),
                            new Point(15,-25,-30))
                            .setEmission(new Color(0,75,100))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4)
                                    .setShininess(50)),
                    new Polygon(new Point(-25,-50,-30),
                            new Point(-25,-50,30),
                            new Point(-25,-25,30),
                            new Point(-25,-25,-30))
                            .setEmission(new Color(0,75,100))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4)
                                    .setShininess(50)),
                    new Polygon(new Point(15,-50,-30),
                            new Point(15,-50,30),
                            new Point(15,-25,30),
                            new Point(15,-25,-30))
                            .setEmission(new Color(0,75,100))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4)
                                    .setShininess(50)),
                    new Polygon(new Point(-25,-50,30),
                            new Point(15,-50,30),
                            new Point(15,-25,30),
                            new Point(-25,-25,30))
                            .setEmission(new Color(0,75,100))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4)
                                    .setShininess(50)),
                    new Polygon(new Point(-25,-50,-30),
                            new Point(15,-50,-30),
                            new Point(15,-25,-30),
                            new Point(-25,-25,-30))
                            .setEmission(new Color(0,75,100))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4)
                                    .setShininess(50)),
                    //2
                    new Polygon(new Point(-15,-25,-20),
                            new Point(-15,-25,20),
                            new Point(5,-25,20),
                            new Point(5,-25,-20))
                            .setEmission(new Color(0,90,100))
                            .setMaterial(new Material()
                                    .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                    .setShininess(60)),
                    new Polygon(new Point(-15,-15,-20),
                            new Point(-15,-15,20),
                            new Point(5,-15,20),
                            new Point(5,-15,-20))
                            .setEmission(new Color(0,90,100))
                            .setMaterial(new Material()
                                    .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                    .setShininess(60)),
                    new Polygon(new Point(-15,-25,-20),
                            new Point(-15,-25,20),
                            new Point(-15,-15,20),
                            new Point(-15,-15,-20))
                            .setEmission(new Color(0,90,100))
                            .setMaterial(new Material()
                                    .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                    .setShininess(60)),
                    new Polygon(new Point(5,-25,-20),
                            new Point(5,-25,20),
                            new Point(5,-15,20),
                            new Point(5,-15,-20))
                            .setEmission(new Color(0,90,100))
                            .setMaterial(new Material()
                                    .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                    .setShininess(60)),
                    new Polygon(new Point(-15,-25,20),
                            new Point(5,-25,20),
                            new Point(5,-15,20),
                            new Point(-15,-15,20))
                            .setEmission(new Color(0,90,100))
                            .setMaterial(new Material()
                                    .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                    .setShininess(30)),
                    new Polygon(new Point(-15,-25,-20),
                            new Point(5,-25,-20),
                            new Point(5,-15,-20),
                            new Point(-15,-15,-20))
                            .setEmission(new Color(0,90,100))
                            .setMaterial(new Material()
                                    .setkR(0.1).setKd(0.5).setKs(0.5).setkT(0.2)
                                    .setShininess(60)),


                    // surface
                    new Polygon(
                            new Point(-100, -50, -150),
                            new Point(-100, -50, 200),
                            new Point(100, -50, 200),
                            new Point(100, -50, -150))
                            .setEmission(new Color(102, 153, 153))
                            .setMaterial(new Material()
                                    .setKd(0.6).setKs(0.4).setkT(0.2)
                                    .setShininess(50)),
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
      /**  @Test
        public void project2() {
            Camera camera = new Camera(
                    new Point3D(0, 0, 1000),
                    new Vector(0, 0, -1),
                    new Vector(0, 1, 0))
                    .setViewPlaneSize(225, 150)
                    .setDistance(800)
                    .setNumOfRays(10)
                    .setFocus(new Point3D(0, 0, 0), 500);

            Scene scene = new Scene("Test Scene");
            scene.lights.add(

                    new SpotLight(
                            new Color(500, 500, 500),
                            new Vector(-0.5, -1, -0.5),
                            new Point3D(-50, 100, 100))
                            .setkL(0.004)
                            .setkQ(0.000006));
            scene.geometries.add(
                    new Sphere(new Point3D(50, 0, 0), 50)
                            .setEmission(new Color(5, 5, 5))
                            .setMaterial(new Material()
                                    .setkR(1.0).setkG(0.8)),
                    new Cylinder(new Ray(
                            new Point3D(-90, -35, 0),
                            new Vector(60, 85, 0)),
                            25, 100)
                            .setEmission(new Color(100, 75, 0))
                            .setMaterial(new Material()
                                    .setkD(0.6).setkD(0.4)
                                    .setnShininess(80)),
                    new Polygon(
                            new Point3D(-100, -50, -150),
                            new Point3D(-100, -50, 150),
                            new Point3D(100, -50, 150),
                            new Point3D(100, -50, -150))
                            .setEmission(new Color(40, 40, 40))
                            .setMaterial(new Material()
                                    .setkD(0.6).setkS(0.4)
                                    .setnShininess(50)),
                    new Polygon(
                            new Point3D(-100, -50, -150),
                            new Point3D(-100, 75, -150),
                            new Point3D(100, 75, -150),
                            new Point3D(100, -50, -150))
                            .setEmission(new Color(40, 40, 40))
                            .setMaterial(new Material()
                                    .setkD(0.6).setkS(0.4)
                                    .setnShininess(50))
            );

            int frames = 16;
            double angle = 360d / frames;
            double angleRadians = 2 * Math.PI / frames;

            double radius = camera.getP0().subtract(Point3D.ZERO).length();

            for (int i = 0; i < frames; i++) {
                System.out.println("Start frame " + (i + 1));

                camera.rotate(0, angle, 0);
                camera.setP0(
                        Math.sin(angleRadians * (i + 1)) * radius,
                        0,
                        Math.cos(angleRadians * (i + 1)) * radius
                );

                Render render = new Render()
                        .setImageWriter(
                                new ImageWriter("project/Project2." + (i + 1), 750, 500))
                        .setCamera(camera)
                        .setMultithreading(5)
                        .setRayTracer(new RayTracerBasic(scene).setGlossinessRays(20));
                render.renderImage();
                render.writeToImage();


            }

        }
    }**/
/**
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
**/


