package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenancelogiclayer.logicJava;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;

import java.util.List;

import com.ebcak.carmaintenance.DALUser;

public class ServiceRecordControl {
    
    private logicJava logic;

    public ServiceRecordControl() {
        logic = new logicJava();
    }

    public boolean addServiceRecord(String carBrand, String whatToDo, String driverName, String driverPhone, int kilometer) {
        int userId = DALUser.getUserIdByUsername(driverName);
        if (userId == -1) {
            System.out.println("User not found with username: " + driverName);
            return false;
        }
        User user = DALUser.getUserById(userId);
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            return false;
        }
        ServiceRecord serviceRecord = new ServiceRecord(0, carBrand, whatToDo, driverName, driverPhone, kilometer, userId, user);
        return logic.addServiceRecord(serviceRecord);
    }

    public boolean updateServiceRecord(int recordId, String carBrand, String whatToDo, String driverName, String driverPhone, int kilometer) {
        int userId = DALUser.getUserIdByUsername(driverName);
        if (userId == -1) {
            System.out.println("User not found with username: " + driverName);
            return false;
        }
        User user = DALUser.getUserById(userId);
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            return false;
        }
        ServiceRecord serviceRecord = new ServiceRecord(recordId, carBrand, whatToDo, driverName, driverPhone, kilometer, userId, user);
        return logic.updateServiceRecord(serviceRecord);
    }

    public ServiceRecord getServiceRecordByDriverName(String driverName) {
        return logic.getServiceRecordByDriverName(driverName);
    }
    public List<ServiceRecord> searchServiceRecords(String searchTerm) {
        return logic.searchServiceRecords(searchTerm);
    }
    
    public boolean deleteServiceRecordByDriverName(String driverName) {
        return logic.deleteServiceRecordByDriverName(driverName);
    }
}