package com.jose.labintroductorio;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by jose on 01/03/2018.
 */

public class AmigosLocalDatasource implements  IAmigosDataSource
{

    private AmigosDAO amigosDAO;
    private static AmigosLocalDatasource mInstance;

    public AmigosLocalDatasource(AmigosDAO amigosDAO) {
        this.amigosDAO = amigosDAO;
    }

    public static AmigosLocalDatasource getInstance(AmigosDAO amigosDAO)
    {
        if (mInstance==null)
        {
            mInstance=new AmigosLocalDatasource(amigosDAO);
        }

        return mInstance;



    }

    @Override
    public Flowable<Amigos> getAmigosById(int amigosId) {
        return amigosDAO.getAmigosById(amigosId);
    }

    @Override
    public Flowable<List<Amigos>> getAllAmigos() {
        return amigosDAO.getAllAmigos();
    }

    @Override
    public void insertAmigos(Amigos... amigos) {
        amigosDAO.insertAmigos(amigos);

    }

    @Override
    public void updateAmigos(Amigos... amigos) {
        amigosDAO.updateAmigos(amigos);

    }

    @Override
    public void deleteAmigos(Amigos amigos) {
        amigosDAO.deleteAmigos(amigos);

    }

    @Override
    public void deleteAllAmigos() {
        amigosDAO.deleteAllAmigos();

    }
}
