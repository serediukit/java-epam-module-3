package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    private double a;
    private double b;
    private double c;

    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {0, -1, 2},
                {0, -1, 3},
                {0, -1, 4},
                {0, 2, 5}
        });
    }


    @Test (expected = IllegalArgumentException.class)
    public void testQuadraticEquationZeroACasesTesting(){
        quadraticEquation.solve(a,b,c);
    }
}