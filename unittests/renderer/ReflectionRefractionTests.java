/**
 * 
 */
package unittests.renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150).setVPDistance(1000).setNumOfRays(50);

		scene.geometries.add( //
				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.3)),
				new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006));

		camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000).setNumOfRays(81); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setkT(0.5)),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(1)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}

	/**@Test
	public void exe11() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(600, 600).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Sphere( new Point(140, 10, -100),25) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.4)),
				new Sphere( new Point(140, 80, -100),15) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.4)),
				new Sphere( new Point(-100, 150, 50),35) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(1)),
				new Sphere( new Point(-75, 100, 50),45) //
						.setEmission(new Color(java.awt.Color.magenta)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),
				new Cylinder(new Ray(
						new Point(-80, -45, 0),
						new Vector(60, 85, 0)),
						13, 3)
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
								.setShininess(50))




		);

			scene.lights.add(new SpotLight(new Color(400, 400, 400), new Point(-50, 100, 100), new Vector(-0.5, -1, -0.5)).setKl(0.004).setKq(0.000006));
			scene.lights.add(new SpotLight(new Color(0,250,350),new Point(-200, 100, 0),new Vector(1, 1, -2)).setSpecularN(40) //
					.setKl(0.00000005).setKq(0.000000005));
			scene.lights.add(new SpotLight(new Color(0,250,350),new Point(-200, 50, 0),new Vector(1, 0.5, -2)).setSpecularN(20) //
					.setKl(0.00000005).setKq(0.000000005));
			scene.lights.add(new SpotLight(new Color(0,250,350),new Point(-200, 55, 0), new Vector(1, 1, -2)).setSpecularN(10) //
					.setKl(0.00000005).setKq(0.000000005));

		ImageWriter imageWriter = new ImageWriter("exe11", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
		camera.writeToImage();
	}
	*///exe11
/**
	@Test
	public void Bonus() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(600, 600).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setEmission(new Color(java.awt.Color.magenta)), //
				new Triangle(new Point(-130, -130, -130), new Point(140, -140, -135), new Point(65, 65, -140)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setEmission(new Color(java.awt.Color.cyan)), //
				new Triangle(new Point(150, -150, -150), new Point(-150, 1500, -150),
						new Point(67, 67, 300)) //
						.setEmission(new Color(java.awt.Color.ORANGE)) //
						.setMaterial(new Material().setkR(1).setkT(0.5)),
				new Triangle(new Point(150, -150, -150), new Point(-1500, 1500, -1500),
						new Point(-150, -150, -200)) //
						.setEmission(new Color(0, 120, 220)) //
						.setMaterial(new Material().setkR(1).setkT(0.5)),
				new Sphere( new Point(140, -150, -100),25) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.4)),
				new Sphere( new Point(140, -60, -100),15) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.4)),
				new Sphere( new Point(140, 10, -100),25) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.4)),
				new Sphere( new Point(140, 80, -100),15) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.4)),
				new Sphere( new Point(-100, 150, 50),35) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkR(1)),
				new Sphere( new Point(-75, 100, 50),45) //
						.setEmission(new Color(java.awt.Color.magenta)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),
				new Sphere( new Point(-55, 100, 50),35) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.3)),
				new Sphere( new Point(-30, 100, 50),25) //
						.setEmission(new Color(java.awt.Color.magenta)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),
				new Sphere( new Point(-10, 100, 50),20) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.5)),
				new Sphere( new Point(15, 100, 50),15) //
						.setEmission(new Color(java.awt.Color.magenta)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),
				new Sphere( new Point(40, 100, 50),10) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.6)),
				new Sphere( new Point(50, 100, 50),5) //
						.setEmission(new Color(java.awt.Color.magenta)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6)),
				new Sphere( new Point(70, -10, -100),10) //
						.setEmission(new Color(java.awt.Color.cyan)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkR(0.4)),
				new Sphere( new Point(75, 75, 50),30) //
						.setEmission(new Color(java.awt.Color.yellow)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setkT(0.6)),
				new Sphere( new Point(-350, -300, -400),400) //
						.setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setkT(0.5)),
				new Sphere( new Point(-350, -300, -400),200) //
						.setEmission(new Color(100, 120, 120)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Polygon(new Point(100,100,0),new Point(-70, 70, -140),new Point(140, -140, -125)).setEmission(new Color(java.awt.Color.blue)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setkT(0.6))

		);

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0),new Vector(0, 0, -1)) //
				.setKq(2E-7)); //.setKq(0.000005));
		scene.lights.add(new SpotLight(new Color(1020, 400, 400),new Point(-750, -750, -150), new Vector(-1, -1, -4)));
		ImageWriter imageWriter = new ImageWriter("bonus", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
		camera.writeToImage();
	}
	*///bonus

	/**
	 * Produce a picture of a few glasses with diffrent level of matte lighted by
	 * spot light
	 */
/**	@Test
	public void diffusedGlass() {
		Scene scene = new Scene("my");
		Camera camera=new Camera(new Point(-900, 50, 20), new Vector(1, 0, 0.02), new Vector(-0.02, 0, 1))
				.setDistance(1000)
				.setViewPlaneSize(300, 300);
		scene.setBackground(new Color(java.awt.Color.red));
		scene.setAmbientLight(Color.BLACK, 0);

		// front wall
		scene.geometries.add(new Plane(new Point(60, 0, 0), new Vector(1, 0, 0))
				.setEmission(new Color(java.awt.Color.gray))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
		// floor
		scene.geometries.add(new Plane(new Point(0, 0, 0), new Vector(0, 0, 1))
				.setEmission(new Color(java.awt.Color.BLACK))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
		// ceiling
		scene.geometries.add(new Plane(new Point(0, 0, 400), new Vector(0, 0, 1))
				.setEmission(new Color(java.awt.Color.WHITE))
				.setMaterial(new Material().setKd(0).setKs(1).setShininess(100)));//,

		scene.geometries.add(new Sphere(new Point(50,140,45),15)
						.setEmission(new Color(java.awt.Color.BLUE))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Sphere(new Point(50,97,45),15)
						.setEmission(new Color(java.awt.Color.BLUE))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Sphere(new Point(50,55,45),15)
						.setEmission(new Color(java.awt.Color.BLUE))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Sphere(new Point(50,13,45),15)
						.setEmission(new Color(java.awt.Color.BLUE))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),
				new Sphere(new Point(50,-30,45),15)
						.setEmission(new Color(java.awt.Color.BLUE))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));

		scene.geometries.add(
				new Polygon(new Point(0, 150, 0),new Point(0, 150, 90), new Point(0, 120, 90), new Point(0, 120, 0))
						.setEmission(new Color(10, 10, 10))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0.9).setkR(0).setkG(0.0).setkB(0.05)),
				new Polygon( new Point(0, 110, 0),new Point(0, 110, 90), new Point(0, 80, 90), new Point(0, 80, 0))
						.setEmission(new Color(10, 10, 10))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0.9).setkR(0).setkG(0.0).setkB(0.25)),
				new Polygon(new Point(0, 70, 0),new Point(0, 70, 90), new Point(0, 40, 90), new Point(0, 40, 0))
						.setEmission(new Color(10, 10, 10))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0.9).setkR(0).setkG(0.0).setkB(0.45)),
				new Polygon(new Point(0, 30, 0),new Point(0, 30, 90), new Point(0, 0, 90), new Point(0, 0, 0))
						.setEmission(new Color(10, 10, 10))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0.9).setkR(0).setkG(0.0).setkB(0.65)),
				new Polygon(new Point(0, -40, 0),new Point(0, -40, 90), new Point(0, -10, 90), new Point(0, -10, 0))
						.setEmission(new Color(10, 10, 10))
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setkT(0.9).setkR(0).setkG(0.0).setkB(1)));

		scene.lights.add(new SpotLight(new Color(java.awt.Color.yellow), new Point(-100, 55, 150), new Vector(1, 0, 0))
										.setKl(0.0000001).setKq(0.0000001));


		ImageWriter imageWriter = new ImageWriter("try2", 1000, 1000);
		camera.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene,300))//
		.renderImage();
		camera.writeToImage();
	}*///try2
}
