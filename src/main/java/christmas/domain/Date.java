package christmas.domain;

import christmas.ErrorMessage;

import java.time.LocalDate;

public class Date {

    private int date;

    public Date(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if(date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getErrorMessage());
        }
    }

    public boolean isApplicableChristmasEvent() {
        return (date >= 1 || date <= 25);
    }

    public boolean isApplicableWeekdayEvent() {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        int dayOfWeekNumber = localDate.getDayOfWeek().getValue();
        return (dayOfWeekNumber == 7 || dayOfWeekNumber == 1 || dayOfWeekNumber == 2 || dayOfWeekNumber == 3 || dayOfWeekNumber == 4);
    }

    public boolean isApplicableWeekendEvent() {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        int dayOfWeekNumber = localDate.getDayOfWeek().getValue();
        return (dayOfWeekNumber == 5 || dayOfWeekNumber == 6);
    }

    public boolean isApplicableSpecialEvent() {
        return (date == 3 || date == 10 || date == 17 || date == 24 || date == 25 || date == 31);
    }

    public int getChristmasEventBenefitAmount() {
        return 1000 + (date - 1) * 100;
    }
}
