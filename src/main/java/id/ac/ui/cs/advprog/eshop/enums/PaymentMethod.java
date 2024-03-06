package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PaymentMethod {
    VOUCHER_CODE("VOUCHER_CODE"),
    CASH_ON_DELIVERY("CASH_ON_DELIVERY");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        return Arrays.stream(PaymentMethod.values())
                .anyMatch(paymentMethod -> paymentMethod.getValue().equals(param));
    }
}