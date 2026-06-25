/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.saberu.excepciones;

/**
 * REQUISITO 2.3: EXCEPCIONES PERSONALIZADAS CON ALERTAS VISUALES
 * Excepción personalizada que se lanza cuando no se encuentra un elemento
 * (universidad, carrera o aspirante) durante una búsqueda.
 * 
 * @author luife
 */
public class ElementoNoEncontradoException extends Exception {
    
    public ElementoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
    
    public ElementoNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
