package com.example.sprinklesbakery.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.sprinklesbakery.data.model.Order;
import com.example.sprinklesbakery.data.repository.OrderRepository;
import java.util.List;

public class OrderViewModel extends AndroidViewModel{

    private OrderRepository orderRepository;
    private LiveData<List<Order>> allOrders;

    public OrderViewModel(Application application) {
        super(application);
        orderRepository = new OrderRepository(application);
        allOrders = orderRepository.getAllOrders();
    }

    public void insert(Order order) {
        orderRepository.insert(order);
    }

    public LiveData<List<Order>> getOrdersByMember(int memberId) {
        return orderRepository.getOrdersByMember(memberId);
    }

    public LiveData<List<Order>> getOrdersByMemberId(int memberId) {
        return orderRepository.getOrdersByMemberId(memberId);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public LiveData<List<Order>> getAllOrders() {
        return allOrders;
    }

    public void update(Order order) {
        orderRepository.update(order);
    }

}
