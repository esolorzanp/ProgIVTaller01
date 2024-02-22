package control.punto43;

import model.punto43.Persona;

import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;

public class PersonasArrayCtrl {
    private Persona[] personas;
    private int pos;
    private final int MAX_REC = 10;

    public PersonasArrayCtrl() {
        inicializaPersonas();
    }

    private void inicializaPersonas() {
        this.personas = new Persona[MAX_REC];

        pos = -1;
    }

    public boolean adicionar(Persona objPers) {
        if (pos < MAX_REC - 1)
            if (!this.existe(objPers.getNombre())) {
                pos++;
                this.personas[pos] = objPers;
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
            int posx = pos;
            inicializaPersonas();
            for (int i = 0; i <= posx; i++) {
                if (px[i].getNombre().equals(x.getNombre()))
                    continue;
                else {
                    pos++;
                    personas[pos] = px[i];
                }
            }
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
        if (index <= pos)
            return personas[index];
        return null;
    }

    public int getIndex(String strPers) {
        for (int i = 0; i <= pos; i++) {
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

//    public List<Persona> getTodos() {
//        List<Persona> p = new ArrayList<Persona>();
//        return p;
//    }

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
        if (pos > -1)
            for (int i = 0; i <= pos; i++) {
                model.addRow(personas[i].getData());
            }
        return model;
    }

    public String getResultados() {
        int cantPersonas = 0;
        int cantHombres = 0;
        int cantMujeres = 0;
        int acumEdadHombres = 0;
        int acumEdadMujeres = 0;
        int edadJoven = 900;
        int edadMayor = 0;
        for (Persona p : personas) {
            if (p != null) {
                cantPersonas++;
                if (p.getSexo() == 'M') {
                    cantHombres++;
                    acumEdadHombres += p.getEdad();
                }
                if (p.getSexo() == 'F') {
                    cantMujeres++;
                    acumEdadMujeres += p.getEdad();
                }
                if (p.getEdad() < edadJoven) edadJoven = p.getEdad();
                if (p.getEdad() > edadMayor) edadMayor = p.getEdad();
            }
        }
        return "Cantidad de personas asistentes a la fiesta: " + cantPersonas + '\n'
                + "Cantidad de hombres: " + cantHombres + '\n'
                + "Cantidad de mujeres: " + cantMujeres + '\n'
                + (cantHombres > 0 ? "Promedio edad hombres: " + (acumEdadHombres / cantHombres) + '\n' : "")
                + (cantMujeres > 0 ? "Promedio edad mujeres: " + (acumEdadMujeres / cantMujeres) + '\n' : "")
                + "Edad persona mas joven: " + edadJoven + '\n'
                + "Edad persona mayor: " + edadMayor + '\n';
    }

    @Override
    public String toString() {
        return "PersonasArrayCtrl {" +
                "personas=" + Arrays.toString(personas) +
                ", pos=" + pos +
                ", MAX_REC=" + MAX_REC +
                '}';
    }

}
