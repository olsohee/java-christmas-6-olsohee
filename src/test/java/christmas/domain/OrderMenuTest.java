package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
