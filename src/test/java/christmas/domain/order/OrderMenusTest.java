package christmas.domain.order;

import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.message.NoticeMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

class OrderMenusTest {

    @DisplayName("21개 이상 주문시 예외 발생")
    @Test
    void validateMaxCount() {
        // given
        HashMap<String, Integer> orderMenuNameAndCount = new HashMap<>();
        orderMenuNameAndCount.put("타파스", 20);
        orderMenuNameAndCount.put("초코케이크", 1);

        // then
        assertThatThrownBy(() -> new OrderMenus(orderMenuNameAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NoticeMessage.IMPOSSIBLE_ORDER_BY_MAX_ORDER_COUNT.getNoticeMessage());
    }

    @DisplayName("음료만 주문시 예외 발생")
    @Test
    void validateOnlyDrink() {
        // given
        HashMap<String, Integer> orderMenuNameAndCount = new HashMap<>();
        orderMenuNameAndCount.put("제로콜라", 1);

        // then
        assertThatThrownBy(() -> new OrderMenus(orderMenuNameAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NoticeMessage.IMPOSSIBLE_ORDER_BY_ONLY_DRINK.getNoticeMessage());
    }

    @DisplayName("총주문 금액 계산")
    @Test
    void calculateTotalOrderPrice() {
        // given
        HashMap<String, Integer> orderMenuNameAndCount = new HashMap<>();
        orderMenuNameAndCount.put("타파스", 10);
        orderMenuNameAndCount.put("초코케이크", 1);

        // then
        assertThat(new OrderMenus(orderMenuNameAndCount).calculateTotalOrderPrice())
                .isEqualTo(Menu.TAPAS.getPrice() * 10 + Menu.CHOCO_CAKE.getPrice());
    }

    @DisplayName("특정 카테고리가 있는지 검사")
    @CsvSource(value = {"APPETIZER, false", "MAIN, true", "DRINK, false", "DESSERT, true"})
    @ParameterizedTest
    void checkCategory(Category category, boolean result) {
        // given
        HashMap<String, Integer> orderMenuNameAndCount = new HashMap<>();
        orderMenuNameAndCount.put("티본스테이크", 1);
        orderMenuNameAndCount.put("초코케이크", 1);

        // then
        assertThat(new OrderMenus(orderMenuNameAndCount).checkCategory(category))
                        .isEqualTo(result);
    }

    @DisplayName("평일 할인 이벤트 혜택 금액 계산")
    @Test
    void getWeekdayEventBenefitAmount() {
        // given
        HashMap<String, Integer> orderMenuNameAndCount = new HashMap<>();
        orderMenuNameAndCount.put("초코케이크", 2);
        orderMenuNameAndCount.put("티본스테이크", 3);
        OrderMenus orderMenus = new OrderMenus(orderMenuNameAndCount);

        // then
        assertThat(orderMenus.getWeekdayEventBenefitAmount())
                .isEqualTo(2 * 2023);
    }

    @DisplayName("주말 할인 이벤트 혜택 금액 계산")
    @Test
    void getWeekendEventBenefitAmount() {
        // given
        HashMap<String, Integer> orderMenuNameAndCount = new HashMap<>();
        orderMenuNameAndCount.put("초코케이크", 2);
        orderMenuNameAndCount.put("티본스테이크", 3);
        OrderMenus orderMenus = new OrderMenus(orderMenuNameAndCount);

        // then
        assertThat(orderMenus.getWeekendEventBenefitAmount())
                .isEqualTo(3 * 2023);
    }
}
