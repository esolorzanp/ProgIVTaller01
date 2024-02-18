package model.punto51;

public class Estudiante {
    private String estudiante;
    private String carrera;
    private double nota1;
    private double nota2;
    private double nota3;
    private final String SISTEMAS = "Sistemas";
    private final String MECANICA = "Mecánica";
    private final String ELECTRONICA = "Electrónica";

    public Estudiante(String estudiante, String carrera, double nota1, double nota2, double nota3) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public Estudiante(String estudiante, String carrera, double nota1, double nota2) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = 0.0;
    }

    public Estudiante(String estudiante) {
        this.estudiante = estudiante;
        this.carrera = "";
        this.nota1 = 0.0;
        this.nota2 = 0.0;
        this.nota3 = 0.0;
    }

    public Estudiante() {
        this.estudiante = "";
        this.carrera = "";
        this.nota1 = 0.0;
        this.nota2 = 0.0;
        this.nota3 = 0.0;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public boolean esSistemas() {
        return this.getCarrera() == SISTEMAS;
    }

    public boolean esMecanica() {
        return this.getCarrera() == MECANICA;
    }

    public boolean esElectronica() {
        return this.getCarrera() == ELECTRONICA;
    }

    public double getNotaFinal() {
        if (this.esMecanica()) {
            return (this.nota1 > this.nota2) ? nota1 : nota2;
        }
        if (this.esElectronica()) {
            return (this.nota1 * 0.3) +
                    (this.nota2 * 0.3) +
                    (this.nota3 * 0.4);
        }
        if (this.esSistemas()) {
            return (this.nota1 + this.nota2 + this.nota3) / 3.0;
        }
        return 0.0;
    }

    public boolean esMateriaAprobada() {
        return this.getNotaFinal() >= 3.5;
    }

    public String[] getCarreras() {
        return new String[]{SISTEMAS, MECANICA, ELECTRONICA};
    }

    public String getResultadoTexto() {
        return "Nota 1: " + String.valueOf(this.getNota1()) + '\n'
                + "Nota 2: " + String.valueOf(this.getNota2()) + '\n'
                + ((this.esSistemas() || this.esElectronica()) ? ("Nota 3: " + String.valueOf(this.nota3) + '\n') : "")
                + "Nota final: " + String.valueOf(this.getNotaFinal()) + '\n'
                + "La materia es aprobada: " + (this.esMateriaAprobada() ? "Si" : "No");
    }

    public String[] getTitles() {
        return new String[]{"Estudiante", "Nota 1", "Nota 2", "Nota 3", "Nota final", "¿Materia aprobada?"};
    }

    public String[] getData() {
        return new String[]{
                this.getEstudiante(),
                String.valueOf(this.getNota1()),
                String.valueOf(this.getNota2()),
                String.valueOf(this.getNota3()),
                String.valueOf(this.getNotaFinal()),
                (this.esMateriaAprobada() ? "Si" : "No")
        };
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "estudiante='" + estudiante + '\'' +
                ", carrera='" + carrera + '\'' +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", nota3=" + nota3 +
                '}';
    }

    public static void main(String[] args) {
        Estudiante e1 = new Estudiante("laura", new Estudiante().MECANICA, 3.5, 2.7);
        Estudiante e2 = new Estudiante("laura", new Estudiante().SISTEMAS, 3.0, 3.5, 2.9);
        Estudiante e3 = new Estudiante("laura", new Estudiante().ELECTRONICA, 3.0, 2.9, 3.5);

        System.out.println(e1);
        System.out.println(e1.getResultadoTexto());
        System.out.println(e2);
        System.out.println(e2.esSistemas());
        System.out.println(e2.getResultadoTexto());
        System.out.println(e3);
        System.out.println(e3.esElectronica());
        System.out.println(e3.getResultadoTexto());

    }
}
