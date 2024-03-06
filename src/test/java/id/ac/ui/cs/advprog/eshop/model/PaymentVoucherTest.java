package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentVoucherTest {
    private static final String TEST_UUID = "13652556-012a-4c07-b546-54eb1396d79b";
    private static final String VOUCHER_CODE_KEY = "voucherCode";

    private Map<String, String> paymentData;
    private PaymentVoucher payment;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        this.payment = new PaymentVoucher(TEST_UUID,
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
    }

    @Test
    void testSetPaymentDataWithEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(this.paymentData));
    }

    @Test
    void testSetPaymentDataWithValidVoucherCode() {
        this.paymentData.put(VOUCHER_CODE_KEY, "ESHOP1234ABC5678");
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithLessThanSixteenCharacters() {
        this.paymentData.put(VOUCHER_CODE_KEY, "ESHOP1234ABC567");
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithoutEshop() {
        this.paymentData.put(VOUCHER_CODE_KEY, "1234ABC5678");
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentDataWithInvalidVoucherCodeWithLessThanEightNumericalCharacters() {
        this.paymentData.put(VOUCHER_CODE_KEY, "ESHOPABCDEFGH");
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testConstructor() {
        PaymentVoucher payment = new PaymentVoucher(TEST_UUID,
                PaymentMethod.VOUCHER_CODE.getValue(), this.paymentData);
        assertEquals(TEST_UUID, payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
    }
}