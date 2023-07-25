package com.example.carental.paypalconfig;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayPalClient {

    private PayPalHttpClient client;

    public PayPalClient(@Value("${paypal.client-id}") String clientId,
                        @Value("${paypal.client-secret}") String clientSecret,
                        @Value("${paypal.mode}") String mode) {
        PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, clientSecret);
        this.client = new PayPalHttpClient(environment);
    }

    public Order createOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(
                new AmountWithBreakdown().currencyCode("USD").value("100.00"));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);

        OrdersCreateRequest request = new OrdersCreateRequest();
        request.requestBody(orderRequest);

        try {
            // Call PayPal to set up a transaction
            return client.execute(request).result();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Order captureOrder(String orderId) {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        try {
            // Call PayPal to capture an order
            return client.execute(request).result();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

