package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();

        Payment payment1 = new Product();
        payment1.setPaymentId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        payment1.setPaymentMethod("VOUCHER_CODE");
        payment1.setPaymentStatus("PENDING");

        Product payment2 = new Product();
        payment2.setPaymentId("a2c62328-4a37-4664-83c7-f32db8620155");
        payment2.setPaymentMethod("CASH_ON_DELIVERY");
        payment2.setPaymentStatus("PENDING");

        this.paymentData.put(payment1);
        this.paymentData.add(payment2);
    }

    void testCreatePaymentVoucherCodeMethod() {
        Payment payment = new Payment("13652556-012a-4c07-b546-bfdt1396d79b", "VOUCHER_CODE", this.paymentData);
        assertEquals("13652556-012a-4c07-b546-bfdt1396d79b", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentCashOnDeliveryMethod() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", this.paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData, "REJECTED");
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData, "SUCCESS");
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData, "INVALID");
        });
    }

    @Test
    void testSetPaymentDataEmpty() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData);
        this.paymentData.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setPaymentData(this.paymentData);
        });
    }

    @Test
    void testSetPaymentDataSuccess() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData);
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment.setPaymentData(this.paymentData);
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test testSetPaymentInvalidStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }
    
}