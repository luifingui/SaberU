package com.mycompany.saberu.Modelo;

public class FiltroConsulta {
    private String ciudad;
    private String carrera;
    private String facultad;
    private boolean soloAcreditadas;

    public FiltroConsulta() {}

    public FiltroConsulta(String ciudad, String carrera, String facultad, boolean soloAcreditadas) {
        this.ciudad = ciudad;
        this.carrera = carrera;
        this.facultad = facultad;
        this.soloAcreditadas = soloAcreditadas;
    }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getFacultad() { return facultad; }
    public void setFacultad(String facultad) { this.facultad = facultad; }

    public boolean isSoloAcreditadas() { return soloAcreditadas; }
    public void setSoloAcreditadas(boolean soloAcreditadas) { this.soloAcreditadas = soloAcreditadas; }
}
