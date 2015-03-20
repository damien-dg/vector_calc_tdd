package com.example.damien.vector_calc_tdd;

/**
 * Created by Damien on 2015-02-18.
 */import java.util.Vector;


public class VectorCalculator {

    static Vector<Double> vectorAddition (Vector<Double> vector1, Vector<Double> vector2){
        Vector<Double> vectorReturn = new Vector<Double>();
        //Adds x1 and x2 together
        double element1 = vector1.elementAt(0) + vector2.elementAt(0);
        //Adds y1 and y2 together
        double element2 = vector1.elementAt(1) + vector2.elementAt(1);
        vectorReturn.add(element1);
        vectorReturn.add(element2);
        return vectorReturn;
    }

    static Vector<Double> vectorAdditionPolar(Vector<Double> vector1, Vector<Double> vector2){
        Vector<Double> vectorReturn = new Vector<Double>();
        double x1 = vector1.elementAt(0) * formatCosin(Math.cos(Math.toRadians(vector1.elementAt(1))));
        double y1 = vector1.elementAt(0) * formatCosin(Math.sin(Math.toRadians(vector1.elementAt(1))));
        double x2 = vector2.elementAt(0) * formatCosin(Math.cos(Math.toRadians(vector2.elementAt(1))));
        double y2 = vector2.elementAt(0) * formatCosin(Math.sin(Math.toRadians(vector2.elementAt(1))));
        Vector<Double> vectorCartesian1 = new Vector<Double>();
        Vector<Double> vectorCartesian2 = new Vector<Double>();
        vectorCartesian1.add(x1);
        vectorCartesian1.add(y1);
        vectorCartesian2.add(x2);
        vectorCartesian2.add(y2);
        Vector<Double> vectorAdded = new Vector<Double>();
        vectorAdded = vectorAddition(vectorCartesian1, vectorCartesian2);
        double magnitude = format(Math.sqrt(Math.pow(vectorAdded.elementAt(0),2) + Math.pow(vectorAdded.elementAt(1), 2)));
        double direction = format(Math.toDegrees(Math.atan(vectorAdded.elementAt(1)/vectorAdded.elementAt(0))));
        double valueAddedBasedOnQuadrant = analyzeQuadrant(vectorAdded);
        vectorReturn.add(magnitude);
        vectorReturn.add(direction + valueAddedBasedOnQuadrant);
        return vectorReturn;
    }

    public static double analyzeQuadrant(Vector<Double> vectorAdded){
        double valueAdded = 0;
        if (vectorAdded.elementAt(0) < 0 && vectorAdded.elementAt(1) > 0){
            valueAdded = valueAdded + 180;
        } else if (vectorAdded.elementAt(0) < 0 && vectorAdded.elementAt(1) < 0){
            valueAdded = valueAdded + 180;
        } else if (vectorAdded.elementAt(0) > 0 && vectorAdded.elementAt(1) < 0){
            valueAdded = valueAdded + 360;
        }
        return valueAdded;
    }

    public static double formatCosin(double value) {
        return (double)Math.round(value * 100000) / 100000;
    }
    public static double format(double value) {
        return (double)Math.round(value * 1000) / 1000;
    }

    static Vector<Double> vectorAddition (Vector<Double> vector1, Vector<Double> vector2, Vector<Double> vector3) {
        Vector<Double> vectorReturn = new Vector<Double>();
        double element1 = vector1.elementAt(0) + vector2.elementAt(0) + vector3.elementAt(0);
        double element2 = vector1.elementAt(1) + vector2.elementAt(1) + vector3.elementAt(1);
        vectorReturn.add(element1);
        vectorReturn.add(element2);
        return vectorReturn;
    }
    static Vector<Double> vectorAdditionPolar(Vector<Double> vector1, Vector<Double> vector2, Vector<Double> vector3) {
        Vector<Double> vectorReturn = new Vector<Double>();
        double x1 = vector1.elementAt(0) * formatCosin(Math.cos(Math.toRadians(vector1.elementAt(1))));
        double y1 = vector1.elementAt(0) * formatCosin(Math.sin(Math.toRadians(vector1.elementAt(1))));
        double x2 = vector2.elementAt(0) * formatCosin(Math.cos(Math.toRadians(vector2.elementAt(1))));
        double y2 = vector2.elementAt(0) * formatCosin(Math.sin(Math.toRadians(vector2.elementAt(1))));
        double x3 = vector3.elementAt(0) * formatCosin(Math.cos(Math.toRadians(vector3.elementAt(1))));
        double y3 = vector3.elementAt(0) * formatCosin(Math.sin(Math.toRadians(vector3.elementAt(1))));
        Vector<Double> vectorCartesian1 = new Vector<Double>();
        Vector<Double> vectorCartesian2 = new Vector<Double>();
        Vector<Double> vectorCartesian3 = new Vector<Double>();
        vectorCartesian1.add(x1);
        vectorCartesian1.add(y1);
        vectorCartesian2.add(x2);
        vectorCartesian2.add(y2);
        vectorCartesian3.add(x3);
        vectorCartesian3.add(y3);
        Vector<Double> vectorAdded = new Vector<Double>();
        vectorAdded = vectorAddition(vectorCartesian1, vectorCartesian2, vectorCartesian3);
        double magnitude = format(Math.sqrt(Math.pow(vectorAdded.elementAt(0),2) + Math.pow(vectorAdded.elementAt(1), 2)));
        double direction = format(Math.toDegrees(Math.atan(vectorAdded.elementAt(1)/vectorAdded.elementAt(0))));
        double valueAddedBasedOnQuadrant = analyzeQuadrant(vectorAdded);
        vectorReturn.add(magnitude);
        vectorReturn.add(direction + valueAddedBasedOnQuadrant);
        return vectorReturn;
    }


    static double vectorScalar (Vector<Double> vector1, Vector<Double> vector2){
        double result = vector1.elementAt(0)*vector2.elementAt(0) + vector1.elementAt(1)*vector2.elementAt(1);
        return result;
    }

    static double vectorScalarPolar(Vector<Double> vector1, Vector<Double> vector2){
        double result = vector1.elementAt(0) * vector2.elementAt(0) * formatCosin(Math.cos(Math.toRadians(vector1.elementAt(1)-vector2.elementAt(1))));
        return result;
    }

    static double vectorProductCartesian(Vector<Double> vector1, Vector<Double> vector2){
        double result = vector1.elementAt(0)*vector2.elementAt(1) - vector1.elementAt(1)*vector2.elementAt(0);
        return result;
    }

    static double vectorProductPolar(Vector<Double> vector1, Vector<Double> vector2){
        double result = vector1.elementAt(0) * vector2.elementAt(0) * formatCosin(Math.sin(Math.toRadians(vector1.elementAt(1)-vector2.elementAt(1))));
        return result;
    }
    //x cross y = x1y2âˆ’x2y1
    //| a x b | = |a| . |b| . sine(theta)



}
