/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saberu.Modelo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    /**
     * Lee el archivo universidades.json y devuelve la lista de universidades.
     * Este metodo es estatico, por lo que no necesitas crear un objeto Universidad
     * para usarlo. Simplemente llamas: Universidad.cargarDesdeJson()
     */
    public static List<Universidad> cargarDesdeJson() {
        String contenidoJson = leerArchivoJson();
        return parsearUniversidades(contenidoJson);
    }

    private static String leerArchivoJson() {
        String ruta = "/datos/universidades.json";
        try (InputStream entrada = Universidad.class.getResourceAsStream(ruta)) {
            if (entrada == null) {
                throw new IllegalStateException("No se encontro el archivo: " + ruta);
            }
            return new String(entrada.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo leer el archivo: " + ruta, ex);
        }
    }

    private static List<Universidad> parsearUniversidades(String json) {
        List<Universidad> universidades = new ArrayList<>();
        String contenido = json.trim();

        if (!contenido.startsWith("[") || !contenido.endsWith("]")) {
            throw new IllegalStateException("El archivo debe ser un arreglo JSON.");
        }

        contenido = contenido.substring(1, contenido.length() - 1).trim();
        List<String> objetos = separarObjetos(contenido);

        for (String objeto : objetos) {
            universidades.add(parsearObjetoUniversidad(objeto));
        }

        return universidades;
    }

    private static Universidad parsearObjetoUniversidad(String objeto) {
        String nombre = extraerTexto(objeto, "nombreUniversidad");
        String ubicacion = extraerTexto(objeto, "ubicacion");
        String sitioWeb = extraerTexto(objeto, "sitioWeb");
        boolean esAcreditada = extraerBooleano(objeto, "esAcreditada");
        List<Carrera> carreras = parsearCarreras(extraerArreglo(objeto, "listaCarreras"));

        return new Universidad(nombre, ubicacion, carreras, sitioWeb, esAcreditada);
    }

    private static List<Carrera> parsearCarreras(String arregloCarreras) {
        List<Carrera> carreras = new ArrayList<>();
        List<String> objetos = separarObjetos(arregloCarreras);

        for (String objeto : objetos) {
            String nombre = extraerTexto(objeto, "nombreCarrera");
            int id = extraerEntero(objeto, "idCarrera");
            double puntaje = extraerDecimal(objeto, "puntajeCorte");
            String facultad = extraerTexto(objeto, "facultad");
            carreras.add(new Carrera(nombre, id, puntaje, facultad));
        }

        return carreras;
    }

    private static List<String> separarObjetos(String contenido) {
        List<String> objetos = new ArrayList<>();
        int nivelLlaves = 0;
        int inicio = -1;

        for (int i = 0; i < contenido.length(); i++) {
            char c = contenido.charAt(i);

            if (c == '{') {
                if (nivelLlaves == 0) {
                    inicio = i;
                }
                nivelLlaves++;
            } else if (c == '}') {
                nivelLlaves--;
                if (nivelLlaves == 0 && inicio >= 0) {
                    objetos.add(contenido.substring(inicio, i + 1));
                    inicio = -1;
                }
            }
        }

        return objetos;
    }

    private static String extraerArreglo(String objeto, String clave) {
        String busqueda = "\"" + clave + "\"";
        int posClave = objeto.indexOf(busqueda);
        if (posClave < 0) {
            throw new IllegalStateException("Falta la clave '" + clave + "' en el JSON.");
        }

        int inicio = objeto.indexOf('[', posClave);
        int fin = encontrarCierreArreglo(objeto, inicio);
        return objeto.substring(inicio + 1, fin);
    }

    private static int encontrarCierreArreglo(String texto, int inicio) {
        int nivel = 0;

        for (int i = inicio; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '[') {
                nivel++;
            } else if (c == ']') {
                nivel--;
                if (nivel == 0) {
                    return i;
                }
            }
        }

        throw new IllegalStateException("El arreglo JSON no esta bien cerrado.");
    }

    private static String extraerTexto(String objeto, String clave) {
        String busqueda = "\"" + clave + "\"";
        int posClave = objeto.indexOf(busqueda);
        if (posClave < 0) {
            throw new IllegalStateException("Falta la clave '" + clave + "' en el JSON.");
        }

        int inicioValor = objeto.indexOf('"', objeto.indexOf(':', posClave) + 1);
        int finValor = objeto.indexOf('"', inicioValor + 1);
        return objeto.substring(inicioValor + 1, finValor);
    }

    private static boolean extraerBooleano(String objeto, String clave) {
        String busqueda = "\"" + clave + "\"";
        int posClave = objeto.indexOf(busqueda);
        if (posClave < 0) {
            throw new IllegalStateException("Falta la clave '" + clave + "' en el JSON.");
        }

        int inicioValor = objeto.indexOf(':', posClave) + 1;
        String valor = objeto.substring(inicioValor).trim();
        return valor.startsWith("true");
    }

    private static int extraerEntero(String objeto, String clave) {
        return (int) Math.round(extraerDecimal(objeto, clave));
    }

    private static double extraerDecimal(String objeto, String clave) {
        String busqueda = "\"" + clave + "\"";
        int posClave = objeto.indexOf(busqueda);
        if (posClave < 0) {
            throw new IllegalStateException("Falta la clave '" + clave + "' en el JSON.");
        }

        int inicioValor = objeto.indexOf(':', posClave) + 1;
        while (inicioValor < objeto.length() && Character.isWhitespace(objeto.charAt(inicioValor))) {
            inicioValor++;
        }

        int finValor = inicioValor;
        while (finValor < objeto.length()) {
            char c = objeto.charAt(finValor);
            if (Character.isDigit(c) || c == '.' || c == '-') {
                finValor++;
            } else {
                break;
            }
        }

        return Double.parseDouble(objeto.substring(inicioValor, finValor).trim());
    }
}
