package com.example.CV_Portfolio.model;

// Una habilidad del currículum.
public class Habilidad {

    private String habilidad;
    private String nivel; // alto, medio o bajo

    public Habilidad(String habilidad, String nivel) {
        this.habilidad = habilidad;
        this.nivel = nivel;
    }

    public String getHabilidad() { return habilidad; }
    public String getNivel() { return nivel; }
}
