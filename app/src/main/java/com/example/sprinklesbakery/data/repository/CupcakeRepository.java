package com.example.sprinklesbakery.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.sprinklesbakery.data.database.AppDatabase;
import com.example.sprinklesbakery.data.dao.CupcakeDao;
import com.example.sprinklesbakery.data.model.Cupcake;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CupcakeRepository {

    private CupcakeDao cupcakeDao;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public CupcakeRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        cupcakeDao = db.cupcakeDao();
    }

    public void insert(Cupcake cupcake) {
        executor.execute(() -> cupcakeDao.insert(cupcake));
    }

    public LiveData<List<Cupcake>> getAllCupcakes() {
        MutableLiveData<List<Cupcake>> cupcakesLiveData = new MutableLiveData<>();
        executor.execute(() -> cupcakesLiveData.postValue(cupcakeDao.getAllCupcakes()));
        return cupcakesLiveData;
    }

    public void delete(Cupcake cupcake) {
        executor.execute(() -> cupcakeDao.delete(cupcake));
    }

    public void update(Cupcake cupcake) {
        executor.execute(() -> cupcakeDao.update(cupcake));
    }

}
