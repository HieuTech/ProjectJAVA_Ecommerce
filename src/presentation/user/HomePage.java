package presentation.user;

import business.implement.AuthenticationImplement;
import business.implement.ProductImplement;
import business.utils.InputMethods;

public class HomePage {
    AuthenticationImplement authenticationImplement = new AuthenticationImplement();
    ProductImplement productImplement = new ProductImplement();
    public void displayHomePageMenu() {
        while (true) {
            System.out.println("==========TRANG CHỦ==========");
            System.out.println("1. Tìm kiếm sản phẩm");
            System.out.println("2. Hiển thị sản phẩm nổi bật");
            System.out.println("3. Hiển thị từng nhóm sản phẩm theo danh mục");
            System.out.println("4. Danh sách sản phẩm");
            System.out.println("5. Hiển thị danh sách sắp xếp theo giá tăng dần/giảm dần");
            System.out.println("6. Thêm vào giỏ hàng");
            System.out.println("7. Thoát");
            System.out.println("Nhập lựa chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    productImplement.searchProduct();
                    break;
                case 2:
                    productImplement.displayTop10Product();
                    break;
                case 3:
                    productImplement.displayProductGroupByCatalog();
                    break;
                case 4:
                    productImplement.showProductList();
                    break;
                case 5:
                    productImplement.displayProductByPrice();
                    break;
                case 6:
                    authenticationImplement.addToCart();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
            }
        }
    }
}
