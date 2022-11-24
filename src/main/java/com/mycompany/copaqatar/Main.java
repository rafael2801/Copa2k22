package com.mycompany.copaqatar;

import com.mycompany.copaqatar.models.User;
import com.mycompany.copaqatar.views.Login;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Login loginView = new Login(user);

        loginView.makeFrame();
    }
}
