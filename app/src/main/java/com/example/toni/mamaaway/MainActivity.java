package com.example.toni.mamaaway;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "MainActivity";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private String mUsername;
    private String mPhotoUrl;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;


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

        /*Producto producto = new Producto();
        producto.getList(database, "", "");
        Log.d("MOCO3", String.valueOf(producto.result.isEmpty()));*/


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in.
        // TODO: Add code to check if user is signed in.
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if(mFirebaseUser == null) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
