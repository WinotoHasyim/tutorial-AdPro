package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentCashOnDeliveryTest {
    private static final String TEST_UUID = "13652556-012a-4c07-b546-54eb1396d79b";
    private static final String ADDRESS_KEY = "address";
    private static final String DELIVERY_FEE_KEY = "deliveryFee";

    private Map<String, String> paymentData;
    private PaymentCashOnDelivery payment;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        this.payment = new PaymentCashOnDelivery(TEST_UUID,
                PaymentMethod.CASH_ON_DELIVERY.getValue(), this.paymentData);
    }

    @Test
    void testSetPaymentData() {
        this.paymentData.put(ADDRESS_KEY, "Jalan Pondok Sofura");
        this.paymentData.put(DELIVERY_FEE_KEY, "25000");
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithEmptyAddress() {
        this.paymentData.put(ADDRESS_KEY, "");
        this.paymentData.put(DELIVERY_FEE_KEY, "25000");
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithEmptyDeliveryFee() {
        this.paymentData.put(ADDRESS_KEY, "Jalan Pondok Sofura");
        this.paymentData.put(DELIVERY_FEE_KEY, "");
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithEmptyPaymentData() {
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}