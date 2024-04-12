package business.implement;

import business.design.IAuthenticationDesign;
import business.entity.*;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static business.implement.ProductImplement.productList;

public class AuthenticationImplement implements IAuthenticationDesign {
    public static List<User> userList;
    public static List<Product> cartList;

    static{
        cartList = IOFile.readFromFile(IOFile.CART_PATH);
    }

    static {
        userList = IOFile.readFromFile(IOFile.USER_PATH);
        User admin = new User(1, "Nguyễn", "Admin", "admin123", "admin123", "admin@gmail.com", "0395692099", "Hà Nội", null, RoleName.ROLEADMIN, true);
        userList.add(admin);
        IOFile.writeToFile(IOFile.USER_PATH, userList);
    }



    public User login(String username, String password) {
        User userLogin = getUserFromUsername(username);
        if (userLogin == null) {
            return null;
        }
//        boolean checkLogin = BCrypt.checkpw(password, userLogin.getPassword());
        boolean checkLogin = userLogin.getPassword().contains(password);
        if (checkLogin) {
            return userLogin;
        }
        return null;
    }

    public void register(User user) {

    }

    public void displayUserList() {
        System.out.println("==========DANH SÁCH NGƯỜI DÙNG==========");
        userList.stream().filter(user -> user.getRole() == RoleName.ROLEUSER).forEach(User::displayData);
//        userList.stream()
//                .sorted(Comparator.comparing(User::getFirstName))
//                .forEach(User::displayData);
    }

    public void searchUserByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên người dùng cần tìm: ");
        String searchName = scanner.nextLine().toLowerCase();
        System.out.println("==========KẾT QUẢ TÌM KIẾM==========");
        userList.stream()
                .filter(user -> user.getFirstName().toLowerCase().contains(searchName) || user.getLastName().toLowerCase().contains(searchName))
                .forEach(User::displayData);
    }

    public void changeUserStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên người dùng cần thay đổi trạng thái: ");
        String username = scanner.nextLine();
        User user = getUserFromUsername(username);
        if (user != null) {
            user.setStatus(!user.isStatus());
            IOFile.writeToFile(IOFile.USER_PATH, userList);
            System.out.println("Thay đổi trạng thái tài khoản thành công!");
        } else {
            System.out.println("Không tìm thấy người dùng!");
        }
    }


    private User getUserFromUsername(String username) {
        return userList.stream().filter(user -> user.getUserName().contains(username)).findFirst().orElse(null);
    }

    public  int getNewId() {
        int max = userList.stream().map(User::getUserId).max(Comparator.naturalOrder()).orElse(0);
        return max + 1;
    }

    public void addToCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID sản phẩm muốn thêm vào giỏ hàng: ");
        int productId = Integer.parseInt(scanner.nextLine());

        Product productToAdd = getProductById(productId);
        if (productToAdd != null) {
            cartList.add(productToAdd);
            IOFile.writeToFile(IOFile.CART_PATH, cartList);
            System.out.println("Sản phẩm đã được thêm vào giỏ hàng.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID là " + productId);
        }
    }
    public void placeOrder() {

        Order order = new Order();
        System.out.println("Total Item In Cart");
        cartList.forEach(Product::displayCartItem);
        try {
            order.inputData();
            order.setOrderStatus(OrderStatus.WAITING);
//            double total = calculateTotal(order.getOrdersDetails());
            double totalPrice = 0;
            for (Product product : cartList){
                totalPrice += product.getUnitPrice();
            }
            order.setTotal(totalPrice
            );

            OrderImplement.orderList.add(order);
            IOFile.writeToFile(IOFile.ORDER_PATH,OrderImplement.orderList);

            cartList.clear();
            System.out.println("Đơn hàng đã được đặt thành công!");
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi đặt hàng: " + e.getMessage());
        }
    }




    public void displayProductListInCart() {
        System.out.println("==========DANH SÁCH SẢN PHẨM TRONG GIỎ HÀNG==========");
        for (Product product : cartList) {
            product.displayCartItem();
        }
    }

    public void changeOrderQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID sản phẩm muốn thay đổi số lượng: ");
        int productId = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập số lượng mới: ");
        int newQuantity = Integer.parseInt(scanner.nextLine());

        Product productToChange = getProductById(productId);
        if (productToChange != null) {
            productToChange.setStock(newQuantity);
            AuthenticationImplement.cartList.set(AuthenticationImplement.cartList.indexOf(productToChange), productToChange );
            IOFile.writeToFile(IOFile.CART_PATH, AuthenticationImplement.cartList);
            System.out.println("Số lượng sản phẩm đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID là " + productId);
        }
    }

    public void deleteProductInQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID sản phẩm muốn xóa khỏi giỏ hàng: ");
        int productId = Integer.parseInt(scanner.nextLine());

        Product productToDelete = getProductByIdInCart(productId);
        if (productToDelete != null) {
            AuthenticationImplement.cartList.remove(productToDelete);
            IOFile.writeToFile(IOFile.CART_PATH, AuthenticationImplement.cartList);
            System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID là " + productId);
        }
    }

    private Product getProductByIdInCart(int productId) {
        for (Product product : cartList) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }




    private double calculateTotal(List<OrderDetail> orderDetails) {
        double total = 0;
        for (OrderDetail detail : orderDetails) {
            total += detail.calculatePrice();
        }
        return total;
    }

    private Product getProductById(int productId) {
        for (Product product : productList) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public void changePassword(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mật khẩu cũ: ");
        String oldPassword = scanner.nextLine();


        if (currentUser.getPassword().equals(oldPassword)) {
            System.out.println("Nhập mật khẩu mới: ");
            String newPassword = scanner.nextLine();

            if (newPassword.length() >= 4) {
                currentUser.setPassword(newPassword);
                AuthenticationImplement.userList.set(AuthenticationImplement.userList.indexOf(currentUser),currentUser );
                IOFile.writeToFile(IOFile.USER_PATH, userList);
                System.out.println("Mật khẩu đã được thay đổi thành công!");
            } else {
                System.out.println("Mật khẩu mới phải có ít nhất 8 ký tự.");
            }
        } else {
            System.out.println("Mật khẩu cũ không đúng.");
        }
    }


    public void displayUserInfo(User currentUser) {
        System.out.println("==========THÔNG TIN CÁ NHÂN==========");
        currentUser.displayData();
    }


    public void editUserInfo(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập họ mới: ");
        String newFirstName = scanner.nextLine();
        if (!newFirstName.isEmpty()) {
            currentUser.setFirstName(newFirstName);
        }

        System.out.println("Nhập tên mới: ");
        String newLastName = scanner.nextLine();
        if (!newLastName.isEmpty()) {
            currentUser.setLastName(newLastName);
        }

        System.out.println("Nhập địa chỉ mới: ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            currentUser.setAddress(newAddress);
        }

        System.out.println("Nhập email mới: ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty() && Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(newEmail).find()) {
            currentUser.setEmail(newEmail);
        }

        System.out.println("Nhập số điện thoại mới: ");
        String newPhoneNumber = scanner.nextLine();
        if (!newPhoneNumber.isEmpty() && newPhoneNumber.matches("^\\+?84\\d{9,10}$")) {
            currentUser.setPhoneNumber(newPhoneNumber);
        }

        IOFile.writeToFile(IOFile.USER_PATH, userList);
        System.out.println("Thông tin cá nhân đã được cập nhật thành công!");
    }
}