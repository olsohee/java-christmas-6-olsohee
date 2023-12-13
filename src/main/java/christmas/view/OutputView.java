package christmas.view;

import christmas.dto.OrderMenuDto;
import christmas.message.OutputMessage;

import java.util.List;

public class OutputView {

    private OutputView() {
    }

    private static class OutputViewHolder {
        private static OutputView outputView = new OutputView();
    }

    public static OutputView getInstance() {
        return OutputViewHolder.outputView;
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printStartMessage() {
        System.out.println(OutputMessage.START_MESSAGE.getMessage());
    }

    public void printOrderMenu(List<OrderMenuDto> orderMenuDtos) {
        System.out.println(OutputMessage.ORDER_MENU.getMessage());
        orderMenuDtos.stream()
                .forEach(dto -> System.out.println(String.format(OutputMessage.ORDER_MENU_INFO.getMessage(),
                        dto.getMenuName(), dto.getCount())));
        System.out.println();
    }
}
