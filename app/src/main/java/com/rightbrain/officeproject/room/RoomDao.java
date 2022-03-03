package com.rightbrain.officeproject.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rightbrain.officeproject.model.ModelCartRoom;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSingleData(ModelCartRoom cartdb);

    @Update
    void updateSingleData(ModelCartRoom cartdb);

    @Delete
    void DeleteSingleData(ModelCartRoom cartdb);

    @Query("DELETE FROM cartitem")
    void deleteAll();

    @Query("SELECT * FROM cartitem")
    LiveData<List<ModelCartRoom>> getAllData();


    @Query("SELECT * FROM cartitem WHERE productId = (:name)")
    LiveData<ModelCartRoom> getNote(String name);

    @Query("SELECT * FROM cartitem WHERE productId = (:name)")
    ModelCartRoom getNotes(String name);


    @Query("UPDATE cartitem SET quantity=:qty WHERE productId = :id")
    void updaterow(int qty, String id);

    @Query("SELECT name FROM cartitem WHERE productId = (:name)")
    String getNoteSearch(String name);


   /* @Query("Select COUNT(*) from cartitem where p_name = (:search)")
    int getNote(String search);*/

   /*@Query("Select * from cartitem where productId = (:search)")
   ModelCartRoom getNote(String search);*/


    @Query("SELECT COUNT(*) FROM cartitem where name = (:search)")
    int agentsCount(String search);





   /* @Query("SELECT * FROM cartitem WHERE p_name = (:name)")
    ModelCartRoom FindName(String name);*/


    


}
