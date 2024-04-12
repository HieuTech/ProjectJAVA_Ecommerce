package presentation.admin;

import business.design.IAuthenticationDesign;
import business.implement.AuthenticationImplement;
import business.utils.InputMethods;

public class UserManagement {
    IAuthenticationDesign authenticationImplement = new AuthenticationImplement();
    public void displayUserMenu() {
        while (true) {
            System.out.println("==========QUẢN LÝ NGƯỜI DÙNG==========");
            System.out.println("1. Hiển thị danh sách người dùng");
            System.out.println("2. Tìm kiếm người dùng theo tên");
            System.out.println("3. Block/ Unblock tài khoản người dùng");
            System.out.println("4. Thoát");
            System.out.println("Nhập lựa chọn: ");
            boolean isExit = false;
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    authenticationImplement.displayUserList();
                    break;
                case 2:
                    authenticationImplement.searchUserByName();
                    break;
                case 3:
                    authenticationImplement.changeUserStatus();
                    break;
                case 4:
                    isExit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
            }
            if (isExit){
                break;
            }
        }
    }
}