package christmas.domain;

import java.util.Map;

public class OrderMenus {

    private Map<Menu, Integer> orderMenus;

    public OrderMenus(Map<Menu, Integer> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public boolean canPromotion() {
        return calculateTotalPrice() >= 10000;
    }

    public Integer getWeekDayEventBenefitAmount() {
        int dessertCount = 0;
        for (Menu menu : orderMenus.keySet()) {
            if (menu.isDessert()) {
                dessertCount++;
            }
        }
        return dessertCount * 2023;
    }

    public int getWeekendEventBenefitAmount() {
        int mainCount = 0;
        for (Menu menu : orderMenus.keySet()) {
            if (menu.isMain()) {
                mainCount++;
            }
        }
        return mainCount * 2023;
    }

    public boolean isApplicableGiftEvent() {
        return calculateTotalPrice() >= 120000;
    }

    public int calculateTotalPrice() {
        return orderMenus.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * orderMenus.get(menu))
                .sum();
    }

    public boolean hasDessert() {
        return orderMenus.keySet().stream()
                .anyMatch(menu -> menu.isDessert());
    }

    public boolean hasMain() {
        return orderMenus.keySet().stream()
                .anyMatch(menu -> menu.isMain());
    }

    public Map<Menu, Integer> getOrderMenus() {
        return orderMenus;
    }
}
