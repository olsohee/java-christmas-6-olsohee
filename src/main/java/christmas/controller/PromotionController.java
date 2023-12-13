package christmas.controller;

import christmas.convertor.InputConvertor;
import christmas.service.PromotionService;
import christmas.view.InputView;
import christmas.view.OutputView;

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
}
