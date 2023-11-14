package christmas.domain.menu;

import christmas.message.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("메뉴 이름으로 메뉴 찾기 성공")
    @Test
    void fromSuccess() {
        Assertions.assertThat(Menu.from("타파스")).isEqualTo(Menu.TAPAS);
    }

    @DisplayName("메뉴 이름으로 메뉴 찾기 실패")
    @Test
    void fromFail() {
        Assertions.assertThatThrownBy(() -> Menu.from("타파스스"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER_INPUT.getErrorMessage());
    }
}
