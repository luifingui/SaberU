/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.saberu.Vista;

import com.mycompany.saberu.Controlador.ControladorAdmision;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada del programa. Su unico trabajo es crear el
 * Controlador y mostrarle la Vista principal. Toda la logica real
 * vive en el Controlador y el Modelo.
 *
 * @author luife
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControladorAdmision controlador = new ControladorAdmision();
            VistaPrincipal vista = new VistaPrincipal(controlador);
            vista.setVisible(true);
        });
    }
}
