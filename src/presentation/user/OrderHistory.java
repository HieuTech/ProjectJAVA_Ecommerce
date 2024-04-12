package presentation.user;

import business.implement.OrderImplement;
import business.utils.InputMethods;

public class OrderHistory {
    OrderImplement orderImplement = new OrderImplement();
    public void displayOrderHistoryMenu() {
        System.out.println("==========LỊCH SỬ ĐƠN HÀNG==========");
        System.out.println("1. Đổ dữ liệu");
        System.out.println("2. Thoát");
        System.out.println("Nhập lựa chọn: ");
        byte choice = InputMethods.getByte();
        switch (choice) {
            case 1:
                orderImplement.historyOrderData();
                break;
            case 2:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
        }
    }
}