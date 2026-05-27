/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saberu.Modelo;

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

    public void registrarPrograma() {}
    public void obtenerCorte() {}
    public void compararPuntaje() {}
    public void actualizarCorte() {}
    public void obtenerInfoCarrera() {}   
}
