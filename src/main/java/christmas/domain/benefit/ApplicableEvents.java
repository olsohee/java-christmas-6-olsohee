package christmas.domain.benefit;

import christmas.domain.event.Event;
import christmas.domain.order.Date;
import christmas.domain.order.OrderMenus;

import java.util.List;

public class ApplicableEvents {

    private final List<Event> applicableEvents;

    public ApplicableEvents(List<Event> applicableEvents) {
        this.applicableEvents = applicableEvents;
    }

    public int calculateTotalBenefitAmount(Date date, OrderMenus orderMenus) {
        return applicableEvents.stream()
                .mapToInt(event -> event.getBenefitAmount(date, orderMenus))
                .sum();
    }

    public boolean containGiftEvent() {
        return applicableEvents.contains(Event.GIFT);
    }

    public List<Event> getEvents() {
        return applicableEvents;
    }
}
