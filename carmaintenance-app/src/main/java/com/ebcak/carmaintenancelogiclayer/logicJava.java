package com.ebcak.carmaintenancelogiclayer;

import com.ebcak.carmaintenance.DALUser;
import com.ebcak.carmaintenanceumple.User;

public class logicJava {

    /**
     * Registers a new user.
     * @param user the user to register
     * @return 0 if registration is successful, -1 otherwise
     */
    public int register(User user) {
        boolean isRegistered = DALUser.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
        return isRegistered ? 0 : -1;
    }

    /**
     * Logs in a user.
     * @param username the user's username
     * @param password the user's password
     * @return true if login is successful, false otherwise
     */
    public boolean login(String username, String password) {
        return DALUser.loginUser(username, password);
    }
}
