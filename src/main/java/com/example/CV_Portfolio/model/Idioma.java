package com.example.CV_Portfolio.model;

public class Idioma {
    private String idioma;
    private Integer anios;
    private String nivel;
    private String certificado;

    public Idioma(String idioma, Integer anio, String nivel, String certificado) {
        this.idioma = idioma;
        this.anios = anio;
        this.nivel = nivel;
        this.certificado = certificado;
    }

    public String getIdioma() { return idioma;}
    public Integer getAnio() { return anios;}
    public String getNivel() { return nivel;}
    public String getCertificado() { return certificado;}
}
