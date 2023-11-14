package christmas.domain.order;

import christmas.message.NoticeMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

class OrderMenusTest {

    @DisplayName("주문 메뉴 리스트 생성 성공")
    @Test
    void createOrderMenus() {
        // given
        HashMap<String, Integer> orderMenuNameAndCount = new HashMap<>();
        orderMenuNameAndCount.put("타파스", 1);
        orderMenuNameAndCount.put("티본스테이크", 1);
        orderMenuNameAndCount.put("초코케이크", 2);
        orderMenuNameAndCount.put("제로콜라", 3);

        // then
        assertThat(orderMenuNameAndCount).isNotNull();
    }

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
        orderMenuNameAndCount.put("레드와인", 1);

        // then
        assertThatThrownBy(() -> new OrderMenus(orderMenuNameAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NoticeMessage.IMPOSSIBLE_ORDER_BY_ONLY_DRINK.getNoticeMessage());
    }

    @DisplayName("평일 할인 이벤트 혜택 금액 계산")
    @Test
    void getWeekdayEventBenefitAmount() {
        // given
        HashMap<String, Integer> orderMenuNameAndCount = new HashMap<>();
        orderMenuNameAndCount.put("초코케이크", 2);
        orderMenuNameAndCount.put("레드와인", 1);

        // when
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
        orderMenuNameAndCount.put("티본스테이크", 2);
        orderMenuNameAndCount.put("레드와인", 1);

        // when
        OrderMenus orderMenus = new OrderMenus(orderMenuNameAndCount);

        // then
        assertThat(orderMenus.getWeekendEventBenefitAmount())
                .isEqualTo(2 * 2023);
    }
}
