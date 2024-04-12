package business.design;

import business.entity.User;

public interface IAuthenticationDesign {
    User login(String username, String password);

    void register(User user);

    void displayUserList();

    void searchUserByName();

    void changeUserStatus();
}
