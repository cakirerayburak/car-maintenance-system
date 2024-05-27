package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;

// line 24 "model.ump"
// line 73 "model.ump"
public class FuelEfficiencyReport
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FuelEfficiencyReport Attributes
  private int report_id;
  private Date reportDate;
  private double mpg;
  private double lper100km;

  //FuelEfficiencyReport Associations
  private ServiceRecord serviceRecord;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FuelEfficiencyReport(int aReport_id, Date aReportDate, double aMpg, double aLper100km, ServiceRecord aServiceRecord)
  {
    report_id = aReport_id;
    reportDate = aReportDate;
    mpg = aMpg;
    lper100km = aLper100km;
    if (aServiceRecord == null || aServiceRecord.getFuelEfficiencyReport() != null)
    {
      throw new RuntimeException("Unable to create FuelEfficiencyReport due to aServiceRecord. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    serviceRecord = aServiceRecord;
  }

  public FuelEfficiencyReport(int aReport_id, Date aReportDate, double aMpg, double aLper100km, int aRecord_idForServiceRecord, String aCarBrandForServiceRecord, String aWhatToDoForServiceRecord, String aDriverNameForServiceRecord, String aDriverPhoneForServiceRecord, int aKilometerForServiceRecord, ExpenseReport aExpenseReportForServiceRecord)
  {
    report_id = aReport_id;
    reportDate = aReportDate;
    mpg = aMpg;
    lper100km = aLper100km;
    serviceRecord = new ServiceRecord(aRecord_idForServiceRecord, aCarBrandForServiceRecord, aWhatToDoForServiceRecord, aDriverNameForServiceRecord, aDriverPhoneForServiceRecord, aKilometerForServiceRecord, this, aExpenseReportForServiceRecord);
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

  public boolean setMpg(double aMpg)
  {
    boolean wasSet = false;
    mpg = aMpg;
    wasSet = true;
    return wasSet;
  }

  public boolean setLper100km(double aLper100km)
  {
    boolean wasSet = false;
    lper100km = aLper100km;
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
   * Miles per gallon
   */
  public double getMpg()
  {
    return mpg;
  }

  /**
   * Liters per 100 kilometers
   */
  public double getLper100km()
  {
    return lper100km;
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
            "mpg" + ":" + getMpg()+ "," +
            "lper100km" + ":" + getLper100km()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reportDate" + "=" + (getReportDate() != null ? !getReportDate().equals(this)  ? getReportDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "serviceRecord = "+(getServiceRecord()!=null?Integer.toHexString(System.identityHashCode(getServiceRecord())):"null");
  }
}