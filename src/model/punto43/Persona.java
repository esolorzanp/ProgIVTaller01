package model.punto43;

public class Persona {
    private String nombre;
    private char sexo;
    private int edad;

    public Persona(String nombre, char sexo, int edad) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
    }

    public Persona() {
        this.nombre = "";
        this.sexo = ' ';
        this.edad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona {" +
                "nombre='" + nombre + '\'' +
                ", sexo=" + sexo +
                ", edad=" + edad +
                '}';
    }

    public String[] getTitles() {
        return new String[]{
                "Nombre",
                "Sexo",
                "Edad"
        };
    }

    public String[] getData() {
        return new String[]{
                this.getNombre(),
                String.valueOf(this.getSexo()),
                String.valueOf(this.getEdad())
        };
    }
}
