package com.example.sprinklesbakery.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;
import com.example.sprinklesbakery.data.model.Cupcake;
import java.util.List;

@Dao
public interface CupcakeDao {

    @Insert
    void insert(Cupcake cupcake);

    @Update
    void update(Cupcake cupcake);

    @Query("SELECT * FROM cupcakes ORDER BY cupcakeName ASC")
    LiveData<List<Cupcake>> getAllCupcakes();

    @Delete
    void delete(Cupcake cupcake);

}
