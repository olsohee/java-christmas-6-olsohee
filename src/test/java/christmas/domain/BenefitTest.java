package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BenefitTest {

    @DisplayName("총 할인 금액 계산")
    @Test
    void getTotalDiscountAmount() {
        // given
        Order order = new Order(
                List.of(new OrderMenu("타파스", 1),
                        new OrderMenu("티본스테이크", 1),
                        new OrderMenu("해산물파스타", 2),
                        new OrderMenu("초코케이크", 1))
        );

        Date date = new Date(3);

        // when
        Benefit benefit = new Benefit(order, date);
        int totalDiscountAmount = benefit.getTotalDiscountAmount();

        // then
        Assertions.assertThat(totalDiscountAmount).isEqualTo(4223);
    }
}
