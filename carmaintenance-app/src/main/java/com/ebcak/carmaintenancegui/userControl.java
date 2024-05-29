package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenancelogiclayer.logicJava;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenance.DALUser;

import java.util.List;

public class userControl {

    private logicJava logic;

    public userControl() {
        logic = new logicJava();
    }

    // Kullanıcı kayıt ve giriş fonksiyonları
    public int registerUser(String username, String password, String email) {
        User user = new User(0, username, password, email);
        int result = logic.register(user);

        if (result == 0) {
            return 0; 
        }
        return -1;
    }

    public boolean loginUser(String username, String password) {
        return DALUser.loginUser(username, password);
    }

    // Hizmet kaydı ekleme fonksiyonu
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

    // Hizmet kaydı güncelleme fonksiyonu
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

    // Sürücü adına göre hizmet kaydı getirme fonksiyonu
    public ServiceRecord getServiceRecordByDriverName(String driverName) {
        return logic.getServiceRecordByDriverName(driverName);
    }

    // Arama terimine göre hizmet kayıtlarını arama fonksiyonu
    public List<ServiceRecord> searchServiceRecords(String searchTerm) {
        return logic.searchServiceRecords(searchTerm);
    }

    // Sürücü adına göre hizmet kaydı silme fonksiyonu
    public boolean deleteServiceRecordByDriverName(String driverName) {
        return logic.deleteServiceRecordByDriverName(driverName);
    }

    // Sürücü adına göre gider raporlarını getirme fonksiyonu
    public List<ExpenseReport> getExpenseReportsByDriverName(String driverName) {
        return logic.getExpenseReportsByDriverName(driverName);
    }
}