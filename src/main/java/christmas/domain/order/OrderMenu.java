package christmas.domain.order;

import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.message.ErrorMessage;

public class OrderMenu {

    private final Menu menu;
    private final int quantity;

    public OrderMenu(String menuName, int quantity) {
        this.menu = Menu.from(menuName);
        validate(quantity);
        this.quantity = quantity;
    }

    private void validate(int quantity) {
        if(quantity < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT.getErrorMessage());
        }
    }

    public int calculateOrderPrice() {
        return menu.getPrice() * quantity;
    }

    public boolean checkCategory(Category category) {
        return category.isContain(menu);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
