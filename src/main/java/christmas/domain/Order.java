package christmas.domain;

import java.util.List;

public class Order {

    private List<OrderMenu> orderMenus;

    public Order(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }
}
