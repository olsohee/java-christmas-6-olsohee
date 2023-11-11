package christmas.view;

public class ConsoleInputView implements InputView {

    private ConsoleInputView() {
    }

    private static class InputViewHolder {
        private static ConsoleInputView consoleInputView = new ConsoleInputView();
    }

    public static ConsoleInputView getInstance() {
        return InputViewHolder.consoleInputView;
    }

    @Override
    public void printStartMessage() {
        printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public String readDate() {
        printMessage("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return readOrder();
    }

    @Override
    public String readOrder() {
        printMessage("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return readOrder();
    }
}