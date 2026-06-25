/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.saberu.controlador;

import com.mycompany.saberu.modelo.Aspirante;
import com.mycompany.saberu.modelo.Carrera;
import com.mycompany.saberu.modelo.ResultadoConsulta;
import com.mycompany.saberu.modelo.Universidad;
import com.mycompany.saberu.excepciones.ElementoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de admision.
 *
 * Esta clase es el puente entre la Vista (JFrame) y el Modelo
 * (Aspirante, Universidad, Carrera, etc). La Vista NUNCA habla
 * directamente con las clases del Modelo: solo le pide cosas a este
 * Controlador y este se encarga de mover los datos y aplicar la logica.
 *
 * Ventaja para el futuro: si despues se agrega Polimorfismo
 * (Universidades Nacionales vs Territoriales, como dice la
 * documentacion) o se cambia la fuente de datos (JSON, base de datos),
 * solo se modifica esta clase y el Modelo. La Vista queda intacta.
 *
 * @author luife
 */
public class ControladorAdmision {

    private final List<Universidad> universidades;

    public ControladorAdmision() {
        this.universidades = Universidad.cargarDesdeJson();
    }

    /**
     * Valida los datos basicos del aspirante antes de consultar.
     * Lanza IllegalArgumentException si algo no es valido, para que la
     * Vista lo capture y muestre un mensaje de error.
     */
    public void validarDatosAspirante(Aspirante aspirante) {
        if (aspirante.getNombre() == null || aspirante.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (aspirante.getPuntajeSaber11() < 0 || aspirante.getPuntajeSaber11() > 500) {
            throw new IllegalArgumentException("El puntaje Saber 11 debe estar entre 0 y 500.");
        }
        if (aspirante.getCarreraA() == null || aspirante.getCarreraA().isBlank()) {
            throw new IllegalArgumentException("Debe elegir una carrera principal.");
        }
    }

    /**
     * Busca en todas las universidades la carrera principal (Opcion A)
     * del aspirante.
     * REQUISITO 2.3: Lanza ElementoNoEncontradoException si no hay resultados.
     */
    public List<ResultadoConsulta> consultarCarreraPrincipal(Aspirante aspirante) throws ElementoNoEncontradoException {
        List<ResultadoConsulta> resultados = buscarPorCarrera(aspirante.getPuntajeSaber11(), aspirante.getCarreraA());
        if (resultados.isEmpty()) {
            throw new ElementoNoEncontradoException(
                "No se encontraron universidades que ofrezcan la carrera: " + aspirante.getCarreraA()
            );
        }
        return resultados;
    }

    /**
     * Busca en todas las universidades la carrera secundaria (Opcion B,
     * "plan de contingencia") del aspirante. Si no eligio una, devuelve
     * una lista vacia.
     */
    public List<ResultadoConsulta> consultarCarreraSecundaria(Aspirante aspirante) {
        if (aspirante.getCarreraB() == null || aspirante.getCarreraB().isBlank()) {
            return new ArrayList<>();
        }
        return buscarPorCarrera(aspirante.getPuntajeSaber11(), aspirante.getCarreraB());
    }

    /**
     * Genera el mensaje de la "Seccion de Analisis" que pide la
     * documentacion: que tan lejos o cerca quedo el aspirante de la
     * universidad donde no fue admitido.
     */
    public String generarMensajeAnalisis(List<ResultadoConsulta> resultadosCarreraA,
            List<ResultadoConsulta> resultadosCarreraB) {

        List<ResultadoConsulta> todos = new ArrayList<>();
        todos.addAll(resultadosCarreraA);
        todos.addAll(resultadosCarreraB);

        if (todos.isEmpty()) {
            return "Ninguna universidad registrada ofrece las carreras seleccionadas.";
        }

        int admitidos = 0;
        ResultadoConsulta masCercano = null;

        for (ResultadoConsulta resultado : todos) {
            if (resultado.isAdmitido()) {
                admitidos++;
            } else if (masCercano == null || resultado.getPuntajeFaltante() < masCercano.getPuntajeFaltante()) {
                masCercano = resultado;
            }
        }

        if (admitidos == todos.size()) {
            return "Felicitaciones, el puntaje alcanza para todas las opciones consultadas.";
        }

        return "La opcion mas cercana donde no alcanzo el puntaje es " + masCercano.getNombreCarrera()
                + " en " + masCercano.getNombreUniversidad()
                + ". Le faltan " + masCercano.getPuntajeFaltante() + " puntos.";
    }

    /**
     * Devuelve los nombres de todas las carreras que existen en alguna
     * universidad del repositorio. La Vista usa esto para llenar los
     * combos de seleccion, en vez de dejar que el usuario escriba el
     * nombre a mano (asi se evitan errores de digitacion).
     */
    public List<String> obtenerCarrerasDisponibles() {
        List<String> carreras = new ArrayList<>();
        for (Universidad universidad : universidades) {
            for (Carrera carrera : universidad.consultarCarreras()) {
                if (!carreras.contains(carrera.getNombreCarrera())) {
                    carreras.add(carrera.getNombreCarrera());
                }
            }
        }
        return carreras;
    }

    private List<ResultadoConsulta> buscarPorCarrera(double puntajeAspirante, String nombreCarreraBuscada) {
        List<ResultadoConsulta> resultados = new ArrayList<>();

        for (Universidad universidad : universidades) {
            for (Carrera carrera : universidad.consultarCarreras()) {
                if (carrera.getNombreCarrera().equalsIgnoreCase(nombreCarreraBuscada)) {
                    boolean admitido = carrera.aceptaPuntaje(puntajeAspirante);
                    double faltante = carrera.calcularFaltante(puntajeAspirante);

                    resultados.add(new ResultadoConsulta(
                            universidad.getNombreUniversidad(),
                            carrera.getNombreCarrera(),
                            carrera.getPuntajeCorte(),
                            admitido,
                            faltante,
                            universidad.obtenerEnlaceOficial()
                    ));
                }
            }
        }

        return resultados;
    }
}
