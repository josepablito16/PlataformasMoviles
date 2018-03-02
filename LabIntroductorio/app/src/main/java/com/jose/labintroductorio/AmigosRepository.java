package com.jose.labintroductorio;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by jose on 01/03/2018.
 */

public class AmigosRepository implements IAmigosDataSource
{
    private IAmigosDataSource mLocalDataSource;

    private static AmigosRepository mInstance;

    public AmigosRepository(IAmigosDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static AmigosRepository getmInstance (IAmigosDataSource mLocalDataSource)
    {
        if (mInstance==null)
        {
            mInstance=new AmigosRepository(mLocalDataSource);
        }
        return mInstance;
    }


    @Override
    public Flowable<Amigos> getAmigosById(int amigosId) {
        return mLocalDataSource.getAmigosById(amigosId);
    }

    @Override
    public Flowable<List<Amigos>> getAllAmigos() {
        return mLocalDataSource.getAllAmigos();
    }

    @Override
    public void insertAmigos(Amigos... amigos) {
        mLocalDataSource.insertAmigos(amigos);

    }

    @Override
    public void updateAmigos(Amigos... amigos) {
        mLocalDataSource.updateAmigos(amigos);

    }

    @Override
    public void deleteAmigos(Amigos amigos) {
        mLocalDataSource.deleteAmigos(amigos);

    }

    @Override
    public void deleteAllAmigos() {
        mLocalDataSource.deleteAllAmigos();

    }
}
