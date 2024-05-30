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

public class logicJava {

    public interface LogicLayer<T> {
        int add(T item);
        boolean remove(String identifier);
        boolean update(T item);
        List<T> getAll();
    }

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

        public boolean login(String username, String password) {
            return DALUser.loginUser(username, password);
        }

        public User getUserById(int userId) {
            return DALUser.getUserById(userId);
        }

        public int getUserIdByUsername(String username) {
            return DALUser.getUserIdByUsername(username);
        }
    }

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

        public ServiceRecord getServiceRecordByDriverName(String driverName) {
            return DALServiceRecord.getServiceRecordByDriverName(driverName);
        }

        public List<ServiceRecord> searchServiceRecords(String searchTerm) {
            return DALServiceRecord.searchServiceRecords(searchTerm);
        }
    }

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

        public List<Reminder> getRemindersByServiceRecordId(int serviceRecordId) {
            return DALReminder.getRemindersByServiceRecordId(serviceRecordId);
        }
    }

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

        public List<ExpenseReport> getExpenseReportsByDriverName(String driverName) {
            ServiceRecord serviceRecord = DALServiceRecord.getServiceRecordByDriverName(driverName);
            if (serviceRecord == null) {
                return null;
            }
            return DALExpenseReport.getExpenseReportsByServiceRecordId(serviceRecord.getRecord_id());
        }

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
