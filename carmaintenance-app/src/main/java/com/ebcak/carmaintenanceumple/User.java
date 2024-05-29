package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 53 "model.ump"
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

  public void delete()
  {
    for(int i=serviceRecords.size(); i > 0; i--)
    {
      ServiceRecord aServiceRecord = serviceRecords.get(i - 1);
      aServiceRecord.delete();
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