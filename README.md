# Running the project
I packaged my code into the included BusinessCardParserApp.jar file. In order to
run it, execute the following command from the command line:

```bash
java -jar BusinessCardParserApp.jar input-file
```

Where input-file is a file containing business card strings separated by a line
containing ###. If no file is given, input is read from input.txt.
The parsed output is printed to the command line.

# Resources used
- [The NameDatabases resources](https://github.com/smashew/NameDatabases)
- Hamcrest version 1.3
- JUnit version 4.13

# Explanation of the system
When the application is run, the business card strings are parsed out of the
input file and processed by BusinessCardParser.getContactInfo(). The input
string is split into an array around the newline characters in order to check
each line individually. Each line is evaluated to see if it contains a name,
phone number, or email address. If any of these are found, they are stored into
a variable for later and a boolean is set to true so that we don't check for
that field again (this is done to ensure we only store the first name/
phone number/email address we find). Names are checked by using the included
firstNames.txt and lastNames.txt files and phone numbers and emails are checked
by using regular expressions. Once the entire business card string has been
passed through, the name, phone number, and email address strings are packaged
into a ContactInfo object, which is then printed using the correct format.
