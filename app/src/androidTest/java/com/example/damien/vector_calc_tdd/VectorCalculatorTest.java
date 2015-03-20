package com.example.damien.vector_calc_tdd;
import junit.framework.Assert;
import junit.framework.TestCase;



import java.util.Vector;

public class VectorCalculatorTest extends TestCase {


    //Test the addition of two vectors (with two integers)
    public void testVectorAdditionCartesian() {

        Vector<Double> vector1 = new Vector<Double>();
        vector1.add(5.0);
        vector1.add(10.0);
        Vector<Double> vector2 = new Vector<Double>();
        vector2.add(6.0);
        vector2.add(3.0);
        Vector<Double> vectorResult = VectorCalculator.vectorAddition(vector1, vector2);
        //This assertNotEquals fails the test if the vectorResult does not contain any element
        assertNotSame(vectorResult, new Vector<Double>());
        assertEquals(11.0, (double)vectorResult.elementAt(0), 0.001);
        assertEquals(13.0, (double)vectorResult.elementAt(1), 0.001);

       }


    //Test the addition of two vectors with negative coordinates in
    //Cartesian system (with two integers)
    public void testVectorAdditionPolar() {
        Vector<Double> vector1 = new Vector<Double>();
        vector1.add(200.0);
        vector1.add(60.0);
        Vector<Double> vector2 = new Vector<Double>();
        vector2.add(120.0);
        vector2.add(-45.0);
        Vector<Double> vectorResult = VectorCalculator.vectorAdditionPolar(vector1,vector2);
        //This assertNotEquals fails the test if vectorResult does not contain any element
        assertNotSame(vectorResult, new Vector<Double>());
        assertEquals(204.88, (double)vectorResult.elementAt(0), 0.1);
        assertEquals(25.5, (double)vectorResult.elementAt(1), 0.1);
    }


    //Test the addition of two vectors (with two integers)
    public void testVectorAdditionPolar2() {
        Vector<Double> vector1 = new Vector<Double>();
        vector1.add(5.0);
        vector1.add(100.0);
        Vector<Double> vector2 = new Vector<Double>();
        vector2.add(57.0);
        vector2.add(180.0);
        Vector<Double> vectorResult = VectorCalculator.vectorAdditionPolar(vector1,vector2);
        assertEquals(58.077, (double)vectorResult.elementAt(0), 0.001);
        assertEquals(175.136, (double)vectorResult.elementAt(1), 0.001);
    }


    //Test the addition of three vectors (with two integers)
    public void testVectorAddition3Cartesian() {
        Vector<Double> vector1 = new Vector<Double>();
        vector1.add(5.0);
        vector1.add(10.0);
        Vector<Double> vector2 = new Vector<Double>();
        vector2.add(6.0);
        vector2.add(3.0);
        Vector<Double> vector3 = new Vector<Double>();
        vector3.add(8.0);
        vector3.add(12.0);
        Vector<Double> vectorResult = VectorCalculator.vectorAddition(vector1,vector2,vector3);
        //This assertNotEquals fails the test if vectorResult does not contain any element
        assertNotSame(vectorResult, new Vector<Double>());
        assertEquals(19.0, (double)vectorResult.elementAt(0), 0.001);
        assertEquals(25.0, (double)vectorResult.elementAt(1), 0.001);
    }

    //Test the addition of two vectors (with two integers)
    public void testVectorAddition3Polar() {
        Vector<Double> vector1 = new Vector<Double>();
        vector1.add(5.0);
        vector1.add(100.0);
        Vector<Double> vector2 = new Vector<Double>();
        vector2.add(57.0);
        vector2.add(180.0);
        Vector<Double> vector3 = new Vector<Double>();
        vector3.add(21.0);
        vector3.add(56.0);
        Vector<Double> vectorResult = VectorCalculator.vectorAdditionPolar(vector1, vector2, vector3);
        //This assertNotEquals fails the test if vectorResult does not contain any element
        assertNotSame(vectorResult, new Vector<Double>());
        assertEquals(51.248, (double) vectorResult.elementAt(0), 0.001);
        assertEquals(154.164, (double) vectorResult.elementAt(1), 0.001);
    }


        public void testVectorScalarCartesian() {
            Vector<Double> vector1 = new Vector<Double>();
            vector1.add(5.0);
            vector1.add(10.0);
            Vector<Double> vector2 = new Vector<Double>();
            vector2.add(6.0);
            vector2.add(3.0);
            assertEquals(60.0, VectorCalculator.vectorScalar(vector1, vector2), 0.001);
        }


        public void testVectorScalarPolar() {
            Vector<Double> vector1 = new Vector<Double>();
            vector1.add(5.0);
            vector1.add(100.0);
            Vector<Double> vector2 = new Vector<Double>();
            vector2.add(57.0);
            vector2.add(180.0);
            assertEquals(49.489, VectorCalculator.vectorScalarPolar(vector1,vector2), 0.01);
        }


    public void testVectorProductCartesian() {
        Vector<Double> vector1 = new Vector<Double>();
        vector1.add(5.0);
        vector1.add(10.0);
        Vector<Double> vector2 = new Vector<Double>();
        vector2.add(6.0);
        vector2.add(3.0);
        assertEquals(-45.0, VectorCalculator.vectorProductCartesian(vector1,vector2),0.01);
    }


    public void testVectorProductPolar() {
        Vector<Double> vector1 = new Vector<Double>();
        vector1.add(5.0);
        vector1.add(120.0);
        Vector<Double> vector2 = new Vector<Double>();
        vector2.add(32.0);
        vector2.add(40.0);
        assertEquals(157.57, VectorCalculator.vectorProductPolar(vector1,vector2),0.1);
    }
}




