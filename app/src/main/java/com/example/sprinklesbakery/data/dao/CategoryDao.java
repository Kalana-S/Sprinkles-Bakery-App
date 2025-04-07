package com.example.sprinklesbakery.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import java.util.List;
import com.example.sprinklesbakery.data.model.Category;
@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);

    @Query("SELECT * FROM categories ORDER BY categoryName ASC")
    List<Category> getAllCategories();

    @Delete
    void delete(Category category);
}
