package gui;

import control.AspiranteCtrl;
import model.Aspirante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Punto55Gui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField aspiranteTextField;
    private JTextField notaTextField;
    private JTable aspirantesTable;
    private JButton adicionarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton consultarButton;
    private JButton promedioButton;
    private JButton cantidadAspirantesAprobadosButton;
    private JButton cantidadAspirantesReprobadosButton;
    private JTextField resultadoTextField;
    private JButton limpiarCamposButton;
    private AspiranteCtrl aspCtrl;
    private DefaultTableModel dtm;
    private final int VALIDAR_TODOS_LOS_CAMPOS = 0;
    private final int VALIDAR_SOLO_CAMPOS_LLAVE = 1;

    public Punto55Gui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        adicionarButton.addActionListener(e -> adicionar());
        modificarButton.addActionListener(e -> modificar());
        eliminarButton.addActionListener(e -> borrar());
        consultarButton.addActionListener(e -> consultar());
        limpiarCamposButton.addActionListener(e -> limpiarCampos());
        promedioButton.addActionListener(e -> calcularPromedio());
        cantidadAspirantesAprobadosButton.addActionListener(e -> getCantidadAspirantesAprobados());
        cantidadAspirantesReprobadosButton.addActionListener(e -> getCantidadAspirantesNoAprobados());

        aspirantesTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int p = aspirantesTable.getSelectedRow();
                setCamposForma(p);
            }
        });
        aspirantesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int p = aspirantesTable.getSelectedRow();
                setCamposForma(p);
            }
        });

        this.aspCtrl = new AspiranteCtrl();
        this.dtm = new DefaultTableModel();
        actualizarTable();

        this.setTitle("Punto 55 - Operaciones con grupo de estudiantes");
        this.setSize(700, 500);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }

    private Aspirante getCamposForma() {
        String strAspir = aspiranteTextField.getText();
        String strNota = notaTextField.getText();
        double dblNota = Double.parseDouble(strNota);
        return new Aspirante(strAspir, dblNota);
    }

    private void setCamposForma(int p) {
        String strAspir = (String) aspirantesTable.getModel().getValueAt(p, 0);
        String strNota = (String) aspirantesTable.getModel().getValueAt(p, 1);
        aspiranteTextField.setText(strAspir);
        notaTextField.setText(strNota);
    }

    // b:  0 --> Validar todos los campos
    //     1 --> Validar solo campos llave
    private boolean validarCamposForma(int b) {
        String strAspir = aspiranteTextField.getText();
        String strNota = notaTextField.getText();
        if (strAspir.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Aspirante no puede estar vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (strNota.isEmpty() && b == VALIDAR_TODOS_LOS_CAMPOS) {
            JOptionPane.showMessageDialog(null, "Campo Nota no puede estar vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (b == VALIDAR_TODOS_LOS_CAMPOS) {
            double dblNota = Double.parseDouble(strNota);
            if ((dblNota < 1.0 || dblNota > 5.0) && b == VALIDAR_TODOS_LOS_CAMPOS) {
                JOptionPane.showMessageDialog(null, "La nota debe tener valores entre 1.0 y 5.0", "Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void adicionar() {
        if (validarCamposForma(VALIDAR_TODOS_LOS_CAMPOS)) {
            Aspirante a = getCamposForma();
            if (!this.aspCtrl.existe(a.getAspirante()))
                if (this.aspCtrl.adicionar(a)) {
                    actualizarTable();
                    JOptionPane.showMessageDialog(null, "Registro guardado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "Registro no pudo ser guardado", "Error", JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Registro ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificar() {
        if (validarCamposForma(VALIDAR_TODOS_LOS_CAMPOS)) {
            Aspirante a = getCamposForma();
            if (this.aspCtrl.existe(a.getAspirante()))
                if (this.aspCtrl.modificar(a)) {
                    actualizarTable();
                    JOptionPane.showMessageDialog(null, "Registro modificado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "Registro no pudo ser modificado", "Error", JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Registro no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrar() {
        if (validarCamposForma(VALIDAR_SOLO_CAMPOS_LLAVE)) {
            Aspirante a = getCamposForma();
            if (this.aspCtrl.existe(a.getAspirante()))
                if (this.aspCtrl.eliminar(a.getAspirante())) {
                    actualizarTable();
                    JOptionPane.showMessageDialog(null, "Registro eliminado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "Registro no pudo ser eliminado", "Error", JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Registro no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultar() {
        if (validarCamposForma(VALIDAR_SOLO_CAMPOS_LLAVE)) {
            String strAspi = aspiranteTextField.getText();
            if (this.aspCtrl.existe(strAspi)) {
                int p = aspCtrl.getIndex(strAspi);
                setCamposForma(p);
            } else
                JOptionPane.showMessageDialog(null, "Registro no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        aspiranteTextField.setText("");
        notaTextField.setText("");
        resultadoTextField.setText("");
    }

    private void calcularPromedio() {
        if (aspCtrl.hayAspirantes()) {
            String r = String.valueOf(aspCtrl.getPromedio());
            resultadoTextField.setText("Promedio: " + r);
        } else
            JOptionPane.showMessageDialog(null, "No se ha cargado un registro de estudiantes", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void getCantidadAspirantesAprobados() {
        if (aspCtrl.hayAspirantes()) {
            String r = String.valueOf(aspCtrl.getCantidadAprobaron());
            resultadoTextField.setText("La cantidad de estudiantes que aprobaron es: " + r);
        } else
            JOptionPane.showMessageDialog(null, "No se ha cargado un registro de estudiantes", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void getCantidadAspirantesNoAprobados() {
        if (aspCtrl.hayAspirantes()) {
            String r = String.valueOf(aspCtrl.getCantidadNoAprobaron());
            resultadoTextField.setText("La cantidad de estudiantes que no aprobaron es: " + r);
        } else
            JOptionPane.showMessageDialog(null, "No se ha cargado un registro de estudiantes", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void actualizarTable() {
        this.dtm = new DefaultTableModel();
        this.dtm = this.aspCtrl.getModelAspirantes();
        aspirantesTable.setModel(dtm);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Punto55Gui dialog = new Punto55Gui();
        dialog.pack();
        System.exit(0);
    }
}
