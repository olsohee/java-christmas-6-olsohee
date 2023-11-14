package christmas.domain.order;

import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.domain.order.OrderMenu;
import christmas.message.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenuTest {

    @DisplayName("주문 메뉴 생성 성공")
    @Test
    void createOrderMenuSuccess() {
        Assertions.assertThat(new OrderMenu("타파스", 1)).isNotNull();
    }

    @DisplayName("주문 메뉴 생성 실패")
    @ValueSource(ints = {-1, 0})
    @ParameterizedTest
    void createOrderMenuFail(int quantity) {
        Assertions.assertThatThrownBy(() -> new OrderMenu("타파스", quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER_INPUT.getErrorMessage());
    }

    @DisplayName("주문 가격 계산")
    @CsvSource(value = {"타파스, 1", "티본스테이크, 2", "초코케이크, 3"})
    @ParameterizedTest
    void calculateOrderPrice(String menuName, int quantity) {
        Assertions.assertThat(new OrderMenu(menuName, quantity).calculateOrderPrice())
                .isEqualTo(Menu.from(menuName).getPrice() * quantity);
    }

    @DisplayName("음료 메뉴인지 판단")
    @CsvSource(value = {"제로콜라, true", "티본스테이크, false"})
    @ParameterizedTest
    void isDrinkMenu(String menuName, boolean result) {
        Assertions.assertThat(new OrderMenu(menuName, 1).checkCategory(Category.DRINK))
                .isEqualTo(result);
    }

    @DisplayName("디저트 메뉴인지 판단")
    @CsvSource(value = {"초코케이크, true", "티본스테이크, false"})
    @ParameterizedTest
    void isDessertMenu(String menuName, boolean result) {
        Assertions.assertThat(new OrderMenu(menuName, 1).checkCategory(Category.DESERT))
                .isEqualTo(result);
    }

    @DisplayName("메인 메뉴인지 판단")
    @CsvSource(value = {"티본스테이크, true", "아이스크림, false"})
    @ParameterizedTest
    void isMainMenu(String menuName, boolean result) {
        Assertions.assertThat(new OrderMenu(menuName, 1).checkCategory(Category.MAIN))
                .isEqualTo(result);
    }
}
