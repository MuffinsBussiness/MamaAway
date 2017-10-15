package com.example.toni.mamaaway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myRef.setValue("Hello World!");

        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("MOCO", "Values is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("MOCO", "Failed to read value.", databaseError.toException());
            }
        });*/

        /*List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto("Leches", 5, "Cristian", 5.5);
        Producto producto1 = new Producto("Leches", 5, "Cristian", 6.5);
        Producto producto2 = new Producto("Leches", 5, "Cristian", 7.5);
        Producto producto3 = new Producto("Leches", 5, "Cristian", 8.5);

        producto.setPaid(true);


        productos.add(producto);
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);

        Producto.saveIntoFirebase(myRef,productos);*/

        //productos = Producto.getList(database, "id", "-KwQ1VZ8ZKKVvfWKgUax");

        /*productos = Producto.getList(database, "", "");

        Log.d("MOCOO", String.valueOf(productos.get(0).getName()));*/

        /*List<Tarea> tareas = new ArrayList<Tarea>();

        Tarea tarea = new Tarea("Hello", "HELLO");
        Tarea tarea1 = new Tarea("Hello1", "HELLO");
        Tarea tarea2 = new Tarea("Hello2", "HELLO");
        Tarea tarea3 = new Tarea("Hello3", "HELLO");
        Tarea tarea4 = new Tarea("Hello4", "HELLO");

        tareas.add(tarea);
        tareas.add(tarea1);
        tareas.add(tarea2);
        tareas.add(tarea3);
        tareas.add(tarea4);

        Tarea.saveIntoFirebase(myRef,tareas);

        List<Evento> eventos = new ArrayList<Evento>();

        Date currentTime = Calendar.getInstance().getTime();
        Evento evento = new Evento("Titulo", "Desc", currentTime, currentTime);
        Evento evento2 = new Evento("Titulo2", "Desc", currentTime, currentTime);
        Evento evento3 = new Evento("Titulo3", "Desc", currentTime, currentTime);
        Evento evento4 = new Evento("Titulo4", "Desc", currentTime, currentTime);

        eventos.add(evento);
        eventos.add(evento2);
        eventos.add(evento3);
        eventos.add(evento4);

        Evento.saveIntoFirebase(myRef, eventos);*/


        ValueEventListener eventosEventListener = new ValueEventListener() {
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

        //DatabaseReference myRef2 = database.getReference().child("pisos").child("id").child("eventos");
        //myRef2.addValueEventListener(eventosEventListener);

        Producto producto = new Producto();
        producto.getList(database, "", "");
        Log.d("MOCO3", String.valueOf(producto.result.isEmpty()));




    }
}
