package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class PaymentVoucher extends Payment {
    final String VOUCHER_CODE_KEY = "voucherCode";

    public PaymentVoucher(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    public PaymentVoucher(String id, String method, Map<String, String> paymentData, String status) {
        super(id, method, paymentData, status);
    }
    
    @Override
    public void setPaymentData(Map<String, String> paymentData) {
    
        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
            this.status = isValidVoucherCode(paymentData.get(VOUCHER_CODE_KEY))
                    ? PaymentStatus.SUCCESS.getValue()
                    : PaymentStatus.REJECTED.getValue();
        }
    }

    private boolean isValidVoucherCode(String voucherCode) {
        int numericalChar = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                numericalChar++;
            }
        }
        return voucherCode.length() == 16
                && voucherCode.startsWith("ESHOP")
                && numericalChar == 8;
    }
}