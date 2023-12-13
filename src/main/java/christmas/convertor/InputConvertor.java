package christmas.convertor;

import christmas.domain.Menu;
import christmas.message.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

public class InputConvertor {

    private InputConvertor() {
    }

    private static class InputConvertorHolder {
        private static InputConvertor inputConvertor = new InputConvertor();
    }

    public static InputConvertor getInstance() {
        return InputConvertorHolder.inputConvertor;
    }

    public int convertStringToInt(String input) {
        try {
           return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getErrorMessage());
        }
    }

    public  Map<Menu, Integer> convertStringToMenu(String input) {
        Map<Menu, Integer> orderMenus = new HashMap<>();

        String[] inputSplit = input.split(",");
        for (String str : inputSplit) {
            String name = str.split("-")[0].trim();
            Menu menu = Menu.findMenuByName(name);
            int count = convertStringToInt(str.split("-")[1].trim());
            validateDuplicated(orderMenus, menu);
            validateCount(count);
            orderMenus.put(menu, count);
        }
        return orderMenus;
    }

    private void validateCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }

    private void validateDuplicated(Map<Menu, Integer> order, Menu menu) {
        if (order.containsKey(menu)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }
}
