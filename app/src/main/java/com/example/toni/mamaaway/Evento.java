package com.example.toni.mamaaway;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;
import java.util.List;

/**
 * Created by toni on 14/10/17.
 */

public class Evento {

    String titulo;
    String descripcion;
    Date fechaIni;
    Date fechaFin;

    public Evento(String titulo, String descripcion, Date fechaIni, Date fechaFin) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
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

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public static void saveIntoFirebase(DatabaseReference databaseReference, List<Evento> eventos) {
        databaseReference.child("pisos").child("id").child("eventos").setValue(eventos);
    }

    public static void saveIntoFirebase(DatabaseReference databaseReference, List<Evento> eventos, String key) {
        databaseReference.child("pisos").child("id").child("eventos").setValue(eventos);
    }
}