package christmas.service;

import christmas.domain.benefit.*;
import christmas.domain.event.Event;
import christmas.domain.menu.Menu;
import christmas.domain.order.*;
import christmas.dto.*;
import christmas.message.NoticeMessage;

import java.util.List;
import java.util.Map;

public class PromotionService {
    private Date date;
    private OrderMenus orderMenus;
    private TotalOrderPrice totalOrderPrice;
    private ApplicableEvents applicableEvents;
    private int totalBenefitAmount;
    private int totalDiscountAmount;
    private Badge badge;

    public void initiateDate(int date) {
        this.date = new Date(date);
    }

    public void initiateOrder(Map<String, Integer> orderNameAndCount) {
        orderMenus = new OrderMenus(orderNameAndCount);
        totalOrderPrice = new TotalOrderPrice(orderMenus.calculateTotalOrderPrice());
    }

    public void validateEventApplicability() {
        if (!totalOrderPrice.isApplicableEvent()) {
            throw new IllegalArgumentException(NoticeMessage.NOT_APPLICABLE_EVENT.getNoticeMessage());
        }
    }

    public void applyPromotion() {
        applicableEvents = new ApplicableEvents(Event.getApplicableEvents(date, orderMenus, totalOrderPrice));
        totalBenefitAmount = applicableEvents.calculateTotalBenefitAmount(date, orderMenus);
        totalDiscountAmount = totalBenefitAmount;
        if (applicableEvents.containGiftEvent()) {
            totalDiscountAmount -= Menu.CHAMPAGNE.getPrice();
        }
        badge = Badge.getBadge(totalBenefitAmount);
    }

    public ResultDto createEventResultDto() {
        List<OrderMenuDto> orderMenuDtos = createOrderMenuDtos();
        List<EventDto> eventDtos = applicableEvents.getEvents().stream()
                .map(event -> new EventDto(event.getEventName(), event.getBenefitAmount(date, orderMenus)))
                .toList();
        int payment = totalOrderPrice.getTotalOrderPrice() - totalDiscountAmount;

        return new ResultDto(date.getDate(), orderMenuDtos, totalOrderPrice.getTotalOrderPrice(),
                applicableEvents.containGiftEvent(), eventDtos, totalBenefitAmount,
                payment, badge.getBadgeName());
    }

    public ResultDto createNonEventResultDto() {
        List<OrderMenuDto> orderMenuDtos = createOrderMenuDtos();
        return new ResultDto(date.getDate(), orderMenuDtos, totalOrderPrice.getTotalOrderPrice());
    }

    private List<OrderMenuDto> createOrderMenuDtos() {
        return orderMenus.getOrderMenus().stream()
                .map(orderMenu -> new OrderMenuDto(orderMenu.getMenu().getMenuName(), orderMenu.getQuantity()))
                .toList();
    }
}
