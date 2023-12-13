package christmas.service;

import christmas.domain.*;
import christmas.dto.OrderMenuDto;
import christmas.message.ErrorMessage;

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

    public void startPromotion() {
        validateCanPromotion();
        applicableEventsAndBenefit = Event.getApplicableEventsAndBenefit(date, orderMenus);
        int totalBenefitAmount = 0;
        for (Event event : applicableEventsAndBenefit.keySet()) {
            totalBenefitAmount += applicableEventsAndBenefit.get(event);
        }
        badge = Badge.findBadgeByBenefitAmount(totalBenefitAmount);
//        for (Event event : applicableEventsAndBenefit.keySet()) {
//            System.out.println(event);
//            System.out.println(applicableEventsAndBenefit.get(event));
//        }
//        System.out.println(badge);
    }

    public void validateCanPromotion() {
        if (!orderMenus.canPromotion()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_POSSIBLE_ORDER.getErrorMessage());
        }
    }

    public List<OrderMenuDto> getOrderMenuDto() {
        return orderMenus.getOrderMenus().keySet().stream()
                .map(menu -> new OrderMenuDto(menu.getName(), orderMenus.getOrderMenus().get(menu)))
                .toList();
    }
}
