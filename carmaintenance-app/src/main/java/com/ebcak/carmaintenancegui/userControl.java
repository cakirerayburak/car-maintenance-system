package com.ebcak.carmaintenancegui;

import com.ebcak.carmaintenancelogiclayer.logicJava.UserLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ServiceRecordLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ExpenseReportLogic;
import com.ebcak.carmaintenancelogiclayer.logicJava.ReminderLogic;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.Reminder;
import java.util.List;

public class userControl {

    private UserLogic userLogic;
    private ServiceRecordLogic serviceRecordLogic;
    private ExpenseReportLogic expenseReportLogic;
    private ReminderLogic reminderLogic;

    public userControl() {
        userLogic = new UserLogic();
        serviceRecordLogic = new ServiceRecordLogic();
        expenseReportLogic = new ExpenseReportLogic();
        reminderLogic = new ReminderLogic();
    }

    // Setter methods for dependency injection
    public void setUserLogic(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    public void setServiceRecordLogic(ServiceRecordLogic serviceRecordLogic) {
        this.serviceRecordLogic = serviceRecordLogic;
    }

    public void setExpenseReportLogic(ExpenseReportLogic expenseReportLogic) {
        this.expenseReportLogic = expenseReportLogic;
    }

    public void setReminderLogic(ReminderLogic reminderLogic) {
        this.reminderLogic = reminderLogic;
    }

    public int registerUser(String username, String password, String email) {
        User user = new User(0, username, password, email);
        int result = userLogic.add(user);

        if (result == 0) {
            return 0;
        }
        return -1;
    }

    public boolean loginUser(String username, String password) {
        return userLogic.login(username, password);
    }

    public boolean addServiceRecord(String carBrand, String whatToDo, String driverName, String driverPhone, int kilometer) {
        int userId = userLogic.getUserIdByUsername(driverName);
        if (userId == -1) {
            System.out.println("User not found with username: " + driverName);
            return false;
        }
        User user = userLogic.getUserById(userId);
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            return false;
        }
        ServiceRecord serviceRecord = new ServiceRecord(0, carBrand, whatToDo, driverName, driverPhone, kilometer, userId, user);
        return serviceRecordLogic.add(serviceRecord) == 0;
    }

    public boolean updateServiceRecord(int recordId, String carBrand, String whatToDo, String driverName, String driverPhone, int kilometer) {
        int userId = userLogic.getUserIdByUsername(driverName);
        if (userId == -1) {
            System.out.println("User not found with username: " + driverName);
            return false;
        }
        User user = userLogic.getUserById(userId);
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            return false;
        }
        ServiceRecord serviceRecord = new ServiceRecord(recordId, carBrand, whatToDo, driverName, driverPhone, kilometer, userId, user);
        return serviceRecordLogic.update(serviceRecord);
    }

    public ServiceRecord getServiceRecordByDriverName(String driverName) {
        return serviceRecordLogic.getServiceRecordByDriverName(driverName);
    }

    public List<ServiceRecord> searchServiceRecords(String searchTerm) {
        return serviceRecordLogic.searchServiceRecords(searchTerm);
    }

    public boolean deleteServiceRecordByDriverName(String driverName) {
        return serviceRecordLogic.remove(driverName);
    }

    public List<ExpenseReport> getExpenseReportsByDriverName(String driverName) {
        return expenseReportLogic.getExpenseReportsByDriverName(driverName);
    }

    public boolean addReminder(Reminder reminder) {
        return reminderLogic.add(reminder) == 0;
    }
}
