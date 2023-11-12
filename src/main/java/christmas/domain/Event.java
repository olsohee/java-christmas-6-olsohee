package christmas.domain;

import christmas.domain.menu.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum Event {
    CHRISTMAS("크리스마스 디데이 할인",
            (order, date) -> date.isApplicableChristmasEvent(),
            (order, date) -> date.getChristmasEventBenefitAmount()),

    WEEKDAY("평일 할인",
            (order, date) -> order.hasDesertMenu() && date.isApplicableWeekdayEvent(),
            (order, date) -> order.getWeekdayEventBenefitAmount()),
    WEEKEND("주말 할인",
            (order, date) -> order.hasMainMenu() && date.isApplicableWeekendEvent(),
            (order, date) -> order.getWeekendEventBenefitAmount()),
    SPECIAL("특별 할인",
            (order, date) -> date.isApplicableSpecialEvent(),
            (order, date) -> 1000),
    GIFT("증정 이벤트",
            (order, date) -> order.isApplicableGiftEvent(),
            (order, date) -> Menu.CHAMPAGNE.getPrice());

    private final String eventName;
    private final BiPredicate<Order, Date> isApplicable;
    private final BiFunction<Order, Date, Integer> benefitAmount;

    Event(String eventName, BiPredicate<Order, Date> isApplicable, BiFunction<Order, Date, Integer> benefitAmount) {
        this.eventName = eventName;
        this.isApplicable = isApplicable;
        this.benefitAmount = benefitAmount;
    }

    public static List<Event> getApplicableEvents(Order order, Date date) {
        return Arrays.stream(values())
                .filter(menu -> menu.isApplicable.test(order, date))
                .toList();
    }

    public int getBenefitAmount(Order order, Date date) {
        return benefitAmount.apply(order, date);
    }

    public String getEventName() {
        return eventName;
    }
}
