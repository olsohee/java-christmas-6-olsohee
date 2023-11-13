package christmas.domain;

import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;

public class OrderMenu {

    private Menu menu;
    private int quantity;

    public OrderMenu(String menuName, int quantity) {
        this.menu = Menu.from(menuName);
        this.quantity = quantity;
    }

    public int calculateOrderPrice() {
        return menu.getPrice() * quantity;
    }

    public boolean isDrinkMenu() {
        return menu.getCategory().equals(Category.DRINK);
    }

    public boolean isDessertMenu() {
        return menu.getCategory().equals(Category.DESERT);
    }

    public boolean isMainMenu() {
        return menu.getCategory().equals(Category.MAIN);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
