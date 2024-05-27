package com.ebcak.carmaintenanceumple;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/



// line 2 "model.ump"
// line 49 "model.ump"
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
  private ServiceRecord serviceRecord;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(int aUser_id, String aUsername, String aPassword, String aEmail, ServiceRecord aServiceRecord)
  {
    user_id = aUser_id;
    username = aUsername;
    password = aPassword;
    email = aEmail;
    boolean didAddServiceRecord = setServiceRecord(aServiceRecord);
    if (!didAddServiceRecord)
    {
      throw new RuntimeException("Unable to create user due to serviceRecord. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
      existingServiceRecord.removeUser(this);
    }
    serviceRecord.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ServiceRecord placeholderServiceRecord = serviceRecord;
    this.serviceRecord = null;
    if(placeholderServiceRecord != null)
    {
      placeholderServiceRecord.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "user_id" + ":" + getUser_id()+ "," +
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "email" + ":" + getEmail()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "serviceRecord = "+(getServiceRecord()!=null?Integer.toHexString(System.identityHashCode(getServiceRecord())):"null");
  }
}