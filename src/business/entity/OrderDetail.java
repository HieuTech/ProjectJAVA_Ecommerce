package business.entity;

import java.io.Serializable;
import java.util.Scanner;

public class OrderDetail implements Serializable {
    private Long productId;
    private String name;
    private double unitPrice;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(Long productId, String name, double unitPrice, int quantity) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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

        System.out.println("Nhập tên sản phẩm: ");
        this.name = scanner.nextLine();

        System.out.println("Nhập đơn giá: ");
        this.unitPrice = Double.parseDouble(scanner.nextLine());

        System.out.println("Nhập số lượng: ");
        this.quantity = Integer.parseInt(scanner.nextLine());
    }

    public void displayData() {
        System.out.printf("| ID sản phẩm: %d | Tên sản phẩm: %s | Đơn giá: %.2f | Số lượng: %d",
                productId, name, unitPrice, quantity);
    }

    public double calculatePrice() {
        return unitPrice * quantity;
    }
}