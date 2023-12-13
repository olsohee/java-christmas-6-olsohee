package christmas.domain;

import christmas.message.ErrorMessage;

import java.time.LocalDate;

public class Date {

    private final int date;

    public Date(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getErrorMessage());
        }
    }

    public boolean isApplicableChristmasEvent() {
        return (date >= 1) && (date <= 25);
    }

    public int getChristmasEventBenefitAmount() {
        return 1000 + (date - 1) * 100;
    }

    public boolean isApplicableWeekDayEvent() {
        LocalDate date = LocalDate.of(2023, 12, this.date);
        int dayOfWeekValue = date.getDayOfWeek().getValue();
        return dayOfWeekValue == 7 || dayOfWeekValue == 1 || dayOfWeekValue == 2 || dayOfWeekValue == 3 || dayOfWeekValue == 4;
    }

    public boolean isApplicableWeekendEvent() {
        LocalDate date = LocalDate.of(2023, 12, this.date);
        int dayOfWeekValue = date.getDayOfWeek().getValue();
        return dayOfWeekValue == 5 || dayOfWeekValue == 6;
    }

    public boolean isApplicableSpecialEvent() {
        return date == 3 || date == 10 || date == 17 || date == 24 || date == 25 || date == 31;
    }
}
