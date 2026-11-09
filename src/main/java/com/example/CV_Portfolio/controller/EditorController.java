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

// Hito 3: editor y vista previa. No se guarda nada.
@Controller
public class EditorController {

    // Formulario vacío con una fila de proyecto.
    @GetMapping("/portfolio/editar")
    public String mostrarFormulario(@Value("${portfolio.max-proyectos}") int max, Model model) {
        CurriculumForm form = new CurriculumForm();
        form.getProyectos().add(new ProyectoForm());

        model.addAttribute("form", form);
        model.addAttribute("max", max);
        model.addAttribute("errores", new HashMap<String, String>());
        return "editar";
    }

    // "accion" dice qué botón se ha pulsado. Con los botones de idioma llega vacía.
    @PostMapping("/portfolio/editar")
    public String procesarFormulario(@ModelAttribute("form") CurriculumForm form,
                                     @RequestParam(name = "accion", defaultValue = "") String accion,
                                     @Value("${portfolio.max-proyectos}") int max,
                                     Model model) {
        Map<String, String> errores = new HashMap<>();

        // El límite se comprueba siempre, por si se manipula el formulario.
        if (form.getProyectos().size() > max) {
            errores.put("limite", "error.limite");
        } else if (accion.equals("anadir") || accion.startsWith("quitar-")) {
            modificarFilas(form, accion, max);
        } else if (accion.equals("generar")) {
            errores = validarDatos(form, max);
            if (errores.isEmpty()) {
                return "vista-previa";
            }
        }

        model.addAttribute("max", max);
        model.addAttribute("errores", errores);
        return "editar";
    }

    // Añade o quita una fila según el botón pulsado.
    public void modificarFilas(CurriculumForm form, String accion, int max) {
        List<ProyectoForm> proyectos = form.getProyectos();

        if (accion.equals("anadir")) {
            if (proyectos.size() < max) {
                proyectos.add(new ProyectoForm());
            }
        }

        // "quitar-2" quita la fila 2.
        if (accion.startsWith("quitar-")) {
            try {
                int indice = Integer.parseInt(accion.substring("quitar-".length()));
                if (indice >= 0 && indice < proyectos.size()) {
                    proyectos.remove(indice);
                }
            } catch (NumberFormatException e) {
                // Si no es un número, no se quita nada.
            }
        }

        // Siempre tiene que quedar una fila.
        if (proyectos.isEmpty()) {
            proyectos.add(new ProyectoForm());
        }
    }

    // Devuelve los errores: campo como clave (ej. "proyectos0.titulo") y mensaje como valor.
    public Map<String, String> validarDatos(CurriculumForm form, int max) {
        List<ProyectoForm> proyectos = form.getProyectos();
        Map<String, String> errores = new HashMap<>();

        if (proyectos.size() > max) {
            errores.put("limite", "error.limite");
        }

        // Las filas vacías se quitan antes de validar, para que los números de fila coincidan.
        proyectos.removeIf(p -> p == null || filasVacias(p));

        // Datos personales.
        if (form.getNombre().isBlank()) {
            errores.put("nombre", "error.obligatorio");
        }
        if (form.getTitular().isBlank()) {
            errores.put("titular", "error.obligatorio");
        }
        if (form.getPresentacion().isBlank()) {
            errores.put("presentacion", "error.obligatorio");
        }
        // El correo es opcional: solo se revisa si está escrito.
        if (!form.getCorreo().isBlank() && !form.getCorreo().matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            errores.put("correo", "error.correo");
        }

        // Proyectos.
        for (int i = 0; i < proyectos.size(); i++) {
            ProyectoForm p = proyectos.get(i);
            String id = "proyectos" + i + ".";

            if (p.getTitulo().isBlank()) {
                errores.put(id + "titulo", "error.obligatorio");
            }

            if (p.getAnio().isBlank()) {
                errores.put(id + "anio", "error.obligatorio");
            } else if (!p.getAnio().trim().matches("\\d{4}")) {
                errores.put(id + "anio", "error.anio");
            }

            if (!List.of("aplicaciones", "sitios-web", "diseno").contains(p.getCategoria())) {
                errores.put(id + "categoria", "error.opcion");
            }

            if (!List.of("en-curso", "terminado").contains(p.getEstado())) {
                errores.put(id + "estado", "error.opcion");
            }

            if (p.getDescripcion().isBlank()) {
                errores.put(id + "descripcion", "error.obligatorio");
            }
        }

        // Si no queda ninguna fila y hay errores, se añade una vacía.
        if (!errores.isEmpty() && proyectos.isEmpty()) {
            proyectos.add(new ProyectoForm());
        }
        return errores;
    }

    // Una fila está vacía si no tiene nada escrito.
    public boolean filasVacias(ProyectoForm p) {
        if (p.getTitulo().isBlank() && p.getAnio().isBlank() && p.getCategoria().isBlank()
                && p.getEstado().isBlank() && p.getDescripcion().isBlank()) {
            return true;
        }
        return false;
    }
}
