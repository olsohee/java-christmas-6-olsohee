package christmas.service;

import christmas.domain.*;
import christmas.dto.BenefitDto;
import christmas.dto.EventBenefitDto;
import christmas.dto.NonPromotionDto;
import christmas.dto.OrderMenuDto;
import christmas.message.ErrorMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PromotionService {

    private Date date;
    private OrderMenus orderMenus;
    private Map<Event, Integer> applicableEventsAndBenefit;
    private Badge badge;

    public void createDate(int date) {
        this.date = new Date(date);
    }

    public void validateOrder(Map<Menu, Integer> orderMenus) {
        validateCount(orderMenus);
        validateOnlyDrink(orderMenus);
    }

    private void validateCount(Map<Menu, Integer> orderMenus) {
        int totalCount = 0;
        for (Integer count : orderMenus.values()) {
            totalCount += count;
        }
        if (totalCount > 20) {
            throw new IllegalArgumentException(ErrorMessage.NOT_POSSIBLE_ORDER.getErrorMessage());
        }
    }

    private void validateOnlyDrink(Map<Menu, Integer> orderMenus) {
        for (Menu menu : orderMenus.keySet()) {
            if (!menu.isDrink()) {
                return;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.NOT_POSSIBLE_ORDER.getErrorMessage());
    }

    public void createOrder(Map<Menu, Integer> orderMenus) {
        this.orderMenus = new OrderMenus(orderMenus);
    }

    public boolean canPromotion() {
        return orderMenus.canPromotion();
    }

    public void startPromotion() {
        applicableEventsAndBenefit = Event.getApplicableEventsAndBenefit(date, orderMenus);
        int totalBenefitAmount = 0;
        for (Event event : applicableEventsAndBenefit.keySet()) {
            totalBenefitAmount += applicableEventsAndBenefit.get(event);
        }
        badge = Badge.findBadgeByBenefitAmount(totalBenefitAmount);
    }

    public List<OrderMenuDto> getOrderMenuDto() {
        return orderMenus.getOrderMenus().keySet().stream()
                .map(menu -> new OrderMenuDto(menu.getName(), orderMenus.getOrderMenus().get(menu)))
                .toList();
    }

    public EventBenefitDto getEventBenefitDto() {
        List<BenefitDto> benefitDtos = new ArrayList<>();
        for (Event event : applicableEventsAndBenefit.keySet()) {
            benefitDtos.add(new BenefitDto(event.getName(), applicableEventsAndBenefit.get(event)));
        }

        return new EventBenefitDto(orderMenus.calculateTotalPrice(), applicableEventsAndBenefit.containsKey(Event.GIFT),
                benefitDtos, badge.getName());
    }

    public NonPromotionDto getNonPromotionDto() {
        return new NonPromotionDto(orderMenus.calculateTotalPrice());
    }
}
