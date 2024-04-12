package presentation;

import business.utils.InputMethods;
import presentation.admin.CatalogManagement;
import presentation.admin.OrderManagement;
import presentation.admin.ProductManagement;
import presentation.admin.UserManagement;

public class MenuAdmin {
    private static MenuAdmin menuAdmin = new MenuAdmin();

    public static MenuAdmin getInstance() {
        return menuAdmin;
    }

    private MenuAdmin() {

    }

    UserManagement userManagement = new UserManagement();
    CatalogManagement catalogManagement = new CatalogManagement();
    ProductManagement productManagement = new ProductManagement();
    OrderManagement orderManagement = new OrderManagement();

    public void displayMenuAdmin() {
        while (true){
            System.out.println("==========CHÀO MỪNG TỚI TRANG QUẢN TRỊ==========");
            System.out.println("1. Quản lý người dùng");
            System.out.println("2. Quản lý danh mục");
            System.out.println("3. Quản lý sản phẩm");
            System.out.println("4. Quản lý hóa đơn");
            System.out.println("5. Đăng xuất");
            System.out.println("Nhập lựa chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    userManagement.displayUserMenu();
                    break;
                case 2:
                    catalogManagement.displayCatalogMenu();
                    break;
                case 3:
                    productManagement.displayProductMenu();
                    break;
                case 4:
                    orderManagement.displayOrderMenu();
                    break;
                case 5:
                Main.user = null;
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
            }
        }
    }
}