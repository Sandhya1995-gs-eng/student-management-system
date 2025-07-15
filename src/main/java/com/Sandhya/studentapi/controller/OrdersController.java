package com.Sandhya.studentapi.controller;

import com.Sandhya.studentapi.dto.CourseOrderRequestDTO;
import com.Sandhya.studentapi.entity.Orders;
import com.Sandhya.studentapi.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class OrdersController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private  ObjectMapper objectMapper;

    @PostMapping("/orders")
    public ResponseEntity<String> initiatePayment(@RequestBody CourseOrderRequestDTO courseOrderRequestDTO) {
        try{
        Orders order=paymentService.createOrder(courseOrderRequestDTO);
            ObjectMapper mapper = new ObjectMapper();
            String createdOrder=mapper.writeValueAsString(order);
            return ResponseEntity.status(HttpStatus.OK).body(createdOrder);
        }catch (RazorpayException e){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Payment gateway error: " + e.getMessage());
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing response JSON");
        }
    }
@GetMapping("orders/{orderId}")
    public String getOrderDetails(@PathVariable int  orderId) throws RazorpayException {
        try {
            Orders order = paymentService.getOrder(orderId);
            ObjectMapper mapper = new ObjectMapper();
            String orderDetails = mapper.writeValueAsString(order);
            return orderDetails;
        }catch(RazorpayException e){
            throw new RazorpayException(e.getMessage());
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
}


}
