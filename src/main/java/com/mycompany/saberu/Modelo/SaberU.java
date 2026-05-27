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
public class SaberU {
    private List<Aspirante> listaAspirantes;
    private List<Universidad> listaUniversidades;

    public SaberU() {}

    public SaberU(List<Aspirante> listaAspirantes, List<Universidad> listaUniversidades) {
        this.listaAspirantes = listaAspirantes;
        this.listaUniversidades = listaUniversidades;
    }

    public List<Aspirante> getListaAspirantes() { return listaAspirantes; }
    public void setListaAspirantes(List<Aspirante> listaAspirantes) { this.listaAspirantes = listaAspirantes; }

    public List<Universidad> getListaUniversidades() { return listaUniversidades; }
    public void setListaUniversidades(List<Universidad> listaUniversidades) { this.listaUniversidades = listaUniversidades; }

    public void iniciarSesion() {}
    public void registrarAspirante() {}
    public void consultarUniversidades() {}
    public void ejecutarComparacion() {}
    public void visualizarResultados() {}
    public void exportarInforme() {}
    public void cerrarSesion() {}
}