package christmas.domain.benefit;

import christmas.domain.event.Event;
import christmas.domain.menu.Menu;
import christmas.domain.order.Date;
import christmas.domain.order.OrderMenus;
import christmas.domain.order.TotalOrderPrice;

import java.util.List;

public class Events {

    private List<Event> events;

    public Events(List<Event> events) {
        this.events = events;
    }

    public int calculateTotalBenefitAmount(Date date, OrderMenus orderMenus) {
        return events.stream()
                .mapToInt(event -> event.getBenefitAmount(date, orderMenus))
                .sum();
    }

    public boolean containGiftEvent() {
        return events.contains(Event.GIFT);
    }

    public List<Event> getEvents() {
        return events;
    }
}
