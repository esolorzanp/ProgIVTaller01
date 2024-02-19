package control.punto51;

import model.punto51.Estudiante;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class EstudianteCtrl {
    private List<Estudiante> estudiantes;

    public EstudianteCtrl() {
        this.estudiantes = new ArrayList<Estudiante>();
    }

    public boolean adicionar(Estudiante objEstud) {
        if (!this.existe(objEstud.getEstudiante())) {
            this.estudiantes.add(objEstud);
            return true;
        }
        return false;
    }

    public boolean modificar(Estudiante objEstud) {
        if (this.existe(objEstud.getEstudiante())) {
            int p = this.getIndex(objEstud.getEstudiante());
            Estudiante ex = estudiantes.get(p);
            ex.setCarrera(objEstud.getCarrera());
            ex.setNota1(objEstud.getNota1());
            ex.setNota2(objEstud.getNota2());
            if (ex.esSistemas() || ex.esElectronica())
                ex.setNota3(objEstud.getNota3());
            else
                ex.setNota3(0.0);
            estudiantes.set(p, ex);
            return true;
        }
        return false;
    }

    public boolean eliminar(String strEstud) {
        if (this.existe(strEstud)) {
            int p = this.getIndex(strEstud);
            estudiantes.remove(p);
            return true;
        }
        return false;
    }

    public Estudiante get(String strEstud) {
        for (Estudiante e : estudiantes)
            if (e.getEstudiante().equals(strEstud))
                return e;
        return null;
    }

    public Estudiante get(int index) {
        if (index <= estudiantes.size())
            return estudiantes.get(index);
        return null;
    }

    public int getIndex(String x) {
        for (Estudiante e : estudiantes)
            if (e.getEstudiante().equals(x))
                return estudiantes.indexOf(e);
        return -1;
    }

    public boolean existe(String x) {
        for (Estudiante e : estudiantes)
            if (e.getEstudiante().equals(x))
                return true;
        return false;
    }

    public List<Estudiante> getTodos() {
        List<Estudiante> es = new ArrayList<Estudiante>();
        return es;
    }

    public boolean hayEstudiantes() {
        return estudiantes.size() > 0;
    }

    public DefaultTableModel getModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Estudiante().getTitles());
        for (Estudiante a : estudiantes) {
            model.addRow(a.getData());
        }
        return model;
    }

    @Override
    public String toString() {
        return "EstudianteCtrl {" +
                "estudiantes= " + estudiantes +
                '}';
    }

    public static void main(String[] args) {
        EstudianteCtrl es = new EstudianteCtrl();

        es.adicionar(new Estudiante("laura", new Estudiante().MECANICA, 3.5, 2.7));
        es.adicionar(new Estudiante("pedro", new Estudiante().MECANICA, 3.5, 2.7));
        es.adicionar(new Estudiante("carolina", new Estudiante().SISTEMAS, 3.0, 3.5, 2.9));
        es.adicionar(new Estudiante("lina", new Estudiante().ELECTRONICA, 3.0, 2.9, 3.5));

        System.out.println(es);

        es.modificar(new Estudiante("carolina", new Estudiante().SISTEMAS, 4.0, 2.5, 3.9));
        es.eliminar("lina");
        System.out.println(es);
    }

}
