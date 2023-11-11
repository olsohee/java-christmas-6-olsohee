package christmas.domain;

import christmas.domain.menu.Menu;

import java.util.List;

public class Benefit {

    private List<Event> events;
    private int totalBenefitAmount;

    public Benefit(Order order, Date date) {
        events = getApplicableEvents(order, date);
        totalBenefitAmount = getTotalBenefitAmount(order, date);
    }

    private List<Event> getApplicableEvents(Order order, Date date) {
        return Event.getApplicableEvents(order, date);
    }

    private int getTotalBenefitAmount(Order order, Date date) {
        return events.stream()
                .mapToInt(event -> event.getBenefitAmount(order, date))
                .sum();
    }

    public int getTotalDiscountAmount() {
        if(events.contains(Event.GIFT)) {
            return totalBenefitAmount - Menu.CHAMPAGNE.getPrice();
        }
        return totalBenefitAmount;
    }
}
