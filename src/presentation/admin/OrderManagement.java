package presentation.admin;

import business.design.IOrderDesign;
import business.implement.OrderImplement;
import business.utils.InputMethods;

public class OrderManagement {
    IOrderDesign orderImplement = new OrderImplement();
    public void displayOrderMenu() {
        while (true) {
            System.out.println("==========QUẢN LÝ HÓA ĐƠN==========");
            System.out.println("1. Hiển thị danh sách hóa đơn chưa xác nhận");
            System.out.println("2. Hiển thị danh sách hóa đơn đã xác nhận");
            System.out.println("3. Xác nhận hóa đơn đang chờ");
            System.out.println("4. Hủy 1 đơn hàng");
            System.out.println("5. Thoát");
            System.out.println("Nhập lựa chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    orderImplement.displayUncomfirmedOrderList();
                    break;
                case 2:
                    orderImplement.displayComfirmedOrderList();
                    break;
                case 3:
                    orderImplement.confirmOrderWaiting();
                    break;
                case 4:
                    orderImplement.cancelOrder();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
            }
        }
    }
}