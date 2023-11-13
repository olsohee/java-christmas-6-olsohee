package christmas.domain.menu;

import christmas.message.ErrorMessage;

import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", Category.APPETIZER, 6000),
    TAPAS("타파스", Category.APPETIZER, 5500),
    CAESAR_SALAD("시저샐러드", Category.APPETIZER, 8000),
    T_BORN_STAKE("티본스테이크", Category.MAIN, 55000),
    BARBECUE_RIP("바비큐립", Category.MAIN, 54000),
    SEAFOOD_PASTA("해산물파스타", Category.MAIN, 35000),
    CHRISTMAS_PASTA("크리스마스파스타", Category.MAIN, 25000),
    CHOCO_CAKE("초코케이크", Category.DESERT, 15000),
    ICE_CREAM("아이스크림", Category.DESERT, 5000),
    ZERO_COKE("제로콜라", Category.DRINT, 3000),
    RED_WINE("레드와인", Category.DRINT, 60000),
    CHAMPAGNE("샴페인", Category.DRINT, 25000)
    ;

    private final String menuName;
    private final Category category;
    private final int price;

    Menu(String menuName, Category category, int price) {
        this.menuName = menuName;
        this.category = category;
        this.price = price;
    }

    public static Menu from(String menuName) {
        return Arrays.stream(values())
                .filter(menu -> menu.menuName.equals(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getErrorMessage()));
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
