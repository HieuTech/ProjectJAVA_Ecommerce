package business.implement;

import business.design.IOrderDesign;
import business.entity.Order;
import business.entity.OrderStatus;
import business.utils.IOFile;
import business.utils.InputMethods;

import java.util.List;

public class OrderImplement implements IOrderDesign {
    public static List<Order> orderList;

    static {
        orderList = IOFile.readFromFile(IOFile.ORDER_PATH);
    }

    @Override
    public void displayUncomfirmedOrderList() {
        long unconfirmedCount = orderList.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.WAITING)
                .peek(Order::displayData)
                .count();
        System.out.println("Hiển thị danh sách hóa đơn chưa xác nhận (" + unconfirmedCount + ")");
    }

    @Override
    public void displayComfirmedOrderList() {
        System.out.println("Hiển thị danh sách hóa đơn đã xác nhận");
        orderList.stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.WAITING)
                .forEach(Order::displayData);
    }

    @Override
    public void confirmOrderWaiting() {
        System.out.println("Xác nhận hóa đơn đang chờ");
        System.out.println("Nhập mã hóa đơn cần xác nhận: ");
        long orderId = InputMethods.getLong();

        orderList.stream()
                .filter(order -> order.getOrderId() == orderId && order.getOrderStatus() == OrderStatus.WAITING)
                .findFirst()
                .ifPresent(order -> {
                    order.setOrderStatus(OrderStatus.PROCESSING);
                    IOFile.writeToFile(IOFile.ORDER_PATH, orderList);
                    System.out.println("Hóa đơn đã được xác nhận.");
                });
    }

    @Override
    public void cancelOrder() {
        System.out.println("Hủy 1 đơn hàng");
        System.out.println("Nhập mã hóa đơn cần hủy: ");
        long orderId = InputMethods.getLong();

        orderList.stream()
                .filter(order -> order.getOrderId() == orderId && order.getOrderStatus() != OrderStatus.DELIVERED && order.getOrderStatus() != OrderStatus.CANCELED)
                .findFirst()
                .ifPresent(order -> {
                    order.setOrderStatus(OrderStatus.CANCELED);
                    IOFile.writeToFile(IOFile.ORDER_PATH, orderList);
                    System.out.println("Hóa đơn đã được hủy.");
                });
    }

    public void historyOrderData() {
        if (orderList.isEmpty()) {
            System.out.println("Không có dữ liệu để hiển thị.");
            return;
        }

        System.out.println("==========LỊCH SỬ ĐƠN HÀNG==========");
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println("Đơn hàng " + (i + 1));
            orderList.get(i).displayData();
        }
    }
}