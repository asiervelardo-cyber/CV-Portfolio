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

// Hito 3: el editor de /portfolio/editar y su vista previa.
// No se guarda nada: todo vive en variables de cada petición.
@Controller
public class EditorController {

    // Primera vez que se abre el editor: formulario vacío con una fila de proyecto.
    // "max" sale de portfolio.max-proyectos en application.properties.
    @GetMapping("/portfolio/editar")
    public String mostrarFormulario(@Value("${portfolio.max-proyectos}") int max, Model model) {
        CurriculumForm form = new CurriculumForm();
        form.getProyectos().add(new ProyectoForm());

        model.addAttribute("form", form);
        model.addAttribute("max", max);
        // El mapa de errores va vacío, pero tiene que existir para que la plantilla no falle.
        model.addAttribute("errores", new HashMap<String, String>());
        return "editar";
    }

    // Aquí llegan todos los botones del formulario.
    // Spring rellena "form" con lo que se ha escrito, y "accion" dice qué botón se ha pulsado.
    // Si se pulsa un botón de idioma, "accion" llega vacía y solo se vuelve a enseñar el formulario.
    @PostMapping("/portfolio/editar")
    public String procesarFormulario(@ModelAttribute("form") CurriculumForm form,
                                     @RequestParam(name = "accion", defaultValue = "") String accion,
                                     @Value("${portfolio.max-proyectos}") int max,
                                     Model model) {
        Map<String, String> errores = new HashMap<>();

        // El límite se comprueba en todos los envíos, por si alguien manipula el formulario
        // para mandar más filas de las permitidas.
        if (form.getProyectos().size() > max) {
            errores.put("limite", "error.limite");
        } else if (accion.equals("anadir") || accion.startsWith("quitar-")) {
            modificarFilas(form, accion, max);
        } else if (accion.equals("generar")) {
            errores = validarDatos(form, max);
            // Sin errores se pasa a la vista previa; "form" ya está en el modelo gracias a @ModelAttribute.
            if (errores.isEmpty()) {
                return "vista-previa";
            }
        }

        model.addAttribute("max", max);
        model.addAttribute("errores", errores);
        return "editar";
    }

    // Añade o quita una fila según el botón pulsado.
    // Los valores de las demás filas se mantienen solos, porque ya vienen dentro de "form".
    public void modificarFilas(CurriculumForm form, String accion, int max){
        List<ProyectoForm> proyectos = form.getProyectos();
        if(accion.equals("anadir")){
            if(proyectos.size()<max) {
                proyectos.add(new ProyectoForm());
            }
        }
        // El botón de quitar manda "quitar-" y el número de fila, por ejemplo "quitar-2".
        if(accion.startsWith("quitar-")){
            try {
                int indice = Integer.parseInt(accion.substring("quitar-".length()));
                // Se comprueba que la fila existe, por si el número viene manipulado.
                if (indice >= 0 && indice < proyectos.size()) {
                    proyectos.remove(indice);
                }
            } catch (NumberFormatException e) {
                // Si no es un número, se ignora y no se quita nada.
            }
        }
        // Siempre tiene que quedar al menos una fila para poder seguir escribiendo.
        if (proyectos.isEmpty()) {
            proyectos.add(new ProyectoForm());
        }
    }

    // Revisa los datos y devuelve los errores encontrados.
    // Cada error se guarda con el nombre del campo (por ejemplo "proyectos0.titulo")
    // y la clave del mensaje que hay que enseñar (por ejemplo "error.obligatorio").
    public Map<String, String> validarDatos(CurriculumForm form, int max){
        List<ProyectoForm> proyectos = form.getProyectos();
        Map<String, String> errores = new HashMap<>();

        if (proyectos.size() > max) {
            errores.put("limite", "error.limite");
        }

        // Las filas completamente vacías no cuentan como proyecto, así que se quitan.
        // Se hace antes de validar para que el número de fila de cada error coincida con lo que se ve.
        proyectos.removeIf(p -> p == null || filasVacias(p));

        // Datos personales: nombre, titular y presentación son obligatorios.
        if (form.getNombre().isBlank()) {
            errores.put("nombre", "error.obligatorio");
        }
        if (form.getTitular().isBlank()) {
            errores.put("titular", "error.obligatorio");
        }
        if (form.getPresentacion().isBlank()) {
            errores.put("presentacion", "error.obligatorio");
        }
        // El correo es opcional: solo se revisa su formato si se ha escrito algo.
        if(!form.getCorreo().isBlank() && !form.getCorreo().matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")){
            errores.put("correo", "error.correo");
        }

        // Proyectos: los cinco campos son obligatorios.
        for (int i = 0; i < proyectos.size(); i++) {
            ProyectoForm p = proyectos.get(i);
            String id = "proyectos" + i + ".";

            if (p.getTitulo().isBlank()) {
                errores.put(id + "titulo", "error.obligatorio");
            }

            // El año tiene que ser un número de cuatro cifras.
            if (p.getAnio().isBlank()) {
                errores.put(id + "anio", "error.obligatorio");
            }else if(!p.getAnio().trim().matches("\\d{4}")){
                errores.put(id + "anio", "error.anio");
            }

            // Categoría y estado tienen que ser uno de los valores de la lista, aunque se manipule el HTML.
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

        // Si al quitar las filas vacías no queda ninguna y hay que volver al formulario,
        // se añade una vacía para que siempre haya una visible.
        if (!errores.isEmpty() && proyectos.isEmpty()) {
            proyectos.add(new ProyectoForm());
        }
        return errores;
    }

    // Dice si una fila está completamente vacía (los cinco campos sin rellenar).
    public boolean filasVacias(ProyectoForm p){
        if (p.getTitulo().isBlank()&&p.getAnio().isBlank()&&p.getCategoria().isBlank()&&p.getEstado().isBlank()&&p.getDescripcion().isBlank()){
            return true;
        }
        return false;
    }
}
