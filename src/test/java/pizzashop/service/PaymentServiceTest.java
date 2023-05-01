package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Test
    public void testGetTotalAmount() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.CASH, 10.0));
        payments.add(new Payment(2, PaymentType.CASH, 15.0));
        payments.add(new Payment(3, PaymentType.CASH, 7.5));

        PaymentRepository paymentRepo = Mockito.mock(PaymentRepository.class);
        when(paymentRepo.getAll()).thenReturn(payments);

        PizzaService pizzaService = new PizzaService(null, paymentRepo);

        double result = pizzaService.getTotalAmount(PaymentType.CASH);

        assertEquals(32.5, result, 0.001);
    }
    @Test
    public void getPayment() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.CASH, 10.0));
        payments.add(new Payment(2, PaymentType.CASH, 15.0));
        payments.add(new Payment(3, PaymentType.CASH, 7.5));
        Payment part1 = new Payment(1, PaymentType.CASH,20);
        PaymentRepository paymentRepo = Mockito.mock(PaymentRepository.class);
        when(paymentRepo.getAll()).thenReturn(payments);

        PizzaService pizzaService = new PizzaService(null, paymentRepo);
        List<Payment> result = pizzaService.getPayments();

        assertEquals(payments,result );
    }
}
