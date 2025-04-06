package com.example.sprinklesbakery.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.sprinklesbakery.data.database.AppDatabase;
import com.example.sprinklesbakery.data.dao.CategoryDao;
import com.example.sprinklesbakery.data.model.Category;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryRepository {

    private CategoryDao categoryDao;
    private MutableLiveData<List<Category>> allCategories = new MutableLiveData<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public CategoryRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        categoryDao = db.categoryDao();
        loadCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    public void insert(Category category) {
        executor.execute(() -> {
            categoryDao.insert(category);
            loadCategories();
        });
    }

    public void delete(Category category) {
        executor.execute(() -> {
            categoryDao.delete(category);
            loadCategories();
        });
    }

    private void loadCategories() {
        executor.execute(() -> allCategories.postValue(categoryDao.getAllCategories()));
    }

}
