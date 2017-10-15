package com.example.toni.mamaaway;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    public static void saveIntoFirebase(DatabaseReference databaseReference, List<Tarea> tareas) {
        databaseReference.child("pisos").child("id").child("tareas").setValue(tareas);
    }

    public static void saveIntoFirebase(DatabaseReference databaseReference, List<Tarea> tareas, String key) {
        databaseReference.child("pisos").child("id").child("tareas").setValue(tareas);
    }

    public static ArrayList<Tarea> getRangedList(ArrayList<Tarea> tareas, Date fechaIni, Date fechaFin) {
        ArrayList<Tarea> result = new ArrayList<>();
        for(Tarea tarea : tareas) {
            if (tarea.getFechaInicio()!= null && tarea.getFechaFinal() !=null) {
                if (!tarea.getFechaInicio().before(fechaIni) && !tarea.getFechaFinal().before(fechaFin)) {
                    result.add(tarea);
                }
            }
        }
        return result;
    }


    ValueEventListener tareaEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            Iterator<DataSnapshot> dataSnapshotsTareas = dataSnapshot.getChildren().iterator();

            while (dataSnapshotsTareas.hasNext()) {
                DataSnapshot dataSnapshotChild = dataSnapshotsTareas.next();
                Tarea TagName_Chosen = dataSnapshotChild.getValue(Tarea.class);

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };

}
