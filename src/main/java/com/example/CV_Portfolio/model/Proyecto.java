package com.example.CV_Portfolio.model;

import java.util.ArrayList;
import java.util.List;

// Un proyecto del portfolio (Hito 2).
public class Proyecto {
    private String nombre; // clave de messages.properties, para poder traducirlo
    private int anio;
    private String categoria; // "aplicaciones", "sitios-web" o "diseno"
    private String descripcion; // clave de messages.properties
    private String estado; // "en-curso" o "terminado"
    private List<String> tecnologias;
    private String enlace; // null si no tiene enlace
    private String img; // null si no tiene imagen

    public Proyecto(String nombre, int anio, String categoria, String descripcion, String estado, List<String> tecnologias, String enlace, String img) {
        this.nombre = nombre;
        this.anio = anio;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.estado = estado;
        this.tecnologias = tecnologias;
        this.enlace = enlace;
        this.img = img;
    }

    public String getNombre() { return nombre; }
    public int getAnio() { return anio; }
    public String getCategoria() { return categoria; }
    public String getDescripcion() { return descripcion; }
    public String getEstado() { return estado; }
    public List<String> getTecnologias() { return tecnologias; }
    public String getEnlace() { return enlace; }
    public String getImg() { return img; }
}
