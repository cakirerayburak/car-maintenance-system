package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

import java.sql.Date;

// line 40 "model.ump"
// line 75 "model.ump"
public class ExpenseReport
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ExpenseReport Attributes
  private int report_id;
  private Date reportDate;
  private double dailyFuel;
  private double annualFuel;
  private double totalCost;
  private int serviceRecord_id;

  //ExpenseReport Associations
  private ServiceRecord serviceRecord;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ExpenseReport(int aReport_id, Date aReportDate, double aDailyFuel, double aAnnualFuel, double aTotalCost, int aServiceRecord_id, ServiceRecord aServiceRecord)
  {
    report_id = aReport_id;
    reportDate = aReportDate;
    dailyFuel = aDailyFuel;
    annualFuel = aAnnualFuel;
    totalCost = aTotalCost;
    serviceRecord_id = aServiceRecord_id;
    boolean didAddServiceRecord = setServiceRecord(aServiceRecord);
    if (!didAddServiceRecord)
    {
      throw new RuntimeException("Unable to create expenseReport due to serviceRecord. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReport_id(int aReport_id)
  {
    boolean wasSet = false;
    report_id = aReport_id;
    wasSet = true;
    return wasSet;
  }

  public boolean setReportDate(Date aReportDate)
  {
    boolean wasSet = false;
    reportDate = aReportDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDailyFuel(double aDailyFuel)
  {
    boolean wasSet = false;
    dailyFuel = aDailyFuel;
    wasSet = true;
    return wasSet;
  }

  public boolean setAnnualFuel(double aAnnualFuel)
  {
    boolean wasSet = false;
    annualFuel = aAnnualFuel;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalCost(double aTotalCost)
  {
    boolean wasSet = false;
    totalCost = aTotalCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setServiceRecord_id(int aServiceRecord_id)
  {
    boolean wasSet = false;
    serviceRecord_id = aServiceRecord_id;
    wasSet = true;
    return wasSet;
  }

  public int getReport_id()
  {
    return report_id;
  }

  public Date getReportDate()
  {
    return reportDate;
  }

  public double getDailyFuel()
  {
    return dailyFuel;
  }

  public double getAnnualFuel()
  {
    return annualFuel;
  }

  public double getTotalCost()
  {
    return totalCost;
  }

  public int getServiceRecord_id()
  {
    return serviceRecord_id;
  }
  /* Code from template association_GetOne */
  public ServiceRecord getServiceRecord()
  {
    return serviceRecord;
  }
  /* Code from template association_SetOneToMany */
  public boolean setServiceRecord(ServiceRecord aServiceRecord)
  {
    boolean wasSet = false;
    if (aServiceRecord == null)
    {
      return wasSet;
    }

    ServiceRecord existingServiceRecord = serviceRecord;
    serviceRecord = aServiceRecord;
    if (existingServiceRecord != null && !existingServiceRecord.equals(aServiceRecord))
    {
      existingServiceRecord.removeExpenseReport(this);
    }
    serviceRecord.addExpenseReport(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ServiceRecord placeholderServiceRecord = serviceRecord;
    this.serviceRecord = null;
    if(placeholderServiceRecord != null)
    {
      placeholderServiceRecord.removeExpenseReport(this);
    }
  }

  @Override
  public String toString()
  {
    return "ExpenseReport[" +
            "report_id:" + getReport_id() + "," +
            "dailyFuel:" + getDailyFuel() + "," +
            "annualFuel:" + getAnnualFuel() + "," +
            "totalCost:" + getTotalCost() + "," +
            "serviceRecord_id:" + getServiceRecord_id() + "]\n" +
            "  reportDate=" + (getReportDate() != null ? getReportDate().toString() : "null") + "\n" +
            "  serviceRecord = " + (getServiceRecord() != null ? Integer.toHexString(System.identityHashCode(getServiceRecord())) : "null");
  }
}
