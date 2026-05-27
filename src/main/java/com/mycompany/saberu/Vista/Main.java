/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.saberu.Vista;

/**
 *
 * @author luife
 */

import com.mycompany.saberu.Modelo.Aspirante;
import com.mycompany.saberu.Modelo.*;  
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        //Persona puede referirse a un Aspirante
        Persona aspirante = new Aspirante("Luifer", 12345, 18, 320.5, "Ingenieria de Sistemas", "Medicina");

        // Crear carreras
        Carrera carrera1 = new Carrera("Ingenieria de Sistemas", 101, 300.0, "Facultad de Ingenieria");
        Carrera carrera2 = new Carrera("Medicina", 102, 350.0, "Facultad de Ciencias de la Salud");

        //lista de carreras y universidad
        List<Carrera> listaCarreras = new ArrayList<>();
        listaCarreras.add(carrera1);
        listaCarreras.add(carrera2);

        Universidad universidad = new Universidad("Universidad de Cartagena", "Cartagena", listaCarreras, "https://unicartagena.edu.co", true);

        //resultados
        Resultados resultado = new Resultados();
        resultado.setIdAspirante(aspirante.getId());
        resultado.setEstadoFinal("Pendiente");

        // Mostrar info
        System.out.println("=== Sistema SaberU ===");
        aspirante.obtenerPerfil(); // uso del metodo abstracto 
        System.out.println("Universidad: " + universidad.getNombreUniversidad() + " | Ubicacion: " + universidad.getUbicacion());
        System.out.println("Carreras disponibles:");
        for (Carrera c : universidad.getListaCarreras()) {
            System.out.println(" - " + c.getNombreCarrera() + " (Corte: " + c.getPuntajeCorte() + ")");
        }
        System.out.println("Estado final: " + resultado.getEstadoFinal());
    
    }
}