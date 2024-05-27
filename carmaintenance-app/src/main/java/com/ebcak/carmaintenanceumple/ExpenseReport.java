package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;

// line 31 "model.ump"
// line 79 "model.ump"
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

  //ExpenseReport Associations
  private ServiceRecord serviceRecord;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ExpenseReport(int aReport_id, Date aReportDate, double aDailyFuel, double aAnnualFuel, double aTotalCost, ServiceRecord aServiceRecord)
  {
    report_id = aReport_id;
    reportDate = aReportDate;
    dailyFuel = aDailyFuel;
    annualFuel = aAnnualFuel;
    totalCost = aTotalCost;
    if (aServiceRecord == null || aServiceRecord.getExpenseReport() != null)
    {
      throw new RuntimeException("Unable to create ExpenseReport due to aServiceRecord. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    serviceRecord = aServiceRecord;
  }

  public ExpenseReport(int aReport_id, Date aReportDate, double aDailyFuel, double aAnnualFuel, double aTotalCost, int aRecord_idForServiceRecord, String aCarBrandForServiceRecord, String aWhatToDoForServiceRecord, String aDriverNameForServiceRecord, String aDriverPhoneForServiceRecord, int aKilometerForServiceRecord, FuelEfficiencyReport aFuelEfficiencyReportForServiceRecord)
  {
    report_id = aReport_id;
    reportDate = aReportDate;
    dailyFuel = aDailyFuel;
    annualFuel = aAnnualFuel;
    totalCost = aTotalCost;
    serviceRecord = new ServiceRecord(aRecord_idForServiceRecord, aCarBrandForServiceRecord, aWhatToDoForServiceRecord, aDriverNameForServiceRecord, aDriverPhoneForServiceRecord, aKilometerForServiceRecord, aFuelEfficiencyReportForServiceRecord, this);
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

  public int getReport_id()
  {
    return report_id;
  }

  /**
   * Rapor tarihi
   */
  public Date getReportDate()
  {
    return reportDate;
  }

  /**
   * G??nl??k yak??t t??ketimi
   */
  public double getDailyFuel()
  {
    return dailyFuel;
  }

  /**
   * Y??ll??k yak??t t??ketimi
   */
  public double getAnnualFuel()
  {
    return annualFuel;
  }

  /**
   * Toplam masraf miktar??
   */
  public double getTotalCost()
  {
    return totalCost;
  }
  /* Code from template association_GetOne */
  public ServiceRecord getServiceRecord()
  {
    return serviceRecord;
  }

  public void delete()
  {
    ServiceRecord existingServiceRecord = serviceRecord;
    serviceRecord = null;
    if (existingServiceRecord != null)
    {
      existingServiceRecord.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "report_id" + ":" + getReport_id()+ "," +
            "dailyFuel" + ":" + getDailyFuel()+ "," +
            "annualFuel" + ":" + getAnnualFuel()+ "," +
            "totalCost" + ":" + getTotalCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reportDate" + "=" + (getReportDate() != null ? !getReportDate().equals(this)  ? getReportDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "serviceRecord = "+(getServiceRecord()!=null?Integer.toHexString(System.identityHashCode(getServiceRecord())):"null");
  }
}