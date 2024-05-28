package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 2 "model.ump"
// line 56 "model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private int user_id;
  private String username;
  private String password;
  private String email;

  //User Associations
  private List<ServiceRecord> serviceRecords;
  private List<Reminder> reminders;
  private List<FuelEfficiencyReport> fuelEfficiencyReports;
  private List<ExpenseReport> expenseReports;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(int aUser_id, String aUsername, String aPassword, String aEmail)
  {
    user_id = aUser_id;
    username = aUsername;
    password = aPassword;
    email = aEmail;
    serviceRecords = new ArrayList<ServiceRecord>();
    reminders = new ArrayList<Reminder>();
    fuelEfficiencyReports = new ArrayList<FuelEfficiencyReport>();
    expenseReports = new ArrayList<ExpenseReport>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUser_id(int aUser_id)
  {
    boolean wasSet = false;
    user_id = aUser_id;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public int getUser_id()
  {
    return user_id;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template association_GetMany */
  public ServiceRecord getServiceRecord(int index)
  {
    ServiceRecord aServiceRecord = serviceRecords.get(index);
    return aServiceRecord;
  }

  public List<ServiceRecord> getServiceRecords()
  {
    List<ServiceRecord> newServiceRecords = Collections.unmodifiableList(serviceRecords);
    return newServiceRecords;
  }

  public int numberOfServiceRecords()
  {
    int number = serviceRecords.size();
    return number;
  }

  public boolean hasServiceRecords()
  {
    boolean has = serviceRecords.size() > 0;
    return has;
  }

  public int indexOfServiceRecord(ServiceRecord aServiceRecord)
  {
    int index = serviceRecords.indexOf(aServiceRecord);
    return index;
  }
  /* Code from template association_GetMany */
  public Reminder getReminder(int index)
  {
    Reminder aReminder = reminders.get(index);
    return aReminder;
  }

  public List<Reminder> getReminders()
  {
    List<Reminder> newReminders = Collections.unmodifiableList(reminders);
    return newReminders;
  }

  public int numberOfReminders()
  {
    int number = reminders.size();
    return number;
  }

  public boolean hasReminders()
  {
    boolean has = reminders.size() > 0;
    return has;
  }

  public int indexOfReminder(Reminder aReminder)
  {
    int index = reminders.indexOf(aReminder);
    return index;
  }
  /* Code from template association_GetMany */
  public FuelEfficiencyReport getFuelEfficiencyReport(int index)
  {
    FuelEfficiencyReport aFuelEfficiencyReport = fuelEfficiencyReports.get(index);
    return aFuelEfficiencyReport;
  }

  public List<FuelEfficiencyReport> getFuelEfficiencyReports()
  {
    List<FuelEfficiencyReport> newFuelEfficiencyReports = Collections.unmodifiableList(fuelEfficiencyReports);
    return newFuelEfficiencyReports;
  }

  public int numberOfFuelEfficiencyReports()
  {
    int number = fuelEfficiencyReports.size();
    return number;
  }

  public boolean hasFuelEfficiencyReports()
  {
    boolean has = fuelEfficiencyReports.size() > 0;
    return has;
  }

  public int indexOfFuelEfficiencyReport(FuelEfficiencyReport aFuelEfficiencyReport)
  {
    int index = fuelEfficiencyReports.indexOf(aFuelEfficiencyReport);
    return index;
  }
  /* Code from template association_GetMany */
  public ExpenseReport getExpenseReport(int index)
  {
    ExpenseReport aExpenseReport = expenseReports.get(index);
    return aExpenseReport;
  }

  public List<ExpenseReport> getExpenseReports()
  {
    List<ExpenseReport> newExpenseReports = Collections.unmodifiableList(expenseReports);
    return newExpenseReports;
  }

  public int numberOfExpenseReports()
  {
    int number = expenseReports.size();
    return number;
  }

  public boolean hasExpenseReports()
  {
    boolean has = expenseReports.size() > 0;
    return has;
  }

  public int indexOfExpenseReport(ExpenseReport aExpenseReport)
  {
    int index = expenseReports.indexOf(aExpenseReport);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServiceRecords()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceRecord addServiceRecord(int aRecord_id, String aCarBrand, String aWhatToDo, String aDriverName, String aDriverPhone, int aKilometer, int aUser_id)
  {
    return new ServiceRecord(aRecord_id, aCarBrand, aWhatToDo, aDriverName, aDriverPhone, aKilometer, aUser_id, this);
  }

  public boolean addServiceRecord(ServiceRecord aServiceRecord)
  {
    boolean wasAdded = false;
    if (serviceRecords.contains(aServiceRecord)) { return false; }
    User existingUser = aServiceRecord.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aServiceRecord.setUser(this);
    }
    else
    {
      serviceRecords.add(aServiceRecord);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeServiceRecord(ServiceRecord aServiceRecord)
  {
    boolean wasRemoved = false;
    //Unable to remove aServiceRecord, as it must always have a user
    if (!this.equals(aServiceRecord.getUser()))
    {
      serviceRecords.remove(aServiceRecord);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceRecordAt(ServiceRecord aServiceRecord, int index)
  {  
    boolean wasAdded = false;
    if(addServiceRecord(aServiceRecord))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceRecords()) { index = numberOfServiceRecords() - 1; }
      serviceRecords.remove(aServiceRecord);
      serviceRecords.add(index, aServiceRecord);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceRecordAt(ServiceRecord aServiceRecord, int index)
  {
    boolean wasAdded = false;
    if(serviceRecords.contains(aServiceRecord))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceRecords()) { index = numberOfServiceRecords() - 1; }
      serviceRecords.remove(aServiceRecord);
      serviceRecords.add(index, aServiceRecord);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceRecordAt(aServiceRecord, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReminders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reminder addReminder(int aReminder_id, Date aReminderDate, String aReminderType, int aServiceRecord_id, ServiceRecord aServiceRecord)
  {
    return new Reminder(aReminder_id, aReminderDate, aReminderType, aServiceRecord_id, this, aServiceRecord);
  }

  public boolean addReminder(Reminder aReminder)
  {
    boolean wasAdded = false;
    if (reminders.contains(aReminder)) { return false; }
    User existingUser = aReminder.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aReminder.setUser(this);
    }
    else
    {
      reminders.add(aReminder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeReminder(Reminder aReminder)
  {
    boolean wasRemoved = false;
    //Unable to remove aReminder, as it must always have a user
    if (!this.equals(aReminder.getUser()))
    {
      reminders.remove(aReminder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addReminderAt(Reminder aReminder, int index)
  {  
    boolean wasAdded = false;
    if(addReminder(aReminder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReminders()) { index = numberOfReminders() - 1; }
      reminders.remove(aReminder);
      reminders.add(index, aReminder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReminderAt(Reminder aReminder, int index)
  {
    boolean wasAdded = false;
    if(reminders.contains(aReminder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReminders()) { index = numberOfReminders() - 1; }
      reminders.remove(aReminder);
      reminders.add(index, aReminder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReminderAt(aReminder, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFuelEfficiencyReports()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public FuelEfficiencyReport addFuelEfficiencyReport(int aReport_id, Date aReportDate, double aMpg, double aLper100km, int aServiceRecord_id, ServiceRecord aServiceRecord)
  {
    return new FuelEfficiencyReport(aReport_id, aReportDate, aMpg, aLper100km, aServiceRecord_id, this, aServiceRecord);
  }

  public boolean addFuelEfficiencyReport(FuelEfficiencyReport aFuelEfficiencyReport)
  {
    boolean wasAdded = false;
    if (fuelEfficiencyReports.contains(aFuelEfficiencyReport)) { return false; }
    User existingUser = aFuelEfficiencyReport.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aFuelEfficiencyReport.setUser(this);
    }
    else
    {
      fuelEfficiencyReports.add(aFuelEfficiencyReport);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFuelEfficiencyReport(FuelEfficiencyReport aFuelEfficiencyReport)
  {
    boolean wasRemoved = false;
    //Unable to remove aFuelEfficiencyReport, as it must always have a user
    if (!this.equals(aFuelEfficiencyReport.getUser()))
    {
      fuelEfficiencyReports.remove(aFuelEfficiencyReport);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFuelEfficiencyReportAt(FuelEfficiencyReport aFuelEfficiencyReport, int index)
  {  
    boolean wasAdded = false;
    if(addFuelEfficiencyReport(aFuelEfficiencyReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFuelEfficiencyReports()) { index = numberOfFuelEfficiencyReports() - 1; }
      fuelEfficiencyReports.remove(aFuelEfficiencyReport);
      fuelEfficiencyReports.add(index, aFuelEfficiencyReport);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFuelEfficiencyReportAt(FuelEfficiencyReport aFuelEfficiencyReport, int index)
  {
    boolean wasAdded = false;
    if(fuelEfficiencyReports.contains(aFuelEfficiencyReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFuelEfficiencyReports()) { index = numberOfFuelEfficiencyReports() - 1; }
      fuelEfficiencyReports.remove(aFuelEfficiencyReport);
      fuelEfficiencyReports.add(index, aFuelEfficiencyReport);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFuelEfficiencyReportAt(aFuelEfficiencyReport, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfExpenseReports()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ExpenseReport addExpenseReport(int aReport_id, Date aReportDate, double aDailyFuel, double aAnnualFuel, double aTotalCost, int aServiceRecord_id, ServiceRecord aServiceRecord)
  {
    return new ExpenseReport(aReport_id, aReportDate, aDailyFuel, aAnnualFuel, aTotalCost, aServiceRecord_id, this, aServiceRecord);
  }

  public boolean addExpenseReport(ExpenseReport aExpenseReport)
  {
    boolean wasAdded = false;
    if (expenseReports.contains(aExpenseReport)) { return false; }
    User existingUser = aExpenseReport.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aExpenseReport.setUser(this);
    }
    else
    {
      expenseReports.add(aExpenseReport);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeExpenseReport(ExpenseReport aExpenseReport)
  {
    boolean wasRemoved = false;
    //Unable to remove aExpenseReport, as it must always have a user
    if (!this.equals(aExpenseReport.getUser()))
    {
      expenseReports.remove(aExpenseReport);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addExpenseReportAt(ExpenseReport aExpenseReport, int index)
  {  
    boolean wasAdded = false;
    if(addExpenseReport(aExpenseReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExpenseReports()) { index = numberOfExpenseReports() - 1; }
      expenseReports.remove(aExpenseReport);
      expenseReports.add(index, aExpenseReport);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveExpenseReportAt(ExpenseReport aExpenseReport, int index)
  {
    boolean wasAdded = false;
    if(expenseReports.contains(aExpenseReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExpenseReports()) { index = numberOfExpenseReports() - 1; }
      expenseReports.remove(aExpenseReport);
      expenseReports.add(index, aExpenseReport);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addExpenseReportAt(aExpenseReport, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=serviceRecords.size(); i > 0; i--)
    {
      ServiceRecord aServiceRecord = serviceRecords.get(i - 1);
      aServiceRecord.delete();
    }
    for(int i=reminders.size(); i > 0; i--)
    {
      Reminder aReminder = reminders.get(i - 1);
      aReminder.delete();
    }
    for(int i=fuelEfficiencyReports.size(); i > 0; i--)
    {
      FuelEfficiencyReport aFuelEfficiencyReport = fuelEfficiencyReports.get(i - 1);
      aFuelEfficiencyReport.delete();
    }
    for(int i=expenseReports.size(); i > 0; i--)
    {
      ExpenseReport aExpenseReport = expenseReports.get(i - 1);
      aExpenseReport.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "user_id" + ":" + getUser_id()+ "," +
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "email" + ":" + getEmail()+ "]";
  }
}