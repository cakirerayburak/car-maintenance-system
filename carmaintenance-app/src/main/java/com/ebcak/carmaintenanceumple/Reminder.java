package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.sql.Date;

// line 39 "model.ump"
// line 58 "model.ump"
public class Reminder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reminder Attributes
  private int reminder_id;
  private Date reminderDate;
  private String reminderType;

  //Reminder Associations
  private ServiceRecord serviceRecord;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reminder(int aReminder_id, Date aReminderDate, String aReminderType, ServiceRecord aServiceRecord)
  {
    reminder_id = aReminder_id;
    reminderDate = aReminderDate;
    reminderType = aReminderType;
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

  public int getReminder_id()
  {
    return reminder_id;
  }

  /**
   * Hat??rlat??c?? tarihi
   */
  public Date getReminderDate()
  {
    return reminderDate;
  }

  /**
   * Hat??rlat??c?? t??r?? (??rn. ya?? de??i??imi, fren kontrol??)
   */
  public String getReminderType()
  {
    return reminderType;
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
            "reminderType" + ":" + getReminderType()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reminderDate" + "=" + (getReminderDate() != null ? !getReminderDate().equals(this)  ? getReminderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "serviceRecord = "+(getServiceRecord()!=null?Integer.toHexString(System.identityHashCode(getServiceRecord())):"null");
  }
}