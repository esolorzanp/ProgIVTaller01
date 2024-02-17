package gui;

import control.AspiranteCtrl;
import control.punto43.PersonasCtrl;
import model.Aspirante;
import model.punto43.Persona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Punto43Gui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable personasTable;
    private JTextField nombreTextField;
    private JTextField sexoTextField;
    private JTextField edadTextField;
    private JButton adicionarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton consultarButton;
    private JButton limpiarCamposButton;
    private JTextArea resultadoTtextArea;
    private JButton traerDatosButton;
    private PersonasCtrl personasCtrl;
    private DefaultTableModel dtm;
    private final int VALIDAR_TODOS_LOS_CAMPOS = 0;
    private final int VALIDAR_SOLO_CAMPOS_LLAVE = 1;

    public Punto43Gui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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
        eliminarButton.addActionListener(e -> eliminar());
        consultarButton.addActionListener(e -> consultar());
        limpiarCamposButton.addActionListener(e -> limpiarCampos());
        traerDatosButton.addActionListener(e -> traerDatos());

        personasTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int p = personasTable.getSelectedRow();
                setCamposForma(p);
            }
        });
        personasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int p = personasTable.getSelectedRow();
                setCamposForma(p);
            }
        });

        this.personasCtrl = new PersonasCtrl();
        this.dtm = new DefaultTableModel();
        actualizarTable();

        this.setTitle("Punto 43 - Personas asistentes a fiesta");
        this.setSize(700, 700);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }

    private Persona getCamposForma() {
        String strNombre = nombreTextField.getText();
        String strSexo = sexoTextField.getText();
        int intEdad = Integer.parseInt(edadTextField.getText());
        return new Persona(strNombre, strSexo.charAt(0), intEdad);
    }

    private void setCamposForma(int p) {
        String strNombre = (String) personasTable.getModel().getValueAt(p, 0);
        String strSexo = (String) personasTable.getModel().getValueAt(p, 1);
        String strEdad = (String) personasTable.getModel().getValueAt(p, 2);
        nombreTextField.setText(strNombre);
        sexoTextField.setText(strSexo);
        edadTextField.setText(strEdad);
    }

    // b:  0 --> Validar todos los campos
    //     1 --> Validar solo campos llave
    private boolean validarCamposForma(int b) {
        String strNombre = nombreTextField.getText();
        String strSexo = sexoTextField.getText();
        String strEdad = edadTextField.getText();
        if (strNombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Nombre no puede estar vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (strSexo.isEmpty() && b == VALIDAR_TODOS_LOS_CAMPOS) {
            JOptionPane.showMessageDialog(null, "Campo Sexo no puede estar vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (strEdad.isEmpty() && b == VALIDAR_TODOS_LOS_CAMPOS) {
            JOptionPane.showMessageDialog(null, "Campo Edad no puede estar vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!strSexo.isEmpty())
            if ((strSexo.charAt(0) != 'M' && strSexo.charAt(0) != 'F') && b == VALIDAR_TODOS_LOS_CAMPOS) {
                JOptionPane.showMessageDialog(null, "El sexo debe ser M o F", "Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        if (!strEdad.isEmpty()) {
            int intEdad = Integer.parseInt(strEdad);
            if ((intEdad < 18) && b == VALIDAR_TODOS_LOS_CAMPOS) {
                JOptionPane.showMessageDialog(null, "La edad debe ser mayor a 18 años", "Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void adicionar() {
        if (validarCamposForma(VALIDAR_TODOS_LOS_CAMPOS)) {
            Persona a = getCamposForma();
            if (!this.personasCtrl.existe(a.getNombre()))
                if (this.personasCtrl.adicionar(a)) {
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
            Persona p = getCamposForma();
            if (this.personasCtrl.existe(p.getNombre()))
                if (this.personasCtrl.modificar(p)) {
                    actualizarTable();
                    JOptionPane.showMessageDialog(null, "Registro modificado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "Registro no pudo ser modificado", "Error", JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Registro no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (validarCamposForma(VALIDAR_SOLO_CAMPOS_LLAVE)) {
            Persona a = getCamposForma();
            if (this.personasCtrl.existe(a.getNombre()))
                if (this.personasCtrl.eliminar(a.getNombre())) {
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
            String strNombre = nombreTextField.getText();
            if (this.personasCtrl.existe(strNombre)) {
                int p = personasCtrl.getIndex(strNombre);
                setCamposForma(p);
            } else
                JOptionPane.showMessageDialog(null, "Registro no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        nombreTextField.setText("");
        sexoTextField.setText("");
        edadTextField.setText("");
        resultadoTtextArea.setText("");
    }

    private void traerDatos() {
        String r = "Cantidad de personas asistentes a la fiesta: " + personasCtrl.getCantidad() + '\n'
                + "Cantidad de hombres: " + personasCtrl.getCantidadHombres() + '\n'
                + "Cantidad de mujeres: " + personasCtrl.getCantidadMujeres() + '\n'
                + "Promedio edad hombres: " + personasCtrl.getPromedioEdadHombres() + '\n'
                + "Promedio edad mujeres: " + personasCtrl.getPromedioEdadMujeres() + '\n'
                + "Edad persona mas joven: " + personasCtrl.getEdadMenor() + '\n'
                + "Edad persona mayor: " + personasCtrl.getEdadMayor() + '\n';
        resultadoTtextArea.setText(r);
    }

    private void actualizarTable() {
        this.dtm = new DefaultTableModel();
        this.dtm = this.personasCtrl.getModel();
        personasTable.setModel(dtm);
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
        Punto43Gui dialog = new Punto43Gui();
        dialog.pack();
        System.exit(0);
    }
}
