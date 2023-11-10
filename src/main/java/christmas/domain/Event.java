package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum Event {
    CHRISTMAS("크리스마스 디데이 할인", (order, date)  -> date.isApplicableChristmasEvent()),
    WEEKDAY("평일 할인", (order, date) -> order.hasDesertMenu() && date.isApplicableWeekdayEvent()),
    WEEKEND("주말 할인", (order, date) -> order.hasMainMenu() && date.isApplicableWeekendEvent()),
    SPECIAL("특별 할인", (order, date) -> date.isApplicableSpecialEvent()),
    GIFT("증정 이벤트", (order, date) -> order.isApplicableGiftEvent())
    ;

    private final String eventName;
    private final BiPredicate<Order, Date> isApplicable;

    Event(String eventName, BiPredicate<Order, Date> isApplicable) {
        this.eventName = eventName;
        this.isApplicable = isApplicable;
    }

    public static List<Event> getApplicableEvents(Order order, Date date) {
        return Arrays.stream(values())
                .filter(menu -> menu.isApplicable.test(order, date))
                .toList();
    }
}
