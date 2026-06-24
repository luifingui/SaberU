package com.mycompany.saberu.Modelo;

public class ResultadoConsulta {
    private String nombreUniversidad;
    private String nombreCarrera;
    private double puntajeCorte;
    private boolean admitido;
    private double puntajeFaltante;
    private String sitioWeb;

    public ResultadoConsulta() {}

    public ResultadoConsulta(String nombreUniversidad, String nombreCarrera, double puntajeCorte,
            boolean admitido, double puntajeFaltante, String sitioWeb) {
        this.nombreUniversidad = nombreUniversidad;
        this.nombreCarrera = nombreCarrera;
        this.puntajeCorte = puntajeCorte;
        this.admitido = admitido;
        this.puntajeFaltante = puntajeFaltante;
        this.sitioWeb = sitioWeb;
    }

    public String getNombreUniversidad() { return nombreUniversidad; }
    public void setNombreUniversidad(String nombreUniversidad) { this.nombreUniversidad = nombreUniversidad; }

    public String getNombreCarrera() { return nombreCarrera; }
    public void setNombreCarrera(String nombreCarrera) { this.nombreCarrera = nombreCarrera; }

    public double getPuntajeCorte() { return puntajeCorte; }
    public void setPuntajeCorte(double puntajeCorte) { this.puntajeCorte = puntajeCorte; }

    public boolean isAdmitido() { return admitido; }
    public void setAdmitido(boolean admitido) { this.admitido = admitido; }

    public double getPuntajeFaltante() { return puntajeFaltante; }
    public void setPuntajeFaltante(double puntajeFaltante) { this.puntajeFaltante = puntajeFaltante; }

    public String getSitioWeb() { return sitioWeb; }
    public void setSitioWeb(String sitioWeb) { this.sitioWeb = sitioWeb; }
}
