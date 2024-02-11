package gui;

import model.Numeros;

import javax.swing.*;
import java.awt.event.*;

public class Punto21Gui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField primerNumeroTextField;
    private JTextField segundoNumeroTextField;
    private JButton validarButton;
    private JTextField resultadoTextField;
    private JButton limpiarCamposButton;

    public Punto21Gui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(300,300);
        setLocationRelativeTo(this);
        setVisible(true);

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

        validarButton.addActionListener(e -> validarCampos());
        limpiarCamposButton.addActionListener(e -> limpiarCampos());
    }

    private void validarCampos() {
        String spn = primerNumeroTextField.getText();
        String ssn = segundoNumeroTextField.getText();
        if (spn.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese un primer número", "Validación", JOptionPane.WARNING_MESSAGE);
        else if (ssn.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese un segundo número", "Validación", JOptionPane.WARNING_MESSAGE);
        else {
            Numeros n = new Numeros();
            n.setX(Integer.parseInt(spn));
            n.setY(Integer.parseInt(ssn));
            resultadoTextField.setText(String.valueOf(n.getValidacion()));
        }
    }

    private void limpiarCampos() {
        primerNumeroTextField.setText("");
        segundoNumeroTextField.setText("");
        resultadoTextField.setText("");
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
        Punto21Gui dialog = new Punto21Gui();
        dialog.pack();
//        dialog.setVisible(true);
        System.exit(0);
    }
}
