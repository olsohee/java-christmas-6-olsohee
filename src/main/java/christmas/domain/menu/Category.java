package christmas.domain.menu;

import java.util.List;

public enum Category {

    APPETIZER(List.of(Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN(List.of(Menu.T_BORN_STAKE, Menu.BARBECUE_RIP, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESERT(List.of(Menu.CHOCO_CAKE, Menu.ICE_CREAM)),
    DRINK(List.of(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE));

    private final List<Menu> menus;

    Category(List<Menu> menus) {
        this.menus = menus;
    }

    public boolean isContain(Menu menu) {
        return this.menus.contains(menu);
    }
}
