package control;

import model.Aspirante;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class AspiranteCtrl {
    private List<Aspirante> aspirantes;

    public AspiranteCtrl() {
        this.aspirantes = new ArrayList<Aspirante>();
    }

    public boolean adicionar(Aspirante objAspir) {
        if (!this.existe(objAspir.getAspirante())) {
            this.aspirantes.add(objAspir);
            return true;
        }
        return false;
    }

    public boolean modificar(Aspirante objAspir) {
        if (this.existe(objAspir.getAspirante())) {
            int p = this.getIndex(objAspir.getAspirante());
            Aspirante ax = aspirantes.get(p);
            ax.setNota(objAspir.getNota());
            aspirantes.set(p, ax);
            return true;
        }
        return false;
    }

    public boolean eliminar(String strAspir) {
        if (this.existe(strAspir)) {
            int p = this.getIndex(strAspir);
            aspirantes.remove(p);
            return true;
        }
        return false;
    }

    public Aspirante get(String strAspir) {
        for (Aspirante a : aspirantes)
            if (a.getAspirante().equals(strAspir))
                return a;
        return null;
    }

    public Aspirante get(int index) {
        if (index <= aspirantes.size())
            return aspirantes.get(index);
        return null;
    }

    public int getIndex(String x) {
        for (Aspirante a : aspirantes)
            if (a.getAspirante().equals(x))
                return aspirantes.indexOf(a);
        return -1;
    }

    public boolean existe(String x) {
        for (Aspirante a : aspirantes)
            if (a.getAspirante().equals(x))
                return true;
        return false;
    }

    public List<Aspirante> getTodos() {
        List<Aspirante> a = new ArrayList<Aspirante>();
        return a;
    }

    public double getPromedio() {
        double prom = 0.0;
        for (Aspirante a : aspirantes)
            prom += a.getNota();
        return prom / aspirantes.size();
    }

    public int getCantidadAprobaron() {
        int n = 0;
        for (Aspirante a : aspirantes)
            if (a.getNota() >= 3.0)
                n++;
        return n;
    }

    public int getCantidadNoAprobaron() {
        int n = 0;
        for (Aspirante a : aspirantes)
            if (a.getNota() < 3.0)
                n++;
        return n;
    }

    public boolean hayAspirantes() {
        return aspirantes.size() > 0;
    }

    public DefaultTableModel getModelAspirantes() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Aspirante().getTitles());
        for (Aspirante a : aspirantes) {
            model.addRow(a.getData());
        }
        return model;
    }

    @Override
    public String toString() {
        return "AspiranteCtrl {" +
                "aspirantes=" + aspirantes +
                '}';
    }

    public static void main(String[] args) {
        AspiranteCtrl act = new AspiranteCtrl();
        Aspirante a1 = new Aspirante("edwin solorzano", 3.0);
        Aspirante a2 = new Aspirante("angela bolivar", 4.0);
        Aspirante a3 = new Aspirante("juan esteban", 4.1);

        act.adicionar(a1);
        act.adicionar(a2);
        act.adicionar(a3);
        System.out.println(act);

        System.out.println(act.getIndex("edwin"));
        System.out.println(act.getIndex("angela bolivar"));
        System.out.println(act.getIndex("juan esteban"));

        System.out.println(act.existe("edwin"));
        System.out.println(act.existe("angela bolivar"));
        System.out.println(act.existe("juan esteban"));

        String strA = "edwin solorzano";
        a1 = act.existe(strA) ? act.get(strA) : null;
        a1.setNota(4.3);
        act.modificar(a1);

        strA = "juan esteban";
        if (act.existe(strA)) {
            act.eliminar(strA);
            System.out.println("registro eliminado");
        }
        System.out.println(act);

        DefaultTableModel m = act.getModelAspirantes();
        System.out.println(m);

    }
}
