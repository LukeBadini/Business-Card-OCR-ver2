import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BusinessCardParserTest 
{
    private BusinessCardParser parser;
    private ContactInfo info;
        
    @Before
    public void setUp()
    {
        parser = new BusinessCardParser();
        info = new ContactInfo();
    }
    
    @After
    public void tearDown()
    {
        parser = null;
        info = null;
    }
    
    @Test
    public void testEmptyString()
    {
        assertEquals("Empty string given to parser", info.toString(),
                     parser.getContactInfo("").toString());
    }
    
    @Test
    public void testExample1()
    {
        String test = "ASYMMETRIK LTD\nMike Smith\nSenior Software Engineer\n"
                      + "(410)555-1234\nmsmith@asymmetrik.com";
        info = new ContactInfo("Mike Smith", "4105551234",
                               "msmith@asymmetrik.com");
        assertEquals("Example 1", info.toString(),
                     parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testExample2()
    {
        String test = "Foobar Technologies\nAnalytic Developer\nLisa Haung"
                      + "\n1234 Sentry Road\nColumbia, MD 12345\nPhone:"
                      + " 410-555-1234\nFax: 410-555-4321"
                      + "\nlisa.haung@foobartech.com";
        info = new ContactInfo("Lisa Haung", "4105551234",
                               "lisa.haung@foobartech.com");
        assertEquals("Example 2", info.toString(),
                     parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testExample3()
    {
        String test = "Arthur Wilson\nSoftware Engineer\nDecision & Security "
                      + "Technologies\nABC Technologies\n123 North 11th Street"
                      + "\nSuite 229\nArlington, VA 22209\nTel: +1 (703) "
                      + "555-1259\nFax: +1 (703) 555-1200\nawilson@abctech.com";
        info = new ContactInfo("Arthur Wilson", "17035551259", "awilson@abctech.com");
        assertEquals("Example 3", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testNoNewlines()
    {
        String test = "ASYMMETRIK LTD Mike Smith Senior Software Engineer"
                      + "(410)555-1234 msmith@asymmetrik.com";
        info = new ContactInfo();
        assertEquals("No newline characters", info.toString(),
                     parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testNullInput()
    {
        String test = null;
        info = null;
        assertEquals("Null input", info, parser.getContactInfo(test));
    }
    
    @Test
    public void testOneMiddleName()
    {
        String test = "Mike John Smith\n4105551234\nmsmith@asymmetrik.com";
        info = new ContactInfo("Mike John Smith", "4105551234",
                               "msmith@asymmetrik.com");
        assertEquals("One middle name", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testMoreMiddleNames()
    {
        String test = "Mike John James Jimmy Smith\n4105551234"
                      + "\nmsmith@asymmetrik.com";
        info = new ContactInfo("Mike John James Jimmy Smith", "4105551234",
                               "msmith@asymmetrik.com");
        assertEquals("More middle names", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testInvalidMiddleName()
    {
        String test = "Mike Abcd Smith\n4105551234\nmsmith@asymmetrik.com";
        info = new ContactInfo("", "4105551234",
                               "msmith@asymmetrik.com");
        assertEquals("Invalid middle name", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testInvalidName()
    {
        String test = "Abcd Efgh\n(410)555-1234\nmsmith@asymmetrik.com";
        info = new ContactInfo("", "4105551234",
                               "msmith@asymmetrik.com");
        assertEquals("Invalid name", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testInvalidPhoneNumber()
    {
        String test = "Mike Smith\nxyz410-55abc5-1234\nmsmith@asymmetrik.com";
        info = new ContactInfo("Mike Smith", "",
                               "msmith@asymmetrik.com");
        assertEquals("Invalid phone number", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testInvalidEmailAddress()
    {
        String test = "Mike Smith\n(410)555-1234\nmsm@ith@asymmetrik.com";
        info = new ContactInfo("Mike Smith", "4105551234",
                               "");
        assertEquals("Invalid email address", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testInvalidEmailAddressLength1()
    {
        String test = "Mike Smith\n(410)555-1234\n"
                      + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@asymmetrik.com";
        info = new ContactInfo("Mike Smith", "4105551234",
                               "");
        assertEquals("Invalid email address", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testInvalidEmailAddressLength2()
    {
        String test = "Mike Smith\n(410)555-1234\n"
                      + "a@asymmetrikkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk.com";
        info = new ContactInfo("Mike Smith", "4105551234",
                               "");
        assertEquals("Invalid email address", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testMultipleNames()
    {
        String test = "Mike Smith\nJohn Doe\n(410)555-1234\nmsmith@asymmetrik.com";
        info = new ContactInfo("Mike Smith", "4105551234",
                               "msmith@asymmetrik.com");
        assertEquals("Multiple names", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testMultiplePhoneNumbers()
    {
        String test = "Mike Smith\nWork: (410)555-1234\nCell: (123)456-7890\n"
                      + "msmith@asymmetrik.com";
        info = new ContactInfo("Mike Smith", "4105551234",
                               "msmith@asymmetrik.com");
        assertEquals("Multiple phone numbers", info.toString(),
                parser.getContactInfo(test).toString());
    }
    
    @Test
    public void testMultipleEmailAddresses()
    {
        String test = "Mike Smith\n(410)555-1234\nmsmith@asymmetrik.com\n"
                      + "foo@bar.foobar";
        info = new ContactInfo("Mike Smith", "4105551234",
                               "msmith@asymmetrik.com");
        assertEquals("Multiple email addresses", info.toString(),
                parser.getContactInfo(test).toString());
    }

}
