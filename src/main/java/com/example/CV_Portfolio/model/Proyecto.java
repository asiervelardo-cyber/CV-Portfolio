package com.example.CV_Portfolio.model;

import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    private String nombre;
    private int anio;
    private String categoria; //"Aplicaciones", "Sitios web" o "Diseño"
    private String descripcion;
    private String estado; //En curso o Terminado
    private List<String> tecnologias;
    private String enlace;
    private String img;

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
