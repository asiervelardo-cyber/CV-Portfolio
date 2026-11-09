package com.example.CV_Portfolio.form;

// Una fila de proyecto del editor.
// El año es texto para que, si se escribe mal, no se pierda y se pueda corregir.
public class ProyectoForm {

    private String titulo = "";
    private String anio = "";
    private String categoria = "";
    private String estado = "";
    private String descripcion = "";

    public ProyectoForm() {}

    public ProyectoForm(String titulo, String anio, String categoria, String estado, String descripcion) {
        this.titulo = titulo;
        this.anio = anio;
        this.categoria = categoria;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnio() {
        return anio;
    }
    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
