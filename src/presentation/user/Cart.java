package presentation.user;

import business.implement.AuthenticationImplement;
import business.utils.InputMethods;

public class Cart {
    AuthenticationImplement authenticationImplement = new AuthenticationImplement();
    public void displayCartMenu() {
        while (true) {
            System.out.println("==========GIỎ HÀNG==========");
            System.out.println("1. Hiển thị danh sách sản phẩm trong giỏ hàng");
            System.out.println("2. Thay đổi số lượng đặt hàng");
            System.out.println("3. Xóa sản phẩm trong giỏ hàng");
            System.out.println("4. Đặt hàng");
            System.out.println("5. Thoát");
            System.out.println("Nhập lựa chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    authenticationImplement.displayProductListInCart();
                    break;
                case 2:
                    authenticationImplement.changeOrderQuantity();
                    break;
                case 3:
                    authenticationImplement.deleteProductInQuantity();
                    break;
                case 4:
                    authenticationImplement.placeOrder();
                    break;
                case 5:
                    return;
                case 6:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
            }
        }
    }
}
