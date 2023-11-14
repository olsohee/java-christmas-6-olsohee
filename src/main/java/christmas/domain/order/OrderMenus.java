package christmas.domain.order;

import christmas.domain.menu.Category;
import christmas.message.NoticeMessage;

import java.util.List;
import java.util.Map;

public class OrderMenus {

    private final List<OrderMenu> orderMenus;

    public OrderMenus(Map<String, Integer> orderMenuNameAndCount) {
        List<OrderMenu> orderMenus = orderMenuNameAndCount.keySet().stream()
                .map(menuName -> new OrderMenu(menuName, orderMenuNameAndCount.get(menuName)))
                .toList();
        validate(orderMenus);
        this.orderMenus = orderMenus;
    }

    private void validate(List<OrderMenu> orderMenus) {
        validateMaxCount(orderMenus);
        validateOnlyDrink(orderMenus);
    }

    private void validateMaxCount(List<OrderMenu> orderMenus) {
        int totalOrderAmount = orderMenus.stream()
                .mapToInt(orderMenu -> orderMenu.getQuantity()).sum();
        if (totalOrderAmount > 20) {
            throw new IllegalArgumentException(NoticeMessage.IMPOSSIBLE_ORDER_BY_MAX_ORDER_COUNT.getNoticeMessage());
        }
    }

    private void validateOnlyDrink(List<OrderMenu> orderMenus) {
        int drinkMenuCount = (int) orderMenus.stream()
                .filter(orderMenu -> orderMenu.checkCategory(Category.DRINK))
                .count();
        if (drinkMenuCount == orderMenus.size()) {
            throw new IllegalArgumentException(NoticeMessage.IMPOSSIBLE_ORDER_BY_ONLY_DRINK.getNoticeMessage());
        }
    }

    public int calculateTotalOrderPrice() {
        return orderMenus.stream()
                .mapToInt(orderMenu -> orderMenu.calculateOrderPrice())
                .sum();
    }

    public boolean checkCategory(Category category) {
        return orderMenus.stream()
                .anyMatch(orderMenu -> orderMenu.checkCategory(category));
    }

    public int getWeekdayEventBenefitAmount() {
        return getMenuCount(Category.DESERT) * 2023;
    }

    public int getWeekendEventBenefitAmount() {
        return getMenuCount(Category.MAIN) * 2023;
    }

    private int getMenuCount(Category category) {
        return orderMenus.stream()
                .filter(orderMenu -> orderMenu.checkCategory(category))
                .mapToInt(orderMenu -> orderMenu.getQuantity())
                .sum();
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }
}
