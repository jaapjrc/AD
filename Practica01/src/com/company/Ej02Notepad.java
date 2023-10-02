package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Ej02Notepad extends JFrame {

    private boolean yaGuardado;
    private JTextArea txtTextoFichero;
    private JButton btnGuardar, btnAbrir, btnGuardarComo;
    private JLabel lblFichero;
    private String nombreFichero;
    private String pathFichero;

    public Ej02Notepad() {
        nombreFichero = "Documento 1";
        lblFichero = new JLabel(nombreFichero);
        txtTextoFichero = new JTextArea();
        btnAbrir = new JButton("Abrir");
        btnGuardar = new JButton("Guardar");
        btnGuardarComo = new JButton("Guardar como");

        JPanel panelBotones = new JPanel();

        MiListener miListener = new MiListener();

        // attach our action listener to the buttons
        btnGuardar.addActionListener(miListener);
        btnAbrir.addActionListener(miListener);
        btnGuardarComo.addActionListener(miListener);

        panelBotones.add(btnAbrir);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnGuardarComo);

        this.setLayout(new BorderLayout());
        add(lblFichero, BorderLayout.NORTH);
        add(txtTextoFichero, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(400, 300));
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        new Ej02Notepad();
    }

    class MiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();

            // Figure out which button was pressed
            if (sourceButton.equals(btnAbrir)) {
                JFileChooser jfc = new JFileChooser();
                int returnVal = jfc.showDialog(jfc, "Abrir");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    yaGuardado = true;
                    nombreFichero = jfc.getSelectedFile().getName();
                    pathFichero = jfc.getSelectedFile().getAbsolutePath();
                    try {
                        //Files.lines(Path.of(pathFichero)).forEach(s->{txtTextoFichero.setText(txtTextoFichero.getText() + s + "\n");});
                        BufferedReader buffer = new BufferedReader(new FileReader(pathFichero));
                        String linea = null;
                        String aux = "";
                        while ((linea = buffer.readLine()) != null) aux += linea + "\n";
                        txtTextoFichero.setText(aux);
                        lblFichero.setText(nombreFichero);
                    } catch (Exception fnfe) {
                        fnfe.printStackTrace();
                    }
                }
            } else if (sourceButton.equals(btnGuardar)) {
                if (yaGuardado) {
                    FileWriter fichero = null;
                    try {
                        fichero = new FileWriter(pathFichero, false);
                        PrintWriter escritor = new PrintWriter(fichero);
                        escritor.println(txtTextoFichero.getText());
                        escritor.close();
                        fichero.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JFileChooser jfc = new JFileChooser();
                    int returnVal = jfc.showSaveDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        yaGuardado = true;
                        nombreFichero = jfc.getSelectedFile().getName();
                        pathFichero = jfc.getSelectedFile().getAbsolutePath();
                        try {
                            nombreFichero = jfc.getSelectedFile().getName();
                            FileWriter fichero = new FileWriter(jfc.getSelectedFile().getAbsolutePath(), false);
                            PrintWriter escritor = new PrintWriter(fichero);
                            escritor.println(txtTextoFichero.getText());
                            escritor.close();
                            fichero.close();
                            lblFichero.setText(nombreFichero);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }
            } else if (sourceButton.equals(btnGuardarComo)) {
                JFileChooser jfc = new JFileChooser();
                int returnVal = jfc.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    yaGuardado = true;
                    nombreFichero = jfc.getSelectedFile().getName();
                    pathFichero = jfc.getSelectedFile().getAbsolutePath();
                    try {
                        nombreFichero = jfc.getSelectedFile().getName();
                        FileWriter fichero = new FileWriter(jfc.getSelectedFile().getAbsolutePath(), false);
                        PrintWriter escritor = new PrintWriter(fichero);
                        escritor.println(txtTextoFichero.getText());
                        escritor.close();
                        fichero.close();
                        lblFichero.setText(nombreFichero);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            } else {
                System.out.println("Unknown button pressed");
            }
        }
    }
}
