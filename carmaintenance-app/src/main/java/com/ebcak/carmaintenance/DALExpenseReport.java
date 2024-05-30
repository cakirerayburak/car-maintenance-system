/**
 * @file DALExpenseReport.java
 * @brief This file contains data access layer methods for ExpenseReport operations.
 */

package com.ebcak.carmaintenance;

import com.ebcak.carmaintenanceumple.ExpenseReport;
import com.ebcak.carmaintenanceumple.ServiceRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALExpenseReport {

    /**
     * @brief Adds a new expense report to the database.
     * @param expenseReport The expense report to be added.
     * @return true if the expense report is successfully added, false otherwise.
     */
    public static boolean addExpenseReport(ExpenseReport expenseReport) {
        String sql = "INSERT INTO expense_report(report_date, daily_fuel, annual_fuel, total_cost, service_record_id) VALUES(?, ?, ?, ?, ?)";
        Connection conn = databaseConnection.getInstance("jdbc:sqlite:./SQLite/carMaintenanceDatabase.db").getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, expenseReport.getReportDate());
            pstmt.setDouble(2, expenseReport.getDailyFuel());
            pstmt.setDouble(3, expenseReport.getAnnualFuel());
            pstmt.setDouble(4, expenseReport.getTotalCost());
            pstmt.setInt(5, expenseReport.getServiceRecord().getRecord_id());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the expense report: " + e.getMessage());
            return false;
        }
    }

    /**
     * @brief Retrieves a list of expense reports associated with a given service record ID.
     * @param serviceRecordId The ID of the service record.
     * @return A list of expense reports associated with the given service record ID.
     */
    public static List<ExpenseReport> getExpenseReportsByServiceRecordId(int serviceRecordId) {
        List<ExpenseReport> expenseReports = new ArrayList<>();
        String sql = "SELECT report_id, report_date, daily_fuel, annual_fuel, total_cost FROM expense_report WHERE service_record_id = ?";

        Connection conn = databaseConnection.getInstance("jdbc:sqlite:./SQLite/carMaintenanceDatabase.db").getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, serviceRecordId);
            ResultSet rs = pstmt.executeQuery();

            ServiceRecord serviceRecord = DALServiceRecord.getServiceRecordById(serviceRecordId); // Retrieve the associated ServiceRecord

            while (rs.next()) {
                int reportId = rs.getInt("report_id");
                java.sql.Date reportDate = rs.getDate("report_date");
                double dailyFuel = rs.getDouble("daily_fuel");
                double annualFuel = rs.getDouble("annual_fuel");
                double totalCost = rs.getDouble("total_cost");

                ExpenseReport expenseReport = new ExpenseReport(reportId, reportDate, dailyFuel, annualFuel, totalCost, serviceRecord.getRecord_id(), serviceRecord);
                expenseReports.add(expenseReport);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the expense reports: " + e.getMessage());
        }

        return expenseReports;
    }

    /**
     * @brief Retrieves a list of expense reports associated with a given driver name.
     * @param driverName The name of the driver.
     * @return A list of expense reports associated with the given driver name.
     */
    public static List<ExpenseReport> getExpenseReportsByDriverName(String driverName) {
        ServiceRecord serviceRecord = DALServiceRecord.getServiceRecordByDriverName(driverName);
        if (serviceRecord != null) {
            return getExpenseReportsByServiceRecordId(serviceRecord.getRecord_id());
        }
        return new ArrayList<>();
    }
}
