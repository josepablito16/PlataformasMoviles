package com.jose.labintroductorio;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jose on 22/02/2018.
 */

public class Gustos implements Parcelable
{
    private String gusto1;


    public Gustos(String gusto1)
    {
        this.gusto1 = gusto1;

    }

    protected Gustos(Parcel in) {
        gusto1 = in.readString();

    }

    public static final Creator<Gustos> CREATOR = new Creator<Gustos>() {
        @Override
        public Gustos createFromParcel(Parcel in) {
            return new Gustos(in);
        }

        @Override
        public Gustos[] newArray(int size) {
            return new Gustos[size];
        }
    };

    public String getGusto1() {
        return gusto1;
    }

    public void setGusto1(String gusto1) {
        this.gusto1 = gusto1;
    }





    @Override
    public String toString() {
        return "com.jose.labintroductorio.Gustos{" + "gusto1='" + gusto1 + '\'' + ", gusto2='"  + '\'' + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gusto1);

    }
}
