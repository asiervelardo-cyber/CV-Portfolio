package com.example.CV_Portfolio.model;

// Una habilidad del currículum.
public class Habilidad {

    private String habilidad;
    private Integer aniosExperiencia;
    private String nivel; // alto, medio o bajo

    public Habilidad(String habilidad, Integer aniosExperiencia, String nivel) {
        this.habilidad = habilidad;
        this.aniosExperiencia = aniosExperiencia;
        this.nivel = nivel;
    }

    public String getHabilidad() { return habilidad; }
    public Integer getAniosExperiencia() { return aniosExperiencia; }
    public String getNivel() { return nivel; }
}
