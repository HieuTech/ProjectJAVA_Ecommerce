package presentation;

import business.utils.InputMethods;
import presentation.user.*;

public class MenuUser {
    HomePage homePage = new HomePage();
    ProductDetail productDetail = new ProductDetail();
    Cart cart = new Cart();
    AccountPage accountPage;
    OrderHistory orderHistory = new OrderHistory();

    public void displayMenuUser() {
        while (true) {
            System.out.println("==========CHÀO MỪNG TỚI TRANG NGƯỜI DÙNG==========");
            System.out.println("1. Trang chủ");
            System.out.println("2. Chi tiết sản phẩm");
            System.out.println("3. Giỏ hàng");
            System.out.println("4. Trang thông tin cá nhân");
            System.out.println("5. Lịch sử đơn hàng");
            System.out.println("6. Đăng xuất");
            System.out.println("Nhập lựa chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    homePage.displayHomePageMenu();
                    break;
                case 2:
                    productDetail.displayProductDetailMenu();
                    break;
                case 3:
                    cart.displayCartMenu();
                    break;
                case 4:
                    accountPage = new AccountPage(Main.user);
                    accountPage.AccountPageMenu();
                    break;
                case 5:
                    orderHistory.displayOrderHistoryMenu();
                    break;
                case 6:
                    Main.user = null;
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
                    break;
            }
        }
    }
}
