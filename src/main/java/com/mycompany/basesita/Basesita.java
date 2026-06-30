package com.mycompany.basesita;

import javax.swing.SwingUtilities;

public class Basesita {

    public static void main(String[] args) {
        System.out.println("Iniciando la aplicación...");
        
        // Es una buena práctica arrancar las interfaces gráficas en su propio hilo
        SwingUtilities.invokeLater(() -> {
            VentanaAlumnos ventana = new VentanaAlumnos();
            ventana.setVisible(true);
        });
    }
}