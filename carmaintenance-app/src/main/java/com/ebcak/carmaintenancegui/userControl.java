/**
 * @file userControl.java
 * @brief This file contains the user control class for managing user, service records, expense reports, and reminders.
 */

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

/**
 * @class userControl
 * @brief Class for managing user control operations including user registration, login, service records, expense reports, and reminders.
 */
public class userControl {

    private UserLogic userLogic;
    private ServiceRecordLogic serviceRecordLogic;
    private ExpenseReportLogic expenseReportLogic;
    private ReminderLogic reminderLogic;

    /**
     * @brief Default constructor initializing logic layer instances.
     */
    public userControl() {
        userLogic = new UserLogic();
        serviceRecordLogic = new ServiceRecordLogic();
        expenseReportLogic = new ExpenseReportLogic();
        reminderLogic = new ReminderLogic();
    }

    /**
     * @brief Setter method for UserLogic instance.
     * @param userLogic Instance of UserLogic.
     */
    public void setUserLogic(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    /**
     * @brief Setter method for ServiceRecordLogic instance.
     * @param serviceRecordLogic Instance of ServiceRecordLogic.
     */
    public void setServiceRecordLogic(ServiceRecordLogic serviceRecordLogic) {
        this.serviceRecordLogic = serviceRecordLogic;
    }

    /**
     * @brief Setter method for ExpenseReportLogic instance.
     * @param expenseReportLogic Instance of ExpenseReportLogic.
     */
    public void setExpenseReportLogic(ExpenseReportLogic expenseReportLogic) {
        this.expenseReportLogic = expenseReportLogic;
    }

    /**
     * @brief Setter method for ReminderLogic instance.
     * @param reminderLogic Instance of ReminderLogic.
     */
    public void setReminderLogic(ReminderLogic reminderLogic) {
        this.reminderLogic = reminderLogic;
    }

    /**
     * @brief Registers a new user.
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param email The email of the new user.
     * @return 0 if successful, -1 otherwise.
     */
    public int registerUser(String username, String password, String email) {
        User user = new User(0, username, password, email);
        int result = userLogic.add(user);

        if (result == 0) {
            return 0;
        }
        return -1;
    }

    /**
     * @brief Logs in a user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if successful, false otherwise.
     */
    public boolean loginUser(String username, String password) {
        return userLogic.login(username, password);
    }

    /**
     * @brief Adds a new service record.
     * @param carBrand The brand of the car.
     * @param whatToDo The description of the service to be done.
     * @param driverName The name of the driver.
     * @param driverPhone The phone number of the driver.
     * @param kilometer The current kilometer reading of the car.
     * @return true if successful, false otherwise.
     */
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

    /**
     * @brief Updates an existing service record.
     * @param recordId The ID of the service record.
     * @param carBrand The brand of the car.
     * @param whatToDo The description of the service to be done.
     * @param driverName The name of the driver.
     * @param driverPhone The phone number of the driver.
     * @param kilometer The current kilometer reading of the car.
     * @return true if successful, false otherwise.
     */
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

    /**
     * @brief Retrieves a service record by driver name.
     * @param driverName The name of the driver.
     * @return The ServiceRecord object if found, null otherwise.
     */
    public ServiceRecord getServiceRecordByDriverName(String driverName) {
        return serviceRecordLogic.getServiceRecordByDriverName(driverName);
    }

    /**
     * @brief Searches for service records matching a search term.
     * @param searchTerm The term to search for.
     * @return A list of ServiceRecord objects matching the search term.
     */
    public List<ServiceRecord> searchServiceRecords(String searchTerm) {
        return serviceRecordLogic.searchServiceRecords(searchTerm);
    }

    /**
     * @brief Deletes a service record by driver name.
     * @param driverName The name of the driver.
     * @return true if successful, false otherwise.
     */
    public boolean deleteServiceRecordByDriverName(String driverName) {
        return serviceRecordLogic.remove(driverName);
    }

    /**
     * @brief Retrieves expense reports by driver name.
     * @param driverName The name of the driver.
     * @return A list of ExpenseReport objects for the specified driver.
     */
    public List<ExpenseReport> getExpenseReportsByDriverName(String driverName) {
        return expenseReportLogic.getExpenseReportsByDriverName(driverName);
    }

    /**
     * @brief Adds a new reminder.
     * @param reminder The Reminder object to be added.
     * @return true if successful, false otherwise.
     */
    public boolean addReminder(Reminder reminder) {
        return reminderLogic.add(reminder) == 0;
    }
}
