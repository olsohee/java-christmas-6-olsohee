//package christmas.domain;
//
//import christmas.domain.menu.Menu;
//import christmas.domain.order.Order;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//class OrderTest {
//
//    @DisplayName("주문 생성")
//    @Test
//    void createOrder() {
//        // given
//        Map<String, Integer> orderMenuAndCount = new HashMap<>();
//        orderMenuAndCount.put("타파스", 1);
//        orderMenuAndCount.put("초코케이크", 2);
//
//        // when
//        Order order = new Order(orderMenuAndCount);
//
//        // then
//        Assertions.assertThat(order).isNotNull();
//    }
//
//    @DisplayName("총주문 금액 계산 기능")
//    @Test
//    void calculateTotalOrderPrice() {
//        // given
//        Map<String, Integer> orderMenuAndCount = new HashMap<>();
//        orderMenuAndCount.put("타파스", 1);
//        orderMenuAndCount.put("초코케이크", 2);
//
//        // when
//        Order order = new Order(orderMenuAndCount);
//
//        // then
//        Assertions.assertThat(order.getTotalOrderPrice()).isEqualTo(
//                Menu.TAPAS.getPrice() + Menu.CHOCO_CAKE.getPrice() * 2);
//    }
//}
