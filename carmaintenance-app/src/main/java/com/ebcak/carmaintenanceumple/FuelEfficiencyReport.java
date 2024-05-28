package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;

// line 35 "model.ump"
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
  private int serviceRecord_id;

  //FuelEfficiencyReport Associations
  private User user;
  private ServiceRecord serviceRecord;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FuelEfficiencyReport(int aReport_id, Date aReportDate, double aMpg, double aLper100km, int aServiceRecord_id, User aUser, ServiceRecord aServiceRecord)
  {
    report_id = aReport_id;
    reportDate = aReportDate;
    mpg = aMpg;
    lper100km = aLper100km;
    serviceRecord_id = aServiceRecord_id;
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create fuelEfficiencyReport due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddServiceRecord = setServiceRecord(aServiceRecord);
    if (!didAddServiceRecord)
    {
      throw new RuntimeException("Unable to create fuelEfficiencyReport due to serviceRecord. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public double getMpg()
  {
    return mpg;
  }

  public double getLper100km()
  {
    return lper100km;
  }

  /**
   * Reference to the ServiceRecord
   */
  public int getServiceRecord_id()
  {
    return serviceRecord_id;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetOne */
  public ServiceRecord getServiceRecord()
  {
    return serviceRecord;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeFuelEfficiencyReport(this);
    }
    user.addFuelEfficiencyReport(this);
    wasSet = true;
    return wasSet;
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
      existingServiceRecord.removeFuelEfficiencyReport(this);
    }
    serviceRecord.addFuelEfficiencyReport(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeFuelEfficiencyReport(this);
    }
    ServiceRecord placeholderServiceRecord = serviceRecord;
    this.serviceRecord = null;
    if(placeholderServiceRecord != null)
    {
      placeholderServiceRecord.removeFuelEfficiencyReport(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "report_id" + ":" + getReport_id()+ "," +
            "mpg" + ":" + getMpg()+ "," +
            "lper100km" + ":" + getLper100km()+ "," +
            "serviceRecord_id" + ":" + getServiceRecord_id()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reportDate" + "=" + (getReportDate() != null ? !getReportDate().equals(this)  ? getReportDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "serviceRecord = "+(getServiceRecord()!=null?Integer.toHexString(System.identityHashCode(getServiceRecord())):"null");
  }
}