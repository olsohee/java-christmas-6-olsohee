package christmas.dto;

import christmas.domain.Benefit;
import christmas.domain.Order;

public class PaymentDto {

    private final int paymentAmount;

    public PaymentDto(Order order, Benefit benefit) {
        paymentAmount = order.getTotalOrderPrice() - benefit.getTotalDiscountAmount();
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }
}
