package christmas.domain.event;

import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.domain.order.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum Event {
    CHRISTMAS("크리스마스 디데이 할인",
            (date, orderMenus, totalOrderPrice) -> date.isApplicableChristmasEvent(),
            (date, orderMenus) -> date.getChristmasEventBenefitAmount()),

    WEEKDAY("평일 할인",
            (date, orderMenus, totalOrderPrice) -> orderMenus.checkCategory(Category.DESERT) && date.isApplicableWeekdayEvent(),
            (date, orderMenus) -> orderMenus.getWeekdayEventBenefitAmount()),
    WEEKEND("주말 할인",
            (date, orderMenus, totalOrderPrice) -> orderMenus.checkCategory(Category.MAIN) && date.isApplicableWeekendEvent(),
            (date, orderMenus) -> orderMenus.getWeekendEventBenefitAmount()),
    SPECIAL("특별 할인",
            (date, orderMenus, totalOrderPrice) -> date.isApplicableSpecialEvent(),
            (date, orderMenus) -> 1000),
    GIFT("증정 이벤트",
            (date, orderMenus, totalOrderPrice) -> totalOrderPrice.isApplicableGiftEvent(),
            (date, orderMenus) -> Menu.CHAMPAGNE.getPrice());

    private final String eventName;
    private final TriPredicate<Date, OrderMenus, TotalOrderPrice> isApplicable;
    private final BiFunction<Date, OrderMenus, Integer> getBenefitAmount;

    Event(String eventName, TriPredicate<Date, OrderMenus, TotalOrderPrice> isApplicable, BiFunction<Date, OrderMenus, Integer> getBenefitAmount) {
        this.eventName = eventName;
        this.isApplicable = isApplicable;
        this.getBenefitAmount = getBenefitAmount;
    }

    public static List<Event> getApplicableEvents(Date date, OrderMenus orderMenus, TotalOrderPrice totalOrderPrice) {
        return Arrays.stream(values())
                .filter(event -> event.isApplicable.test(date, orderMenus, totalOrderPrice))
                .toList();
    }

    public int getBenefitAmount(Date date, OrderMenus orderMenus) {
        return getBenefitAmount.apply(date, orderMenus);
    }

    public String getEventName() {
        return eventName;
    }
}
