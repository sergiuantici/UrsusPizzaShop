package pizzashop.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class IntegrareE {

    @Test
    public void testAll()
    {
        testGetTotalAmount();
        testGetPayment();

    }

    @Test
    public void testGetTotalAmount() {

        PaymentRepository paymentRepo = new PaymentRepository();

        PizzaService pizzaService = new PizzaService(null, paymentRepo);
        pizzaService.addPayment(1, PaymentType.CASH, 10.0);
        pizzaService.addPayment(2, PaymentType.CASH, 15.0);
        pizzaService.addPayment(3, PaymentType.CASH, 7.5);


        double result = pizzaService.getTotalAmount(PaymentType.CASH);

        assertEquals(32.5, result, 0.001);
    }
    @Test
    public void testGetPayment() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.CASH, 10.0));
        payments.add(new Payment(2, PaymentType.CASH, 15.0));
        payments.add(new Payment(3, PaymentType.CASH, 7.5));
        PaymentRepository paymentRepo = new PaymentRepository();

        PizzaService pizzaService = new PizzaService(null, paymentRepo);
        pizzaService.addPayment(1, PaymentType.CASH, 10.0);
        pizzaService.addPayment(2, PaymentType.CASH, 15.0);
        pizzaService.addPayment(3, PaymentType.CASH, 7.5);
        List<Payment> result = pizzaService.getPayments();

        assertEquals(payments.get(0).getAmount(),result.get(0).getAmount() );
        assertEquals(payments.get(1).getTableNumber(),result.get(1).getTableNumber() );
        assertEquals(payments.get(2).getType(),result.get(2).getType() );

    }
}
