package christmas.view;

import christmas.dto.ResultDto;

import java.text.DecimalFormat;
import java.util.List;

public class ResultMessage {

    private static final String START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_AND_COUNT = "%s %s개";
    private static final String PRICE = "%s원";
    private static final String BENEFITS = "%s: -%s원";
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

    public String getResultStartMessage(ResultDto resultDto) {
        return String.format(START_MESSAGE, resultDto.date());
    }

    public String getOrderMenusMessage(ResultDto resultDto) {
        List<String> orderMenus = resultDto.orderMenuDtos().stream()
                .map(orderMenu -> String.format(ORDER_MENU_AND_COUNT, orderMenu.menuName(), orderMenu.quantity()))
                .toList();

        return String.join(NEW_LINE , orderMenus);
    }

    public String getTotalOrderPrice(ResultDto resultDto) {
        return String.format(PRICE, decimalFormat.format(resultDto.totalOrderPrice()));
    }

    public String getGiftMessage(ResultDto resultDto) {
        if(resultDto.hasGift()) {
            return "샴페인 1개";
        }
        return NONE;
    }

    public String getBenefits(ResultDto resultDto) {
        if(resultDto.eventDtos() == null) {
            return NONE;
        }
        List<String> benefits = resultDto.eventDtos().stream()
                .map(benefit -> String.format(BENEFITS, benefit.eventName(), decimalFormat.format(benefit.benefitAmount())))
                .toList();
        return String.join(NEW_LINE, benefits);
    }

    public String getTotalBenefitAmount(ResultDto resultDto) {
        if(resultDto.totalBenefitAmount() == null) {
            return NONE;
        }
        return String.format(TOTAL_BENEFIT_AMOUNT, decimalFormat.format(resultDto.totalBenefitAmount()));
    }

    public String getPayment(ResultDto resultDto) {
        return String.format(PRICE, decimalFormat.format(resultDto.payment()));
    }

    public String getBadge(ResultDto resultDto) {
        return resultDto.badgeName();
    }
}
