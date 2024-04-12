package presentation.admin;

import business.design.IProductDesign;
import business.implement.ProductImplement;
import business.utils.InputMethods;

public class ProductManagement {
    IProductDesign productImplement = new ProductImplement();
    public void displayProductMenu() {
        while (true) {
            System.out.println("=========QUẢN LÝ SẢN PHẨM==========");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm mới 1 hoặc nhiều sản phẩm");
            System.out.println("3. Chỉnh sửa thông tin sản phẩm");
            System.out.println("4. Ẩn sản phẩm theo mã sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Thoát");
            System.out.println("Nhập lựa chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    productImplement.displayProductList();
                    break;
                case 2:
                    productImplement.addNewProduct();
                    break;
                case 3:
                    productImplement.editProductInfo();
                    break;
                case 4:
                    productImplement.hideProductById();
                    break;
                case 5:
                    productImplement.searchProductByName();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
            }
        }
    }
}