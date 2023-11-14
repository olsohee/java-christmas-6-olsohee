package christmas.view;

import christmas.dto.OrderMenuDto;
import christmas.dto.PromotionDto;
import christmas.dto.ResultDto;

import java.text.DecimalFormat;
import java.util.List;

public class ResultMessage {

    private static final String START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU = "%s %s개";
    private static final String PRICE = "%s원";
    private static final String GIFT = "샴페인 1개";
    private static final String BENEFIT = "%s: -%s원";
    private static final String TOTAL_BENEFIT_AMOUNT = "-%s원";
    private static final String NEW_LINE = "\n";
    private static final String NONE = "없음";
    private DecimalFormat decimalFormat = new DecimalFormat("###,###");

    private ResultMessage() {
    }

    private static class ResultMessageHolder {
        private static ResultMessage resultMessage = new ResultMessage();
    }

    public static ResultMessage getInstance() {
        return ResultMessageHolder.resultMessage;
    }

    public String getResultStartMessage(int date) {
        return String.format(START_MESSAGE, date);
    }

    public String getOrderMenusMessage(List<OrderMenuDto> orderMenuDtos) {
        List<String> orderMenus = orderMenuDtos.stream()
                .map(orderMenu -> String.format(ORDER_MENU, orderMenu.menuName(), orderMenu.quantity()))
                .toList();

        return String.join(NEW_LINE , orderMenus);
    }

    public String getTotalOrderPrice(int totalOrderPrice) {
        return String.format(PRICE, decimalFormat.format(totalOrderPrice));
    }

    public String getGiftMessage(PromotionDto promotionDto) {
        if(promotionDto == null) {
            return NONE;
        }
        if(promotionDto.hasGift()) {
            return GIFT;
        }
        return NONE;
    }

    public String getBenefits(PromotionDto promotionDto) {
        if(promotionDto == null) {
            return NONE;
        }
        List<String> benefits = promotionDto.eventDtos().stream()
                .map(benefit -> String.format(BENEFIT, benefit.eventName(), decimalFormat.format(benefit.benefitAmount())))
                .toList();
        return String.join(NEW_LINE, benefits);
    }

    public String getTotalBenefitAmount(PromotionDto promotionDto) {
        if(promotionDto == null) {
            return NONE;
        }
        return String.format(TOTAL_BENEFIT_AMOUNT, decimalFormat.format(promotionDto.totalBenefitAmount()));
    }

    public String getPayment(ResultDto resultDto) {
        if(resultDto.promotionDto() == null) {
            return String.format(PRICE, decimalFormat.format(resultDto.totalOrderPrice()));
        }
        return String.format(PRICE, decimalFormat.format(resultDto.promotionDto().payment()));
    }

    public String getBadge(PromotionDto promotionDto) {
        if(promotionDto == null) {
            return NONE;
        }
        return promotionDto.badgeName();
    }
}
