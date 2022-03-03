package com.rightbrain.officeproject.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rightbrain.officeproject.model.ModelCartRoom;


@Database(entities = ModelCartRoom.class,version = 13,exportSchema = false)
public abstract class MyRoomDataBase extends RoomDatabase {

    private static MyRoomDataBase roomDataBase = null;

    public abstract RoomDao roomDao();

    public static synchronized MyRoomDataBase getInstance(Context context)
    {
        if (roomDataBase==null)
        {
            roomDataBase = Room.databaseBuilder(context, MyRoomDataBase.class,"cartdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return roomDataBase;
    }

}
