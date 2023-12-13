package christmas.service;

import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.message.ErrorMessage;

import java.util.Map;

public class PromotionService {

    private Date date;

    public void createDate(int date) {
        this.date = new Date(date);
    }

    public void validateOrder(Map<Menu, Integer> orderMenus) {
        validateCount(orderMenus);
        validateOnlyDrink(orderMenus);
    }

    private void validateCount(Map<Menu, Integer> orderMenus) {
        int totalCount = 0;
        for (Integer count : orderMenus.values()) {
            totalCount += count;
        }
        if (totalCount > 20) {
            throw new IllegalArgumentException(ErrorMessage.NOT_POSSIBLE_ORDER.getErrorMessage());
        }
    }

    private void validateOnlyDrink(Map<Menu, Integer> orderMenus) {
        for (Menu menu : orderMenus.keySet()) {
            if (!menu.isDrink()) {
                return;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.NOT_POSSIBLE_ORDER.getErrorMessage());
    }
}
