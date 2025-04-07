package com.example.sprinklesbakery.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.data.model.Cupcake;
import java.util.ArrayList;
import java.util.List;

public class ViewCupcakeAdapter extends RecyclerView.Adapter<ViewCupcakeAdapter.CupcakeViewHolder> {

    private List<Cupcake> cupcakeList = new ArrayList<>();

    @NonNull
    @Override
    public CupcakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_cupcake, parent, false);
        return new CupcakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CupcakeViewHolder holder, int position) {
        Cupcake cupcake = cupcakeList.get(position);
        holder.tvCupcakeName.setText(cupcake.getCupcakeName());
        holder.tvCategory.setText("Category: " + cupcake.getCategory());
        holder.tvPrice.setText("Price: $" + cupcake.getPrice());
        holder.tvQuantity.setText("Quantity: " + cupcake.getQuantity());
        holder.imageCupcake.setImageURI(Uri.parse(cupcake.getImageUri()));
    }

    @Override
    public int getItemCount() {
        return cupcakeList.size();
    }

    public void setCupcakeList(List<Cupcake> cupcakes) {
        this.cupcakeList = cupcakes;
        notifyDataSetChanged();
    }

    static class CupcakeViewHolder extends RecyclerView.ViewHolder {
        TextView tvCupcakeName, tvCategory, tvPrice, tvQuantity;
        ImageView imageCupcake;

        public CupcakeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCupcakeName = itemView.findViewById(R.id.tvCupcakeName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            imageCupcake = itemView.findViewById(R.id.imageCupcake);
        }
    }

}