package com.example.sprinklesbakery.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.sprinklesbakery.data.model.Order;
import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insert(Order order);

    @Query("SELECT * FROM orders WHERE memberId = :memberId")
    LiveData<List<Order>> getOrdersByMember(int memberId);

    @Delete
    void delete(Order order);

    @Update
    void update(Order order);

    @Query("SELECT * FROM orders")
    LiveData<List<Order>> getAllOrders();

}
