package christmas.view;

import christmas.dto.EventBenefitDto;
import christmas.dto.NonPromotionDto;
import christmas.dto.OrderMenuDto;
import christmas.message.OutputMessage;

import java.util.List;

public class OutputView {

    private OutputView() {
    }

    private static class OutputViewHolder {
        private static OutputView outputView = new OutputView();
    }

    public static OutputView getInstance() {
        return OutputViewHolder.outputView;
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printStartMessage() {
        System.out.println(OutputMessage.START_MESSAGE.getMessage());
    }

    public void printOrderMenu(List<OrderMenuDto> orderMenuDtos) {
        System.out.println(OutputMessage.ORDER_MENU.getMessage());
        orderMenuDtos.stream()
                .forEach(dto -> System.out.println(String.format(OutputMessage.ORDER_MENU_INFO.getMessage(),
                        dto.getMenuName(), dto.getCount())));
        System.out.println();
    }

    public void printNonPromotion(NonPromotionDto nonPromotionDto) {
        System.out.println(OutputMessage.TOTAL_PRICE.getMessage());
        System.out.println(String.format(OutputMessage.PRICE.getMessage(), nonPromotionDto.getTotalPrice()) + '\n');

        System.out.println(OutputMessage.GIFT.getMessage());
        System.out.println(OutputMessage.NONE.getMessage() + '\n');

        System.out.println(OutputMessage.BENEFIT_INFO.getMessage());
        System.out.println(OutputMessage.NONE.getMessage() + '\n');

        System.out.println(OutputMessage.TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.println(String.format(OutputMessage.PRICE.getMessage(), 0) + '\n');

        System.out.println(OutputMessage.PAYMENT_PRICE.getMessage());
        System.out.println(String.format(OutputMessage.PRICE.getMessage(), nonPromotionDto.getTotalPrice()) + '\n');

        System.out.println(OutputMessage.BADGE.getMessage());
        System.out.println(OutputMessage.NONE.getMessage() + '\n');
    }

    public void printEventBenefit(EventBenefitDto eventBenefitDto) {
        System.out.println(OutputMessage.TOTAL_PRICE.getMessage());
        System.out.println(String.format(OutputMessage.PRICE.getMessage(), eventBenefitDto.getTotalPrice()));
        System.out.println();

        System.out.println(OutputMessage.GIFT.getMessage());
        if (eventBenefitDto.hasGift()) {
            System.out.println(String.format(OutputMessage.ORDER_MENU_INFO.getMessage(), "샴페인", 1));
        } else {
            System.out.println("없음");
        }
        System.out.println();

        System.out.println(OutputMessage.BENEFIT_INFO.getMessage());
        eventBenefitDto.getBenefitDtos().stream()
                .forEach(dto -> System.out.println(
                        String.format(OutputMessage.BENEFIT.getMessage(), dto.getName(), dto.getAmount())));
        System.out.println();

       int totalBenefitAmount = eventBenefitDto.getBenefitDtos().stream()
                .mapToInt(dto -> dto.getAmount())
                .sum();
        System.out.println(OutputMessage.TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.println("-" + String.format(OutputMessage.PRICE.getMessage(), totalBenefitAmount));
        System.out.println();

        System.out.println(OutputMessage.PAYMENT_PRICE.getMessage());
        int paymentAmount = eventBenefitDto.getTotalPrice() - totalBenefitAmount;
        if (eventBenefitDto.hasGift()) {
            paymentAmount += 25000;
        }
        System.out.println(String.format(OutputMessage.PRICE.getMessage(), paymentAmount));
        System.out.println();

        System.out.println(OutputMessage.BADGE.getMessage());
        System.out.println(eventBenefitDto.getBadge());
    }
}
