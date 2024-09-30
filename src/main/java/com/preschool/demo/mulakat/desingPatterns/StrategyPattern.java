package com.preschool.demo.mulakat.desingPatterns;
// Strateji arayüzü
interface PaymentStrategy {
    void pay(double amount);
}

// Strateji sınıfları
class CreditCardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public CreditCardPaymentStrategy(String cardNumber, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " TL tutarında kredi kartı ile ödeme yapıldı.");
    }
}

class PayPalPaymentStrategy implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPaymentStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " TL tutarında PayPal ile ödeme yapıldı.");
    }
}

// Kullanım
public class StrategyPattern {
    public static void main(String[] args) {
        PaymentStrategy strategy = new CreditCardPaymentStrategy("1234-5678-9101-1121", "12/25", "123");
        strategy.pay(100.0);

        strategy = new PayPalPaymentStrategy("example@example.com", "password");
        strategy.pay(50.0);
    }
}
