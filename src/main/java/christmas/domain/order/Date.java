package christmas.domain.order;

import christmas.domain.event.EventDate;
import christmas.message.ErrorMessage;

public class Date {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
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
        return EventDate.CHRISTMAS_EVENT_DATE.isApplicableDate(date);
    }

    public boolean isApplicableWeekdayEvent() {
        return EventDate.WEEKDAY_EVENT_DATE.isApplicableDate(date);
    }

    public boolean isApplicableWeekendEvent() {
        return EventDate.WEEKEND_EVENT_DATE.isApplicableDate(date);
    }

    public boolean isApplicableSpecialEvent() {
        return EventDate.SPECIAL_EVENT_DATE.isApplicableDate(date);
    }

    public int getChristmasEventBenefitAmount() {
        return 1000 + (date - 1) * 100;
    }

    public int getDate() {
        return date;
    }
}
