package christmas.service;

import christmas.domain.order.*;
import christmas.dto.*;
import christmas.message.NoticeMessage;

import java.util.List;
import java.util.Map;

public class OrderService {
    private Date date;
    private OrderMenus orderMenus;
    private TotalOrderPrice totalOrderPrice;
    private PromotionService promotionService;

    public void initiateDate(int date) {
        this.date = new Date(date);
    }

    public void initiateOrder(Map<String, Integer> orderNameAndCount) {
        orderMenus = new OrderMenus(orderNameAndCount);
        totalOrderPrice = new TotalOrderPrice(orderMenus.calculateTotalOrderPrice());
    }

    public void startPromotion() {
        validate();
        promotionService = new PromotionService(date, orderMenus, totalOrderPrice);
        promotionService.startPromotion();
    }

    private void validate() {
        if (!totalOrderPrice.isApplicableEvent()) {
            throw new IllegalArgumentException(NoticeMessage.NOT_APPLICABLE_EVENT.getNoticeMessage());
        }
    }

    public ResultDto createEventResultDto(boolean isApplicableEvent) {
        PromotionDto promotionDto = null;
        if (isApplicableEvent) {
            promotionDto = promotionService.createPromotionDto();
        }
        List<OrderMenuDto> orderMenuDtos = createOrderMenuDtos();
        return new ResultDto(date.getDate(), orderMenuDtos, totalOrderPrice.getTotalOrderPrice(), promotionDto);
    }

    private List<OrderMenuDto> createOrderMenuDtos() {
        return orderMenus.getOrderMenus().stream()
                .map(orderMenu -> new OrderMenuDto(orderMenu.getMenu().getMenuName(), orderMenu.getQuantity()))
                .toList();
    }
}
