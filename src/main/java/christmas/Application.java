package christmas;

import christmas.controller.PromotionController;
import christmas.validate.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PromotionController promotionController = new PromotionController(InputView.getInstance(), OutputView.getInstance(), InputValidator.getInstance());
        promotionController.run();
    }
}
