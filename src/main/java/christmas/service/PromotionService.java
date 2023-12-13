package christmas.service;

import christmas.domain.Date;

public class PromotionService {

    private Date date;

    public void createDate(int date) {
        this.date = new Date(date);
    }
}
