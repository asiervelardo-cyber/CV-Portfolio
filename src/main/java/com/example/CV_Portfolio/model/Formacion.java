package com.example.CV_Portfolio.model;

// Unos estudios del currículum.
public class Formacion {

    private String estudios;
    private String centro;
    private Integer anioI;
    private Integer anioF; // null = en curso

    public Formacion(String estudios, String centro, Integer anioI, Integer anioF) {
        this.estudios = estudios;
        this.centro = centro;
        this.anioI = anioI;
        this.anioF = anioF;
    }

    public String getEstudios() { return estudios; }
    public String getCentro() { return centro; }
    public Integer getAnioI() { return anioI; }
    public Integer getAnioF() { return anioF; }
}
