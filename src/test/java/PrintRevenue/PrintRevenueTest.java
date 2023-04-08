package PrintRevenue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrintRevenueTest {

    PizzaService pizzaService;
    PaymentRepository paymentRepository;
    String FILE_PATH = "data/payments.txt";
    private BufferedReader reader;

    @BeforeEach
    void setUp() throws IOException {
        new FileOutputStream(FILE_PATH).close(); // delete file content
        reader = new BufferedReader(new FileReader(FILE_PATH));
        paymentRepository = new PaymentRepository();
        pizzaService = new PizzaService(null, paymentRepository);
    }

    @Test
    @Tag("P03")
    void testValid() throws IOException {
        //Arrange
        int tableNumber = 7;
        PaymentType type = PaymentType.CASH;
        double amount = 187.0;
        Payment payment=new Payment(tableNumber,type,amount);
        paymentRepository.add(payment);

        //Act
        double howMuch = pizzaService.getTotalAmount(PaymentType.CARD);

        //Assert
        double howMuchSaved = 0.0f;
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(PaymentType.CARD.toString())) {
                howMuchSaved += Double.parseDouble(line.split(",")[2]);
            }
        }
        assert(Math.abs(howMuchSaved - howMuch) < 0.001);
    }

    @Test
    @Tag("P01")
    void testInvalid() throws IOException {
        //Arrange
        int tableNumber = 8;
        PaymentType type = PaymentType.CARD;

        //Act
        double howMuch = pizzaService.getTotalAmount(PaymentType.CARD);

        //Assert
        double howMuchSaved = 0.0f;
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(tableNumber + "," + type)) {
                howMuchSaved += Double.parseDouble(line.split(",")[2]);
            }
        }
        assertEquals(howMuchSaved, howMuch);
    }

    @Test
    @Tag("P02")
    void testInvalid2() throws IOException {
        //Arrange
        int tableNumber = 8;
        PaymentType type = PaymentType.CARD;
        double amount = 187.0;
        Payment payment=new Payment(tableNumber,type,amount);
        paymentRepository.add(payment);

        //Act
        double howMuch = pizzaService.getTotalAmount(PaymentType.CARD);

        //Assert
        double howMuchSaved = 0.0f;
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(type.toString())) {
                howMuchSaved += Double.parseDouble(line.split(",")[2]);
            }
        }
        assert(Math.abs(howMuchSaved - howMuch) < 0.001);
    }

}


