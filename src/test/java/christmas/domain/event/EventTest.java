package christmas.domain.event;

import christmas.domain.event.Event;
import christmas.domain.menu.Menu;
import christmas.domain.order.Date;
import christmas.domain.order.OrderMenus;
import christmas.domain.order.TotalOrderPrice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class EventTest {

    private Date date;
    private OrderMenus orderMenus;
    private TotalOrderPrice totalOrderPrice;

    @BeforeEach
    void init() {
        date = new Date(3);

        Map<String, Integer> orderMenuAndCount = new HashMap<>();
        orderMenuAndCount.put("타파스", 1);
        orderMenuAndCount.put("티본스테이크", 1);
        orderMenuAndCount.put("해산물파스타", 2);
        orderMenuAndCount.put("초코케이크", 1);
        orderMenus = new OrderMenus(orderMenuAndCount);

        totalOrderPrice = new TotalOrderPrice(orderMenus.calculateTotalOrderPrice());
    }

    @DisplayName("적용되는 이벤트 리스트 생성")
    @Test
    void getApplicableEvents() {
        // given
        List<Event> events = Event.getApplicableEvents(date, orderMenus, totalOrderPrice);

        // then
        Assertions.assertThat(events.size()).isEqualTo(4);
        Assertions.assertThat(events.containsAll(List.of(Event.CHRISTMAS, Event.WEEKDAY, Event.SPECIAL, Event.GIFT)));
    }

    @DisplayName("적용되는 이벤트 리스트를 통해 총혜택 금액 반환")
    @Test
    void getTotalBenefitAmount() {
        // given
        List<Event> events = Event.getApplicableEvents(date, orderMenus, totalOrderPrice);

        // when
        int totalBenefitAmount = events.stream()
                .mapToInt(event -> event.getBenefitAmount(date, orderMenus))
                .sum();

        // then
        Assertions.assertThat(totalBenefitAmount)
                .isEqualTo(date.getChristmasEventBenefitAmount()
                        + orderMenus.getWeekdayEventBenefitAmount()
                        + 1000
                        + Menu.CHAMPAGNE.getPrice());
    }
}
