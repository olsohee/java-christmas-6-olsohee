package christmas.domain;

import christmas.message.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @DisplayName("날짜 생성")
    @Test
    void createDate() {
        // given
        Date date = new Date(1);

        // then
        Assertions.assertThat(date).isNotNull();
    }

    @DisplayName("1 ~ 31 이외의 숫자이면 날짜 생성 실패")
    @Test
    void createDateFail() {
        Assertions.assertThatThrownBy(() -> new Date(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE_INPUT.getErrorMessage());
    }
}
