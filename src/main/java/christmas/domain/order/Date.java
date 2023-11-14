package christmas.domain.order;

import christmas.domain.event.EventDate;
import christmas.message.ErrorMessage;

import java.time.LocalDate;

public class Date {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private final int date;

    public Date(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if(date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_INPUT.getErrorMessage());
        }
    }

    public boolean isApplicableChristmasEvent() {
        return EventDate.isApplicableChristmasEvent(date);
    }

    public boolean isApplicableWeekdayEvent() {
        LocalDate localDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, date);
        int dayOfWeekNumber = localDate.getDayOfWeek().getValue();
        return EventDate.isApplicableWeekdayEvent(dayOfWeekNumber);
    }

    public boolean isApplicableWeekendEvent() {
        LocalDate localDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, date);
        int dayOfWeekNumber = localDate.getDayOfWeek().getValue();
        return EventDate.isApplicableWeekendEvent(dayOfWeekNumber);
    }

    public boolean isApplicableSpecialEvent() {
        return EventDate.isApplicableSpecialEvent(date);
    }

    public int getChristmasEventBenefitAmount() {
        return 1000 + (date - 1) * 100;
    }

    public int getDate() {
        return date;
    }
}
