package christmas.domain;

import christmas.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {

    @DisplayName("주문 메뉴 생성")
    @Test
    void createOrderMenu() {
        // given
        OrderMenu orderMenu = new OrderMenu("타파스", 1);

        // then
        Assertions.assertThat(orderMenu).isNotNull();
    }

    @DisplayName("주문 메뉴 실패")
    @Test
    void createOrderMenuFail() {
        Assertions.assertThatThrownBy(() -> new OrderMenu("타파스스", 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getErrorMessage());
    }
}
