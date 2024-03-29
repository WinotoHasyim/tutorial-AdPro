package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;

    List<Payment> payments;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Payment payment1 = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);

        Payment payment2 = new Payment("a2c62328-4a37-4664-83c7-f32db8620155", PaymentMethod.CASH_ON_DELIVERY.getValue(), this.paymentData);

        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);

        Payment newPayment = new Payment(payment.getId(), PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> findResult = paymentRepository.findAll();
        assertEquals(payments.size(), findResult.size());
        for (int i = 0; i < payments.size(); i++) {
            Payment originalPayment = payments.get(i);
            Payment retrievedPayment = findResult.get(i);
        
            assertEquals(originalPayment.getId(), retrievedPayment.getId());
            assertEquals(originalPayment.getMethod(), retrievedPayment.getMethod());
            assertEquals(originalPayment.getStatus(), retrievedPayment.getStatus());
            assertSame(originalPayment.getPaymentData(), retrievedPayment.getPaymentData());
        }
    }

    @Test
    void testFindAllEmpty() {
        paymentRepository = new PaymentRepository();
        List<Payment> findResult = paymentRepository.findAll();
        assertTrue(findResult.isEmpty());
    }
}