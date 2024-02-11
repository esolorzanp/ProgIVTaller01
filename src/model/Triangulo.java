package model;

import util.Numbers;

public class Triangulo {
    private int a;
    private int b;
    private int c;

    public Triangulo(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangulo(boolean b) {
        this.a = Numbers.calcAleatorio(1, 10);
        this.b = Numbers.calcAleatorio(1, 10);
        this.c = Numbers.calcAleatorio(1, 10);
    }

    public Triangulo() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getValidacion() {
        String s = "Triangulo es ";
        if (a == b && a == c && b == c)
            s += "equilatero";
        else if ((a == b && a != c) || (b == c && b != a) || (a == c && a != b))
            s += "isosceles";
        else if (a != b && a != c && b != c)
            s += "escaleno";
        return s;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Triangulo t = new Triangulo(true);
            System.out.println("Lado a: " + t.getA()
                    + '\n' + "Lado b: " + t.getB()
                    + '\n' + "Lado c: " + t.getC()
                    + '\n' + t.getValidacion());

        }
    }
}
