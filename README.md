# SaberU

Proyecto de aula POO – Universidad de Cartagena.

## Descripción

Sistema de consulta de admisiones universitarias que permite a los aspirantes buscar carreras en diferentes universidades según su puntaje Saber 11.

## Tecnologías

- Java 21
- Maven
- Swing (GUI)

## Estructura del Proyecto

El proyecto sigue una estructura profesional de paquetes MVC:

```
SaberU/
├── src/
│   ├── main/
│   │   ├── java/com/mycompany/saberu/
│   │   │   ├── modelo/           (Entidades del dominio)
│   │   │   │   ├── Aspirante.java
│   │   │   │   ├── Carrera.java
│   │   │   │   ├── Persona.java
│   │   │   │   ├── ResultadoConsulta.java
│   │   │   │   └── Universidad.java
│   │   │   ├── vista/            (Interfaz gráfica)
│   │   │   │   ├── Main.java
│   │   │   │   ├── VistaPrincipal.java
│   │   │   │   └── Limpiable.java
│   │   │   ├── controlador/      (Lógica de negocio)
│   │   │   │   └── ControladorAdmision.java
│   │   │   └── excepciones/      (Excepciones personalizadas)
│   │   │       └── ElementoNoEncontradoException.java
│   │   └── resources/
│   │       └── datos/
│   │           └── universidades.json
├── pom.xml
└── README.md
```

## Cómo Ejecutar

### Desde Maven

```bash
mvn compile exec:java
```

### Desde IDE

1. Abrir el proyecto en IntelliJ IDEA, Eclipse, o NetBeans
2. Ejecutar la clase `Main.java` ubicada en `src/main/java/com/mycompany/saberu/vista/`

## Datos

Las universidades y carreras se almacenan en el archivo `src/main/resources/datos/universidades.json`. Para agregar o modificar datos, simplemente edita este archivo JSON.

## Arquitectura

El proyecto sigue el patrón MVC (Modelo-Vista-Controlador) con paquetes profesionales:

- **modelo**: Clases que representan las entidades (Universidad, Carrera, Aspirante, Persona, etc.)
- **vista**: Interfaz gráfica (Swing JFrame) e interfaces de vista (Limpiable)
- **controlador**: Lógica de negocio que conecta Vista y Modelo
- **excepciones**: Excepciones personalizadas del dominio

---

## Tercera Entrega - Requisitos Implementados

Esta versión del proyecto cumple con los requisitos de la tercera entrega de POO. A continuación se detalla dónde puede encontrar cada funcionalidad en la interfaz gráfica:

### 2.1 Ligadura Dinámica (Polimorfismo)

**Ubicación en el código:**
- `modelo/Persona.java`: Método `obtenerDetalles()`
- `modelo/Aspirante.java`: Sobrescritura de `obtenerDetalles()` con `@Override`

**Cómo probarlo en la interfaz:**
1. Ejecute la aplicación
2. Ingrese los datos de un aspirante (nombre, ID, edad, puntaje, carreras)
3. Presione el botón **"Consultar Admision"**
4. Observe el panel inferior **"Analisis"** → Área **"Detalles del Aspirante (Ligadura Dinámica)"**
5. El texto mostrado usa una referencia de tipo `Persona` para llamar al método, pero en tiempo de ejecución se ejecuta la versión de `Aspirante` (polimorfismo)

**Explicación técnica:**
```java
Persona persona = aspirante; // Upcasting: Aspirante -> Persona
String detalles = persona.obtenerDetalles(); // Ligadura dinámica en ejecución
```

### 2.2 Interfaces y Vista (Limpiable)

**Ubicación en el código:**
- `vista/Limpiable.java`: Interfaz con método `limpiarCampos()`
- `vista/VistaPrincipal.java`: Implementa `Limpiable`

**Cómo probarlo en la interfaz:**
1. Ingrese datos en el formulario
2. Presione el botón **"Limpiar Formulario"** ubicado junto a **"Consultar Admision"**
3. Todos los campos del formulario se vaciarán, la tabla se limpiará y los estados se reiniciarán

**Explicación técnica:**
```java
public class VistaPrincipal extends JFrame implements Limpiable {
    @Override
    public void limpiarCampos() {
        // Lógica para vaciar todos los campos
    }
}
```

### 2.3 Excepciones Personalizadas con Alertas Visuales

**Ubicación en el código:**
- `excepciones/ElementoNoEncontradoException.java`: Excepción personalizada (paquete separado)
- `controlador/ControladorAdmision.java`: Lanza la excepción cuando no hay resultados
- `vista/VistaPrincipal.java`: Bloque `try-catch` dentro del hilo con `JOptionPane.showMessageDialog`

**Cómo probarlo en la interfaz:**
1. Deje los campos de nombre, identificación o puntaje vacíos
2. Presione el botón **"Consultar Admision"**
3. Aparecerá un mensaje emergente nativo: **"Error: Datos no encontrados o campos vacíos"** con icono de error
4. La etiqueta de análisis mostrará "Error en la consulta"

### 2.5 Concurrencia / Hilos en la Vista

**Ubicación en el código:**
- `vista/VistaPrincipal.java`: Método `consultarAdmision()` con `Runnable` directo

**Cómo probarlo en la interfaz:**
1. Ingrese los datos del aspirante
2. Presione el botón **"Consultar Admision"**
3. Observe la etiqueta de **Análisis** cambiará a **"Procesando consulta en segundo plano..."**
4. Después de 1 segundo, mostrará los resultados y cambiará a **"¡Consulta Completada con éxito!"**
5. **Importante:** La ventana NO se congela durante el procesamiento (el hilo corre en segundo plano)

**Explicación técnica:**
```java
Thread hilo = new Thread(new Runnable() {
    @Override
    public void run() {
        // Procesamiento asíncrono con Thread.sleep(1000)
        // Actualización de UI con SwingUtilities.invokeLater
    }
});
hilo.start(); // No bloquea la interfaz
```

---

## Resumen de Archivos Modificados/Agregados

### Archivos Modificados:
- `modelo/Persona.java`: Agregado método `obtenerDetalles()`
- `modelo/Aspirante.java`: Sobrescritura de `obtenerDetalles()`
- `controlador/ControladorAdmision.java`: Lanza `ElementoNoEncontradoException`
- `vista/VistaPrincipal.java`: Interfaz limpia sin panel extra, implementa `Limpiable`, concurrencia en botón principal, manejo de excepciones

### Archivos Nuevos:
- `vista/Limpiable.java`: Interfaz para limpiar campos de la vista
- `excepciones/ElementoNoEncontradoException.java`: Excepción personalizada (paquete separado)

### Archivos Eliminados:
- `Utilidades/ArchivosUtil.java`: Manejo de archivos (eliminado por requerimiento)
- `Utilidades/ProcesadorReportes.java`: Runnable externo (reemplazado por Runnable inline en botón principal)
- `Modelo/Filtrable.java`: Interfaz de filtrado (reemplazada por Limpiable)
