/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saberu.modelo;

/**
 *
 * @author luife
 */
public abstract class Persona {
protected String nombre;
    protected long id;
    protected int edad;

    public Persona() {}

    public Persona(String nombre, long id, int edad) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    // REQUISITO 2.1: LIGADURA DINÁMICA
    // Método que será sobrescrito en las clases hijas para demostrar polimorfismo
    public String obtenerDetalles() {
        return "Nombre: " + nombre + " | ID: " + id + " | Edad: " + edad;
    }

    public abstract void obtenerPerfil();
}
