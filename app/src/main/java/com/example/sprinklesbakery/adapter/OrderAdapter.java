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
import java.util.ArrayList;
import java.util.List;
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    public interface OnOrderDeleteListener {
        void onDeleteOrder(Order order);
    }

    private List<Order> orderList = new ArrayList<>();
    private final OnOrderDeleteListener deleteListener;

    public OrderAdapter(OnOrderDeleteListener listener) {
        this.deleteListener = listener;
    }

    public void setOrderList(List<Order> orders) {
        this.orderList = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_member, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvDetails.setText(
                "Cupcake: " + order.getCupcakeName() +
                        "\nCategory: " + order.getCategory() +
                        "\nPrice: $" + order.getPrice() +
                        "\nQuantity: " + order.getQuantity() +
                        "\nTotal: $" + order.getTotalPrice()
        );
        holder.btnDelete.setOnClickListener(v -> deleteListener.onDeleteOrder(order));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvDetails;
        Button btnDelete;

        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetails = itemView.findViewById(R.id.tvOrderDetails);
            btnDelete = itemView.findViewById(R.id.btnDeleteOrder);
        }
    }

}
