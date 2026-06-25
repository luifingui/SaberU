/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saberu.modelo;

/**
 *
 * @author luife
 */
public class Carrera {
    private String nombreCarrera;
    private int idCarrera;
    private double puntajeCorte;
    private String facultad;

    public Carrera() {}

    public Carrera(String nombreCarrera, int idCarrera, double puntajeCorte, String facultad) {
        this.nombreCarrera = nombreCarrera;
        this.idCarrera = idCarrera;
        this.puntajeCorte = puntajeCorte;
        this.facultad = facultad;
    }

    public String getNombreCarrera() { return nombreCarrera; }
    public void setNombreCarrera(String nombreCarrera) { this.nombreCarrera = nombreCarrera; }

    public int getIdCarrera() { return idCarrera; }
    public void setIdCarrera(int idCarrera) { this.idCarrera = idCarrera; }

    public double getPuntajeCorte() { return puntajeCorte; }
    public void setPuntajeCorte(double puntajeCorte) { this.puntajeCorte = puntajeCorte; }

    public String getFacultad() { return facultad; }
    public void setFacultad(String facultad) { this.facultad = facultad; }

    public double obtenerCorte() {
        return puntajeCorte;
    }

    public boolean compararPuntaje(double puntajeAspirante) {
        return puntajeAspirante >= puntajeCorte;
    }

    public double calcularFaltante(double puntajeAspirante) {
        if (compararPuntaje(puntajeAspirante)) {
            return 0;
        }

        return puntajeCorte - puntajeAspirante;
    }

    public String obtenerInfoCarrera() {
        return nombreCarrera + " | Facultad: " + facultad + " | Corte: " + puntajeCorte;
    }

    public boolean aceptaPuntaje(double puntajeAspirante) {
        return compararPuntaje(puntajeAspirante);
    }
}
