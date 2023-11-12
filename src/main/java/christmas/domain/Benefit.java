package christmas.domain;

import christmas.domain.menu.Menu;

import java.util.List;

import static christmas.domain.Event.getApplicableEvents;

public class Benefit {

    private List<Event> events;
    private int totalBenefitAmount;
    private int totalDiscountAmount;
    private Badge badge;

    public Benefit(Order order, Date date) {
        events = calculateApplicableEvents(order, date);
        totalBenefitAmount = calculateTotalBenefitAmount(order, date);
        totalDiscountAmount = calculateTotalDiscountAmount();
        badge = Badge.getBadge(totalBenefitAmount);
    }

    private List<Event> calculateApplicableEvents(Order order, Date date) {
        return getApplicableEvents(order, date);
    }

    private int calculateTotalBenefitAmount(Order order, Date date) {
        return events.stream()
                .mapToInt(event -> event.getBenefitAmount(order, date))
                .sum();
    }

    private int calculateTotalDiscountAmount() {
        if (events.contains(Event.GIFT)) {
            return totalBenefitAmount - Menu.CHAMPAGNE.getPrice();
        }
        return totalBenefitAmount;
    }

    public boolean hasGift() {
        return events.stream()
                .anyMatch(event -> event.equals(Event.GIFT));
    }

    public List<Event> getEvents() {
        return events;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public Badge getBadge() {
        return badge;
    }
}
