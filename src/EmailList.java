

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
    private static ArrayList<String> emailList = new ArrayList<>();
    private static ArrayList<String> nameList = new ArrayList<>();

    /**
     *
     * @return Outputs the email list contained in this class
     */
    public ArrayList<String> getEmailList() 
    {
        return emailList;
    }

    //Changed getNameList() to static
    public ArrayList<String> getNameList()
    {
        return nameList;
    }

    /**
     *
     * @return Outputs the email list in a string format
     */
    public static String[] outputEmailList() 
    {
        String[] addresses = emailList.toArray(new String[0]);
        
        return addresses;
    }

    public static String[] outputNameList()
    {
    	String[] names = nameList.toArray(new String[0]);
    	
        return names;
    }

    /**
     * @param input The initial array list that is being modified.
     * @param email The email to be added to the array list.
     */
    public void addEmail(ArrayList<String> input, String email) 
    {
    	if(emailList.contains(email)){
    		//Do nothing
    	} else {
        input.add(email);
    	}
    }

    /**
     * @param input The initial array list that is being modified.
     * @param email The email to be removed from the array list.
     */
    public void delEmail(ArrayList<String> input, String email) 
    {
    	if(emailList.contains(email)){
    		input.remove(email);
    	} else {
    		//Do nothing
    	}
    }

    public void addName(ArrayList<String> input, String name) 
    {
    	if(nameList.contains(name)){
    		
    	} else {
    		input.add(name);
    	}
    }

    public void delName(ArrayList<String> input, String name) 
    {
    	if(nameList.contains(name)){
    		input.remove(name);
    	} else {
    		
    	}
    }

    /**
     * @throws IOException
     * @throws BiffException
     */
    public void getAllEmails() throws IOException, BiffException 
    {	
    	emailList.clear();
    	File file = new File("./src/Test Spreadsheet.xls");
    	//File file = new File(getClass().getClassLoader().getResource(".\\refDocs\\Test Spreadsheet.xls").getFile()); //TODO
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(0);
        
        for (int i = 0; i < sheet.getRows(); i++) 
        {
            Cell cell = sheet.getCell(4, i);
            if(cell.getContents().equals("")){
            	//Do nothing
            } else {
            	emailList.add(cell.getContents());
            }
        }
    }
    
    public Boolean isEmpty() {
    	if (emailList.isEmpty()){
    		return true;
    	}
    	return false;
    }
    
    public void clearList(){
    	emailList.clear();
    }
}
