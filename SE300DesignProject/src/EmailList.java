import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
// Logan was here
/**So was Peter
 * Created by Andrew Stilwell on 24-Oct-16.
 */
public class EmailList 
{
    private static final ArrayList<String> emailList = new ArrayList<>();
    private static final ArrayList<String> nameList = new ArrayList<>();

    /**
     *
     * @return Outputs the email list contained in this class
     */
    public ArrayList<String> getEmailList() 
    {
        return emailList;
    }

    public ArrayList<String> getNameList()
    {
        return nameList;
    }

    /**
     *
     * @return Outputs the email list in a string format
     */
    public String[] outputEmailList() 
    {
        return (String[]) emailList.toArray();//TODO
    }

    public String outputNameList()
    {
        return nameList.toString();
    }

    /**
     * @param input The initial array list that is being modified.
     * @param email The email to be added to the array list.
     */
    public void addEmail(ArrayList<String> input, String email) 
    {
        if (!input.contains(email)) 
        {
            input.add(email);
        } else 
        {
            System.out.println("Something went wrong...");
        }
    }

    /**
     * @param input The initial array list that is being modified.
     * @param email The email to be removed from the array list.
     */
    public void delEmail(ArrayList<String> input, String email) 
    {
        if (input.contains(email)) 
        {
            input.remove(email);
        } else 
        {
            System.out.println("Something went wrong...");
        }
    }

    public void addName(ArrayList<String> input, String name) 
    {
        if (!input.contains(name)) 
        {
            input.add(name);
        } else 
        {
            System.out.println("Something went wrong...");
        }
    }

    public void delName(ArrayList<String> input, String name) 
    {
        if (input.contains(name)) 
        {
            input.remove(name);
        } else 
        {
            System.out.println("Something went wrong...");
        }
    }

    /**
     * @throws IOException
     * @throws BiffException
     */
    public void getAllEmails() throws IOException, BiffException 
    {
        File file = new File("C:\\Users\\Andrew Stilwell\\OneDrive\\ProgrammingStuff\\Java\\SE300 Design Project\\src\\GUI\\Test.xls" );
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(0);

        for (int i = 0; i < sheet.getRows(); i++) 
        {
            Cell cell = sheet.getCell(4, i);

            emailList.add(cell.getContents());
        }
    }
}
