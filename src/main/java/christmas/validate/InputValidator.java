package christmas.validate;

import christmas.message.ErrorMessage;

import java.util.*;
import java.util.regex.Pattern;
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

        try {
            Map<String, Integer> orderMenuAndCount = Pattern.compile(",")
                    .splitAsStream(orderInput.trim())
                    .map(order -> order.split("-", 2))
                    .collect(Collectors.toMap(orderSplit -> orderSplit[0].trim(), orderSplit -> Integer.parseInt(orderSplit[1].trim())));
            return orderMenuAndCount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getErrorMessage());
        }
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
}
