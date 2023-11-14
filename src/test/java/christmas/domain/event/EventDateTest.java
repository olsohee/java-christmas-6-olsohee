package christmas.domain.event;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EventDateTest {

    @DisplayName("특정 날짜에 해당 이벤트가 적용되는지 확인")
    @MethodSource("provideEventDate")
    @ParameterizedTest
    void isApplicableDate(EventDate eventDate, boolean result) {
        assertThat(eventDate.isApplicableDate(1))
                .isEqualTo(result);
    }

    private static Stream<Arguments> provideEventDate() {
        return Stream.of(
                Arguments.of(EventDate.CHRISTMAS_EVENT_DATE, true),
                Arguments.of(EventDate.WEEKDAY_EVENT_DATE, false),
                Arguments.of(EventDate.WEEKEND_EVENT_DATE, true),
                Arguments.of(EventDate.SPECIAL_EVENT_DATE, false));
    }
}
