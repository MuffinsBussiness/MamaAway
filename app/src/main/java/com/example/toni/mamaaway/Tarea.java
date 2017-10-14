package com.example.toni.mamaaway;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;
import java.util.List;

/**
 * Created by toni on 14/10/17.
 */

public class Tarea {

    private String titulo;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFinal;
    //TODO: cambiar por lista de usuarios
    private String encargado;

    public Tarea() {}

    public Tarea(String titulo, String descripcion, Date fechaInicio, Date fechaFinal) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    public Tarea(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void saveIntoFirebase(DatabaseReference databaseReference) {
        String key = databaseReference.child("tareas").push().getKey();
        databaseReference.child("tareas").child(key).setValue(this);
    }

}
