package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;


import static primitives.Util.isZero;

class VectorTest<assertEquals>
{
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);


    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */


    @Test
    void testVector()
    {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Vector(0,0,0), "ERROR: zero vector does not throw an exception");
    }


    @Test
    void testLengthSquared()
    {
        // ============ Equivalence Partitions Tests ==============
        assertTrue(isZero(v1.lengthSquared()-14),
                "ERROR: lengthSquared() wrong value");
    }

    @Test
    void testLength()
    {
        // ============ Equivalence Partitions Tests ==============
        assertTrue(isZero(new Vector(0,3,4).length()-5),
                "ERROR: length() wrong value");
    }

    @Test
    void testAdd()
    {
        // ============ Equivalence Partitions Tests ==============
        Vector v0=new Vector(2,-1,4);
        Vector v1=new Vector(3,2,4);

        assertEquals(v0.add(v1),new Vector(5,1,8),
                "add() wrong result of adding");
    }



    @Test
    void testCrossProduct()
    {
        // =============== Boundary Values Tests ==================
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertTrue(isZero(vr.length() - v1.length() * v3.length()),
                "ERROR: crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)),
                "ERROR: crossProduct() result is not orthogonal to its operands");
    }

    @Test
    void testDotProduct()
    {
        // =============== Boundary Values Tests ==================
        //dotProduct for orthogonal vectors
        assertTrue(isZero(v1.dotProduct(v3))
                ,"ERROR: dotProduct() for orthogonal vectors is not zero");

        // ============ Equivalence Partitions Tests ==============
        // Simple dotProduct test
        assertTrue(isZero(v1.dotProduct(v2) + 28)
                ,"ERROR: dotProduct() wrong value");
    }

    @Test
    void testNormlize()
    {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normlize();

        // ============ Equivalence Partitions Tests ==============
        // Simple test
        assertTrue(isZero(u.length() - 1),
                "ERROR: the normalized vector is not a unit vector");
        assertThrows(IllegalArgumentException.class,() -> v.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");
        assertFalse(v.dotProduct(u) < 0 ,
                "ERROR: the normalized vector is opposite to the original one");

    }
}