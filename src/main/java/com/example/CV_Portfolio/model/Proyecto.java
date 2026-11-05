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

    private List<Proyecto> crearProyectos() {
        List<Proyecto> lista = new ArrayList<>();

        // Con todos los datos.
        lista.add(new Proyecto("Gestor de tareas", 2023, "aplicaciones",
                "proyecto.gestor.descripcion", "terminado",
                List.of("Java", "Spring Boot", "Thymeleaf"),
                "https://github.com/asiervelardo-cyber/juego",
                "/img/img_prueba.webp"));

        // Sin enlace ni imagen.
        lista.add(new Proyecto("Web de una panadería", 2024, "sitios-web",
                "proyecto.panaderia.descripcion", "en-curso",
                List.of("HTML", "CSS"),
                null,
                null));

        // Sin tecnologías ni imagen.
        lista.add(new Proyecto("Identidad visual de un club deportivo", 2025, "diseno",
                "proyecto.club.descripcion", "terminado",
                List.of(),
                "https://github.com/asiervelardo-cyber/juego",
                null));

        return lista;
    }
}
