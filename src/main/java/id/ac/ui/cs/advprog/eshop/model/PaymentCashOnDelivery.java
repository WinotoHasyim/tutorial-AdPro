package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class PaymentCashOnDelivery extends Payment {
    private static final String ADDRESS_KEY = "address";
    private static final String DELIVERY_FEE_KEY = "deliveryFee";

    public PaymentCashOnDelivery(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty() || isNullOrEmpty(paymentData.get(ADDRESS_KEY)) || isNullOrEmpty(paymentData.get(DELIVERY_FEE_KEY))) {
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            this.paymentData = paymentData;
            this.status = PaymentStatus.SUCCESS.getValue();
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}