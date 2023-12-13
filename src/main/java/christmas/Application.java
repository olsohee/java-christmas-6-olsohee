package christmas;

import christmas.controller.PromotionController;
import christmas.convertor.InputConvertor;
import christmas.service.PromotionService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {

        PromotionController promotionController = new PromotionController(InputView.getInstance(), InputConvertor.getInstance(),
                OutputView.getInstance(), new PromotionService());
        promotionController.run();
    }
}
