package christmas.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum Event {

    CHRISTMAS("크리스마스 디데이 할인",
            (date, orderMenus) -> date.isApplicableChristmasEvent(),
            (date, orderMenus) -> date.getChristmasEventBenefitAmount()
    ),
    WEEKDAY("평일 할인",
            (date, orderMenus) -> date.isApplicableWeekDayEvent() && orderMenus.hasDessert(),
            (date, orderMenus) -> orderMenus.getWeekDayEventBenefitAmount()
    ),
    WEEKEND("주말 할인",
            (date, orderMenus) -> date.isApplicableWeekendEvent() && orderMenus.hasMain(),
            (date, orderMenus) -> orderMenus.getWeekendEventBenefitAmount()
    ),
    SPECIAL("특별 할인",
            (date, orderMenus) -> date.isApplicableSpecialEvent(),
            (date, orderMenus) -> 1000
    ),
    GIFT("증정 이벤트",
            (date, orderMenus) -> orderMenus.isApplicableGiftEvent(),
            (date, orderMenus) -> 25000
    )
    ;

    private final String name;
    private final BiPredicate<Date, OrderMenus> isApplicable;
    private final BiFunction<Date, OrderMenus, Integer> getBenefitAmount;

    Event(String name, BiPredicate<Date, OrderMenus> isApplicable, BiFunction<Date, OrderMenus, Integer> getBenefitAmount) {
        this.name = name;
        this.isApplicable = isApplicable;
        this.getBenefitAmount = getBenefitAmount;
    }

    public static Map<Event, Integer> getApplicableEventsAndBenefit(Date date, OrderMenus orderMenus) {
        Map<Event, Integer> applicableEventsAndBenefit = new HashMap<>();

        List<Event> applicableEvents = Arrays.stream(Event.values())
                .filter(event -> event.isApplicable.test(date, orderMenus))
                .toList();

        for (Event event : applicableEvents) {
            applicableEventsAndBenefit.put(event, event.getBenefitAmount.apply(date, orderMenus));
        }
        return applicableEventsAndBenefit;
    }
}
