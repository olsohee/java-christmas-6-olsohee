package christmas.domain;

import christmas.ErrorMessage;

import java.util.List;

public class Order {

    private List<OrderMenu> orderMenus;
    private int totalOrderPrice;

    public Order(List<OrderMenu> orderMenus) {
        validate();
        this.orderMenus = orderMenus;
        this.totalOrderPrice = calculateTotalOrderPrice();
    }

    private void validate() {
        int totalOrderAmount = orderMenus.stream()
                .mapToInt(orderMenu -> orderMenu.getQuantity())
                .sum();

        if(totalOrderAmount < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getErrorMessage());
        }
    }

    private int calculateTotalOrderPrice() {
        return orderMenus.stream()
                .mapToInt(orderMenu -> orderMenu.calculateOrderPrice())
                .sum();
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
        return orderMenus.stream()
                .filter(orderMenu -> orderMenu.isDessertMenu())
                .mapToInt(orderMenu -> orderMenu.getQuantity())
                .sum();
    }

    public int getWeekendEventBenefitAmount() {
        return getMainMenuCount() * 2023;
    }

    private int getMainMenuCount() {
        return orderMenus.stream()
                .filter(orderMenu -> orderMenu.isMainMenu())
                .mapToInt(orderMenus -> orderMenus.getQuantity())
                .sum();
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }
}
