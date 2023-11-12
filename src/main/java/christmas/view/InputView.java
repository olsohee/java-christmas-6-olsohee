package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public interface InputView {

    void printInputStartMessage();

    String readDate();

    String readOrder();

    default void printMessage(String message) {
        System.out.println(message);
    }

    default String readInput() {
        return Console.readLine();
    }
}
