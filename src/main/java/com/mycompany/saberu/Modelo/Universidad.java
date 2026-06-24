/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saberu.Modelo;

import java.util.List;

/**
 *
 * @author luife
 */
public class Universidad {
    private String nombreUniversidad;
    private String ubicacion;
    private List<Carrera> listaCarreras;
    private String sitioWeb;
    private boolean esAcreditada;

    public Universidad() {}

    public Universidad(String nombreUniversidad, String ubicacion, List<Carrera> listaCarreras, String sitioWeb, boolean esAcreditada) {
        this.nombreUniversidad = nombreUniversidad;
        this.ubicacion = ubicacion;
        this.listaCarreras = listaCarreras;
        this.sitioWeb = sitioWeb;
        this.esAcreditada = esAcreditada;
    }

    public String getNombreUniversidad() { return nombreUniversidad; }
    public void setNombreUniversidad(String nombreUniversidad) { this.nombreUniversidad = nombreUniversidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public List<Carrera> getListaCarreras() { return listaCarreras; }
    public void setListaCarreras(List<Carrera> listaCarreras) { this.listaCarreras = listaCarreras; }

    public String getSitioWeb() { return sitioWeb; }
    public void setSitioWeb(String sitioWeb) { this.sitioWeb = sitioWeb; }

    public boolean isEsAcreditada() { return esAcreditada; }
    public void setEsAcreditada(boolean esAcreditada) { this.esAcreditada = esAcreditada; }

    public List<Carrera> consultarCarreras() {
        return listaCarreras;
    }

    public String obtenerEnlaceOficial() {
        return sitioWeb;
    }
}
