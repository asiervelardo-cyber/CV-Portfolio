package com.example.CV_Portfolio.controller;

import com.example.CV_Portfolio.form.CurriculumForm;
import com.example.CV_Portfolio.form.ProyectoForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EditorController {

    @GetMapping("/portfolio/editar")
    public String mostrarFormulario(@Value("${portfolio.max-proyectos}") int max, Model model) {
        CurriculumForm form = new CurriculumForm();
        // El formulario empieza con una fila de proyecto vacía.
        form.getProyectos().add(new ProyectoForm());

        model.addAttribute("form", form);
        model.addAttribute("max", max);
        model.addAttribute("errores", new HashMap<String, String>());
        return "editar";
    }

    @PostMapping("/portfolio/editar")
    public String procesarFormulario(@ModelAttribute("form") CurriculumForm form,
                                     @RequestParam(name = "accion", defaultValue = "") String accion,
                                     @Value("${portfolio.max-proyectos}") int max,
                                     Model model) {
        Map<String, String> errores = new HashMap<>();

        // Paso 6.5: si accion es "anadir" o empieza por "quitar-", modificar las filas.
        // Paso 6.7: si accion es "generar", validar y, sin errores, devolver "vista-previa".

        model.addAttribute("max", max);
        model.addAttribute("errores", errores);
        return "editar";
    }
}
