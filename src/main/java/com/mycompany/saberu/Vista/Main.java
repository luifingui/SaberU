/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.saberu.Vista;

/**
 *
 * @author luife
 */

import com.mycompany.saberu.Modelo.Aspirante;
import com.mycompany.saberu.Modelo.Carrera;
import com.mycompany.saberu.Modelo.Universidad;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Path rutaJson = Path.of("src", "main", "resources", "datos", "universidades.json");
            String datosJson = Files.readString(rutaJson);
            int cantidadUniversidades = datosJson.split("\"nombreUniversidad\"").length - 1;
            
            System.out.println("=== Sistema SaberU ===");
            System.out.println("Conexion con JSON realizada.");
            System.out.println("Universidades encontradas en el archivo: " + cantidadUniversidades);

           
            //
            System.out.println("Caracteres leidos del archivo: " + datosJson.length());

            Aspirante aspirante = new Aspirante("Luifer", 12345, 18, 320.5, "Ingenieria de Sistemas", "Medicina");
            validarAspirante(aspirante);

            Carrera carrera1 = new Carrera("Ingenieria de Sistemas", 101, 300.0, "Facultad de Ingenieria");
            Carrera carrera2 = new Carrera("Medicina", 102, 350.0, "Facultad de Ciencias de la Salud");

            List<Carrera> listaCarreras = new ArrayList<>();
            listaCarreras.add(carrera1);
            listaCarreras.add(carrera2);

            Universidad universidad = new Universidad(
                    "Universidad de Cartagena",
                    "Cartagena",
                    listaCarreras,
                    "https://unicartagena.edu.co",
                    true);

            System.out.println();
            aspirante.obtenerPerfil();
            consultarOpcion(aspirante, universidad, carrera1);
            consultarOpcion(aspirante, universidad, carrera2);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Dato invalido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e.getMessage());
        }
    }

    private static void validarAspirante(Aspirante aspirante) {
        if (aspirante.getPuntajeSaber11() < 0 || aspirante.getPuntajeSaber11() > 500) {
            throw new IllegalArgumentException("El puntaje Saber 11 debe estar entre 0 y 500.");
        }

        if (aspirante.getCarreraA() == null || aspirante.getCarreraA().isBlank()) {
            throw new IllegalArgumentException("Debe elegir una carrera como primera opcion.");
        }
    }

    private static void consultarOpcion(Aspirante aspirante, Universidad universidad, Carrera carrera) {
        boolean admitido = carrera.aceptaPuntaje(aspirante.getPuntajeSaber11());
        double faltante = admitido ? 0 : carrera.getPuntajeCorte() - aspirante.getPuntajeSaber11();
        String estado = admitido ? "Puede aspirar" : "No alcanza el corte";

        System.out.println(universidad.getNombreUniversidad() + " - " + carrera.getNombreCarrera()
                + " | " + estado + " | " + mensajeFaltante(admitido, faltante));
    }

    private static String mensajeFaltante(boolean admitido, double faltante) {
        if (admitido) {
            return "Cumple el corte";
        }

        return "Le faltan " + faltante + " puntos";
    }
}
