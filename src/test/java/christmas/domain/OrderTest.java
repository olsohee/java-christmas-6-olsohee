package christmas.domain;

import christmas.domain.menu.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class OrderTest {

    @DisplayName("주문 생성")
    @Test
    void createOrder() {
        // given
        OrderMenu tapas = new OrderMenu("타파스", 1);
        OrderMenu chocoCake = new OrderMenu("초코케이크", 2);
        List<OrderMenu> orderMenus = List.of(tapas, chocoCake);

        // when
        Order order = new Order(orderMenus);

        // then
        Assertions.assertThat(order).isNotNull();
    }

    @DisplayName("총주문 금액 계산 기능")
    @Test
    void calculateTotalOrderPrice() {
        // given
        OrderMenu tapas = new OrderMenu("타파스", 1);
        OrderMenu chocoCake = new OrderMenu("초코케이크", 2);
        List<OrderMenu> orderMenus = List.of(tapas, chocoCake);

        // when
        Order order = new Order(orderMenus);

        // then
        Assertions.assertThat(order.getTotalOrderPrice()).isEqualTo(
                Menu.TAPAS.getPrice() + Menu.CHOCO_CAKE.getPrice() * 2);
    }
}
