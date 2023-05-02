package pizzashop.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegrareR {

    @Test
    public void testAll()
    {
        testGetTotalAmount();
        testGetPayment();

    }
    @Test
    public void testGetTotalAmount() {

        PaymentRepository paymentRepo = new PaymentRepository();

        Payment payment = mock(Payment.class);
        Mockito.when(payment.getAmount()).thenReturn(800.0);
        Mockito.when(payment.getType()).thenReturn(PaymentType.CASH);
        Mockito.when(payment.getTableNumber()).thenReturn(1);


        PizzaService pizzaService = new PizzaService(null, paymentRepo);
        paymentRepo.add(payment);

        double result = pizzaService.getTotalAmount(PaymentType.CASH);

        assertEquals(800, result, 0.001);
    }
    @Test
    public void testGetPayment() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.CASH, 800.0));
        payments.add(new Payment(2, PaymentType.CASH, 200.0));
        PaymentRepository paymentRepo = new PaymentRepository();

        Payment payment1 = mock(Payment.class);
        Mockito.when(payment1.getAmount()).thenReturn(800.0);
        Mockito.when(payment1.getType()).thenReturn(PaymentType.CASH);
        Mockito.when(payment1.getTableNumber()).thenReturn(1);

        Payment payment2 = mock(Payment.class);
        Mockito.when(payment2.getAmount()).thenReturn(200.0);
        Mockito.when(payment2.getType()).thenReturn(PaymentType.CASH);
        Mockito.when(payment2.getTableNumber()).thenReturn(2);

        PizzaService pizzaService = new PizzaService(null, paymentRepo);
        paymentRepo.add(payment1);
        paymentRepo.add(payment2);

        List<Payment> result = pizzaService.getPayments();

        assertEquals(payments.get(0).getAmount(),result.get(0).getAmount() );
        assertEquals(payments.get(1).getTableNumber(),result.get(1).getTableNumber() );

    }
}