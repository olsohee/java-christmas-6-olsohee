package christmas.domain.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TotalOrderPriceTest {

    @DisplayName("주문 가격 생성")
    @Test
    void createTotalOrderPrice() {
        Assertions.assertThat(new TotalOrderPrice(120000)).isNotNull();
    }

    @DisplayName("이벤트 적용 유무 판단")
    @CsvSource(value = {"9000, false", "10000, true", "11000, true"})
    @ParameterizedTest
    void isApplicableEvent(int totalOrderPrice, boolean result) {
        Assertions.assertThat(new TotalOrderPrice(totalOrderPrice).isApplicableEvent())
                .isEqualTo(result);
    }

    @DisplayName("증정 이벤트 적용 유무 판단")
    @CsvSource(value = {"110000, false", "120000, true", "121000, true"})
    @ParameterizedTest
    void isApplicableGiftEvent(int totalOrderPrice, boolean result) {
        Assertions.assertThat(new TotalOrderPrice(totalOrderPrice).isApplicableGiftEvent())
                .isEqualTo(result);
    }
}
