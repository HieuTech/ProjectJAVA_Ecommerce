package presentation.user;

import business.entity.User;
import business.implement.AuthenticationImplement;
import business.utils.InputMethods;

public class AccountPage {
    AuthenticationImplement authenticationImplement = new AuthenticationImplement();
    private User currentUser;

    public AccountPage(User currentUser) {
        this.currentUser = currentUser;
    }

    public void AccountPageMenu() {
       while (true){
           System.out.println("==========TRANG THÔNG TIN CÁ NHÂN==========");
           System.out.println("1. Đổi mật khẩu");
           System.out.println("2. Hiển thị thông tin cá nhân");
           System.out.println("3. Chỉnh sửa thông tin cá nhân");
           System.out.println("4. Thoát");
           System.out.println("Nhập lựa chọn: ");
           boolean isExit = false;
           byte choice = InputMethods.getByte();
           switch (choice) {
               case 1:
                   authenticationImplement.changePassword(currentUser);
                   break;
               case 2:
                   authenticationImplement.displayUserInfo(currentUser);
                   break;
               case 3:
                   authenticationImplement.editUserInfo(currentUser);
                   break;
               case 4:
                   isExit = true;
                   break;
               default:
                   System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
           }
           if(isExit){
               break;
           }
       }
    }
}