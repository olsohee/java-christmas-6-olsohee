package christmas.message;

public enum ErrorMessage {

    INVALID_DATE_INPUT("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_INPUT( "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ;
    private String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
