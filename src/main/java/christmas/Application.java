package christmas;

import christmas.controller.PromotionController;
import christmas.validate.InputValidator;
import christmas.view.ConsoleInputView;
import christmas.view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        PromotionController promotionController = new PromotionController(ConsoleInputView.getInstance(), ConsoleOutputView.getInstance(), InputValidator.getInstance());
        promotionController.readUserInput();
        promotionController.applyPromotion();
        promotionController.printOrderResult();
    }
}
