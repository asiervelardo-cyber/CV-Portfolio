package com.example.CV_Portfolio.controller;

import com.example.CV_Portfolio.model.Proyecto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

// Hito 2: portfolio con filtro.
@Controller
public class PortfolioController {

    @GetMapping("/portfolio")
    public String portfolio(@RequestParam(name = "anio", defaultValue = "") String anio,
                            @RequestParam(name = "categoria", defaultValue = "") String categoria,
                            Model model) {
        Integer anioNumero = null;
        boolean errorAnio = false;

        // El año llega como texto. Si no es un número, se avisa y no se filtra por año.
        if (!anio.isBlank()) {
            try {
                anioNumero = Integer.parseInt(anio.trim());
            } catch (NumberFormatException e) {
                errorAnio = true;
            }
        }

        List<Proyecto> filtrado = filtrarProyectos(crearProyectos(), categoria, anioNumero);

        model.addAttribute("errorAnio", errorAnio);
        model.addAttribute("anioFiltro", anio);
        model.addAttribute("categoriaFiltro", categoria);
        model.addAttribute("filtrado", filtrado);
        model.addAttribute("totalResultados", filtrado.size());
        return "portfolio";
    }

    // Título y descripción son claves de messages.properties.
    private List<Proyecto> crearProyectos() {
        List<Proyecto> lista = new ArrayList<>();

        // Con todos los datos.
        lista.add(new Proyecto("proyecto.gestor.titulo", 2023, "aplicaciones",
                "proyecto.gestor.descripcion", "terminado",
                List.of("Java", "Spring Boot", "Thymeleaf"),
                null,
                "/img/gestor-de-tareas.webp"));

        // Sin enlace ni imagen.
        lista.add(new Proyecto("proyecto.panaderia.titulo", 2024, "sitios-web",
                "proyecto.panaderia.descripcion", "en-curso",
                List.of("HTML", "CSS"),
                null,
                null));

        // Sin tecnologías ni imagen.
        lista.add(new Proyecto("proyecto.club.titulo", 2026, "diseno",
                "proyecto.club.descripcion", "en-curso",
                List.of(),
                "https://github.com/asiervelardo-cyber/juego",
                "/img/juego.png"));

        return lista;
    }

    // "todas" o vacío no filtra por categoría, y un año null no filtra por año.
    private List<Proyecto> filtrarProyectos(List<Proyecto> proyectos, String categoria, Integer anio) {
        List<Proyecto> lista = new ArrayList<>();
        boolean cumpleCategoria = categoria == null || categoria.equals("todas") || categoria.isEmpty();
        boolean cumpleAnio = anio == null;

        for (Proyecto proyecto : proyectos) {
            if (cumpleCategoria) {
                if (cumpleAnio) {
                    lista.add(proyecto);
                } else {
                    if (anio.equals(proyecto.getAnio())) {
                        lista.add(proyecto);
                    }
                }
            } else {
                if (proyecto.getCategoria().equals(categoria)) {
                    if (cumpleAnio) {
                        lista.add(proyecto);
                    } else {
                        if (anio.equals(proyecto.getAnio())) {
                            lista.add(proyecto);
                        }
                    }
                }
            }
        }
        return lista;
    }
}
