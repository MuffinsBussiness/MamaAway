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

        List<Producto> productos = new ArrayList<Producto>();
        Producto producto = new Producto("Leches", 5, "Cristian", 5.5);
        productos.add(producto);

        String key = myRef.child("productos").push().getKey();
        myRef.child("productos").child(key).setValue(productos);

    }
}
