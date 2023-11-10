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
}
