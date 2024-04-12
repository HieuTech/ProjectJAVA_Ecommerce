package business.entity;

import java.io.Serializable;
import java.util.Scanner;

public class CartItem implements Serializable {
    private Long productId;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID sản phẩm: ");
        this.productId = Long.parseLong(scanner.nextLine());

        System.out.println("Nhập số lượng: ");
        this.quantity = Integer.parseInt(scanner.nextLine());
    }

    public void displayData() {
        System.out.printf("| ID sản phẩm: %d | Số lượng: %d", productId, quantity);
    }
}