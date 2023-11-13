package christmas.view;

import christmas.dto.BadgeDto;
import christmas.dto.DateDto;
import christmas.dto.PaymentDto;
import christmas.dto.benefitDto.BenefitsDto;
import christmas.dto.orderMenuDto.OrderDto;

public class ConsoleOutputView implements OutputView {

    private final OutputMessage outputMessage;

    private ConsoleOutputView(OutputMessage outputMessage) {
        this.outputMessage = outputMessage;
    }

    private static class ConsoleOutputViewHolder {
        private static ConsoleOutputView consoleOutputView = new ConsoleOutputView(OutputMessage.getInstance());
    }

    public static ConsoleOutputView getInstance() {
        return ConsoleOutputViewHolder.consoleOutputView;
    }

    @Override
    public void printErrorMessage(String errorMessage) {
        printMessage(errorMessage);
    }

    @Override
    public void printEventOrderResult(DateDto dateDto, OrderDto orderDto, BenefitsDto benefitsDto, PaymentDto paymentDto, BadgeDto badgeDto) {
        printOutputStartMessage(dateDto);
        printOrder(orderDto);
        printBenefits(benefitsDto);
        printPayment(paymentDto);
        printBadge(badgeDto);
    }

    @Override
    public void printNonEventOrderResult(DateDto dateDto, OrderDto orderDto) {
        printOutputStartMessage(dateDto);
        printOrder(orderDto);
        printNonEvent(dateDto, orderDto);
    }

    private void printNonEvent(DateDto dateDto, OrderDto orderDto) {
        printMessage("<증정 메뉴>");
        printMessage("없음");
        printNewLine();

        printMessage("<혜택 내역>");
        printMessage("없음");
        printNewLine();

        printMessage("<총혜택 금액>");
        printMessage("0원");
        printNewLine();

        printMessage("<할인 후 예상 결제 금액>");
        printMessage(orderDto.getTotalOrderPrice() + "원");
        printNewLine();

        printMessage("<12월 이벤트 배지>");
        printMessage("없음");
    }

    @Override
    public void printOutputStartMessage(DateDto dateDto) {
        printMessage(outputMessage.getStartMessage(dateDto.getDate()));
        printNewLine();
    }

    @Override
    public void printOrder(OrderDto orderDto) {
        printMessage("<주문 메뉴>");
        orderDto.getOrderMenuDtos().stream()
                .forEach(orderMenuDto -> printMessage(
                        outputMessage.getOrderMenusMessage(orderMenuDto.getMenuName(), orderMenuDto.getQuantity())));
        printNewLine();

        printMessage("<할인 전 총주문 금액>");
        printMessage(outputMessage.getTotalOrderPriceMessage(orderDto.getTotalOrderPrice()));
        printNewLine();
    }

    @Override
    public void printBenefits(BenefitsDto benefitsDto) {
        printMessage("<증정 메뉴>");
        printMessage(outputMessage.getGiftMessage(benefitsDto.getGift()));
        printNewLine();

        printMessage("<혜택 내역>");
        benefitsDto.getBenefitDtos().stream()
                .forEach(benefitDto -> printMessage(
                        outputMessage.getBenefitsMessage(benefitDto.getEventName(), benefitDto.getBenefitAmount())));
        printNewLine();

        printMessage("<총혜택 금액>");
        printMessage(outputMessage.getTotalBenefitAmount(benefitsDto.getTotalBenefitAmount()));
        printNewLine();
    }

    @Override
    public void printPayment(PaymentDto paymentDto) {

        printMessage("<할인 후 예상 결제 금액>");
        printMessage(outputMessage.getPaymentMessage(paymentDto.getPaymentAmount()));
        printNewLine();
    }

    @Override
    public void printBadge(BadgeDto badgeDto) {
        printMessage("<12월 이벤트 배지>");
        printMessage(badgeDto.getBadgeName());
    }
}
