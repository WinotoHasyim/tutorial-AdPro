package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.paymentData = paymentData;
        this.method = method;
        this.status = "PENDING";
    }

    public Payment(String id, String method, Map<String, String> paymentData, String status) {
        this(id, method, paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (status.equals("PENDING") || status.equals("SUCCESS") || status.equals("REJECTED")){
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }
    }
}