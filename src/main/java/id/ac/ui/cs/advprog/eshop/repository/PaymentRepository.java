package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        ListIterator<Payment> iterator = paymentData.listIterator();
        while (iterator.hasNext()) {
            Payment savedPayment = iterator.next();
            if (savedPayment.getId().equals(payment.getId())) {
                iterator.set(payment);
                return payment;
            }
        }

        paymentData.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        return paymentData.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Payment> findAll() {
        return paymentData;
    }
}