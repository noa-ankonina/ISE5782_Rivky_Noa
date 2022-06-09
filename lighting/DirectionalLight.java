package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class of directional light (like sun)
 *
 * @author Noa & Rivky
 */
public class DirectionalLight extends Light implements LightSource{

    /**
     * vector of direction of light
     */
    private Vector direction;

    /**
     * create the intensity and direction of the light
     * @param intensity of the light
     * @param direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction=direction.normlize();
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
