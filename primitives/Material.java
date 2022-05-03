package primitives;

/**
 * The material kind of the object
 *
 */
public class Material {


    /**
     * factors of the diffuse,specular light,transparency and reflection
     */
    public double kD=0,kS=0,kT=0.0,kR=0.0;
    /**
     * factor of the shininess
     */
    public int nShininess=0;

    /**
     * @param kD factor of the diffuse affect of the material
     * @return this Material
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * @param kS factor of the specular affect of the material
     * @return this Material
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }
    /**
     * @param kT factor of the Transparency of the material
     * @return this Material
     */
    public Material setkT(double kT) {
        this.kT = kT;
        return this;
    }
    /**
     * @param kR factor of the reflection of the material
     * @return this Material
     */
    public Material setkR(double kR) {
        this.kR = kR;
        return this;
    }

    /**
     * @param nShininess factor of the reflection of the material
     * @return this Material
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
