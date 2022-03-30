package elements;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {

    private Color intensity;

    public AmbientLight(Color Ia, Double3 Ka) {
        intensity=Ia.scale(Ka);
    }

    public AmbientLight() {
        this.intensity = Color.BLACK;
    }

    public Color getIntensity() {
        return intensity;
    }

}