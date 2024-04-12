package business.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User implements Serializable {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private List<CartItem> cart;
    private RoleName role = RoleName.ROLEUSER;
    private boolean status = true;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String userName, String password, String email, String phoneNumber, String address, List<CartItem> cart, RoleName role, boolean status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cart = cart;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập họ: ");
        this.firstName = scanner.nextLine();
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException("Họ không được để trống");
        }

        System.out.println("Nhập tên: ");
        this.lastName = scanner.nextLine();
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống");
        }

        System.out.println("Nhập tên đăng nhập: ");
        this.userName = scanner.nextLine();
        if (userName.length() < 6 || userName.matches(".*\\W.*")) {
            throw new IllegalArgumentException("Tên đăng nhập phải có ít nhất 6 ký tự, không chứa ký tự đặc biệt");
        }

        System.out.println("Nhập mật khẩu: ");
        this.password = scanner.nextLine();
        if (password.length() < 8) {
            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 8 ký tự");
        }

        System.out.println("Nhập địa chỉ email: ");
        this.email = scanner.nextLine();
        if (!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email).find()) {
            throw new IllegalArgumentException("Định dạng email không hợp lệ");
        }

        System.out.println("Nhập số điện thoại: ");
        this.phoneNumber = scanner.nextLine();
        if (!phoneNumber.matches("^\\+?84\\d{9,10}$")) {
            throw new IllegalArgumentException("Định dạng số điện thoại Việt Nam không hợp lệ");
        }

        System.out.println("Nhập địa chỉ: ");
        this.address = scanner.nextLine();
    }

    public void displayData() {
        System.out.printf("| Mã ID: %d | Họ: %s | Tên: %s | Tên đăng nhập: %s | Mật khẩu: %s | Địa chỉ email: %s | Số điện thoại: %s | Địa chỉ: %s | Quyền: %s | Trạng thái tài khoản: %s \n",
                userId, firstName, lastName, userName, password, email, phoneNumber, address, role, status ? "Block" : "Unblock");
    }
}