package christmas.domain.event;

import java.util.List;
import java.util.stream.IntStream;

public enum EventDate {

    CHRISTMAS(List.of(IntStream.range(1, 26))),
    WEEKDAY(List.of(7, 1, 2, 3, 4)),
    WEEKEND(List.of(5, 6)),
    SPECIAL(List.of(3, 10, 17, 24, 25, 31));

    private final List<Integer> eventApplicableDate;

    EventDate(List<Integer> eventApplicableDate) {
        this.eventApplicableDate = eventApplicableDate;
    }

    public static boolean isApplicableChristmasEvent(int date) {
        return CHRISTMAS.eventApplicableDate.contains(date);
    }

    public static boolean isApplicableWeekdayEvent(int dayOfWeekNumber) {
        return WEEKDAY.eventApplicableDate.contains(dayOfWeekNumber);
    }

    public static boolean isApplicableWeekendEvent(int dayOfWeekNumber) {
        return WEEKEND.eventApplicableDate.contains(dayOfWeekNumber);
    }

    public static boolean isApplicableSpecialEvent(int date) {
        return SPECIAL.eventApplicableDate.contains(date);
    }
}
