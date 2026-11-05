package com.example.CV_Portfolio.controller;

import com.example.CV_Portfolio.model.Proyecto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PortfolioController {

    @GetMapping("/portfolio")
    public String portfolio(/* parámetros del filtro, paso 5.3 */ Model model) {
        // aquí llamas a crearProyectos() y a filtrarProyectos(...)
    }

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

    private List<Proyecto> filtrarProyectos(List<Proyecto> proyectos, String categoria, Integer anio) {
        List<Proyecto> lista = new ArrayList<>();
        boolean cumpleCategoria = categoria == null || categoria.equals("Todas") || categoria.isEmpty();
        boolean cumpleAnio = anio == null;

        for (Proyecto proyecto : proyectos) {
            if (cumpleCategoria) {
                if (cumpleAnio) {
                    lista.add(proyecto);
                }else{
                    if(proyecto.getAnio()==anio){
                        lista.add(proyecto);
                    }
                }
            }else{
                if(proyecto.getCategoria().equals(categoria)){
                    if (cumpleAnio) {
                        lista.add(proyecto);
                    }else{
                        if(proyecto.getAnio()==anio){
                            lista.add(proyecto);
                        }
                    }
                }
            }
        }
        return lista;
    }
    //enlace: https://cv-portfolio-one-blush.vercel.app/
}
