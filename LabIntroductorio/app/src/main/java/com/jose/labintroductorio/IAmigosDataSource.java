package com.jose.labintroductorio;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by jose on 01/03/2018.
 */

public interface IAmigosDataSource
{

    Flowable<Amigos> getAmigosById(int amigosId);
    Flowable<List<Amigos>> getAllAmigos();
    void insertAmigos(Amigos... amigos);
    void updateAmigos(Amigos... amigos);
    void deleteAmigos(Amigos amigos);
    void deleteAllAmigos();


}
