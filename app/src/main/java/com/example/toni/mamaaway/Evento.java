package com.example.toni.mamaaway;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by toni on 14/10/17.
 */

public class Evento {

    String titulo;
    String descripcion;
    Date fechaIni;
    Date fechaFin;

    public Evento() {}

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

    ValueEventListener eventosEventListener = new ValueEventListener() {

        /*DatabaseReference myRef2 = database.getReference().child("pisos").child("id").child("eventos");
        myRef2.addValueEventListener(eventosEventListener);*/

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            Iterator<DataSnapshot> dataSnapshotsEventos = dataSnapshot.getChildren().iterator();

            while (dataSnapshotsEventos.hasNext()) {
                DataSnapshot dataSnapshotChild = dataSnapshotsEventos.next();
                Evento TagName_Chosen = dataSnapshotChild.getValue(Evento.class);

                Log.d("MOCO", TagName_Chosen.getTitulo());

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };

    public static ArrayList<Evento> getRangedList(ArrayList<Evento> eventos, Date fechaIni, Date fechaFin) {
        ArrayList<Evento> result = new ArrayList<>();
        for(Evento evento : eventos) {
            if (!evento.getFechaIni().before(fechaIni) && !evento.getFechaFin().before(fechaFin)) {
                result.add(evento);
            }
        }
        return result;
    }
}