package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;

// line 25 "model.ump"
// line 65 "model.ump"
public class Reminder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reminder Attributes
  private int reminder_id;
  private Date reminderDate;
  private String reminderType;
  private int serviceRecord_id;

  //Reminder Associations
  private ServiceRecord serviceRecord;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reminder(int aReminder_id, Date aReminderDate, String aReminderType, int aServiceRecord_id, ServiceRecord aServiceRecord)
  {
    reminder_id = aReminder_id;
    reminderDate = aReminderDate;
    reminderType = aReminderType;
    serviceRecord_id = aServiceRecord_id;
    boolean didAddServiceRecord = setServiceRecord(aServiceRecord);
    if (!didAddServiceRecord)
    {
      throw new RuntimeException("Unable to create reminder due to serviceRecord. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReminder_id(int aReminder_id)
  {
    boolean wasSet = false;
    reminder_id = aReminder_id;
    wasSet = true;
    return wasSet;
  }

  public boolean setReminderDate(Date aReminderDate)
  {
    boolean wasSet = false;
    reminderDate = aReminderDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setReminderType(String aReminderType)
  {
    boolean wasSet = false;
    reminderType = aReminderType;
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

  public int getReminder_id()
  {
    return reminder_id;
  }

  public Date getReminderDate()
  {
    return reminderDate;
  }

  public String getReminderType()
  {
    return reminderType;
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
      existingServiceRecord.removeReminder(this);
    }
    serviceRecord.addReminder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ServiceRecord placeholderServiceRecord = serviceRecord;
    this.serviceRecord = null;
    if(placeholderServiceRecord != null)
    {
      placeholderServiceRecord.removeReminder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "reminder_id" + ":" + getReminder_id()+ "," +
            "reminderType" + ":" + getReminderType()+ "," +
            "serviceRecord_id" + ":" + getServiceRecord_id()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reminderDate" + "=" + (getReminderDate() != null ? !getReminderDate().equals(this)  ? getReminderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "serviceRecord = "+(getServiceRecord()!=null?Integer.toHexString(System.identityHashCode(getServiceRecord())):"null");
  }
}