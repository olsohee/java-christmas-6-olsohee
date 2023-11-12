package christmas.view;

import christmas.dto.BadgeDto;
import christmas.dto.DateDto;
import christmas.dto.PaymentDto;
import christmas.dto.benefitDto.BenefitsDto;
import christmas.dto.orderMenuDto.OrderDto;

public interface OutputView {

    void printOutputStartMessage(DateDto dateDto);

    void printOrder(OrderDto orderDto);

    void printBenefits(BenefitsDto benefitsDto);

    void printPayment(PaymentDto paymentDto);

    void printBadge(BadgeDto badgeDto);

    default void printMessage(String message) {
        System.out.println(message);
    }

    default void printNewLine() {
        System.out.println();
    }
}