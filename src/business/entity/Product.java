package business.entity;

import business.implement.ProductImplement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private String categoryId;
    private String description;
    private double unitPrice;
    private int stock;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate updatedAt;
    private boolean status = true;

    public Product() {
    }

    public Product(int productId, String productName, String categoryId, String description, double unitPrice, int stock, LocalDate updatedAt, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên sản phẩm: ");
        this.productName = scanner.nextLine();
        if (productName.isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống");
        }

        System.out.println("Nhập phân loại sản phẩm: ");
        this.categoryId = scanner.nextLine();

        System.out.println("Nhập mô tả/thông số: ");
        this.description = scanner.nextLine();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Mô tả/thông số không được để trống");
        }

        System.out.println("Nhập đơn giá: ");
        this.unitPrice = Double.parseDouble(scanner.nextLine());
        if (unitPrice <= 0) {
            throw new IllegalArgumentException("Đơn giá phải lớn hơn 0");
        }

        System.out.println("Nhập số lượng trong kho: ");
        this.stock = Integer.parseInt(scanner.nextLine());
        if (stock <= 0) {
            throw new IllegalArgumentException("Số lượng trong kho phải lớn hơn 0");
        }


        this.updatedAt = LocalDate.now();


    }

    public void displayData() {
        System.out.printf("| Mã sản phẩm: %d | Tên sản phẩm: %s | Phân loại sản phẩm: %s | Mô tả/thông số: %s | Đơn giá: %.2f | Số lượng trong kho: %d | Thời gian được tạo: %s | Thời gian cập nhật gần nhất: %s | Trạng thái: %s",
                productId, productName, categoryId, description, unitPrice, stock, createdAt, updatedAt, status ? "Hoạt động" : "Không hoạt động");
    }

    public void displayCartItem(){
        System.out.printf( "| Tên sản phẩm: %s | Mô tả/thông số: %s | Đơn giá: %.2f ",
                productName, description, unitPrice );
    }

    public int getSoldCount() {
        return 0;
    }
}