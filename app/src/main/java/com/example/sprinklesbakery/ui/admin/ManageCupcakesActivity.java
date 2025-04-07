package com.example.sprinklesbakery.ui.admin;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.adapter.CupcakeAdapter;
import com.example.sprinklesbakery.data.model.Cupcake;
import com.example.sprinklesbakery.viewmodel.CupcakeViewModel;
import java.util.ArrayList;
import java.util.List;

public class ManageCupcakesActivity extends AppCompatActivity implements CupcakeAdapter.OnCupcakeDeleteListener {

    private CupcakeViewModel cupcakeViewModel;
    private CupcakeAdapter cupcakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cupcakes);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCupcakes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cupcakeAdapter = new CupcakeAdapter(this);
        recyclerView.setAdapter(cupcakeAdapter);

        cupcakeViewModel = new ViewModelProvider(this).get(CupcakeViewModel.class);
        cupcakeViewModel.getAllCupcakes().observe(this, new Observer<List<Cupcake>>() {
            @Override
            public void onChanged(List<Cupcake> cupcakes) {
                cupcakeAdapter.setCupcakeList(cupcakes);
            }
        });
    }

    @Override
    public void onDeleteCupcake(Cupcake cupcake) {
        cupcakeViewModel.delete(cupcake);
        Toast.makeText(this, "Cupcake deleted", Toast.LENGTH_SHORT).show();

        List<Cupcake> updatedList = new ArrayList<>(cupcakeAdapter.getCupcakeList());
        updatedList.remove(cupcake);
        cupcakeAdapter.setCupcakeList(updatedList);
    }

}
