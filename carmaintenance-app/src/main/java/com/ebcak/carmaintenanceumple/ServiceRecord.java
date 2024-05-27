package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 11 "model.ump"
// line 64 "model.ump"
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

  //ServiceRecord Associations
  private FuelEfficiencyReport fuelEfficiencyReport;
  private ExpenseReport expenseReport;
  private List<Reminder> reminders;
  private List<User> users;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceRecord(int aRecord_id, String aCarBrand, String aWhatToDo, String aDriverName, String aDriverPhone, int aKilometer, FuelEfficiencyReport aFuelEfficiencyReport, ExpenseReport aExpenseReport)
  {
    record_id = aRecord_id;
    carBrand = aCarBrand;
    whatToDo = aWhatToDo;
    driverName = aDriverName;
    driverPhone = aDriverPhone;
    kilometer = aKilometer;
    if (aFuelEfficiencyReport == null || aFuelEfficiencyReport.getServiceRecord() != null)
    {
      throw new RuntimeException("Unable to create ServiceRecord due to aFuelEfficiencyReport. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    fuelEfficiencyReport = aFuelEfficiencyReport;
    if (aExpenseReport == null || aExpenseReport.getServiceRecord() != null)
    {
      throw new RuntimeException("Unable to create ServiceRecord due to aExpenseReport. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    expenseReport = aExpenseReport;
    reminders = new ArrayList<Reminder>();
    users = new ArrayList<User>();
  }

  public ServiceRecord(int aRecord_id, String aCarBrand, String aWhatToDo, String aDriverName, String aDriverPhone, int aKilometer, int aReport_idForFuelEfficiencyReport, Date aReportDateForFuelEfficiencyReport, double aMpgForFuelEfficiencyReport, double aLper100kmForFuelEfficiencyReport, int aReport_idForExpenseReport, Date aReportDateForExpenseReport, double aDailyFuelForExpenseReport, double aAnnualFuelForExpenseReport, double aTotalCostForExpenseReport)
  {
    record_id = aRecord_id;
    carBrand = aCarBrand;
    whatToDo = aWhatToDo;
    driverName = aDriverName;
    driverPhone = aDriverPhone;
    kilometer = aKilometer;
    fuelEfficiencyReport = new FuelEfficiencyReport(aReport_idForFuelEfficiencyReport, aReportDateForFuelEfficiencyReport, aMpgForFuelEfficiencyReport, aLper100kmForFuelEfficiencyReport, this);
    expenseReport = new ExpenseReport(aReport_idForExpenseReport, aReportDateForExpenseReport, aDailyFuelForExpenseReport, aAnnualFuelForExpenseReport, aTotalCostForExpenseReport, this);
    reminders = new ArrayList<Reminder>();
    users = new ArrayList<User>();
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

  public int getRecord_id()
  {
    return record_id;
  }

  /**
   * Ara?? markas??
   */
  public String getCarBrand()
  {
    return carBrand;
  }

  /**
   * Yap??lacak i??lemler
   */
  public String getWhatToDo()
  {
    return whatToDo;
  }

  /**
   * S??r??c??n??n ad?? (zorunlu alan)
   */
  public String getDriverName()
  {
    return driverName;
  }

  /**
   * S??r??c??n??n telefon numaras??
   */
  public String getDriverPhone()
  {
    return driverPhone;
  }

  /**
   * Ara?? kilometre bilgisi
   */
  public int getKilometer()
  {
    return kilometer;
  }
  /* Code from template association_GetOne */
  public FuelEfficiencyReport getFuelEfficiencyReport()
  {
    return fuelEfficiencyReport;
  }
  /* Code from template association_GetOne */
  public ExpenseReport getExpenseReport()
  {
    return expenseReport;
  }
  /* Code from template association_GetMany */
  public Reminder getReminder(int index)
  {
    Reminder aReminder = reminders.get(index);
    return aReminder;
  }

  /**
   * Her servis kayd?? i??in birden fazla hat??rlat??c??
   */
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
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfReminders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Reminder addReminder(int aReminder_id, Date aReminderDate, String aReminderType)
  {
    return new Reminder(aReminder_id, aReminderDate, aReminderType, this);
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
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(int aUser_id, String aUsername, String aPassword, String aEmail)
  {
    return new User(aUser_id, aUsername, aPassword, aEmail, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    ServiceRecord existingServiceRecord = aUser.getServiceRecord();
    boolean isNewServiceRecord = existingServiceRecord != null && !this.equals(existingServiceRecord);
    if (isNewServiceRecord)
    {
      aUser.setServiceRecord(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a serviceRecord
    if (!this.equals(aUser.getServiceRecord()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    FuelEfficiencyReport existingFuelEfficiencyReport = fuelEfficiencyReport;
    fuelEfficiencyReport = null;
    if (existingFuelEfficiencyReport != null)
    {
      existingFuelEfficiencyReport.delete();
    }
    ExpenseReport existingExpenseReport = expenseReport;
    expenseReport = null;
    if (existingExpenseReport != null)
    {
      existingExpenseReport.delete();
    }
    for(int i=reminders.size(); i > 0; i--)
    {
      Reminder aReminder = reminders.get(i - 1);
      aReminder.delete();
    }
    for(int i=users.size(); i > 0; i--)
    {
      User aUser = users.get(i - 1);
      aUser.delete();
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
            "kilometer" + ":" + getKilometer()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fuelEfficiencyReport = "+(getFuelEfficiencyReport()!=null?Integer.toHexString(System.identityHashCode(getFuelEfficiencyReport())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "expenseReport = "+(getExpenseReport()!=null?Integer.toHexString(System.identityHashCode(getExpenseReport())):"null");
  }
}