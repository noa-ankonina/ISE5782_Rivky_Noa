package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = null;

    /**
     * A list of all the light source
     */
    public List<LightSource> lights=new LinkedList<>();

    public Scene(String name) {
        this.name = name;
        this.geometries = new Geometries();
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}