package gui;

import control.punto43.PersonasCtrl;
import control.punto51.EstudianteCtrl;
import model.punto43.Persona;
import model.punto51.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Punto51Gui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField estudianteTextField;
    private JTextField nota1TextField;
    private JTextField nota2TextField;
    private JTextField nota3TextField;
    private JTable estudiantesTable;
    private JButton traerResultadosButton;
    private JButton adicionarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton consultaButton;
    private JButton limpiarCamposButton;
    private JComboBox carreraComboBox;
    private JTextArea resultadosTextArea;
    private EstudianteCtrl estudianteCtrl;
    private DefaultTableModel dtm;
    private final int VALIDAR_TODOS_LOS_CAMPOS = 0;
    private final int VALIDAR_SOLO_CAMPOS_LLAVE = 1;

    public Punto51Gui() {
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
        consultaButton.addActionListener(e -> consulta());
        limpiarCamposButton.addActionListener(e -> limpiarCampos());
        traerResultadosButton.addActionListener(e -> traerResultados());
        carreraComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habilitarNota3();
            }
        });
        estudiantesTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int p = estudiantesTable.getSelectedRow();
                setCamposForma(p);
            }
        });
        estudiantesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int p = estudiantesTable.getSelectedRow();
                setCamposForma(p);
            }
        });

        carreraComboBox.addItem(new Estudiante().ELECTRONICA);
        carreraComboBox.addItem(new Estudiante().MECANICA);
        carreraComboBox.addItem(new Estudiante().SISTEMAS);
        carreraComboBox.setSelectedIndex(-1);
        nota3TextField.setEnabled(false);

        this.estudianteCtrl = new EstudianteCtrl();
        this.dtm = new DefaultTableModel();
        actualizarTable();

        this.setTitle("Punto 51 - Notas de estudiantes por carrera");
        this.setSize(600, 600);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }

    private Estudiante getCamposForma() {
        String strEstudiante = estudianteTextField.getText();
        String strCarrera = (String) carreraComboBox.getSelectedItem();
        String strNota1 = nota1TextField.getText();
        String strNota2 = nota2TextField.getText();
        String strNota3 = nota3TextField.getText();
        if (strCarrera == new Estudiante().MECANICA)
            return new Estudiante(strEstudiante,
                    strCarrera,
                    Double.parseDouble(strNota1),
                    Double.parseDouble(strNota2));
        else
            return new Estudiante(strEstudiante,
                    strCarrera,
                    Double.parseDouble(strNota1),
                    Double.parseDouble(strNota2),
                    Double.parseDouble(strNota3));
    }

    private void setCamposForma(int p) {
        String strEstudiante = (String) estudiantesTable.getModel().getValueAt(p, 0);
        String strCarrera = (String) estudiantesTable.getModel().getValueAt(p, 1);
        String strNota1 = (String) estudiantesTable.getModel().getValueAt(p, 2);
        String strNota2 = (String) estudiantesTable.getModel().getValueAt(p, 3);
        String strNota3 = (String) estudiantesTable.getModel().getValueAt(p, 4);
        if (strCarrera == new Estudiante().MECANICA)
            strNota3 = "0.0";
        estudianteTextField.setText(strEstudiante);
        carreraComboBox.setSelectedItem(strCarrera);
        nota1TextField.setText(strNota1);
        nota2TextField.setText(strNota2);
        nota3TextField.setText(strNota3);
    }

    // b:  0 --> Validar todos los campos
    //     1 --> Validar solo campos llave
    private boolean validarCamposForma(int b) {
        String strEstudiante = estudianteTextField.getText();
        String strCarrera = (String) carreraComboBox.getSelectedItem();
        String strNota1 = nota1TextField.getText();
        String strNota2 = nota2TextField.getText();
        String strNota3 = nota3TextField.getText();
        if (strEstudiante.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Estudiante no puede estar vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (strCarrera.isEmpty() && b == VALIDAR_TODOS_LOS_CAMPOS) {
            JOptionPane.showMessageDialog(null, "Campo Carrera no seleccionado", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (strNota1.isEmpty() && b == VALIDAR_TODOS_LOS_CAMPOS) {
            JOptionPane.showMessageDialog(null, "Campo Nota 1 no puede estar vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (strNota2.isEmpty() && b == VALIDAR_TODOS_LOS_CAMPOS) {
            JOptionPane.showMessageDialog(null, "Campo Nota 2 no puede estar vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (strNota3.isEmpty() && b == VALIDAR_TODOS_LOS_CAMPOS) {
            JOptionPane.showMessageDialog(null, "Campo Nota 3 no puede estar vacío", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!strNota1.isEmpty()) {
            double dblNota = Double.parseDouble(strNota1);
            if ((dblNota < 1.0 || dblNota > 5.0) && b == VALIDAR_TODOS_LOS_CAMPOS) {
                JOptionPane.showMessageDialog(null, "La Nota 1 debe estar entre 1.0 y 5.0", "Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        if (!strNota2.isEmpty()) {
            double dblNota = Double.parseDouble(strNota2);
            if ((dblNota < 1.0 || dblNota > 5.0) && b == VALIDAR_TODOS_LOS_CAMPOS) {
                JOptionPane.showMessageDialog(null, "La Nota 1 debe estar entre 1.0 y 5.0", "Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        if (!strNota3.isEmpty()) {
            double dblNota = Double.parseDouble(strNota3);
            if ((dblNota < 1.0 || dblNota > 5.0) && b == VALIDAR_TODOS_LOS_CAMPOS) {
                JOptionPane.showMessageDialog(null, "La Nota 1 debe estar entre 1.0 y 5.0", "Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void adicionar() {
        if (validarCamposForma(VALIDAR_TODOS_LOS_CAMPOS)) {
            Estudiante e = getCamposForma();
            if (!this.estudianteCtrl.existe(e.getEstudiante()))
                if (this.estudianteCtrl.adicionar(e)) {
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
            Estudiante e = getCamposForma();
            if (this.estudianteCtrl.existe(e.getEstudiante()))
                if (this.estudianteCtrl.modificar(e)) {
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
            Estudiante e = getCamposForma();
            if (this.estudianteCtrl.existe(e.getEstudiante()))
                if (this.estudianteCtrl.eliminar(e.getEstudiante())) {
                    actualizarTable();
                    JOptionPane.showMessageDialog(null, "Registro eliminado con éxito", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "Registro no pudo ser eliminado", "Error", JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Registro no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consulta() {
        if (validarCamposForma(VALIDAR_SOLO_CAMPOS_LLAVE)) {
            String strEstudiante = estudianteTextField.getText();
            if (this.estudianteCtrl.existe(strEstudiante)) {
                int p = estudianteCtrl.getIndex(strEstudiante);
                setCamposForma(p);
            } else
                JOptionPane.showMessageDialog(null, "Registro no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        estudianteTextField.setText("");
        carreraComboBox.setSelectedIndex(-1);
        nota1TextField.setText("");
        nota2TextField.setText("");
        nota3TextField.setText("");
        resultadosTextArea.setText("");
    }

    private void traerResultados() {
        if (validarCamposForma(VALIDAR_SOLO_CAMPOS_LLAVE)) {
            String strEstudiante = estudianteTextField.getText();
            if (this.estudianteCtrl.existe(strEstudiante)) {
                int p = estudianteCtrl.getIndex(strEstudiante);
                Estudiante e = estudianteCtrl.get(p);
                setCamposForma(p);
                resultadosTextArea.setText(e.getResultadoTexto());
            } else
                JOptionPane.showMessageDialog(null, "Registro no existe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTable() {
        this.dtm = new DefaultTableModel();
        this.dtm = this.estudianteCtrl.getModel();
        estudiantesTable.setModel(dtm);
    }

    private void habilitarNota3() {
        String strCarrera = (String) carreraComboBox.getSelectedItem();
        int p = carreraComboBox.getSelectedIndex();
        if (p != -1)
            if (!strCarrera.isEmpty() && strCarrera == new Estudiante().MECANICA)
                nota3TextField.setEnabled(false);
            else
                nota3TextField.setEnabled(true);
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
        Punto51Gui dialog = new Punto51Gui();
        dialog.pack();
        System.exit(0);
    }
}
