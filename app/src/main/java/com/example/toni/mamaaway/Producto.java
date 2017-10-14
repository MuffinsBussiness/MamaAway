package com.example.toni.mamaaway;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by toni on 14/10/17.
 */

public class Producto {

    private String name;
    private int quantity;
    private String owner;
    private double price;
    private boolean paid;


    static ArrayList<Producto> result = new ArrayList<>();


    public Producto() {
    }

    public Producto(String name, int quantity, String owner, double price) {
        this.name = name;
        this.quantity = quantity;
        this.owner = owner;
        this.price = price;
        this.paid = false;
    }

    public Producto(String name, int quantity, String owner, double price, boolean paid) {
        this.name = name;
        this.quantity = quantity;
        this.owner = owner;
        this.price = price;
        this.paid = paid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public static void saveIntoFirebase(DatabaseReference databaseReference, List<Producto> productos) {
        String key = databaseReference.child("pisos").child("id").child("listas").push().getKey();
        databaseReference.child("pisos").child("id").child("listas").child(key).setValue(productos);

        double total = getTotalPrice(productos);
        double duty = getDuty(productos);

        databaseReference.child("pisos").child("id").child("listas").child(key).child("total").setValue(total);
        databaseReference.child("pisos").child("id").child("listas").child(key).child("duty").setValue(duty);

    }

    public static void saveIntoFirebase(DatabaseReference databaseReference, List<Producto> productos, String key) {
        databaseReference.child("pisos").child("id").child("listas").child(key).setValue(productos);

        double total = getTotalPrice(productos);
        double duty = getDuty(productos);

        databaseReference.child("pisos").child("id").child("listas").child(key).child("total").setValue(total);
        databaseReference.child("pisos").child("id").child("listas").child(key).child("total").setValue(duty);
    }

    static double getTotalPrice(List<Producto> productos) {
        double result = 0;
        for(Producto producto : productos) {
            result += producto.getQuantity() * producto.price;
        }
        return result;
    }

    static double getDuty(List<Producto> productos) {
        double result = 0;
        for(Producto producto : productos) {

            if(! producto.isPaid()) {
                result += producto.getQuantity() * producto.price;
            }
        }
        return result;
    }

    static ArrayList<Producto> getList(FirebaseDatabase database, String flatKey, String listKey) {
        //DatabaseReference myRef = database.getReference("/pisos/id/listas/-KwQ1VZ8ZKKVvfWKgUax");
        DatabaseReference myRef = database.getReference().child("pisos").child("id").child("listas").child("-KwQ1VZ8ZKKVvfWKgUax");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> dataSnapshotsChat = dataSnapshot.getChildren().iterator();

                while (dataSnapshotsChat.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshotsChat.next();
                    result.add(dataSnapshotChild.getValue(Producto.class));
                    //Producto TagName_Chosen = dataSnapshotChild.getValue(Producto.class); // check here whether you are getting the TagName_Chosen

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("MOCO", "Failed to read value.", databaseError.toException());
            }
        });

        return result;
    }

    ValueEventListener productoEventListener = new ValueEventListener() {

        /*DatabaseReference myRef2 = database.getReference().child("pisos").child("id").child("listas").child("-KwRiLg-E3WthnSDRQLZ");
        myRef2.addValueEventListener(productoEventListener);*/

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            Iterator<DataSnapshot> dataSnapshotsProductos = dataSnapshot.getChildren().iterator();

            while (dataSnapshotsProductos.hasNext()) {
                DataSnapshot dataSnapshotChild = dataSnapshotsProductos.next();
                if (dataSnapshotChild.hasChildren()) {
                    Producto TagName_Chosen = dataSnapshotChild.getValue(Producto.class);
                    Log.d("MOCO2", TagName_Chosen.getName());
                }

                else {
                    Double TagName_Chosen = dataSnapshotChild.getValue(Double.class);
                    Log.d("MOCO",TagName_Chosen.toString());
                }


            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };


}
