package com.Sandhya.studentapi.service;

import com.Sandhya.studentapi.dto.CourseOrderRequestDTO;
import com.Sandhya.studentapi.dto.OrderResponseDTO;
import com.Sandhya.studentapi.entity.Orders;
import com.Sandhya.studentapi.repository.OrdersRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final RazorpayClient razorpayClient;//this dependency is required to interact with Razorpay's payment APIs.
    private final OrdersRepository ordersRepository;// to save and fetch order details from the     database.

    //Constroctor injection:Spring will inject RazorpayClient bean and ordersRepository bean
    public PaymentService(RazorpayClient razorpayClient, OrdersRepository ordersRepository) {
        this.razorpayClient = razorpayClient;
        this.ordersRepository = ordersRepository;
    }

    //this method creates razorpay order and save it to db
    public Orders createOrder(CourseOrderRequestDTO orderRequest) throws RazorpayException {
        //Now ill save the order into DB using Orders entity
        Orders orders = new Orders();
        orders.setName(orderRequest.getCourseName());
        orders.setEmail(orderRequest.getStudentEmail());
        orders.setAmount(orderRequest.getAmount());
        //Razorpay order creation( JSON payload required by Razorpay) and if the razorpay server returns any error like network issue,invalid amount then catch it so will not save failed order to db
        try {
            JSONObject obj = new JSONObject();
            obj.put("amount", orderRequest.getAmount() * 100);//Amount in paise
            obj.put("currency", "INR");
            obj.put("receipt", "txn_" + System.currentTimeMillis());
            //Internally  Calls Razorpay create API to create an order
            Order razorpayOrder = razorpayClient.orders.create(obj);
            orders.setOrderStatus("Created");
            orders.setRazorpayOrderId(razorpayOrder.get("id"));
        } catch (RazorpayException e) {
            orders.setOrderStatus("Failed");
            System.err.println("Razorpay error: " + e.getMessage());
        }
        return ordersRepository.save(orders);//if no error then save order to db and return orders

    }
    public Orders getOrder(int orderId) throws RazorpayException {
        Optional<Orders> orders = ordersRepository.findById(orderId);
        if (orders.isPresent()) {
           Orders receivedOrder= orders.get();
           return receivedOrder;
        }else
        {
            throw new RazorpayException("Order not found");
        }
    }

}
