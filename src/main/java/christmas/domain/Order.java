package christmas.domain;

import java.util.List;

public class Order {

    private List<OrderMenu> orderMenus;
    private int totalOrderPrice;

    public Order(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
        this.totalOrderPrice = calculateTotalOrderPrice();
    }

    private int calculateTotalOrderPrice() {
        return orderMenus.stream()
                .mapToInt(orderMenu -> orderMenu.calculateOrderPrice())
                .sum();
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public boolean hasDesertMenu() {
        return orderMenus.stream()
                .anyMatch(orderMenu -> orderMenu.isDessertMenu());
    }

    public boolean hasMainMenu() {
        return orderMenus.stream()
                .anyMatch(orderMenu -> orderMenu.isMainMenu());
    }

    public boolean isApplicableGiftEvent() {
        return totalOrderPrice >= 120000;
    }

    public int getWeekdayEventBenefitAmount() {
        return getDessertMenuCount() * 2023;
    }

    private int getDessertMenuCount() {
        return (int) orderMenus.stream()
                .filter(orderMenu -> orderMenu.isDessertMenu())
                .count();
    }

    public int getWeekendEventBenefitAmount() {
        return getMainMenuCount() * 2023;
    }

    private int getMainMenuCount() {
        return (int) orderMenus.stream()
                .filter(orderMenu -> orderMenu.isMainMenu())
                .count();
    }
}
