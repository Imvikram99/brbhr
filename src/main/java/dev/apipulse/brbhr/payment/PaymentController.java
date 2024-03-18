package dev.apipulse.brbhr.payment;


import com.cashfree.ApiException;
import com.cashfree.ApiResponse;
import com.cashfree.Cashfree;
import com.cashfree.model.CreateOrderRequest;
import com.cashfree.model.CustomerDetails;
import com.cashfree.model.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired private Cashfree cashfree;
    private String xApiVersion = "2023-08-01"; // Adjust the API version as per Cashfree documentation

    @PostMapping("/create-order")
    public String createOrder(@RequestBody OrderDetails orderDetails) {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerId(orderDetails.getCustomerId());
        customerDetails.setCustomerPhone(orderDetails.getCustomerPhone());

        CreateOrderRequest request = new CreateOrderRequest();
        request.setOrderAmount(orderDetails.getOrderAmount());
        request.setOrderCurrency(orderDetails.getOrderCurrency());
        request.setCustomerDetails(customerDetails);

        try {
            ApiResponse<OrderEntity> response = cashfree.PGCreateOrder(xApiVersion, request, null, null, null);
            return response.getData().getOrderId();
        } catch (ApiException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create order: " + e.getMessage());
        }
    }

    @GetMapping("/{orderId}")
    public OrderDetailsResponse fetchOrder(@PathVariable String orderId) {
        try {
            ApiResponse<OrderEntity> responseFetchOrder = cashfree.PGFetchOrder(xApiVersion, orderId, null, null, null);
            OrderDetailsResponse orderDetailsResponse= new OrderDetailsResponse();
            orderDetailsResponse.setOrderStatus(responseFetchOrder.getData().getOrderStatus());
            orderDetailsResponse.setOrderId(responseFetchOrder.getData().getOrderId());
            orderDetailsResponse.setOrderCurrency(responseFetchOrder.getData().getOrderCurrency());
            orderDetailsResponse.setOrderAmount(responseFetchOrder.getData().getOrderAmount());
            return orderDetailsResponse;
        } catch (ApiException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch order: " + e.getMessage());
        }
    }
}
