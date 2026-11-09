package com.example.CV_Portfolio.form;

import java.util.ArrayList;
import java.util.List;

// Guarda todo lo que se escribe en el editor (/portfolio/editar).
// Spring lo rellena solo con los campos del formulario: "nombre" llama a setNombre(...), etc.
// Todo empieza con "" y la lista vacía para no tener valores null al validar.
public class CurriculumForm {
    private String nombre = "";
    private String titular = "";
    private String presentacion ="";
    private String ciudad ="";
    private String correo = "";
    // Cada fila de proyecto del formulario es un ProyectoForm de esta lista.
    private List<ProyectoForm> proyectos = new ArrayList<>();

    // Spring necesita este constructor vacío para crear el objeto antes de rellenarlo.
    public CurriculumForm() {}

    public CurriculumForm(String nombre, String titular, String presentacion, String ciudad, String correo, List<ProyectoForm> proyectos){
        this.nombre = nombre;
        this.titular = titular;
        this.presentacion = presentacion;
        this.ciudad = ciudad;
        this.correo = correo;
        this.proyectos = proyectos;
    }

    // Getters y setters: Spring usa los setters para guardar lo enviado,
    // y Thymeleaf los getters para enseñarlo (por ejemplo ${form.nombre}).
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public String getPresentacion() {
        return presentacion;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public List<ProyectoForm> getProyectos() {
        return proyectos;
    }
    public void setProyectos(List<ProyectoForm> proyectos) {
        this.proyectos = proyectos;
    }
}
