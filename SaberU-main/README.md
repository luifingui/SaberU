# SaberU

Proyecto de aula POO – Universidad de Cartagena.

## Descripción

Sistema de consulta de admisiones universitarias que permite a los aspirantes buscar carreras en diferentes universidades según su puntaje Saber 11.

## Tecnologías

- Java 21
- Maven
- Swing (GUI)

## Estructura del Proyecto

```
SaberU-main/
├── src/
│   ├── main/
│   │   ├── java/com/mycompany/saberu/
│   │   │   ├── Controlador/
│   │   │   │   └── ControladorAdmision.java
│   │   │   ├── Modelo/
│   │   │   │   ├── Aspirante.java
│   │   │   │   ├── Carrera.java
│   │   │   │   ├── FiltroConsulta.java
│   │   │   │   ├── Persona.java
│   │   │   │   ├── ResultadoConsulta.java
│   │   │   │   └── Universidad.java
│   │   │   └── Vista/
│   │   │       ├── Main.java
│   │   │       └── VistaPrincipal.java
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
2. Ejecutar la clase `Main.java` ubicada en `src/main/java/com/mycompany/saberu/Vista/`

## Datos

Las universidades y carreras se almacenan en el archivo `src/main/resources/datos/universidades.json`. Para agregar o modificar datos, simplemente edita este archivo JSON.

## Arquitectura

El proyecto sigue el patrón MVC (Modelo-Vista-Controlador):

- **Modelo**: Clases que representan las entidades (Universidad, Carrera, Aspirante, etc.)
- **Vista**: Interfaz gráfica (Swing JFrame)
- **Controlador**: Lógica de negocio que conecta Vista y Modelo
