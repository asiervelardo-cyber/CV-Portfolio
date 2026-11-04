package com.example.CV_Portfolio.model;

public class Idioma {
    private String idioma;
    private Integer anios;
    private String nivel;

    public Idioma(String idioma, Integer anio, String nivel) {
        this.idioma = idioma;
        this.anios = anio;
        this.nivel = nivel;
    }

    public String getIdioma() { return idioma;}
    public Integer getAnio() { return anios;}
    public String getNivel() { return nivel;}
}
