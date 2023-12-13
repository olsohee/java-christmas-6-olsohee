package christmas.message;

public enum OutputMessage {

    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ORDER_MENU("<주문 메뉴>"),
    ORDER_MENU_INFO("%s %d개")
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
