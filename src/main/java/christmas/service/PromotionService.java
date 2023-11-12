package christmas.service;

import christmas.domain.*;
import christmas.dto.BadgeDto;
import christmas.dto.DateDto;
import christmas.dto.PaymentDto;
import christmas.dto.benefitDto.BenefitsDto;
import christmas.dto.orderMenuDto.OrderDto;

import java.util.List;
import java.util.Map;

public class PromotionService {
    private Date date;
    private Order order;
    private Benefit benefit;

    public void createDate(int date) {
        this.date = new Date(date);
    }

    public void createOrder(Map<String, Integer> orderMenuAndCount) {
        List<OrderMenu> orderMenus = orderMenuAndCount.keySet().stream()
                .map(menuName -> new OrderMenu(menuName, orderMenuAndCount.get(menuName)))
                .toList();
        this.order = new Order(orderMenus);
    }

    public void applyPromotion() {
        this.benefit = new Benefit(order, date);
    }


    public DateDto createDateDto() {
        return new DateDto(date);
    }

    public OrderDto createOrderDto() {
        return new OrderDto(order);
    }

    public BenefitsDto createBenefitsDto() {
        return new BenefitsDto(benefit, order, date);
    }

    public PaymentDto createPaymentDto() {
        return new PaymentDto(order, benefit);
    }

    public BadgeDto createBadgeDto() {
        return new BadgeDto(benefit);
    }
}
