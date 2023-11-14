package christmas.view;

import christmas.dto.*;

public class OutputView {

    private static final String BLANK_LINE = "";
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
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printOrderResult(ResultDto resultDto) {
        System.out.println(resultMessage.getResultStartMessage(resultDto));
        System.out.println(BLANK_LINE);

        printOrder(resultDto);
        printBenefit(resultDto);
        printPayment(resultDto);
        printBadge(resultDto);
    }

    private void printOrder(ResultDto resultDto) {
        System.out.println("<주문 메뉴>");
        System.out.println(resultMessage.getOrderMenusMessage(resultDto));
        System.out.println(BLANK_LINE);

        System.out.println("<할인 전 총주문 금액>");
        System.out.println(resultMessage.getTotalOrderPrice(resultDto));
        System.out.println(BLANK_LINE);
    }

    private void printBenefit(ResultDto resultDto) {
        System.out.println("<증정 메뉴>");
        System.out.println(resultMessage.getGiftMessage(resultDto));
        System.out.println(BLANK_LINE);

        System.out.println("<혜택 내역>");
        System.out.println(resultMessage.getBenefits(resultDto));
        System.out.println(BLANK_LINE);

        System.out.println("<총혜택 금액>");
        System.out.println(resultMessage.getTotalBenefitAmount(resultDto));
        System.out.println(BLANK_LINE);
    }

    private void printPayment(ResultDto resultDto) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(resultMessage.getPayment(resultDto));
        System.out.println(BLANK_LINE);
    }

    private void printBadge(ResultDto resultDto) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(resultMessage.getBadge(resultDto));
    }
}
