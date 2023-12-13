package christmas.dto;

public class OrderMenuDto {

    private final String menuName;
    private final int count;

    public OrderMenuDto(String menuName, int count) {
        this.menuName = menuName;
        this.count = count;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }
}
