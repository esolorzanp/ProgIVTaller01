package gui;

import model.FacturacionCobroConsumoVvienda;
import util.Numbers;

import javax.swing.*;
import java.awt.event.*;

public class Punto31Gui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField consumoTextField;
    private JButton validarButton;
    private JButton limpiarCamposButton;
    private JButton asignarValoresAleatoriosButton;
    private JTextField resultadoTextField;

    public Punto31Gui() {
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
        validarButton.addActionListener(e -> validar());
        limpiarCamposButton.addActionListener(e -> limpiarCampos());
        asignarValoresAleatoriosButton.addActionListener(e -> asignarValoresAleatorios());

        this.setTitle("Punto 31 - Cálculo del gasto de consumo de agua en una vivienda");
        this.setSize(600,400);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }

    private void validar() {
        String strConsumo = consumoTextField.getText();
        if (strConsumo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese una cifra de consumo de agua en litros","Validación",JOptionPane.WARNING_MESSAGE);
        } else {
            int intConsumo = Integer.parseInt(strConsumo);
            FacturacionCobroConsumoVvienda fccv = new FacturacionCobroConsumoVvienda(intConsumo);
            resultadoTextField.setText(String.valueOf(fccv.getCobroFacturacionConsumo()));
        }
    }

    private void limpiarCampos() {
        consumoTextField.setText("");
        resultadoTextField.setText("");
    }

    private void asignarValoresAleatorios() {
        int min = 1;
        int max = 300;
        int a = Numbers.calcAleatorio(min,max);
        consumoTextField.setText(String.valueOf(a));
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
        Punto31Gui dialog = new Punto31Gui();
        dialog.pack();
        System.exit(0);
    }
}
