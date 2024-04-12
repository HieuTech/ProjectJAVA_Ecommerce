package presentation;

import business.entity.RoleName;
import business.entity.User;
import business.implement.AuthenticationImplement;
import business.utils.IOFile;
import business.utils.InputMethods;

import java.util.Comparator;

public class Main {
    private static MenuUser menuUser = new MenuUser();
    private static AuthenticationImplement authenticationImplement = new AuthenticationImplement();
    public static User user = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("==========MENU=========");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("3. Thoát");
            System.out.println("Nhập chức năng: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ");
            }
            if (choice == 3) {
                break;
            }
        }
    }

    public static void login() {
        System.out.println("==========ĐĂNG NHẬP==========");
        System.out.println("Nhập username: ");
        String username = InputMethods.getString();
        System.out.println("Nhập password: ");
        String password = InputMethods.getString();

        User userLogin = authenticationImplement.login(username, password);
        if (userLogin == null) {
            System.err.println("Tài khoản hoặc mật khẩu không chính xác");
            System.out.println("1. Tiếp tục đăng nhập");
            System.out.println("2. Bạn chưa có tài khoản, đăng ký ngay");
            System.out.println("3. Thoát");
            System.out.println("Nhập lựa chọn: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Nhập lựa chọn không chính xác");
            }
        } else {
            if (userLogin.getRole().equals(RoleName.ROLEADMIN)) {
                user = userLogin;
                MenuAdmin.getInstance().displayMenuAdmin();
            } else if (userLogin.getRole().equals(RoleName.ROLEUSER)) {
                if (!userLogin.isStatus()) {
                    System.err.println("Tài khoản đã bị khóa, vui lòng liên hệ admin (0365981802)");
                } else {
                    user = userLogin;
                    menuUser.displayMenuUser();

                }
            } else {
                System.err.println("Không có quyền truy cập");
            }
        }
    }

    public static void register() {
        System.out.println("==========ĐĂNG KÝ==========");
        User user = new User();
        System.out.println("Nhập tên đăng nhập: ");
        user.setUserName(InputMethods.getString());
        System.out.println("Nhập mật khẩu: ");
        user.setPassword(InputMethods.getString());
        System.out.println("Nhập địa chỉ email: ");
        user.setEmail(InputMethods.getString());
        System.out.println("Nhập số điện thoại: ");
        user.setPhoneNumber(InputMethods.getString());
        System.out.println("Nhập họ: ");
        user.setLastName(InputMethods.getString());
        System.out.println("Nhập tên: ");
        user.setFirstName(InputMethods.getString());
//        authenticationImplement.register(user);
        int max = AuthenticationImplement.userList.stream().map(User::getUserId).max(Comparator.naturalOrder()).orElse(0);
        user.setUserId(max + 1);
        user.setRole(RoleName.ROLEUSER);
        user.setStatus(true);
        AuthenticationImplement.userList.add(user);
        IOFile.writeToFile(IOFile.USER_PATH, AuthenticationImplement.userList);
        System.out.println("Đăng ký thành công");
        login();
    }
}