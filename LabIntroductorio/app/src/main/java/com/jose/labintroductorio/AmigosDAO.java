package com.jose.labintroductorio;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by jose on 01/03/2018.
 */

@Dao
public interface AmigosDAO
{
    @Query("SELECT * FROM amigos WHERE id=:amigosId")
    Flowable<Amigos> getAmigosById(int amigosId);

    @Query("SELECT * FROM amigos")
    Flowable<List<Amigos>> getAllAmigos();

    @Insert
    void insertAmigos(Amigos... amigos);

    @Update
    void updateAmigos(Amigos... amigos);

    @Delete
    void deleteAmigos(Amigos amigos);

    @Query("DELETE FROM amigos")
    void deleteAllAmigos();













}
