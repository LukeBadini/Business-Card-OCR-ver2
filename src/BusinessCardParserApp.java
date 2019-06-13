import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class BusinessCardParserApp
{

    public static void main(String[] args)
    {
        String fileName = "";
        try {
            fileName = args[0];
        } catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("No input given, using input.txt\n");
            fileName = "input.txt"; // fall back if no input is given
        }
        
        ArrayList<String> input = new ArrayList<String>();
        String curCard = "";
        try
        {
            FileReader file = new FileReader(fileName);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine())
            {
                String curLine = scanner.nextLine();
                if(curLine.equals("###"))
                {
                    input.add(curCard);
                    curCard = "";
                }
                else
                {
                    curCard += curLine + "\n"; // scanner parses out the \n
                }
            }
            // add last business card
            input.add(curCard);
            curCard = "";
        } catch (FileNotFoundException e){}
        
        for(int i = 0; i < input.size(); i++)
        {
            BusinessCardParser parser = new BusinessCardParser();
            System.out.println("Parsed business card:\n" 
                               + parser.getContactInfo(input.get(i)).toString() + "\n");
        }
    }

}
