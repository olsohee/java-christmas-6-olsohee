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
        readUserInput();
        try {
            promotionService.validateEventApplicability();
            applyPromotion();
            outputView.printOrderResult(promotionService.createEventResultDto());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            outputView.printOrderResult(promotionService.createNonEventResultDto());
        }
    }

    private void readUserInput() {
        boolean flag;
        do {
            flag = readDate();
        } while (!flag);

        do {
            readOrder();
        } while (!flag);
    }

    private boolean readDate() {
        try {
            int date = inputValidator.convertDateInputToInt(inputView.readDate());
            promotionService.createDate(date);
            return true;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return false;
        }
    }

    private boolean readOrder() {
        try {
            Map<String, Integer> orderMenuAndCount = inputValidator.convertOrderInputToMap(inputView.readOrder());
            promotionService.createOrder(orderMenuAndCount);
            return true;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return false;
        }
    }

    public void applyPromotion() {
        promotionService.applyPromotion();
    }
}
