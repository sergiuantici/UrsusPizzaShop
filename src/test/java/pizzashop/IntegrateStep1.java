package pizzashop;

import org.junit.jupiter.api.Test;
import pizzashop.model.PaymentTest;
import pizzashop.repository.PaymentRepository;
import pizzashop.repository.PaymentRepositoryTest;
import pizzashop.service.PaymentServiceTest;


public class IntegrateStep1 {
    @Test
    void allTestsMock()
    {
        PaymentTest paymentTest=new PaymentTest();
        paymentTest.beforeEach();
        paymentTest.getAmount();
        paymentTest.setAmount();
        //2
        PaymentRepositoryTest paymentRepositoryTest=new PaymentRepositoryTest();
        PaymentRepositoryTest.setUp();
        paymentRepositoryTest.testAdd();
        paymentRepositoryTest.testGetAll();
        //3
        PaymentServiceTest paymentServiceTest=new PaymentServiceTest();
        paymentServiceTest.testGetPayment();
        paymentServiceTest.testGetTotalAmount();

    }
}
