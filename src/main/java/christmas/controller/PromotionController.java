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

    public void run() {
        outputView.printStartMessage();
        generateDate();
        generateOrder();
        try {
            promotionService.validateEventApplicability();
            promotionService.applyPromotion();
            outputView.printOrderResult(promotionService.createEventResultDto());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            outputView.printOrderResult(promotionService.createNonEventResultDto());
        }
    }

    private void generateDate() {
        boolean containException;
        do {
            containException = readDate();
        } while (containException);
    }

    private boolean readDate() {
        try {
            int date = inputValidator.convertDateInputToInt(inputView.readDate());
            promotionService.initiateDate(date);
            return false;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return true;
        }
    }

    private void generateOrder() {
        boolean containException;
        do {
            containException = readOrder();
        } while (containException);
    }

    private boolean readOrder() {
        try {
            Map<String, Integer> orderNameAndCount = inputValidator.convertOrderInputToMap(inputView.readOrder());
            promotionService.initiateOrder(orderNameAndCount);
            return false;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return true;
        }
    }
}
