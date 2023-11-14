package christmas.view;

import christmas.dto.*;

public class OutputView {

    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String BLANK_LINE = "";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String TOTAL_ORDER_PRICE = "<할인 전 총주문 금액>";
    private static final String GIFT = "<증정 메뉴>";
    private static final String BENEFITS = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT = "<총혜택 금액>";
    private static final String PAYMENT = "<할인 후 예상 결제 금액>";
    private static final String BADGE = "<12월 이벤트 배지>";

    private final ResultMessage resultMessage;

    private OutputView(ResultMessage resultMessage) {
        this.resultMessage = resultMessage;
    }

    private static class ConsoleOutputViewHolder {
        private static OutputView outputView = new OutputView(ResultMessage.getInstance());
    }

    public static OutputView getInstance() {
        return ConsoleOutputViewHolder.outputView;
    }

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printOrderResult(ResultDto resultDto) {
        printResultStartMessage(resultDto);
        printOrder(resultDto);
        printBenefit(resultDto);
        printPayment(resultDto);
        printBadge(resultDto);
    }

    private void printResultStartMessage(ResultDto resultDto) {
        System.out.println(resultMessage.getResultStartMessage(resultDto.date()));
        System.out.println(BLANK_LINE);
    }

    private void printOrder(ResultDto resultDto) {
        System.out.println(ORDER_MENU);
        System.out.println(resultMessage.getOrderMenusMessage(resultDto.orderMenuDtos()));
        System.out.println(BLANK_LINE);

        System.out.println(TOTAL_ORDER_PRICE);
        System.out.println(resultMessage.getTotalOrderPrice(resultDto.totalOrderPrice()));
        System.out.println(BLANK_LINE);
    }

    private void printBenefit(ResultDto resultDto) {
        System.out.println(GIFT);
        System.out.println(resultMessage.getGiftMessage(resultDto.promotionDto()));
        System.out.println(BLANK_LINE);

        System.out.println(BENEFITS);
        System.out.println(resultMessage.getBenefits(resultDto.promotionDto()));
        System.out.println(BLANK_LINE);

        System.out.println(TOTAL_BENEFIT_AMOUNT);
        System.out.println(resultMessage.getTotalBenefitAmount(resultDto.promotionDto()));
        System.out.println(BLANK_LINE);
    }

    private void printPayment(ResultDto resultDto) {
        System.out.println(PAYMENT);
        System.out.println(resultMessage.getPayment(resultDto));
        System.out.println(BLANK_LINE);
    }

    private void printBadge(ResultDto resultDto) {
        System.out.println(BADGE);
        System.out.println(resultMessage.getBadge(resultDto.promotionDto()));
    }
}
