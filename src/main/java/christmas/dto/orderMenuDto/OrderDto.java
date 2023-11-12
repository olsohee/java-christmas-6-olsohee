package christmas.dto.orderMenuDto;

import christmas.domain.Order;

import java.util.List;

public class OrderDto {

    private final List<OrderMenuDto> orderMenuDtos;
    private final int totalOrderPrice;

    public OrderDto(Order order) {
        this.orderMenuDtos = order.getOrderMenus().stream()
                .map(orderMenu -> new OrderMenuDto(orderMenu.getMenu().getMenuName(), orderMenu.getQuantity()))
                .toList();
        this.totalOrderPrice = order.getTotalOrderPrice();
    }

    public List<OrderMenuDto> getOrderMenuDtos() {
        return orderMenuDtos;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }
}
