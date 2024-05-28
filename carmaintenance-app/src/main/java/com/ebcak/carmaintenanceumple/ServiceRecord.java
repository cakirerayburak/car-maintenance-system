package com.ebcak.carmaintenanceumple;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 14 "model.ump"
// line 61 "model.ump"
public class ServiceRecord
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceRecord Attributes
  private int record_id;
  private String carBrand;
  private String whatToDo;
  private String driverName;
  private String driverPhone;
  private int kilometer;
  private int user_id;

  //ServiceRecord Associations
  private List<Reminder> reminders;
  private List<FuelEfficiencyReport> fuelEfficiencyReports;
  private List<ExpenseReport> expenseReports;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceRecord(int aRecord_id, String aCarBrand, String aWhatToDo, String aDriverName, String aDriverPhone, int aKilometer, int aUser_id, User aUser)
  {
    record_id = aRecord_id;
    carBrand = aCarBrand;
    whatToDo = aWhatToDo;
    driverName = aDriverName;
    driverPhone = aDriverPhone;
    kilometer = aKilometer;
    user_id = aUser_id;
    reminders = new ArrayList<Reminder>();
    fuelEfficiencyReports = new ArrayList<FuelEfficiencyReport>();
    expenseReports = new ArrayList<ExpenseReport>();
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create serviceRecord due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRecord_id(int aRecord_id)
  {
    boolean wasSet = false;
    record_id = aRecord_id;
    wasSet = true;
    return wasSet;
  }

  public boolean setCarBrand(String aCarBrand)
  {
    boolean wasSet = false;
    carBrand = aCarBrand;
    wasSet = true;
    return wasSet;
  }

  public boolean setWhatToDo(String aWhatToDo)
  {
    boolean wasSet = false;
    whatToDo = aWhatToDo;
    wasSet = true;
    return wasSet;
  }

  public boolean setDriverName(String aDriverName)
  {
    boolean wasSet = false;
    driverName = aDriverName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDriverPhone(String aDriverPhone)
  {
    boolean wasSet = false;
    driverPhone = aDriverPhone;
    wasSet = true;
    return wasSet;
  }

  public boolean setKilometer(int aKilometer)
  {
    boolean wasSet = false;
    kilometer = aKilometer;
    wasSet = true;
    return wasSet;
  }

  public boolean setUser_id(int aUser_id)
  {
    boolean wasSet = false;
    user_id = aUser_id;
    wasSet = true;
    return wasSet;
  }

  public int getRecord_id()
  {
    return record_id;
  }

  public String getCarBrand()
  {
    return carBrand;
  }

  public String getWhatToDo()
  {
    return whatToDo;
  }

  public String getDriverName()
  {
    return driverName;
  }

  public String getDriverPhone()
  {
    return driverPhone;
  }

  public int getKilometer()
  {
    return kilometer;
  }

  /**
   * Reference to the User
   */
  public int getUser_id()
  {
    return user_id;
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
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReminders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reminder addReminder(int aReminder_id, Date aReminderDate, String aReminderType, int aServiceRecord_id, User aUser)
  {
    return new Reminder(aReminder_id, aReminderDate, aReminderType, aServiceRecord_id, aUser, this);
  }

  public boolean addReminder(Reminder aReminder)
  {
    boolean wasAdded = false;
    if (reminders.contains(aReminder)) { return false; }
    ServiceRecord existingServiceRecord = aReminder.getServiceRecord();
    boolean isNewServiceRecord = existingServiceRecord != null && !this.equals(existingServiceRecord);
    if (isNewServiceRecord)
    {
      aReminder.setServiceRecord(this);
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
    //Unable to remove aReminder, as it must always have a serviceRecord
    if (!this.equals(aReminder.getServiceRecord()))
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
  public FuelEfficiencyReport addFuelEfficiencyReport(int aReport_id, Date aReportDate, double aMpg, double aLper100km, int aServiceRecord_id, User aUser)
  {
    return new FuelEfficiencyReport(aReport_id, aReportDate, aMpg, aLper100km, aServiceRecord_id, aUser, this);
  }

  public boolean addFuelEfficiencyReport(FuelEfficiencyReport aFuelEfficiencyReport)
  {
    boolean wasAdded = false;
    if (fuelEfficiencyReports.contains(aFuelEfficiencyReport)) { return false; }
    ServiceRecord existingServiceRecord = aFuelEfficiencyReport.getServiceRecord();
    boolean isNewServiceRecord = existingServiceRecord != null && !this.equals(existingServiceRecord);
    if (isNewServiceRecord)
    {
      aFuelEfficiencyReport.setServiceRecord(this);
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
    //Unable to remove aFuelEfficiencyReport, as it must always have a serviceRecord
    if (!this.equals(aFuelEfficiencyReport.getServiceRecord()))
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
  public ExpenseReport addExpenseReport(int aReport_id, Date aReportDate, double aDailyFuel, double aAnnualFuel, double aTotalCost, int aServiceRecord_id, User aUser)
  {
    return new ExpenseReport(aReport_id, aReportDate, aDailyFuel, aAnnualFuel, aTotalCost, aServiceRecord_id, aUser, this);
  }

  public boolean addExpenseReport(ExpenseReport aExpenseReport)
  {
    boolean wasAdded = false;
    if (expenseReports.contains(aExpenseReport)) { return false; }
    ServiceRecord existingServiceRecord = aExpenseReport.getServiceRecord();
    boolean isNewServiceRecord = existingServiceRecord != null && !this.equals(existingServiceRecord);
    if (isNewServiceRecord)
    {
      aExpenseReport.setServiceRecord(this);
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
    //Unable to remove aExpenseReport, as it must always have a serviceRecord
    if (!this.equals(aExpenseReport.getServiceRecord()))
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
      existingUser.removeServiceRecord(this);
    }
    user.addServiceRecord(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
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
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeServiceRecord(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "record_id" + ":" + getRecord_id()+ "," +
            "carBrand" + ":" + getCarBrand()+ "," +
            "whatToDo" + ":" + getWhatToDo()+ "," +
            "driverName" + ":" + getDriverName()+ "," +
            "driverPhone" + ":" + getDriverPhone()+ "," +
            "kilometer" + ":" + getKilometer()+ "," +
            "user_id" + ":" + getUser_id()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}