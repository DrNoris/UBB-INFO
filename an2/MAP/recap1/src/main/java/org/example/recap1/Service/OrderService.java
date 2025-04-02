package org.example.recap1.Service;

import org.example.recap1.Domain.MenuItem;
import org.example.recap1.Domain.Order;
import org.example.recap1.Repo.OrderRepo;

import java.util.List;

public class OrderService extends Observable{
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo){
        this.orderRepo = orderRepo;
    }

    public void addOrder(int tableId, List<MenuItem> items){
        orderRepo.createOrder(tableId, items);
        notif();
    }

    public List<Order> getAll(){
        return orderRepo.getAllOrders();
    }
}
