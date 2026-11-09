package com.example.CV_Portfolio.model;

// Un idioma del currículum.
public class Idioma {

    private String idioma;
    private String nivel; // alto, medio o bajo
    private String certificado; // C1, B2... o "Nativo"

    public Idioma(String idioma, String nivel, String certificado) {
        this.idioma = idioma;
        this.nivel = nivel;
        this.certificado = certificado;
    }

    public String getIdioma() { return idioma; }
    public String getNivel() { return nivel; }
    public String getCertificado() { return certificado; }
}
