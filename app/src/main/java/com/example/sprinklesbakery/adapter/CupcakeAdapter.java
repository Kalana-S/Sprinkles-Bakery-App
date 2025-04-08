package com.example.sprinklesbakery.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.data.model.Cupcake;
import java.util.ArrayList;
import java.util.List;

public class CupcakeAdapter extends RecyclerView.Adapter<CupcakeAdapter.CupcakeViewHolder> {

    private List<Cupcake> cupcakeList = new ArrayList<>();
    private OnCupcakeDeleteListener deleteListener;
    private OnCupcakeUpdateListener updateListener;

    public interface OnCupcakeDeleteListener {
        void onDeleteCupcake(Cupcake cupcake);
    }

    public interface OnCupcakeUpdateListener {
        void onUpdateCupcake(Cupcake cupcake);
    }

    public CupcakeAdapter(OnCupcakeDeleteListener deleteListener, OnCupcakeUpdateListener updateListener) {
        this.deleteListener = deleteListener;
        this.updateListener = updateListener;
    }

    @NonNull
    @Override
    public CupcakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cupcake, parent, false);
        return new CupcakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CupcakeViewHolder holder, int position) {
        Cupcake cupcake = cupcakeList.get(position);

        holder.tvCupcakeName.setText(cupcake.getCupcakeName());
        holder.tvCategory.setText("Category: " + cupcake.getCategory());
        holder.tvPrice.setText("Price: $" + cupcake.getPrice());
        holder.tvQuantity.setText("Quantity: " + cupcake.getQuantity());

        String imageUriString = cupcake.getImageUri();
        if (imageUriString != null && !imageUriString.isEmpty()) {
            try {
                Uri imageUri = Uri.parse(imageUriString);
                holder.imageCupcake.setImageURI(imageUri);
            } catch (Exception e) {
                e.printStackTrace();
                holder.imageCupcake.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        } else {
            holder.imageCupcake.setImageResource(android.R.drawable.ic_menu_gallery);
        }
        holder.btnDelete.setOnClickListener(v -> deleteListener.onDeleteCupcake(cupcake));
        holder.btnUpdate.setOnClickListener(v -> updateListener.onUpdateCupcake(cupcake));
    }

    @Override
    public int getItemCount() {
        return cupcakeList.size();
    }

    public void setCupcakeList(List<Cupcake> cupcakes) {
        this.cupcakeList = cupcakes;
        notifyDataSetChanged();
    }

    public List<Cupcake> getCupcakeList() {
        return cupcakeList;
    }

    static class CupcakeViewHolder extends RecyclerView.ViewHolder {
        TextView tvCupcakeName, tvCategory, tvPrice, tvQuantity;
        ImageView imageCupcake;
        Button btnDelete, btnUpdate;

        public CupcakeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCupcakeName = itemView.findViewById(R.id.tvCupcakeName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            imageCupcake = itemView.findViewById(R.id.imageCupcake);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }

}
