import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * A class that parses a business card String into a ContactInfo object
 *
 */
public class BusinessCardParser
{
    
    /**
     * Parses a given string representing a business card into a ContactInfo
     * object that contains the name, phone number, and email address on
     * the business card
     * 
     * @param document - the input business card string
     * @return a ContactInfo object with the parsed name, phone number,
     *         and email address
     */
    public ContactInfo getContactInfo(String document)
    {
        if(document == null)
        {
            return null;
        }
        return parseBusinessCard(document);
    }
    
    /**
     * Helper method that does the bulk of the work for getContactInfo()
     */
    private ContactInfo parseBusinessCard(String document)
    {
        String[] docArray   = document.split("\n");
        String name         = "";
        String phoneNumber  = "";
        String emailAddress = "";
        
        // Only take the first name, phone number, and email address we find
        boolean nameFound         = false;
        boolean phoneNumberFound  = false;
        boolean emailAddressFound = false;
        
        for(String line : docArray)
        {
            if(!nameFound && containsName(line))
            {
                nameFound = true;
                name = line;
            }
            else if(!phoneNumberFound && containsPhoneNumber(line))
            {
                phoneNumberFound = true;
                phoneNumber = parsePhoneNumber(line);
            }
            else if(!emailAddressFound && containsEmailAddress(line))
            {
                emailAddressFound = true;
                emailAddress = line;
            }
        }
        
        return new ContactInfo(name, phoneNumber, emailAddress);
    }
    
    /**
     * Checks if a given string contains a valid name consisting of
     * a first and last name (and optionally middle name(s))
     * 
     * @param aString - a string corresponding to a name
     * @return true if the input is a valid name false otherwise
     */
    private boolean containsName(String aString)
    {
        String[] split = aString.split(" ");
        
        // Make sure we have at least a first and last name
        if(split.length > 1)
        {
            String firstName = split[0];
            String lastName  = split[split.length - 1];
            
            // In the case where there are no middle names
            // we want this to be true
            boolean validMiddleNames = true;            
            
            if(split.length > 2)
            {
                for(int i = 1; i < split.length - 1; i++)
                {
                    String name = split[i];
                    // I'm not sure what the naming convention
                    // for middle names is so check both first and last
                    if(!checkFirstName(name) && !checkLastName(name))
                    {
                        // if any middle name isn't valid then
                        // the name is not valid
                        validMiddleNames = false;
                    }
                }
            }
            
            return checkFirstName(firstName) && checkLastName(lastName)
                    && validMiddleNames;
        }
        
        return false;
    }
    
    /**
     * Checks a file of valid first names to determine if the input
     * is a valid first name
     * 
     * @param firstName - a string corresponding to a first name
     * @return true of the input is a valid first name false otherwise
     */
    private boolean checkFirstName(String firstName)
    {
        try
        {
            FileReader file = new FileReader("firstNames.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String curLine = scanner.nextLine();
                if(firstName.equalsIgnoreCase(curLine))
                {
                    return true;
                }
            }
        } catch (FileNotFoundException e){}
        
        return false;
    }
    
    /**
     * Checks a file of valid last names to determine if the input
     * is a valid last name
     * 
     * @param lastName - a string corresponding to a last name
     * @return true of the input is a valid last name false otherwise
     */
    private boolean checkLastName(String lastName)
    {
        try
        {
            FileReader file = new FileReader("lastNames.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String curLine = scanner.nextLine();
                if(lastName.equalsIgnoreCase(curLine))
                {
                    return true;
                }
            }
        } catch (FileNotFoundException e){}
        
        return false;
    }
    
    /**
     * Parses a given string into a phone number string
     * 
     * @param phoneNumber - a given string corresponding to an phone number
     * @return a string containing the phone number
     */
    private String parsePhoneNumber(String phoneNumber)
    {
        // We want to ignore fax numbers
        if(!phoneNumber.toLowerCase().contains("fax"))
        {
            // Remove the first leading characters followed by a : or -
            // e.g. "Phone:" or "Tel -" 
            //
            // Note: I'm not sure how to write this in a way that would prevent
            // something like "foo: 123-456-7890" from being a valid phone number
            // while still asserting that we always get a phone number and not a
            // fax number (without the if check above, if we get a fax number
            // before a phone number that will be stored as the phone number).
            phoneNumber = phoneNumber.replaceFirst("[a-zA-Z]* *(-|:)", "");
            
            // Remove special characters used in phone numbers
            phoneNumber = phoneNumber.replaceAll("\\(|\\)|\\+|\\-| ", "");
            
            // Ensure the result contains only numbers
            if(phoneNumber.matches("[0-9]+"))
            {
                return phoneNumber;
            }
        }
        
        return "";
    }
    
    /**
     * Checks if a given string contains a valid phone number by comparing it
     * against the results of parsing the string
     * 
     * @param phoneNumber - a given string corresponding to an phone number
     * @return true if the input is a valid phone number, false otherwise
     */
    private boolean containsPhoneNumber(String phoneNumber)
    {
        return parsePhoneNumber(phoneNumber) != "";
    }
    
    /**
     * Checks if a given string contains a valid email address
     * 
     * @param emailAddress - a given string corresponding to an email address
     * @return true if the input is a valid email address, false otherwise
     */
    private boolean containsEmailAddress(String emailAddress)
    {
        boolean validLocal  = false;
        boolean validDomain = false;
        
        if(emailAddress.contains("@") && !emailAddress.contains(".."))
        {
            String[] emailParts = emailAddress.split("@");
            if(emailParts.length == 2) // ensure only 1 @
            {
                String local = emailParts[0];
                String domain = emailParts[1];
                
                // Local-part requirements:
                // - less than 64 characters
                // - uppercase and lowercase Latin letters A to Z and a to z
                // - digits 0 to 9
                // - printable characters !#$%&'*+-/=?^_`{|}~
                // - dot ., provided that it is not the first or last character
                //   and provided that it does not appear consecutively
                String localRE = "([a-zA-Z]|[0-9]|[!#$%&'\\*\\+\\-/\\=\\?\\^_`{\\|}~])"
                                 + "([a-zA-Z]|[0-9]|[!#$%&'\\*\\+\\-/\\=\\?\\^_`{\\|}~]|\\.)*"
                                 + "([a-zA-Z]|[0-9]|[!#$%&'\\*\\+\\-/\\=\\?\\^_`{\\|}~])";
                
                validLocal = ((local.length() < 64) && local.matches(localRE));
                
                // Domain requirements:
                // - less than 255 characters
                // - uppercase and lowercase Latin letters A to Z and a to z
                // - digits 0 to 9
                // - hyphen -, provided that it is not the first or last character.
                String domainRE = "([a-zA-Z]|[0-9])([a-zA-Z]|[0-9]|-)*"
                                  + "([a-zA-Z]|[0-9])";
                
                if(domain.contains("."))
                {
                    String[] domainParts = domain.split("\\.");
                    if(domainParts.length == 2) // ensure only 1 .
                    {
                        validDomain = ((domainParts[0].length() < 255) &&
                                       domainParts[0].matches(domainRE)); 
                    }
                }
            }
        }
        
        return validLocal && validDomain;
    }
}
