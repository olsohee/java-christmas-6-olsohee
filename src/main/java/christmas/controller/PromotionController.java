package christmas.controller;

import christmas.convertor.InputConvertor;
import christmas.domain.Menu;
import christmas.service.PromotionService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class PromotionController {

    private final InputView inputView;
    private final InputConvertor inputConvertor;
    private final OutputView outputView;
    private final PromotionService promotionService;

    public PromotionController(InputView inputView, InputConvertor inputConvertor, OutputView outputView, PromotionService promotionService) {
        this.inputView = inputView;
        this.inputConvertor = inputConvertor;
        this.outputView = outputView;
        this.promotionService = promotionService;
    }

    public void run() {
        outputView.printStartMessage();
        readDate();
        readMenu();
    }

    private void readDate() {
        try {
            int date = inputConvertor.convertStringToInt(inputView.readDate());
            promotionService.createDate(date);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            readDate();
        }
    }

    private void readMenu() {
        try {
            Map<Menu, Integer> orderMenus = inputConvertor.convertStringToMenu(inputView.readMenu());
            promotionService.validateOrder(orderMenus);
            promotionService.createOrder(orderMenus);
            promotionService.startPromotion();
            promotionService.getDto();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            readMenu();
        }

    }

}
