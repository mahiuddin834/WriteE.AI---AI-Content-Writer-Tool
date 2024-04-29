package com.itnation.writee_aicontentwritertool.MVVM;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Content.class, version = 1)
public abstract class ContentDataBase extends RoomDatabase {

    public static ContentDataBase instance ;
    public abstract ContentDao contentDao();
    public static synchronized ContentDataBase getInstance(Context context){

        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), ContentDataBase.class, "content_database").fallbackToDestructiveMigration().build();
        }

        return instance;
    }
}
