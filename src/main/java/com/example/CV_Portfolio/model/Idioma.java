package com.example.CV_Portfolio.model;

// Un idioma del currículum.
// "idioma" es una clave de messages.properties, para poder traducirlo.
public class Idioma {
    private String idioma;
    private Integer anios;
    private String nivel; // "alto", "medio" o "bajo", siempre en minúsculas
    private String certificado; // C1, B2... o "Nativo", que la plantilla traduce

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
