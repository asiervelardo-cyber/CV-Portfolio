package com.example.CV_Portfolio.model;

public class Formacion {
    private String estudios;
    private String centro;
    private Integer anioI; // null = en curso
    private Integer anioF;

    public Formacion(String estudios, String centro, Integer anioI, Integer anioF) {
        this.estudios = estudios;
        this.centro = centro;
        this.anioI = anioI;
        this.anioF = anioI;
    }

    public String getEstudios() { return estudios; }
    public String getCentro() { return centro; }
    public Integer getAnioI() { return anioI; }
    public Integer getAnioF() { return anioF; }
}
