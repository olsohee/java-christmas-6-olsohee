package christmas.dto;

import christmas.domain.Date;

public class DateDto {

    private final int date;

    public DateDto(Date date) {
        this.date = date.getDate();
    }

    public int getDate() {
        return date;
    }
}
