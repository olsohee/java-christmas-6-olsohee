package christmas.validate;

import christmas.message.ErrorMessage;

import java.util.*;
import java.util.stream.Collectors;

public class InputValidator {

    private InputValidator() {
    }

    private static class InputValidatorHolder {
        private static InputValidator inputValidator = new InputValidator();
    }

    public static InputValidator getInstance() {
        return InputValidatorHolder.inputValidator;
    }

    public int convertDateInputToInt(String dateInput) {
        try {
            return Integer.parseInt(dateInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_INPUT.getErrorMessage());
        }
    }

    public Map<String, Integer> convertOrderInputToMap(String orderInput) {
        validateDuplicateMenu(orderInput);
        return Arrays.stream(orderInput.split(","))
                .map(order -> order.split("-", 2))
                .collect(Collectors.toMap(orderSplit -> orderSplit[0].trim(),
                        orderSplit -> convertOrderCountInputToInt(orderSplit[1].trim())));
    }

    private void validateDuplicateMenu(String orderInput) {
        List<String> orderMenus = Arrays.stream(orderInput.split(","))
                .map(order -> order.split("-")[0].trim())
                .toList();

        int count = (int) orderMenus.stream()
                .distinct()
                .count();

        if(count != orderMenus.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getErrorMessage());
        }
    }

    private Integer convertOrderCountInputToInt(String orderCountInput) {
        try {
            return Integer.parseInt(orderCountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getErrorMessage());
        }
    }
}
