package business.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order implements Serializable {
    private long orderId;
    private long userId;
    private String nameReceive;
    private String phoneNumber;
    private String address;
    private double total;
    private OrderStatus orderStatus = OrderStatus.WAITING;
    private List<OrderDetail> ordersDetails;
    private LocalDateTime orderAt = LocalDateTime.now();
    private LocalDateTime deliverAt = LocalDateTime.now().plusDays(3);

    public Order() {
    }

    public Order(long orderId, long userId, String nameReceive, String phoneNumber, String address, double total, List<OrderDetail> ordersDetails) {
        this.orderId = orderId;
        this.userId = userId;
        this.nameReceive = nameReceive;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.total = total;
        this.ordersDetails = ordersDetails;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNameReceive() {
        return nameReceive;
    }

    public void setNameReceive(String nameReceive) {
        this.nameReceive = nameReceive;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetail> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(List<OrderDetail> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }

    public LocalDateTime getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(LocalDateTime orderAt) {
        this.orderAt = orderAt;
    }

    public LocalDateTime getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(LocalDateTime deliverAt) {
        this.deliverAt = deliverAt;
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã người mua: ");
        this.userId = Long.parseLong(scanner.nextLine());

        System.out.println("Nhập tên người nhận: ");
        this.nameReceive = scanner.nextLine();
        if (nameReceive.isEmpty()) {
            throw new IllegalArgumentException("Tên người nhận không được để trống");
        }

        System.out.println("Nhập số điện thoại người nhận: ");
        this.phoneNumber = scanner.nextLine();
        if (!phoneNumber.matches("^\\+?84\\d{9,10}$")) {
            System.out.println("Định dạng số điện thoại Việt Nam không hợp lệ");
        }

        System.out.println("Nhập địa chỉ giao hàng: ");
        this.address = scanner.nextLine();
        if (address.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống");
        }



    }

    public void displayData() {
        System.out.printf("| Mã hóa đơn: %d | Mã người mua: %d | Tên người nhận: %s | Số điện thoại: %s | Địa chỉ giao hàng: %s | Tổng tiền: %.2f | Trạng thái đơn hàng: %s | Ngày đặt hàng: %s | Ngày giao hàng: %s",
                orderId, userId, nameReceive, phoneNumber, address, total, orderStatus, orderAt, deliverAt);
    }
}