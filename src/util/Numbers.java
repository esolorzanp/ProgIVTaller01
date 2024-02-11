package util;

public abstract class  Numbers {
    public static int calcAleatorio(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
