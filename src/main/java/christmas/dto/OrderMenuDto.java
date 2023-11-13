package christmas.dto;

public class OrderMenuDto {

    private final String menuName;
    private final int quantity;

    public OrderMenuDto(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}
