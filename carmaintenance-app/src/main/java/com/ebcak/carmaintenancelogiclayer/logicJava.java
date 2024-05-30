/**
 * @file logicJava.java
 * @brief This file contains the logic layer classes for various operations in the car maintenance application.
 */

package com.ebcak.carmaintenancelogiclayer;

import com.ebcak.carmaintenance.DALExpenseReport;
import com.ebcak.carmaintenance.DALReminder;
import com.ebcak.carmaintenance.DALServiceRecord;
import com.ebcak.carmaintenance.DALUser;
import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.Reminder;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import com.ebcak.carmaintenanceumple.User;

import java.sql.Date;
import java.util.List;

/**
 * @class logicJava
 * @brief Contains logic layer classes and interfaces for various operations.
 */
public class logicJava {

    /**
     * @interface LogicLayer
     * @brief Interface for basic CRUD operations.
     * @tparam T Type of the item to be managed.
     */
    public interface LogicLayer<T> {
        int add(T item);
        boolean remove(String identifier);
        boolean update(T item);
        List<T> getAll();
    }

    /**
     * @class AbstractOperations
     * @brief Abstract class that implements basic CRUD operations.
     * @tparam T Type of the item to be managed.
     */
    public static abstract class AbstractOperations<T> implements LogicLayer<T> {
        @Override
        public int add(T item) {
            if (validate(item)) {
                return performAdd(item);
            } else {
                return -1;
            }
        }

        @Override
        public boolean remove(String identifier) {
            if (identifier != null && !identifier.isEmpty()) {
                return performRemove(identifier);
            } else {
                return false;
            }
        }

        @Override
        public boolean update(T item) {
            if (validate(item)) {
                return performUpdate(item);
            } else {
                return false;
            }
        }

        @Override
        public List<T> getAll() {
            return performGetAll();
        }

        protected abstract boolean validate(T item);
        protected abstract int performAdd(T item);
        protected abstract boolean performRemove(String identifier);
        protected abstract boolean performUpdate(T item);
        protected abstract List<T> performGetAll();
    }

    /**
     * @class UserLogic
     * @brief Logic class for managing users.
     */
    public static class UserLogic extends AbstractOperations<User> {

        @Override
        protected boolean validate(User user) {
            return user.getUsername() != null && !user.getUsername().isEmpty() &&
                   user.getPassword() != null && !user.getPassword().isEmpty() &&
                   user.getEmail() != null && !user.getEmail().isEmpty();
        }

        @Override
        protected int performAdd(User user) {
            return DALUser.registerUser(user.getUsername(), user.getPassword(), user.getEmail()) ? 0 : -1;
        }

        @Override
        protected boolean performRemove(String username) {
            return false; // DALUser.deleteUser(username); Bu yöntem henüz tanımlı değil.
        }

        @Override
        protected boolean performUpdate(User user) {
            return false; // DALUser.updateUser(user); Bu yöntem henüz tanımlı değil.
        }

        @Override
        protected List<User> performGetAll() {
            return null; // DALUser.getUsers(); Bu yöntem henüz tanımlı değil.
        }

        /**
         * @brief Logs in a user with the given username and password.
         * @param username The username of the user.
         * @param password The password of the user.
         * @return True if the login is successful, false otherwise.
         */
        public boolean login(String username, String password) {
            return DALUser.loginUser(username, password);
        }

        /**
         * @brief Retrieves a user by their ID.
         * @param userId The ID of the user.
         * @return The User object.
         */
        public User getUserById(int userId) {
            return DALUser.getUserById(userId);
        }

        /**
         * @brief Retrieves a user ID by their username.
         * @param username The username of the user.
         * @return The user ID.
         */
        public int getUserIdByUsername(String username) {
            return DALUser.getUserIdByUsername(username);
        }
    }

    /**
     * @class ServiceRecordLogic
     * @brief Logic class for managing service records.
     */
    public static class ServiceRecordLogic extends AbstractOperations<ServiceRecord> {

        @Override
        protected boolean validate(ServiceRecord serviceRecord) {
            return serviceRecord.getCarBrand() != null && !serviceRecord.getCarBrand().isEmpty() &&
                   serviceRecord.getDriverName() != null && !serviceRecord.getDriverName().isEmpty() &&
                   serviceRecord.getDriverPhone() != null && !serviceRecord.getDriverPhone().isEmpty();
        }

        @Override
        protected int performAdd(ServiceRecord serviceRecord) {
            return DALServiceRecord.addServiceRecord(serviceRecord) ? 0 : -1;
        }

        @Override
        protected boolean performRemove(String identifier) {
            return DALServiceRecord.deleteServiceRecordByDriverName(identifier);
        }

        @Override
        protected boolean performUpdate(ServiceRecord serviceRecord) {
            return DALServiceRecord.updateServiceRecord(serviceRecord);
        }

        @Override
        protected List<ServiceRecord> performGetAll() {
            return null; // DALServiceRecord.getAllServiceRecords(); Bu yöntem henüz tanımlı değil.
        }

        /**
         * @brief Retrieves a service record by driver name.
         * @param driverName The driver name.
         * @return The ServiceRecord object.
         */
        public ServiceRecord getServiceRecordByDriverName(String driverName) {
            return DALServiceRecord.getServiceRecordByDriverName(driverName);
        }

        /**
         * @brief Searches service records by a search term.
         * @param searchTerm The search term.
         * @return A list of ServiceRecord objects.
         */
        public List<ServiceRecord> searchServiceRecords(String searchTerm) {
            return DALServiceRecord.searchServiceRecords(searchTerm);
        }
    }

    /**
     * @class ReminderLogic
     * @brief Logic class for managing reminders.
     */
    public static class ReminderLogic extends AbstractOperations<Reminder> {

        @Override
        protected boolean validate(Reminder reminder) {
            return reminder.getReminderType() != null && !reminder.getReminderType().isEmpty() &&
                   reminder.getServiceRecord() != null;
        }

        @Override
        protected int performAdd(Reminder reminder) {
            return DALReminder.addReminder(reminder) ? 0 : -1;
        }

        @Override
        protected boolean performRemove(String identifier) {
            return false; // DALReminder.deleteReminder(Integer.parseInt(identifier)); Bu yöntem henüz tanımlı değil.
        }

        @Override
        protected boolean performUpdate(Reminder reminder) {
            return false; // DALReminder.updateReminder(reminder); Bu yöntem henüz tanımlı değil.
        }

        @Override
        protected List<Reminder> performGetAll() {
            return null; // DALReminder.getAllReminders(); Bu yöntem henüz tanımlı değil.
        }

        /**
         * @brief Retrieves reminders by service record ID.
         * @param serviceRecordId The service record ID.
         * @return A list of Reminder objects.
         */
        public List<Reminder> getRemindersByServiceRecordId(int serviceRecordId) {
            return DALReminder.getRemindersByServiceRecordId(serviceRecordId);
        }
    }

    /**
     * @class ExpenseReportLogic
     * @brief Logic class for managing expense reports.
     */
    public static class ExpenseReportLogic extends AbstractOperations<ExpenseReport> {

        @Override
        protected boolean validate(ExpenseReport expenseReport) {
            return expenseReport.getDailyFuel() >= 0 && expenseReport.getAnnualFuel() >= 0 && expenseReport.getTotalCost() >= 0;
        }

        @Override
        protected int performAdd(ExpenseReport expenseReport) {
            return DALExpenseReport.addExpenseReport(expenseReport) ? 0 : -1;
        }

        @Override
        protected boolean performRemove(String identifier) {
            return false; // DALExpenseReport.deleteExpenseReport(Integer.parseInt(identifier)); Bu yöntem henüz tanımlı değil.
        }

        @Override
        protected boolean performUpdate(ExpenseReport expenseReport) {
            return false; // DALExpenseReport.updateExpenseReport(expenseReport); Bu yöntem henüz tanımlı değil.
        }

        @Override
        protected List<ExpenseReport> performGetAll() {
            return null; // DALExpenseReport.getAllExpenseReports(); Bu yöntem henüz tanımlı değil.
        }

        /**
         * @brief Retrieves expense reports by driver name.
         * @param driverName The driver name.
         * @return A list of ExpenseReport objects.
         */
        public List<ExpenseReport> getExpenseReportsByDriverName(String driverName) {
            ServiceRecord serviceRecord = DALServiceRecord.getServiceRecordByDriverName(driverName);
            if (serviceRecord == null) {
                return null;
            }
            return DALExpenseReport.getExpenseReportsByServiceRecordId(serviceRecord.getRecord_id());
        }

        /**
         * @brief Adds an expense report.
         * @param driverName The driver name.
         * @param dailyFuel The daily fuel cost.
         * @param annualFuel The annual fuel cost.
         * @param totalCost The total cost.
         * @return True if the expense report is added successfully, false otherwise.
         */
        public boolean addExpenseReport(String driverName, double dailyFuel, double annualFuel, double totalCost) {
            ServiceRecord serviceRecord = DALServiceRecord.getServiceRecordByDriverName(driverName);
            if (serviceRecord == null) {
                return false;
            }
            ExpenseReport expenseReport = new ExpenseReport(0, new Date(System.currentTimeMillis()), dailyFuel, annualFuel, totalCost, serviceRecord.getRecord_id(), serviceRecord);
            return DALExpenseReport.addExpenseReport(expenseReport);
        }
    }
}
