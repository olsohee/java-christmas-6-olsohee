package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class EventTest {

    @DisplayName("주문 내역과 방문 날짜를 통해 적용되는 이벤트 리스트 반환")
    @Test
    void getApplicableEvents() {
        // given
        Order order = new Order(
                List.of(new OrderMenu("타파스", 1),
                        new OrderMenu("티본스테이크", 1),
                        new OrderMenu("해산물파스타", 2),
                        new OrderMenu("초코케이크", 1))
                );

        Date date = new Date(3);

        // when
        List<Event> events = Event.getApplicableEvents(order, date);

        // then
        Assertions.assertThat(events.size()).isEqualTo(4);
        Assertions.assertThat(events.containsAll(List.of(Event.CHRISTMAS, Event.WEEKDAY, Event.SPECIAL, Event.GIFT)));
    }
}
