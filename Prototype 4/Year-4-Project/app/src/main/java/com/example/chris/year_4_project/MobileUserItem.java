package com.example.chris.year_4_project;

/**
 * Created by Chris on 25/01/2015.
 */
public class MobileUserItem
{
    private int mobileUserID;
    private String username;
    private String password;
    private String mobileUserEmail;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getMobileUserID() { return mobileUserID; }
    public String getMobileUserEmail() { return mobileUserEmail; }

    public void setUserName(String theUsername) { username = theUsername; }
    public void setPassword(String thePassword) { password = thePassword; }
    public void setMobileUserID(int theMobileUserID) { mobileUserID = theMobileUserID; }
    public void setMobileUserEmail(String theMobileUserEmail){ mobileUserEmail = theMobileUserEmail; }
}
