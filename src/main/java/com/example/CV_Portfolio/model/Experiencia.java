package com.example.CV_Portfolio.model;

public class Experiencia {
    private String puesto;
    private String empresa;
    private Integer anioI;
    private Integer anioF;

    public Experiencia(String puesto, String empresa, Integer anioI, Integer anioF) {
        this.puesto = puesto;
        this.empresa = empresa;
        this.anioI = anioI;
        this.anioF = anioF;
    }

    public String getPuesto() { return puesto; }
    public String getEmpresa() { return empresa; }
    public Integer getAnioI() { return anioI; }
    public Integer getAnioF() { return anioF; }
}
