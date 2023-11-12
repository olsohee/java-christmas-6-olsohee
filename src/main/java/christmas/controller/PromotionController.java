package christmas.controller;

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

    public void readUserInput() {
        inputView.printStartMessage();
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

    public void applyPromotion() {
        promotionService.applyPromotion();
    }

    public void printOrderResult() {
        outputView.printStartMessage(promotionService.createDateDto());
        outputView.printOrder(promotionService.createOrderDto());
        outputView.printBenefits(promotionService.createBenefitsDto());
        outputView.printPayment(promotionService.createPaymentDto());
        outputView.printBadge(promotionService.createBadgeDto());
    }
}
