package com.ebcak.carmaintenance;

import com.ebcak.carmaintenanceumple.ExpenseReport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DALExpenseReport {

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
}
