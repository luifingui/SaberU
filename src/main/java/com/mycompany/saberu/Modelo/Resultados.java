/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saberu.Modelo;

import java.time.LocalDateTime;

/**
 *
 * @author luife
 */
public class Resultados {

    private long idAspirante;
    private boolean admitidoOpcionA;
    private boolean admitidoOpcionB;
    private double puntajeFaltante;
    private String estadoFinal;
    private LocalDateTime fechaGeneracion;

    public Resultados() {}

    public Resultados(long idAspirante, boolean admitidoOpcionA, boolean admitidoOpcionB, double puntajeFaltante, String estadoFinal, LocalDateTime fechaGeneracion) {
        this.idAspirante = idAspirante;
        this.admitidoOpcionA = admitidoOpcionA;
        this.admitidoOpcionB = admitidoOpcionB;
        this.puntajeFaltante = puntajeFaltante;
        this.estadoFinal = estadoFinal;
        this.fechaGeneracion = fechaGeneracion;
    }

    public long getIdAspirante() { return idAspirante; }
    public void setIdAspirante(long idAspirante) { this.idAspirante = idAspirante; }

    public boolean isAdmitidoOpcionA() { return admitidoOpcionA; }
    public void setAdmitidoOpcionA(boolean admitidoOpcionA) { this.admitidoOpcionA = admitidoOpcionA; }

    public boolean isAdmitidoOpcionB() { return admitidoOpcionB; }
    public void setAdmitidoOpcionB(boolean admitidoOpcionB) { this.admitidoOpcionB = admitidoOpcionB; }

    public double getPuntajeFaltante() { return puntajeFaltante; }
    public void setPuntajeFaltante(double puntajeFaltante) { this.puntajeFaltante = puntajeFaltante; }

    public String getEstadoFinal() { return estadoFinal; }
    public void setEstadoFinal(String estadoFinal) { this.estadoFinal = estadoFinal; }

    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }

    public void procesarAdmision() {}
    public void calcularDiferencia() {}
    public void generarEstadoFinal() {}
    public void sugerirCarreras() {}
    public void mostrarTablaComparativa() {}    
}
