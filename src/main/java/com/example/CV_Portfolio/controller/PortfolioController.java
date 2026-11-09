package com.example.CV_Portfolio.controller;

import com.example.CV_Portfolio.model.Proyecto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

// Hito 2: la página /portfolio con el filtro por categoría y año.
@Controller
public class PortfolioController {

    // El formulario del filtro manda "categoria" y "anio" en la URL (es un GET).
    // Si no llegan, valen "" y no filtran nada.
    @GetMapping("/portfolio")
    public String portfolio(@RequestParam(name = "anio", defaultValue="") String anio, @RequestParam(name = "categoria", defaultValue = "") String categoria, Model model) {
        Integer anioNumero = null;
        boolean errorAnio = false;

        // El año llega como texto. Se intenta pasar a número; si no se puede (por ejemplo "abc"),
        // se marca el error para enseñar "Introduce un año válido" y no se filtra por año.
        if (!anio.isBlank()) {
            try {
                anioNumero = Integer.parseInt(anio.trim());
            } catch (NumberFormatException e) {
                errorAnio = true;
            }
        }
        List<Proyecto> filtrado = filtrarProyectos(crearProyectos(),categoria,anioNumero);

        // Se devuelven también los valores del filtro, para que el formulario los siga enseñando.
        model.addAttribute("errorAnio", errorAnio);
        model.addAttribute("anioFiltro", anio);
        model.addAttribute("categoriaFiltro", categoria);
        model.addAttribute("filtrado", filtrado);
        model.addAttribute("totalResultados", filtrado.size());
        return "portfolio";
    }

    // Crea una lista nueva en cada petición, así no se guarda nada en el controlador.
    // Categoría y estado son códigos (aplicaciones, en-curso...) que la plantilla traduce.
    // Título y descripción son claves de messages.properties, para que salgan en los dos idiomas.
    private List<Proyecto> crearProyectos() {
        List<Proyecto> lista = new ArrayList<>();

        // Con todos los datos.
        lista.add(new Proyecto("proyecto.gestor.titulo", 2023, "aplicaciones",
                "proyecto.gestor.descripcion", "terminado",
                List.of("Java", "Spring Boot", "Thymeleaf"),
                "https://github.com/asiervelardo-cyber/juego",
                "/img/gestor-de-tareas.webp"));

        // Sin enlace ni imagen: sirve para comprobar que no salen vacíos.
        lista.add(new Proyecto("proyecto.panaderia.titulo", 2024, "sitios-web",
                "proyecto.panaderia.descripcion", "en-curso",
                List.of("HTML", "CSS"),
                null,
                null));

        // Sin tecnologías ni imagen: sirve para comprobar que se esconde el apartado "Tecnologías".
        lista.add(new Proyecto("proyecto.club.titulo", 2025, "diseno",
                "proyecto.club.descripcion", "terminado",
                List.of(),
                "https://github.com/asiervelardo-cyber/juego",
                null));

        return lista;
    }

    // Devuelve solo los proyectos que cumplen el filtro.
    // "todas" (o vacío) no filtra por categoría, y un año null no filtra por año.
    // Si hay categoría y año, el proyecto tiene que cumplir las dos cosas.
    private List<Proyecto> filtrarProyectos(List<Proyecto> proyectos, String categoria, Integer anio) {
        List<Proyecto> lista = new ArrayList<>();
        boolean cumpleCategoria = categoria == null || categoria.equals("todas") || categoria.isEmpty();
        boolean cumpleAnio = anio == null;

        for (Proyecto proyecto : proyectos) {
            if (cumpleCategoria) {
                if (cumpleAnio) {
                    lista.add(proyecto);
                }else{
                    if(anio.equals(proyecto.getAnio())){
                        lista.add(proyecto);
                    }
                }
            }else{
                if(proyecto.getCategoria().equals(categoria)){
                    if (cumpleAnio) {
                        lista.add(proyecto);
                    }else{
                        if(anio.equals(proyecto.getAnio())){
                            lista.add(proyecto);
                        }
                    }
                }
            }
        }
        return lista;
    }
}
