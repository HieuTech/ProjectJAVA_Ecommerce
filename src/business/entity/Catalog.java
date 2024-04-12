package business.entity;

import java.io.Serializable;
import java.util.Scanner;

public class Catalog implements Serializable {
    private String catalogId;
    private String catalogName;
    private String description;
    private boolean status = true;

    public Catalog() {
    }

    public Catalog(String catalogId, String catalogName, String description, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.status = status;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã danh mục: ");
        this.catalogId = scanner.nextLine();
        if (!catalogId.matches("^C\\d{3}$")) {
            throw new IllegalArgumentException("Mã danh mục phải bắt đầu bằng ký tự 'C' và có tổng cộng 4 ký tự");
        }

        System.out.println("Nhập tên danh mục: ");
        this.catalogName = scanner.nextLine();
        if (catalogName.isEmpty()) {
            throw new IllegalArgumentException("Tên danh mục không được để trống");
        }

        System.out.println("Nhập mô tả/thông số: ");
        this.description = scanner.nextLine();
    }

    public void displayData() {
        System.out.printf("| Mã danh mục: %s | Tên danh mục: %s | Mô tả/thông số: %s | Trạng thái: %s \n",
                catalogId, catalogName, description, status ? "Hoạt động" : "Không hoạt động");
    }
}