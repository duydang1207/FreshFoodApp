package com.example.freshfoodapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.freshfoodapp.DAO.CartDAO;
import com.example.freshfoodapp.Entity.CartEntity;

@Database(entities = {CartEntity.class},version = 1)
public abstract class AbstractDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "cart.db";
    private static AbstractDatabase instance;

    public static synchronized AbstractDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext()
            ,AbstractDatabase.class,DATABASE_NAME).allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract CartDAO cartDAO();
}


