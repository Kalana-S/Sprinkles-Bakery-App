package com.example.sprinklesbakery.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.sprinklesbakery.data.model.Cupcake;
import com.example.sprinklesbakery.data.repository.CupcakeRepository;
import java.util.List;

public class CupcakeViewModel extends AndroidViewModel {

    private CupcakeRepository repository;
    private LiveData<List<Cupcake>> allCupcakes;

    public CupcakeViewModel(Application application) {
        super(application);
        repository = new CupcakeRepository(application);
        allCupcakes = repository.getAllCupcakes();
    }

    public LiveData<List<Cupcake>> getAllCupcakes() {
        return allCupcakes;
    }

    public void refreshCupcakes() {
        allCupcakes = repository.getAllCupcakes();
    }

    public void insert(Cupcake cupcake) {
        repository.insert(cupcake);
    }

    public void delete(Cupcake cupcake) {
        repository.delete(cupcake);
    }

    public void update(Cupcake cupcake) {
        repository.update(cupcake);
    }

}
