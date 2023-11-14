package christmas.controller;

import christmas.service.OrderService;
import christmas.validate.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator inputValidator;
    private final OrderService orderService;

    public Controller(InputView inputView, OutputView outputView, InputValidator inputValidator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputValidator = inputValidator;
        this.orderService = new OrderService();
    }

    public void run() {
        outputView.printStartMessage();
        generateDate();
        generateOrder();
        try {
            orderService.startPromotion();
            outputView.printOrderResult(orderService.createEventResultDto(true));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            outputView.printOrderResult(orderService.createEventResultDto(false));
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
            orderService.initiateDate(date);
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
            orderService.initiateOrder(orderNameAndCount);
            return false;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return true;
        }
    }
}
