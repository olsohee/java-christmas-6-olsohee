package christmas.domain.benefit;

import christmas.domain.event.Event;
import christmas.domain.event.EventDate;
import christmas.domain.menu.Menu;
import christmas.domain.order.Date;
import christmas.domain.order.OrderMenus;
import christmas.domain.order.TotalOrderPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicableEventsTest {

    private Date date;
    private OrderMenus orderMenus;
    private TotalOrderPrice totalOrderPrice;
    private ApplicableEvents applicableEvents;

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

        applicableEvents = new ApplicableEvents(Event.getApplicableEvents(date, orderMenus, totalOrderPrice));
    }

    @DisplayName("총혜택 금액 계산")
    @Test
    void calculateTotalBenefitAmount() {
        assertThat(applicableEvents.calculateTotalBenefitAmount(date, orderMenus))
                .isEqualTo(date.getChristmasEventBenefitAmount()
                        + orderMenus.getWeekdayEventBenefitAmount()
                        + 1000
                        + Menu.CHAMPAGNE.getPrice());
    }

    @DisplayName("증정 이벤트가 포함되는지 확인")
    @Test
    void containGiftEvent() {
        assertThat(applicableEvents.containGiftEvent()).isTrue();
    }
}
