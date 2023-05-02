package pizzashop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    static Payment part;



    @BeforeEach
    public void beforeEach() {
        part = new Payment(1, PaymentType.CASH, 0);
    }

    @Test
    public void getAmount() {
        assertEquals(0, part.getAmount());
    }

    @Test
    public void setAmount() {
        part.setAmount(2);
        assertEquals(2, part.getAmount());
    }
}
