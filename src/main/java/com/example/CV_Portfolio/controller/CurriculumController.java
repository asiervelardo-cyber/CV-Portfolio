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

import java.util.List;

@Controller
public class CurriculumController {
    private static final List<Experiencia> EXPERIENCIA = List.of(new Experiencia("Técnico","Netflix", 2022,2023), new Experiencia("Programador", "Microsoft", 2023, 2025));
    private static final List<Formacion> FORMACION = List.of(new Formacion("DAW", "IES Velázquez", 2025, 2027));
    private static final List<Habilidad> HABILIDAD = List.of(new Habilidad("HTML", 2, "Alto"), new Habilidad("Java", 2, "Alto"));
    private static final List<Idioma> IDIOMA = List.of(new Idioma("Inglés", 10, "Alto", "C1"));

    @GetMapping("/curriculum")
    public String curriculum(@RequestParam(name = "imprimir", defaultValue = "false") boolean imprimir, Model model, @Value("${cv.nombre}") String nombre){
        model.addAttribute("experiencia", EXPERIENCIA);
        model.addAttribute("formacion", FORMACION);
        model.addAttribute("habilidad", HABILIDAD);
        model.addAttribute("idioma", IDIOMA);
        model.addAttribute("nombre", nombre);
        model.addAttribute("imprimir", imprimir);
        return "curriculum";
    }
}
