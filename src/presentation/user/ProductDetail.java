package presentation.user;

import business.implement.AuthenticationImplement;
import business.implement.ProductImplement;
import business.utils.InputMethods;

public class ProductDetail {
    AuthenticationImplement authenticationImplement = new AuthenticationImplement();
    ProductImplement productImplement = new ProductImplement();

    // Thêm đối số productId vào phương thức
    public void displayProductDetailMenu() {
        while (true) {
            System.out.println("==========CHI TIẾT SẢN PHẨM==========");
            System.out.println("1. Đổ dữ liệu");
            System.out.println("2. Thêm vào giỏ hàng");
            System.out.println("3. Thoát");
            System.out.println("Nhập lựa chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    productImplement.displayProductById();
                    break;
                case 2:
                    authenticationImplement.addToCart();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
            }
        }
    }
}