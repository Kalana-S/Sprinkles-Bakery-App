package com.example.sprinklesbakery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sprinklesbakery.R;
import com.example.sprinklesbakery.data.model.Order;
import java.util.List;

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Order order);
    }

    public AdminOrderAdapter(List<Order> orderList, OnDeleteClickListener listener) {
        this.orderList = orderList;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_admin, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvCupcakeName.setText("Cupcake: " + order.getCupcakeName());
        holder.tvCategory.setText("Category: " + order.getCategory());
        holder.tvPrice.setText("Price: $" + order.getPrice());
        holder.tvQuantity.setText("Qty: " + order.getQuantity());
        holder.tvTotalPrice.setText("Total: $" + order.getTotalPrice());
        holder.tvMember.setText("Member: " + order.getMemberName() + "\n" + order.getMemberEmail());

        holder.btnDelete.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void updateOrders(List<Order> orders) {
        this.orderList = orders;
        notifyDataSetChanged();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvCupcakeName, tvCategory, tvPrice, tvQuantity, tvTotalPrice, tvMember;
        Button btnDelete;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCupcakeName = itemView.findViewById(R.id.tvCupcakeName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvMember = itemView.findViewById(R.id.tvMember);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

}
