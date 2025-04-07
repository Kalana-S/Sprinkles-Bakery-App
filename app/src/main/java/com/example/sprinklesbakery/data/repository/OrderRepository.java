package com.example.sprinklesbakery.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.sprinklesbakery.data.database.AppDatabase;
import com.example.sprinklesbakery.data.dao.OrderDao;
import com.example.sprinklesbakery.data.model.Order;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class OrderRepository {

    private OrderDao orderDao;
    private ExecutorService executorService;

    public OrderRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        orderDao = database.orderDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Order order) {
        executorService.execute(() -> orderDao.insert(order));
    }

    public LiveData<List<Order>> getOrdersByMember(int memberId) {
        return orderDao.getOrdersByMember(memberId);
    }

    public LiveData<List<Order>> getOrdersByMemberId(int memberId) {
        return orderDao.getOrdersByMember(memberId);
    }

    public LiveData<List<Order>> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public void delete(Order order) {
        executorService.execute(() -> orderDao.delete(order));
    }

    public void update(Order order) {
        executorService.execute(() -> orderDao.update(order));
    }

}
