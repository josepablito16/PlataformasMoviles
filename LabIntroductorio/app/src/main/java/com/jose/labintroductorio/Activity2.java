package com.jose.labintroductorio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //se crean las variables necesarias y se inicializan con los valores que se le enviaron desde el MainActivity
        // se utiliza el metodo getVarExtra y se pone el id
        String nombre=getIntent().getStringExtra("nombre");
        String carrera=getIntent().getStringExtra("carrera");
        int edad=getIntent().getIntExtra("edad",5);
        ArrayList<Gustos> gustos=getIntent().getParcelableArrayListExtra("gustos");

        //se crea un objeto de tipo TextView y se referencia por medio del ID al creado en la GUI
        TextView textView= findViewById(R.id.textView);

        // se crea un objeto amigos solo para acceder a un metodo el cual da un toString del ArrayList()
        Amigos amics=new Amigos();
        String concatenar =amics.getGustos2(gustos);

        //se le ingresa la data que deseamos al TextView
        textView.setText("El nombre del amigo es: "+nombre+"\n La edad del amigo es:"+edad+"\n Y su carrera es "+carrera+"\n \n  \n y sus gustos son: \n" +
                concatenar);



    }
}
