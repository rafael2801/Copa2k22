package com.mycompany.copaqatar;

import com.mycompany.copaqatar.models.User;
import com.mycompany.copaqatar.views.Home;
import com.mycompany.copaqatar.views.Login;

import java.util.Arrays;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        Login loginView = new Login(user);
        loginView.makeFrame();
//        new Home();

    }

}
