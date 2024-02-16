package gui;

import javax.swing.*;
import java.awt.event.*;

public class MainGui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton punto21Button;
    private JButton punto25Button;
    private JButton punto31Button;
    private JButton punto55Button;

    public MainGui() {
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
        punto21Button.addActionListener(e -> callPunto21());
        punto25Button.addActionListener(e -> callPunto25());
        punto31Button.addActionListener(e -> callPunto31());
        punto55Button.addActionListener(e -> callPunto55());

        this.setTitle("Menú principal - Taller sesión 01");
        this.setSize(500, 300);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
    }

    private void callPunto21() {
        Punto21Gui punto21Gui = new Punto21Gui();
    }

    private void callPunto25() {
        Punto25Gui punto25Gui = new Punto25Gui();
    }

    private void callPunto31() {
        Punto31Gui punto31Gui = new Punto31Gui();
    }

    private void callPunto55() { Punto55Gui punto55Gui = new Punto55Gui(); }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        MainGui dialog = new MainGui();
        dialog.pack();
        System.exit(0);
    }
}
