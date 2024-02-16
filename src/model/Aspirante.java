package model;

public class Aspirante {
    private String aspirante;
    private double nota;

    public Aspirante(String aspirante, double nota) {
        this.aspirante = aspirante;
        this.nota = nota;
    }

    public Aspirante(String aspirante) {
        this.aspirante = aspirante;
        this.nota = 0.0;
    }

    public Aspirante() {
        this.aspirante = "";
        this.nota = 0.0;
    }

    public String getAspirante() {
        return aspirante;
    }

    public void setAspirante(String aspirante) {
        this.aspirante = aspirante;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String[] getTitles() {
        return new String[]{
                "Aspirante",
                "Nota"
        };
    }

    public String[] getData() {
        return new String[]{
                this.getAspirante(),
                String.valueOf(this.getNota())
        };
    }

    @Override
    public String toString() {
        return "Aspirante {" +
                "aspirante='" + aspirante + '\'' +
                ", nota=" + nota +
                '}';
    }
}
