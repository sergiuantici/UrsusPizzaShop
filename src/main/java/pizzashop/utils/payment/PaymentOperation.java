package pizzashop.utils.payment;

public interface PaymentOperation {
     void cardPayment();
     void cashPayment();
     void cancelPayment();
}
