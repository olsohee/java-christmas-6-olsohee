package christmas.view;

import java.text.DecimalFormat;

public class OutputMessage {

    private static final String START_MESSGAE = "12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "%s %s개";
    private static final String BENEFIT_MESSAGE = "%s: -%s원";
    private static final String PRICE = "%s원";
    private static final String BENEFIT_AMOUNT = "-%s원";
    private static DecimalFormat decimalFormat = new DecimalFormat("###,###");

    private OutputMessage() {
    }

    private static class OutputMessageHolder {
        private static OutputMessage outputMessage = new OutputMessage();
    }

    public static OutputMessage getInstance() {
        return OutputMessageHolder.outputMessage;
    }

    public String getStartMessage(int date) {
        return String.format(START_MESSGAE, date);
    }

    public String getOrderMenusMessage(String menuName, int quantity) {
        return String.format(ORDER_MENU_MESSAGE, menuName, quantity);
    }

    public String getTotalOrderPriceMessage(int price) {
        return String.format(PRICE, decimalFormat.format(price));
    }

    public String getGiftMessage(boolean gift) {
        if (!gift) {
            return "없음";
        }
        return "샴페인 1개";
    }

    public String getBenefitsMessage(String eventName, int benefitAmount) {
        return String.format(BENEFIT_MESSAGE, eventName, decimalFormat.format(benefitAmount));
    }

    public String getTotalBenefitAmount(int totalBenefitAmount) {
        return String.format(BENEFIT_AMOUNT, decimalFormat.format(totalBenefitAmount));
    }

    public String getPaymentMessage(int paymentAmount) {
        return String.format(PRICE, decimalFormat.format(paymentAmount));
    }
}
