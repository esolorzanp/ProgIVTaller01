package gui;

import model.Numeros;
import util.Numbers;

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
    private JButton asignaValoresAleatoriosButton;

    public Punto21Gui() {
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

        validarButton.addActionListener(e -> validarCampos());
        limpiarCamposButton.addActionListener(e -> limpiarCampos());
        asignaValoresAleatoriosButton.addActionListener(e -> asignaValoresAleatorios());

        this.setTitle("Punto 21 - Operaciones lógicas con números");
        this.setSize(500, 300);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }

    private void validarCampos() {
        String strPn = primerNumeroTextField.getText();
        String strSn = segundoNumeroTextField.getText();
        if (strPn.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese un primer número", "Validación", JOptionPane.WARNING_MESSAGE);
        else if (strSn.isEmpty())
            JOptionPane.showMessageDialog(null, "Ingrese un segundo número", "Validación", JOptionPane.WARNING_MESSAGE);
        else {
            int pn = Integer.parseInt(strPn);
            int sn = Integer.parseInt(strSn);
            Numeros n = new Numeros(pn, sn);
            resultadoTextField.setText(String.valueOf(n.getValidacion()));
        }
    }

    private void limpiarCampos() {
        primerNumeroTextField.setText("");
        segundoNumeroTextField.setText("");
        resultadoTextField.setText("");
    }


    private void asignaValoresAleatorios() {
        int min = 1;
        int max = 20;
        int pn = Numbers.calcAleatorio(min,max);
        int sn = Numbers.calcAleatorio(min,max);
        primerNumeroTextField.setText(String.valueOf(pn));
        segundoNumeroTextField.setText(String.valueOf(sn));
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
