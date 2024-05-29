package com.ebcak.carmaintenanceumple;

import java.sql.Date;

public class Reminder {

    private int reminder_id;
    private Date reminderDate;
    private String reminderType;
    private int serviceRecord_id;
    private ServiceRecord serviceRecord;

    public Reminder(int reminder_id, Date reminderDate, String reminderType, int serviceRecord_id, ServiceRecord serviceRecord) {
        this.reminder_id = reminder_id;
        this.reminderDate = reminderDate;
        this.reminderType = reminderType;
        this.serviceRecord_id = serviceRecord_id;
        boolean didAddServiceRecord = setServiceRecord(serviceRecord);
        if (!didAddServiceRecord) {
            throw new RuntimeException("Unable to create reminder due to serviceRecord. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
    }

    public int getReminder_id() {
        return reminder_id;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public String getReminderType() {
        return reminderType;
    }

    public int getServiceRecord_id() {
        return serviceRecord_id;
    }

    public ServiceRecord getServiceRecord() {
        return serviceRecord;
    }

    public boolean setReminder_id(int reminder_id) {
        this.reminder_id = reminder_id;
        return true;
    }

    public boolean setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
        return true;
    }

    public boolean setReminderType(String reminderType) {
        this.reminderType = reminderType;
        return true;
    }

    public boolean setServiceRecord_id(int serviceRecord_id) {
        this.serviceRecord_id = serviceRecord_id;
        return true;
    }

    public boolean setServiceRecord(ServiceRecord serviceRecord) {
        if (serviceRecord == null) {
            return false;
        }

        ServiceRecord existingServiceRecord = this.serviceRecord;
        this.serviceRecord = serviceRecord;
        if (existingServiceRecord != null && !existingServiceRecord.equals(serviceRecord)) {
            existingServiceRecord.removeReminder(this);
        }
        serviceRecord.addReminder(this);
        return true;
    }

    public void delete() {
        ServiceRecord placeholderServiceRecord = serviceRecord;
        this.serviceRecord = null;
        if (placeholderServiceRecord != null) {
            placeholderServiceRecord.removeReminder(this);
        }
    }

    public String toString() {
        return super.toString() + "[" +
                "reminder_id" + ":" + getReminder_id() + "," +
                "reminderType" + ":" + getReminderType() + "," +
                "serviceRecord_id" + ":" + getServiceRecord_id() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "reminderDate" + "=" + (getReminderDate() != null ? !getReminderDate().equals(this) ? getReminderDate().toString().replaceAll("  ", "    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
                "  " + "serviceRecord = " + (getServiceRecord() != null ? Integer.toHexString(System.identityHashCode(getServiceRecord())) : "null");
    }
}
