/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saberu.modelo;

/**
 *
 * @author luife
 */
public class Aspirante extends Persona {
    private double puntajeSaber11;
    private String carreraA;
    private String carreraB;

    public Aspirante() {}

    public Aspirante(String nombre, long id, int edad, double puntajeSaber11, String carreraA, String carreraB) {
        super(nombre, id, edad); // herencia desde Persona
        this.puntajeSaber11 = puntajeSaber11;
        this.carreraA = carreraA;
        this.carreraB = carreraB;
    }

    // Getters y Setters
    public double getPuntajeSaber11() { return puntajeSaber11; }
    public void setPuntajeSaber11(double puntajeSaber11) { this.puntajeSaber11 = puntajeSaber11; }

    public String getCarreraA() { return carreraA; }
    public void setCarreraA(String carreraA) { this.carreraA = carreraA; }

    public String getCarreraB() { return carreraB; }
    public void setCarreraB(String carreraB) { this.carreraB = carreraB; }

    // Métodos del UML
    public void ingresarDatos() {}
    public void seleccionarCarreras() {}
    public void validarPuntaje() {}
    public void actualizarPreferencia() {}

    // REQUISITO 2.1: LIGADURA DINÁMICA
    // Sobrescritura del método de la clase padre para demostrar polimorfismo
    @Override
    public String obtenerDetalles() {
        return super.obtenerDetalles() + 
               " | Puntaje Saber 11: " + puntajeSaber11 +
               " | Carrera A: " + carreraA +
               " | Carrera B: " + (carreraB != null ? carreraB : "Ninguna");
    }

    // Implementación del método abstracto
    @Override
    public void obtenerPerfil() {
        System.out.println("Perfil del aspirante: " + nombre + 
                           " | Edad: " + edad + 
                           " | Puntaje Saber 11: " + puntajeSaber11);
    }
}
