package com.example.CV_Portfolio.model;

// Un trabajo del currículum.
// "puesto" es una clave de messages.properties, para poder traducirlo.
public class Experiencia {
    private String puesto;
    private String empresa;
    private Integer anioI;
    private Integer anioF; // null = sigue en la empresa

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
