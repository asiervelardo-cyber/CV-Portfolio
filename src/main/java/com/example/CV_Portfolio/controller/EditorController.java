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
import java.util.List;
import java.util.Map;

@Controller
public class EditorController {

    @GetMapping("/portfolio/editar")
    public String mostrarFormulario(@Value("${portfolio.max-proyectos}") int max, Model model) {
        CurriculumForm form = new CurriculumForm();
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
        if(accion.equals("anadir")||accion.startsWith("quitar-")){
            modificarFilas(form,accion,max);
        }
        model.addAttribute("max", max);
        model.addAttribute("errores", errores);
        return "editar";
    }

    public void modificarFilas(CurriculumForm form, String accion, int max){
        List<ProyectoForm> proyectos = form.getProyectos();
        if(accion.equals("anadir")){
            if(proyectos.size()<max) {
                proyectos.add(new ProyectoForm());
            }
        }
        if(accion.startsWith("quitar-")){
            try {
                int indice = Integer.parseInt(accion.substring("quitar-".length()));
                if (indice >= 0 && indice < proyectos.size()) {
                    proyectos.remove(indice);
                }
            } catch (NumberFormatException e) {
            }
        }
        if (proyectos.isEmpty()) {
            proyectos.add(new ProyectoForm());
        }
    }

    public void validarDatos(CurriculumForm form, Map<String, String> errores, int max){
        List<ProyectoForm> proyectos = form.getProyectos();
        if (proyectos.size() > max) {
            errores.put("limite", "error.limite");
        }
// Bucle con índice: cada error tiene que saber a qué fila pertenece.
        for (int i = 0; i < proyectos.size(); i++) {
            ProyectoForm p = proyectos.get(i);
            if (p.getTitulo().isBlank()) {
                errores.put("proyectos" + i + ".titulo", "error.obligatorio");
            }
            if (p.getAnio().isBlank()) {
                errores.put("proyectos" + i + ".anio", "error.obligatorio");
            }
            // La opción «--» envía "", no "--".
            if (p.getCategoria().isBlank()) {
                errores.put("proyectos" + i + ".categoria", "error.obligatorio");
            }
            if (p.getEstado().isBlank()) {
                errores.put("proyectos" + i + ".estado", "error.obligatorio");
            }
        }
        if (form.getNombre().isBlank()) {
            errores.put("nombre", "error.obligatorio");
        }
        if (form.getTitular().isBlank()) {
            errores.put("titular", "error.obligatorio");
        }
        if (form.getPresentacion().isBlank()) {
            errores.put("presentacion", "error.obligatorio");
        }

        /*
        editar.revisa=Revisa los campos indicados
        error.obligatorio=Este campo es obligatorio
        error.correo=Introduce un correo electrónico válido
        error.anio=Introduce un año válido
        error.opcion=Elige una opción de la lista
        error.limite=Se ha superado el número máximo de proyectos
        * */
    }
}
