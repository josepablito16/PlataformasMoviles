package com.jose.labintroductorio;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by jose on 21/02/2018.
 */

@Entity(tableName="amigos")
public class Amigos implements Parcelable
{
    @NonNull
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="nombre")
    private String nombre;

    @ColumnInfo(name = "edad")
    private int edad;

    @ColumnInfo(name = "carrera")
    private String carrera;




    //constructor
    @Ignore
    public Amigos(String n, int e, String c, String gusto1, String gusto2)
    {
        this.nombre=n;
        this.edad=e;
        this.carrera=c;

    }

    public Amigos(){}

    //todos los gets

    public String getNombre() {
        return nombre;
    }


    public int getEdad() {
        return edad;
    }



    public String getCarrera() {
        return carrera;
    }

    @NonNull
    public int getId() {
        return id;
    }


    public void setId(@NonNull int id) {
        this.id = id;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }


    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }



    @Ignore
    public static Creator<Amigos> getCREATOR() {
        return CREATOR;
    }

    @Ignore
    protected Amigos(Parcel in) {
        nombre = in.readString();
        edad = in.readInt();
        carrera = in.readString();
    }


    @Ignore
    public String getGustos2(ArrayList<Gustos> gustosL)
    {
        String concatenarGustos="";
        for (Gustos i:gustosL)
        {
            concatenarGustos+=i.getGusto1()+"\n";

        }

        return concatenarGustos;

    }

    //metodos del Parcelable
    @Override
    @Ignore
    public int describeContents() {
        return 0;
    }

    @Override
    @Ignore
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(edad);
        dest.writeString(carrera);


    }

    @Override
    @Ignore
    public String toString() {
        return nombre;

    }

    @SuppressWarnings("unused")
    @Ignore
    public static final Creator<Amigos> CREATOR = new Creator<Amigos>() {
        @Override
        public Amigos createFromParcel(Parcel in) {
            return new Amigos(in);
        }

        @Override
        public Amigos[] newArray(int size) {
            return new Amigos[size];
        }
    };
}