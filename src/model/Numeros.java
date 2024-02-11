package model;

import util.Numbers;

public class Numeros {
    private int x;
    private int y;

    public Numeros(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Numeros(boolean b) {
        if (b) {
            this.x = Numbers.calcAleatorio(1, 10);
            this.y = Numbers.calcAleatorio(1, 10);
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public Numeros() {
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean sonIguales() {
        return (x == y);
    }

    public boolean primeroEsMayor() {
        return (x > y);
    }

    public boolean segundoEsMayor() {
        return (x < y);
    }

    public int getValidacion() {
        int r = 0;
        if (sonIguales())
            r = x * y;
        else if (primeroEsMayor())
            r = x - y;
        else if (segundoEsMayor())
            r = x + y;
        return r;
    }

//    public int calcAleatorio(int min, int max) {
//        return (int) (Math.random() * (max - min + 1)) + min;
//    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Numeros n1 = new Numeros(true);
            System.out.println("1er número: " + n1.getX()
                    + '\n' + "2do número: " + n1.getY()
                    + '\n' + n1.getValidacion());
        }

    }
}
