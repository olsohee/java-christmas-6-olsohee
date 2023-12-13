package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String READ_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String READ_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private InputView() {
    }

    private static class InputViewHolder {
        private static InputView inputView = new InputView();
    }

    public static InputView getInstance() {
        return InputViewHolder.inputView;
    }

    public String readDate() {
        System.out.println(READ_DATE);
        return Console.readLine();
    }

    public String readMenu() {
        System.out.println(READ_MENU);
        return Console.readLine();
    }
}
