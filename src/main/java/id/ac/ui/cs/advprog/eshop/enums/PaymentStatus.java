package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PaymentStatus {
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED"),
    PENDING("PENDING");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        return Arrays.stream(PaymentStatus.values())
                .anyMatch(paymentStatus -> paymentStatus.getValue().equals(param));
    }
}