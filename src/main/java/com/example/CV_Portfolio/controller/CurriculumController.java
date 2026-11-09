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

// Hito 1: la página /curriculum.
@Controller
public class CurriculumController {

    // Los datos van en arrays (con []), porque el enunciado lo pide así.
    // Son "static final" porque no cambian nunca: no se guarda nada de ninguna petición.
    // Los textos que hay que traducir (puesto, estudios, idioma) son claves de messages.properties;
    // la plantilla las traduce con #{${...}}.
    private static final Experiencia[] EXPERIENCIA = {new Experiencia("dato.puesto.tecnico", "Netflix", 2022, 2023), new Experiencia("dato.puesto.programador", "Microsoft", 2023, 2025)};

    // anioF = null significa que todavía sigo estudiando, y la página pone "En curso".
    private static final Formacion[] FORMACION = {new Formacion("dato.estudios.daw", "IES Velázquez", 2025, null)};

    // El nivel va siempre en minúsculas: alto, medio o bajo. La plantilla elige el color según este valor.
    private static final Habilidad[] HABILIDAD = {new Habilidad("HTML", 2, "alto"), new Habilidad("Java", 2, "alto"), new Habilidad("CSS", 2, "medio"), new Habilidad("Spring Boot", 1, "bajo")};

    // "Nativo" es especial: la plantilla lo traduce; cualquier otro certificado (C1, B2...) se enseña tal cual.
    private static final Idioma[] IDIOMA = {new Idioma("dato.idioma.espanol", 19, "alto", "Nativo"), new Idioma("dato.idioma.ingles", 10, "alto", "C1")};

    // Si lo dejo vacío (""), la plantilla esconde el apartado de contacto entero.
    private static final String CONTACTO = "avelnor1711@g.educaand.es";

    // "imprimir" llega como true cuando se pulsa el botón de imprimir, para enseñar el aviso de Ctrl+P.
    // El nombre sale de cv.nombre en application.properties: si lo cambio y reinicio, cambia en la página.
    @GetMapping("/curriculum")
    public String curriculum(@RequestParam(name = "imprimir", defaultValue = "false") boolean imprimir, Model model,@Value("${cv.nombre}") String nombre) {
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
