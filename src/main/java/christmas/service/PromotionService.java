package christmas.service;

import christmas.domain.*;
import christmas.dto.*;
import christmas.dto.BenefitDto;
import christmas.dto.OrderMenuDto;
import christmas.message.EventNoticeMessage;

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
        this.order = new Order(orderMenuAndCount);
    }

    public void validateEventApplicability() {
        if (!order.isApplicableEvent()) {
            throw new IllegalArgumentException(EventNoticeMessage.NOT_APPLICABLE_EVENT.getErrorMessage());
        }
    }

    public void applyPromotion() {
        this.benefit = new Benefit(order, date);
    }

    public ResultDto createEventResultDto() {
        List<OrderMenuDto> orderMenuDtos = order.getOrderMenus().stream()
                .map(orderMenu -> new OrderMenuDto(orderMenu.getMenu().getMenuName(), orderMenu.getQuantity()))
                .toList();

        List<BenefitDto> BenefitDtos = benefit.getEvents().stream()
                .map(event -> new BenefitDto(event.getEventName(), event.getBenefitAmount(order, date)))
                .toList();

        return new ResultDto(date.getDate(), orderMenuDtos, order.getTotalOrderPrice(), benefit.hasGift(),
                BenefitDtos, benefit.getTotalBenefitAmount(),
                order.getTotalOrderPrice() - benefit.getTotalDiscountAmount(),
                benefit.getBadge().getBadgeName());
    }

    public ResultDto createNonEventResultDto() {
        List<OrderMenuDto> orderMenuDtos = order.getOrderMenus().stream()
                .map(orderMenu -> new OrderMenuDto(orderMenu.getMenu().getMenuName(), orderMenu.getQuantity()))
                .toList();

        return new ResultDto(date.getDate(), orderMenuDtos, order.getTotalOrderPrice());
    }
}
