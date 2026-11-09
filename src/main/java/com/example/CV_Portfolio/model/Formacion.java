package com.example.CV_Portfolio.model;

// Unos estudios del currículum.
public class Formacion {

    private String estudios;
    private String centro;
    private Integer anioF; // null = en curso

    public Formacion(String estudios, String centro, Integer anioF) {
        this.estudios = estudios;
        this.centro = centro;
        this.anioF = anioF;
    }

    public String getEstudios() { return estudios; }
    public String getCentro() { return centro; }
    public Integer getAnioF() { return anioF; }
}
