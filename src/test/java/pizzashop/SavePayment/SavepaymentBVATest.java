package pizzashop.SavePayment;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SavepaymentBVATest {

    PaymentRepository paymentRepository;
    String FILE_PATH="data/payments.txt";
    private BufferedReader reader;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        paymentRepository=new PaymentRepository();
        reader = new BufferedReader(new FileReader(FILE_PATH));
    }

    @AfterEach
    void tearDown() throws IOException {
        PrintWriter writer = new PrintWriter(FILE_PATH);
        writer.print("");
        writer.close();
        reader.close();
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 8 })
    @Tag("valid")
    void testValid(Integer tableNumber) throws IOException {
        //Arrange

        PaymentType type = PaymentType.CASH;
        double amount = 123.0;
        Payment payment=new Payment(tableNumber,type,amount);
        //Act
        paymentRepository.add(payment);

        //Assert
        String line;
        boolean found = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains(tableNumber + "," + type + "," + amount)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
    @ParameterizedTest
    @ValueSource(ints = {8 })
    @Tag("valid")
    void testValid2(Integer tableNumber) throws IOException {
        //Arrange

        PaymentType type = PaymentType.CASH;
        double amount = 1;
        Payment payment=new Payment(tableNumber,type,amount);
        //Act
        paymentRepository.add(payment);

        //Assert
        String line;
        boolean found = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains(tableNumber + "," + type + "," + amount)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("InvalidTest")
    @Tag("invalid")
    void testInvalid() {
        int tableNumber = 0;
        PaymentType type = PaymentType.CASH;
        double amount = 50.0;
        Payment payment = new Payment(tableNumber, type, amount);
        assertThrows(IllegalArgumentException.class, () -> paymentRepository.add(payment));
    }

    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("InvalidTest")
    @Timeout(2)
    @Tag("invalid")
    void testInvalid2() {
        int tableNumber = 7;
        PaymentType type = PaymentType.CARD;
        double amount = 0.99;
        Payment payment = new Payment(tableNumber, type, amount);
        assertThrows(IllegalArgumentException.class, () -> paymentRepository.add(payment));
    }

}