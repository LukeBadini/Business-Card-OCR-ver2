/**
 * A storage class that contains the parsed contents of a business card:
 * name, phone number, and email address
 *
 */
public class ContactInfo
{
    private String name;
    private String phoneNumber;
    private String emailAddress;
        
    
    public ContactInfo()
    {
        name = phoneNumber = emailAddress = "";
    }
    
    /**
     * Constructs a new ContactInfo object with the specified name,
     * phone number, and email address
     * @param aName
     * @param aPhoneNumber
     * @param anEmailAddress
     */
    public ContactInfo(String aName, String aPhoneNumber, String anEmailAddress)
    {
        name = phoneNumber = emailAddress = "";
        setName(aName);
        setPhoneNumber(aPhoneNumber);
        setEmailAddress(anEmailAddress);
    }
    
    /**
     * Sets the name of this ContactInfo object
     * 
     * @param aName - a provided name String
     */
    private void setName(String aName)
    {
        if(aName != null)
        {
            name = aName;
        }
    }
    
    /**
     * Sets the phone number of this ContactInfo object
     * 
     * @param aPhoneNumber - a provided phone number String
     */
    private void setPhoneNumber(String aPhoneNumber)
    {
        if(aPhoneNumber != null)
        {
            phoneNumber = aPhoneNumber;
        }
    }
    
    /**
     * Sets the email address of this ContactInfo object
     *  
     * @param anEmailAddress - a provided email address String
     */
    private void setEmailAddress(String anEmailAddress)
    {
        if(anEmailAddress != null)
        {
            emailAddress = anEmailAddress;
        }
    }
    
    /**
     * @return a String containing the name of this ContactInfo object
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return a String containing the phone number of this ContactInfo object
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    /**
     * @return a String containing the email address of this ContactInfo object
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }
    
    public String toString()
    {
        return "Name: " + name + "\nPhone: " + phoneNumber +
                    "\nEmail: " + emailAddress;
    }
}
