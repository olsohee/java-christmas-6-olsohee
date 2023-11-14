package christmas.domain.order;

import christmas.domain.order.OrderMenu;
import christmas.message.ErrorMessage;
import christmas.message.EventNoticeMessage;

import java.util.List;
import java.util.Map;

public class OrderMenus {

    private List<OrderMenu> orderMenus;

    public OrderMenus(Map<String, Integer> orderMenuNameAndCount) {
        List<OrderMenu> orderMenus = orderMenuNameAndCount.keySet().stream()
                .map(menuName -> new OrderMenu(menuName, orderMenuNameAndCount.get(menuName)))
                .toList();
        validate(orderMenus);
        this.orderMenus = orderMenus;
    }

    private void validate(List<OrderMenu> orderMenus) {
        validateMinCount(orderMenus);
        validateMaxCount(orderMenus);
        validateOnlyDrink(orderMenus);
    }

    private void validateMinCount(List<OrderMenu> orderMenus) {
        int totalOrderAmount = orderMenus.stream()
                .mapToInt(orderMenu -> orderMenu.getQuantity()).sum();

        if (totalOrderAmount < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getErrorMessage());
        }
    }

    private void validateMaxCount(List<OrderMenu> orderMenus) {
        int totalOrderAmount = orderMenus.stream()
                .mapToInt(orderMenu -> orderMenu.getQuantity()).sum();

        if (totalOrderAmount > 20) {
            throw new IllegalArgumentException(EventNoticeMessage.IMPOSSIBLE_ORDER_BY_MAX_ORDER_COUNT.getErrorMessage());
        }
    }

    private void validateOnlyDrink(List<OrderMenu> orderMenus) {
        int drinkMenuCount = (int) orderMenus.stream()
                .filter(orderMenu -> orderMenu.isDrinkMenu())
                .count();
        if (drinkMenuCount == orderMenus.size()) {
            throw new IllegalArgumentException(EventNoticeMessage.IMPOSSIBLE_ORDER_BY_ONLY_DRINK.getErrorMessage());
        }
    }

    public int calculateTotalOrderPrice() {
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
}
