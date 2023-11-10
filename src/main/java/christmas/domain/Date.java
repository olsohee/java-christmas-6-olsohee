package christmas.domain;

import christmas.ErrorMessage;

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
}
