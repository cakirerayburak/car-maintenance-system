package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenancelogiclayer.logicJava;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenance.DALUser;

public class userControl {
    public int registerUser(String username, String password, String email) {
        logicJava main = new logicJava();
        User user = new User(0, username, password, email);
        int result = main.register(user);

        if (result == 0) {
            return 0; 
        }
        return -1;
    }

    public boolean loginUser(String username, String password) {
        return DALUser.loginUser(username, password);
    }
}
