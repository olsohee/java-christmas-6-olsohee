package christmas.domain;

import christmas.message.ErrorMessage;

import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP(Category.APPETIZER, "양송이수프", 6000),
    TAPAS(Category.APPETIZER, "타파스", 5000),
    CAESAR_SALAD(Category.APPETIZER, "시저샐러드", 8000),
    T_BORN_STAKE(Category.MAIN, "티본스테이크", 55000),
    BBQ_RIB(Category.MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(Category.MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(Category.MAIN, "크리스마스파스타", 25000),
    CHOCO_CAKE(Category.DESSERT, "초코케이크", 15000),
    ICE_CREAM(Category.DESSERT, "아이스크림", 5000),
    ZERO_COKE(Category.DRINK, "제로콜라", 3000),
    RED_WINE(Category.DRINK, "레드와인", 60000),
    CHAMPAGNE(Category.DRINK, "샴페인", 25000)
    ;

    private final Category category;
    private final String name;
    private final int price;

    Menu(Category category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Menu findMenuByName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }

    public String getName() {
        return name;
    }

    public boolean isDrink() {
        return this.category.equals(Category.DRINK);
    }
}
