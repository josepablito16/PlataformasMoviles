package com.jose.labintroductorio;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import static com.jose.labintroductorio.AmigosDatabase.DATABASE_VERSION;

/**
 * Created by jose on 01/03/2018.
 */

@Database(entities = Amigos.class,version = DATABASE_VERSION)
public abstract class AmigosDatabase extends RoomDatabase
{
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="EDMT-Database-Room";

    public abstract AmigosDAO amigosDAO();

    private static AmigosDatabase mInstance;

    public static AmigosDatabase getmInstance(Context context)
    {
        if (mInstance==null)
        {
            mInstance= Room.databaseBuilder(context,AmigosDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return mInstance;

    }



}
