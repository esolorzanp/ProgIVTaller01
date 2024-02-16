package gui;

import model.Triangulo;
import util.Numbers;

import javax.swing.*;
import java.awt.event.*;

public class Punto25Gui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField ladoATextField;
    private JTextField ladoBTextField;
    private JTextField ladoCTextField;
    private JButton validarButton;
    private JButton limpiarCamposButton;
    private JTextField resultadoTextField;
    private JButton asignaValoresAleatoriosButton;

    public Punto25Gui() {
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
        asignaValoresAleatoriosButton.addActionListener(e -> asignaValoresAleatorios());

        this.setTitle("Punto 25 - Identificación de triángulos por la longitud de sus lados");
        this.setSize(600, 320);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }

    private void validar() {
        String strLadoA = ladoATextField.getText();
        String strLadoB = ladoBTextField.getText();
        String strLadoC = ladoCTextField.getText();
        if (strLadoA.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese un valor de lado a del triángulo", "Validación", JOptionPane.WARNING_MESSAGE);
        else if (strLadoB.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese un valor de lado b del triángulo", "Validación", JOptionPane.WARNING_MESSAGE);
        else if (strLadoC.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese un valor de lado c del triángulo", "Validación", JOptionPane.WARNING_MESSAGE);
        else {
            int a = Integer.parseInt(strLadoA);
            int b = Integer.parseInt(strLadoB);
            int c = Integer.parseInt(strLadoC);
            Triangulo t = new Triangulo(a, b, c);
            resultadoTextField.setText(t.getValidacion());
        }
    }

    private void limpiarCampos() {
        ladoATextField.setText("");
        ladoBTextField.setText("");
        ladoCTextField.setText("");
        resultadoTextField.setText("");
    }

    private void asignaValoresAleatorios() {
        int min = 1;
        int max = 20;
        int a = Numbers.calcAleatorio(min,max);
        int b = Numbers.calcAleatorio(min,max);
        int c = Numbers.calcAleatorio(min,max);
        ladoATextField.setText(String.valueOf(a));
        ladoBTextField.setText(String.valueOf(b));
        ladoCTextField.setText(String.valueOf(c));
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
        Punto25Gui dialog = new Punto25Gui();
        dialog.pack();
        System.exit(0);
    }
}
