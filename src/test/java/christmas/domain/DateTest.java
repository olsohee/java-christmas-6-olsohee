package christmas.domain;

import christmas.domain.order.Date;
import christmas.message.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.IntStream;

class DateTest {

    @DisplayName("1 ~ 31 사이의 숫자이면 날짜 생성 성공")
    @ValueSource(ints = {1, 20, 31})
    @ParameterizedTest
    void createDateSuccess(int date) {
        Assertions.assertThat(new Date(date)).isNotNull();
    }

    @DisplayName("1 ~ 31 이외의 숫자이면 날짜 생성 실패")
    @ValueSource(ints = {-1, 0, 32})
    @ParameterizedTest
    void createDateFail(int date) {
        Assertions.assertThatThrownBy(() -> new Date(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE_INPUT.getErrorMessage());
    }

    @DisplayName("크리스마스 디데이 이벤트 적용 유무 판단")
    @CsvSource(value = {"1, true", "25, true", "26, false", "31, false"})
    @ParameterizedTest
    void isApplicableChristmasEvent(int date, boolean result) {
        Assertions.assertThat(new Date(date).isApplicableChristmasEvent()).isEqualTo(result);
    }

    @DisplayName("평일 할인 이벤트 적용 유무 판단")
    @CsvSource(value = {"1, false", "2, false", "3, true", "4, true", "5, true", "6, true", "7, true"})
    @ParameterizedTest
    void isApplicableWeekdayEvent(int date, boolean result) {
        Assertions.assertThat(new Date(date).isApplicableWeekdayEvent()).isEqualTo(result);
    }

    @DisplayName("주말 할인 이벤트 적용 유무 판단")
    @CsvSource(value = {"1, true", "2, true", "3, false", "4, false", "5, false", "6, false", "7, false"})
    @ParameterizedTest
    void isApplicableWeekendEvent(int date, boolean result) {
        Assertions.assertThat(new Date(date).isApplicableWeekendEvent()).isEqualTo(result);
    }

    @DisplayName("특별 할인 이벤트 적용 유무 판단")
    @CsvSource(value = {"3, true", "10, true", "17, true", "24, true", "25, true", "31, true"})
    @ParameterizedTest
    void isApplicableSpecialEvent(int date, boolean result) {
        Assertions.assertThat(new Date(date).isApplicableSpecialEvent()).isEqualTo(result);
    }

    @DisplayName("크리스마스 디데이 이벤트 혜택 금액 계산")
    @Test
    void getChristmasEventBenefitAmount() {
        List<Integer> dates = IntStream.range(1, 26).boxed().toList();
        Assertions.assertThat(dates.stream()
                .allMatch(date -> new Date(date).getChristmasEventBenefitAmount() == (1000 + (date - 1) * 100)))
                .isTrue();
    }
}
