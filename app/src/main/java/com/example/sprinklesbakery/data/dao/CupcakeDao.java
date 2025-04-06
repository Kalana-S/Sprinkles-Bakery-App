package com.example.sprinklesbakery.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;
import com.example.sprinklesbakery.data.model.Cupcake;

@Dao
public interface CupcakeDao {
    @Insert
    void insert(Cupcake cupcake);

    @Update
    void update(Cupcake cupcake);

    @Query("SELECT * FROM cupcakes ORDER BY cupcakeName ASC")
    List<Cupcake> getAllCupcakes();

    @Delete
    void delete(Cupcake cupcake);
}