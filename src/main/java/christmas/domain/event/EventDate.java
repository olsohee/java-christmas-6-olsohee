package christmas.domain.event;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

public enum EventDate {

    CHRISTMAS_EVENT_DATE(Event.CHRISTMAS, IntStream.range(1, 26).boxed().toList()),
    WEEKDAY_EVENT_DATE(Event.WEEKDAY, List.of(7, 1, 2, 3, 4)),
    WEEKEND_EVENT_DATE(Event.WEEKEND, List.of(5, 6)),
    SPECIAL_EVENT_DATE(Event.SPECIAL, List.of(3, 10, 17, 24, 25, 31));

    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private final Event event;
    private final List<Integer> applicableDates;

    EventDate(Event event, List<Integer> applicableDates) {
        this.event = event;
        this.applicableDates = applicableDates;
    }

    public boolean isApplicableDate(int date) {
        if (this == WEEKDAY_EVENT_DATE || this == WEEKEND_EVENT_DATE) {
            LocalDate localDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, date);
            date = localDate.getDayOfWeek().getValue();
        }
        return this.applicableDates.contains(date);
    }
}
