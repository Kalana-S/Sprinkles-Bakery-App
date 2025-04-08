package com.example.sprinklesbakery.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.adapter.CupcakeAdapter;
import com.example.sprinklesbakery.data.model.Cupcake;
import com.example.sprinklesbakery.viewmodel.CupcakeViewModel;
import java.util.ArrayList;
import java.util.List;

public class ManageCupcakesActivity extends AppCompatActivity implements CupcakeAdapter.OnCupcakeDeleteListener, CupcakeAdapter.OnCupcakeUpdateListener {

    private CupcakeViewModel cupcakeViewModel;
    private CupcakeAdapter cupcakeAdapter;
    private ActivityResultLauncher<Intent> updateCupcakeLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cupcakes);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCupcakes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cupcakeAdapter = new CupcakeAdapter(this, this);
        recyclerView.setAdapter(cupcakeAdapter);

        cupcakeViewModel = new ViewModelProvider(this).get(CupcakeViewModel.class);
        observeCupcakes();

        updateCupcakeLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        cupcakeViewModel.refreshCupcakes();
                    }
                }
        );
    }

    private void observeCupcakes() {
        cupcakeViewModel.getAllCupcakes().observe(this, cupcakes -> {
            cupcakeAdapter.setCupcakeList(cupcakes);
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

    @Override
    public void onUpdateCupcake(Cupcake cupcake) {
        Intent intent = new Intent(this, UpdateCupcakeActivity.class);
        intent.putExtra("cupcake", cupcake);
        updateCupcakeLauncher.launch(intent);
    }

}
