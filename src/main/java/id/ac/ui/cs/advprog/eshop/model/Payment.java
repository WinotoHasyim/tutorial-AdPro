package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    Map<String, String> paymentData;
    String method;
    @Setter
    String status;

    public Payment(String id, Map<String, String> paymentData, String method) {
    }

    public Payment(String id, Map<String, String> paymentData, String method, String status) {
    }
}