package com.ebcak.carmaintenancelogiclayer;

import com.ebcak.carmaintenance.DALUser;
import com.ebcak.carmaintenanceumple.User;

import java.util.List;

import com.ebcak.carmaintenance.DALServiceRecord;
import com.ebcak.carmaintenanceumple.ServiceRecord;

public class logicJava {

    public int register(User user) {
        boolean isRegistered = DALUser.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
        return isRegistered ? 0 : -1;
    }

    public boolean login(String username, String password) {
        return DALUser.loginUser(username, password);
    }

    public boolean addServiceRecord(ServiceRecord serviceRecord) {
        return DALServiceRecord.addServiceRecord(serviceRecord);
    }

    public boolean updateServiceRecord(ServiceRecord serviceRecord) {
        return DALServiceRecord.updateServiceRecord(serviceRecord);
    }

    public ServiceRecord getServiceRecordByDriverName(String driverName) {
        return DALServiceRecord.getServiceRecordByDriverName(driverName);
    }
    public List<ServiceRecord> searchServiceRecords(String searchTerm) {
        return DALServiceRecord.searchServiceRecords(searchTerm);
    }
    public boolean deleteServiceRecordByDriverName(String driverName) {
        return DALServiceRecord.deleteServiceRecordByDriverName(driverName);
    }
}
