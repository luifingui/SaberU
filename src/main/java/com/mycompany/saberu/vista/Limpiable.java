/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.saberu.vista;

/**
 * REQUISITO 2.2: INTERFAZ LIMPIABLE
 * Interfaz que define el comportamiento para limpiar los campos de una vista.
 * Las clases que la implementan deben proporcionar lógica para reiniciar
 * los componentes de la interfaz a su estado inicial.
 * 
 * @author luife
 */
public interface Limpiable {
    
    /**
     * Limpia todos los campos de la interfaz y reinicia los estados.
     */
    void limpiarCampos();
}
