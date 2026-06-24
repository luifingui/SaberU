/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.saberu.vista;

import com.mycompany.saberu.controlador.ControladorAdmision;
import com.mycompany.saberu.modelo.Aspirante;
import com.mycompany.saberu.modelo.Persona;
import com.mycompany.saberu.modelo.ResultadoConsulta;
import com.mycompany.saberu.excepciones.ElementoNoEncontradoException;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Vista principal de Saber U.
 *
 * Esta clase SOLO se encarga de mostrar la interfaz y de leer/escribir
 * cosas en pantalla. No contiene logica de negocio (no calcula nada,
 * no decide si alguien fue admitido): para eso siempre le pregunta al
 * Controlador. Asi, si la profesora pide agregar mas funcionalidad
 * (mas universidades, mas filtros, otra ventana, etc.), la Vista se
 * puede ampliar sin tener que entender ni tocar la logica del Modelo.
 *
 * @author luife
 */
public class VistaPrincipal extends JFrame implements Limpiable {

    private static final String OPCION_SIN_CARRERA = "-- Ninguna --";

    private final ControladorAdmision controlador;

    private JTextField txtNombre;
    private JTextField txtIdentificacion;
    private JTextField txtEdad;
    private JTextField txtPuntaje;
    private JComboBox<String> cmbCarreraPrincipal;
    private JComboBox<String> cmbCarreraSecundaria;
    private JButton btnConsultar;
    private JButton btnLimpiar;

    private DefaultTableModel modeloTabla;
    private JTable tablaResultados;
    private JLabel lblAnalisis;
    private JTextArea txtDetallesAspirante;

    public VistaPrincipal(ControladorAdmision controlador) {
        this.controlador = controlador;
        construirInterfaz();
    }

    private void construirInterfaz() {
        setTitle("Saber U - Consulta de Admision");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        add(crearPanelFormulario(), BorderLayout.NORTH);
        add(crearPanelResultados(), BorderLayout.CENTER);
        add(crearPanelAnalisis(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelFormulario() {
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Datos del aspirante"),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));

        txtNombre = new JTextField(20);
        txtIdentificacion = new JTextField(20);
        txtEdad = new JTextField(8);
        txtPuntaje = new JTextField(8);

        cmbCarreraPrincipal = new JComboBox<>(obtenerCarrerasParaCombo(false));
        cmbCarreraSecundaria = new JComboBox<>(obtenerCarrerasParaCombo(true));

        int fila = 0;
        agregarCampoFormulario(panelCampos, new JLabel("Nombre:"), txtNombre, fila++);
        agregarCampoFormulario(panelCampos, new JLabel("Identificacion:"), txtIdentificacion, fila++);
        agregarCampoFormulario(panelCampos, new JLabel("Edad:"), txtEdad, fila++);
        agregarCampoFormulario(panelCampos, new JLabel("Puntaje Saber 11 (0-500):"), txtPuntaje, fila++);
        agregarCampoFormulario(panelCampos, new JLabel("Carrera principal (Opcion A):"), cmbCarreraPrincipal, fila++);
        agregarCampoFormulario(panelCampos, new JLabel("Carrera secundaria (Opcion B):"), cmbCarreraSecundaria, fila);

        btnConsultar = new JButton("Consultar Admision");
        btnConsultar.addActionListener(e -> consultarAdmision());

        // REQUISITO 2.2: Botón para limpiar formulario (interfaz Limpiable)
        btnLimpiar = new JButton("Limpiar Formulario");
        btnLimpiar.addActionListener(e -> limpiarCampos());

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 8, 0));
        panelBotones.add(btnConsultar);
        panelBotones.add(btnLimpiar);

        JPanel panelBoton = new JPanel(new BorderLayout());
        panelBoton.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        panelBoton.add(panelBotones, BorderLayout.EAST);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(panelCampos, BorderLayout.CENTER);
        contenedor.add(panelBoton, BorderLayout.SOUTH);

        return contenedor;
    }

    /**
     * Coloca una fila del formulario: etiqueta a la izquierda, campo a la
     * derecha. GridBagLayout permite que cada fila tenga su propio tamano
     * sin forzar celdas iguales como GridLayout.
     */
    private void agregarCampoFormulario(JPanel panel, JLabel etiqueta, Component campo, int fila) {
        GridBagConstraints gbcEtiqueta = new GridBagConstraints();
        gbcEtiqueta.gridx = 0;
        gbcEtiqueta.gridy = fila;
        gbcEtiqueta.anchor = GridBagConstraints.WEST;
        gbcEtiqueta.insets = new Insets(6, 4, 6, 12);
        panel.add(etiqueta, gbcEtiqueta);

        GridBagConstraints gbcCampo = new GridBagConstraints();
        gbcCampo.gridx = 1;
        gbcCampo.gridy = fila;
        gbcCampo.weightx = 1.0;
        gbcCampo.fill = GridBagConstraints.HORIZONTAL;
        gbcCampo.insets = new Insets(6, 0, 6, 4);
        panel.add(campo, gbcCampo);
    }

    private JScrollPane crearPanelResultados() {
        String[] columnas = {"Opcion", "Universidad", "Carrera", "Puntaje de Corte", "Estado", "Puntos Faltantes"};

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaResultados = new JTable(modeloTabla);
        tablaResultados.setRowHeight(24);
        tablaResultados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ajustarAnchoColumnasTabla();

        JScrollPane scroll = new JScrollPane(tablaResultados);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Resultados de la consulta"),
                BorderFactory.createEmptyBorder(4, 8, 8, 8)));
        return scroll;
    }

    private void ajustarAnchoColumnasTabla() {
        int[] anchos = {110, 200, 180, 120, 100, 120};
        for (int i = 0; i < anchos.length; i++) {
            tablaResultados.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }

    private JPanel crearPanelAnalisis() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Analisis"),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        lblAnalisis = new JLabel("Ingrese sus datos y presione \"Consultar Admision\".");
        panel.add(lblAnalisis, BorderLayout.NORTH);
        
        // REQUISITO 2.1: Área para mostrar detalles usando ligadura dinámica
        txtDetallesAspirante = new JTextArea(4, 50);
        txtDetallesAspirante.setEditable(false);
        txtDetallesAspirante.setBorder(BorderFactory.createTitledBorder("Detalles del Aspirante (Ligadura Dinámica)"));
        panel.add(new JScrollPane(txtDetallesAspirante), BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Accion del boton: lee el formulario, le pide al Controlador que
     * haga la consulta y pinta los resultados en la tabla.
     * REQUISITO 2.3: Lanza ElementoNoEncontradoException si campos están vacíos.
     * REQUISITO 2.5: Usa hilos para procesamiento asíncrono.
     */
    private void consultarAdmision() {
        // REQUISITO 2.5: Iniciar hilo para procesamiento asíncrono
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Actualizar etiqueta en el hilo de Swing
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        lblAnalisis.setText("Procesando consulta en segundo plano...");
                    });
                    
                    // REQUISITO 2.3: Validar que campos no estén vacíos
                    if (txtNombre.getText().trim().isEmpty() || 
                        txtIdentificacion.getText().trim().isEmpty() ||
                        txtPuntaje.getText().trim().isEmpty()) {
                        throw new ElementoNoEncontradoException("Datos no encontrados o campos vacíos");
                    }
                    
                    Aspirante aspirante = construirAspiranteDesdeFormulario();
                    controlador.validarDatosAspirante(aspirante);

                    // REQUISITO 2.3: Manejo de excepción personalizada
                    List<ResultadoConsulta> resultadosA = controlador.consultarCarreraPrincipal(aspirante);
                    List<ResultadoConsulta> resultadosB = controlador.consultarCarreraSecundaria(aspirante);

                    // REQUISITO 2.5: Simular procesamiento
                    Thread.sleep(1000);
                    
                    // Mostrar resultados en el hilo de Swing
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        mostrarResultados(resultadosA, resultadosB);

                        String mensaje = controlador.generarMensajeAnalisis(resultadosA, resultadosB);
                        lblAnalisis.setText(mensaje);

                        // REQUISITO 2.1: LIGADURA DINÁMICA
                        // Usamos referencia de la clase padre (Persona) para llamar al método
                        Persona persona = aspirante; // Upcasting: Aspirante -> Persona
                        String detalles = persona.obtenerDetalles(); // Ligadura dinámica en ejecución
                        txtDetallesAspirante.setText(detalles);
                        
                        lblAnalisis.setText("¡Consulta Completada con éxito!");
                    });
                    
                } catch (NumberFormatException ex) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(VistaPrincipal.this,
                                "Identificacion, edad y puntaje deben ser numeros validos.",
                                "Dato invalido", JOptionPane.ERROR_MESSAGE);
                        lblAnalisis.setText("Error en la consulta");
                    });
                } catch (IllegalArgumentException ex) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(VistaPrincipal.this, ex.getMessage(), "Dato invalido", JOptionPane.ERROR_MESSAGE);
                        lblAnalisis.setText("Error en la consulta");
                    });
                } catch (ElementoNoEncontradoException ex) {
                    // REQUISITO 2.3: Alerta visual nativa para excepción personalizada
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(VistaPrincipal.this,
                                "Error: Datos no encontrados o campos vacíos",
                                "Error de Búsqueda",
                                JOptionPane.ERROR_MESSAGE);
                        lblAnalisis.setText("Error en la consulta");
                    });
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        lblAnalisis.setText("Procesamiento interrumpido");
                    });
                }
            }
        });
        hilo.start(); // Inicia el hilo sin congelar la ventana
    }

    private Aspirante construirAspiranteDesdeFormulario() {
        String nombre = txtNombre.getText().trim();
        long identificacion = Long.parseLong(txtIdentificacion.getText().trim());
        int edad = Integer.parseInt(txtEdad.getText().trim());
        double puntaje = Double.parseDouble(txtPuntaje.getText().trim());

        String carreraA = (String) cmbCarreraPrincipal.getSelectedItem();

        String seleccionB = (String) cmbCarreraSecundaria.getSelectedItem();
        String carreraB = OPCION_SIN_CARRERA.equals(seleccionB) ? null : seleccionB;

        return new Aspirante(nombre, identificacion, edad, puntaje, carreraA, carreraB);
    }

    private void mostrarResultados(List<ResultadoConsulta> resultadosA, List<ResultadoConsulta> resultadosB) {
        modeloTabla.setRowCount(0);

        agregarFilas("A - Principal", resultadosA);
        agregarFilas("B - Secundaria", resultadosB);
    }

    private void agregarFilas(String etiquetaOpcion, List<ResultadoConsulta> resultados) {
        for (ResultadoConsulta resultado : resultados) {
            String estado = resultado.isAdmitido() ? "Admitido" : "No admitido";
            String faltante = resultado.isAdmitido() ? "-" : String.valueOf(resultado.getPuntajeFaltante());

            modeloTabla.addRow(new Object[]{
                etiquetaOpcion,
                resultado.getNombreUniversidad(),
                resultado.getNombreCarrera(),
                resultado.getPuntajeCorte(),
                estado,
                faltante
            });
        }
    }

    private String[] obtenerCarrerasParaCombo(boolean incluirOpcionVacia) {
        List<String> carreras = new ArrayList<>();
        if (incluirOpcionVacia) {
            carreras.add(OPCION_SIN_CARRERA);
        }
        carreras.addAll(controlador.obtenerCarrerasDisponibles());
        return carreras.toArray(new String[0]);
    }

    // REQUISITO 2.2: Implementación de la interfaz Limpiable
    @Override
    public void limpiarCampos() {
        txtNombre.setText("");
        txtIdentificacion.setText("");
        txtEdad.setText("");
        txtPuntaje.setText("");
        cmbCarreraPrincipal.setSelectedIndex(0);
        cmbCarreraSecundaria.setSelectedIndex(0);
        modeloTabla.setRowCount(0);
        lblAnalisis.setText("Ingrese sus datos y presione \"Consultar Admision\".");
        txtDetallesAspirante.setText("");
    }
}
