package com.example.CV_Portfolio.controller;

import com.example.CV_Portfolio.model.Experiencia;
import com.example.CV_Portfolio.model.Formacion;
import com.example.CV_Portfolio.model.Habilidad;
import com.example.CV_Portfolio.model.Idioma;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Hito 1: currículum.
@Controller
public class CurriculumController {

    // Los textos que se traducen (puesto, estudios, idioma) son claves de messages.properties.
    private static final Experiencia[] EXPERIENCIA = {
            new Experiencia("dato.puesto.tecnico", "Netflix", 2022, 2023),
            new Experiencia("dato.puesto.programador", "Microsoft", 2023, 2025)
    };

    // anioF = null significa que está en curso.
    private static final Formacion[] FORMACION = {
            new Formacion("dato.estudios.daw", "IES Velázquez", 2025, null)
    };

    // Nivel siempre en minúsculas: alto, medio o bajo.
    private static final Habilidad[] HABILIDAD = {
            new Habilidad("HTML", 2, "alto"),
            new Habilidad("Java", 2, "alto"),
            new Habilidad("CSS", 2, "medio"),
            new Habilidad("Spring Boot", 1, "bajo")
    };

    private static final Idioma[] IDIOMA = {
            new Idioma("dato.idioma.espanol", 19, "alto", "Nativo"),
            new Idioma("dato.idioma.ingles", 10, "alto", "C1")
    };

    // Si se deja vacío, no sale el apartado de contacto.
    private static final String CONTACTO = "avelnor1711@g.educaand.es";

    // "imprimir" es true al pulsar el botón de imprimir, para enseñar el aviso.
    @GetMapping("/curriculum")
    public String curriculum(@RequestParam(name = "imprimir", defaultValue = "false") boolean imprimir,
                             Model model,
                             @Value("${cv.nombre}") String nombre) {
        model.addAttribute("experiencia", EXPERIENCIA);
        model.addAttribute("formacion", FORMACION);
        model.addAttribute("habilidad", HABILIDAD);
        model.addAttribute("idioma", IDIOMA);
        model.addAttribute("contacto", CONTACTO);
        model.addAttribute("nombre", nombre);
        model.addAttribute("imprimir", imprimir);
        return "curriculum";
    }
}
