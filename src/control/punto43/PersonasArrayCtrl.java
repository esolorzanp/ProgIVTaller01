package control.punto43;

import model.punto43.Persona;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonasArrayCtrl {
    private Persona[] personas;
    private int pos;
    private final int MAX_REC = 50;

    public PersonasArrayCtrl() {
        this.personas = new Persona[MAX_REC];
        pos = 0;
    }

    public boolean adicionar(Persona objPers) {
        if (pos < MAX_REC)
            if (!this.existe(objPers.getNombre())) {
                this.personas[pos] = objPers;
                pos++;
                return true;
            }
        return false;
    }

    public boolean modificar(Persona objPers) {
        if (this.existe(objPers.getNombre())) {
            int p = this.getIndex(objPers.getNombre());
            Persona px = personas[p];
            px.setSexo(objPers.getSexo());
            px.setEdad(objPers.getEdad());
            personas[p] = px;
            return true;
        }
        return false;
    }

    public boolean eliminar(String strPers) {
        if (this.existe(strPers)) {
            int p = this.getIndex(strPers);
            Persona x = this.get(p);
            Persona[] px = personas.clone();
            personas = new Persona[MAX_REC];
            int j = 0;
            for (int i = 0; i < MAX_REC; i++) {
                if (px[i].getNombre().equals(x.getNombre()))
                    continue;
                else {
                    personas[j] = px[i];
                    j++;
                }
            }
            this.pos = j;
            return true;
        }
        return false;
    }

    public Persona get(String strPers) {
        for (Persona p : personas)
            if (p != null && p.getNombre().equals(strPers))
                return p;
        return null;
    }

    public Persona get(int index) {
        if (index <= MAX_REC)
            return personas[index];
        return null;
    }

    public int getIndex(String strPers) {
        for (int i = 0; i < MAX_REC; i++) {
            if (personas[i].getNombre().equals(strPers))
                return i;
        }
        return -1;
    }

    public boolean existe(String strPers) {
        for (Persona p : personas)
            if (p != null && p.getNombre().equals(strPers))
                return true;
        return false;
    }

    public List<Persona> getTodos() {
        List<Persona> p = new ArrayList<Persona>();
        return p;
    }

    public boolean hayPersonas() {
        for (int i = 0; i < MAX_REC; i++) {
            Persona p = personas[i];
            if (p != null)
                return true;
        }
        return false;
    }

    public DefaultTableModel getModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Persona().getTitles());
        for (Persona p : personas) {
            model.addRow(p.getData());
        }
        return model;
    }

    public int getCantidad() {
        return pos;
    }

    public int getCantidadHombres() {
        int n = 0;
        for (Persona p : personas)
            if (p != null && p.getSexo() == 'M')
                n++;
        return n;
    }

    public int getCantidadMujeres() {
        int n = 0;
        for (Persona p : personas)
            if (p != null && p.getSexo() == 'F')
                n++;
        return n;
    }

    public double getPromedioEdadHombres() {
        int s = 0;
        int c = 0;
        for (Persona p : personas)
            if (p != null && p.getSexo() == 'M') {
                s += p.getEdad();
                c++;
            }
        return (double) s / c;
    }

    public double getPromedioEdadMujeres() {
        int s = 0;
        int c = 0;
        for (Persona p : personas)
            if (p != null && p.getSexo() == 'F') {
                s += p.getEdad();
                c++;
            }
        return (double) s / c;
    }

    public int getEdadMenor() {
        int e = 999;
        for (Persona p : personas)
            if (p != null && p.getEdad() < e)
                e = p.getEdad();
        return e;
    }

    public int getEdadMayor() {
        int e = 0;
        for (Persona p : personas)
            if (p != null && p.getEdad() > e)
                e = p.getEdad();
        return e;
    }

    @Override
    public String toString() {
        return "PersonasArrayCtrl {" +
                "personas=" + Arrays.toString(personas) +
                ", pos=" + pos +
                ", MAX_REC=" + MAX_REC +
                '}';
    }

    public static void main(String[] args) {
        PersonasArrayCtrl p = new PersonasArrayCtrl();

        p.adicionar(new Persona("laura", 'F', 15));
        p.adicionar(new Persona("pedro", 'F', 244));
        p.adicionar(new Persona("Maria", 'F', 30));
        p.adicionar(new Persona("paco", 'M', 18));
        p.adicionar(new Persona("Santiago", 'M', 25));
        System.out.println(p);

        p.modificar(new Persona("pedro", 'M', 24));
        System.out.println(p);

        System.out.println("Cantidad hombres: " + p.getCantidadHombres());
        System.out.println("Cantidad mujeres: " + p.getCantidadMujeres());
        System.out.println("Promedio edad hombres: " + p.getPromedioEdadHombres());
        System.out.println("Promedio edad mujeres: " + p.getPromedioEdadMujeres());
        System.out.println("Edad persona mas joven: " + p.getEdadMenor());
        System.out.println("Edad persona mas mayor: " + p.getEdadMayor());
    }
}
