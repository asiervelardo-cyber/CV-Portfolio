package com.example.CV_Portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Página de inicio: solo enseña el menú con las tres páginas.
// Sirve para que el enlace de Vercel no abra un error 404.
@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }
}
