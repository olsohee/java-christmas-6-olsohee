package christmas.message;

public enum ErrorMessage {

    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String ERROR_HEADER = "[ERROR]";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return String.format("%s %s", ERROR_HEADER, errorMessage);
    }
}
