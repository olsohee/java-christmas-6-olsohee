package christmas.controller;

import christmas.dto.benefitDto.BenefitsDto;
import christmas.service.PromotionService;
import christmas.validate.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class PromotionController {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator inputValidator;
    private final PromotionService promotionService;

    public PromotionController(InputView inputView, OutputView outputView, InputValidator inputValidator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputValidator = inputValidator;
        this.promotionService = new PromotionService();
    }

    public void start() {
        readUserInput();

        try {
            validateEventApplicability();
            applyPromotion();
            printEventOrderResult();
        } catch (IllegalArgumentException e) {
            printNonEventOrderResult();
        }
    }

    private void readUserInput() {
        inputView.printInputStartMessage();
        readDate();
        readOrder();
    }

    private void readDate() {
        int date = inputValidator.convertDateInputToInt(inputView.readDate());
        promotionService.createDate(date);
    }

    private void readOrder() {
        Map<String, Integer> orderMenuAndCount = inputValidator.convertOrderInputToMap(inputView.readOrder());
        promotionService.createOrder(orderMenuAndCount);
    }

    private void validateEventApplicability() {
        promotionService.validateEventApplicability();
    }

    public void applyPromotion() {
        promotionService.applyPromotion();
    }

    public void printEventOrderResult() {
        outputView.printOutputStartMessage(promotionService.createDateDto());
        outputView.printOrder(promotionService.createOrderDto());
        outputView.printBenefits(promotionService.createBenefitsDto());
        outputView.printPayment(promotionService.createPaymentDto());
        outputView.printBadge(promotionService.createBadgeDto());
    }

    public void printNonEventOrderResult() {
        outputView.printNonEventOrderResult(promotionService.createDateDto(),
                promotionService.createOrderDto());
    }
}
