import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
// Logan was here
/**So was Peter
 * Created by Andrew Stilwell on 24-Oct-16.
 */
public class EmailList 
{
    protected static ArrayList<String> emailList = new ArrayList<>();
    protected static ArrayList<String> nameList = new ArrayList<>();
    protected static Map<String, Integer> posList = getNamePos();
    
    /**
     *
     * @return Outputs the email list contained in this class
     */
    public static ArrayList<String> getEmailList() 
    {
        return emailList;
    }

    //Changed getNameList() to static
    public static ArrayList<String> getNameList()
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
    public static void addEmail(String email) 
    {
    	if(emailList.contains(email)){
    		//Do nothing
    	} else {
    		emailList.add(email);
    	}
    }

    /**
     * @param input The initial array list that is being modified.
     * @param email The email to be removed from the array list.
     */
    public static void delEmail(String email) 
    {
    	if(emailList.contains(email)){
    		emailList.remove(email);
    	} else {
    		//Do nothing
    	}
    }

    public static void addName(String name) 
    {
    	//System.out.println("Entering Function");
    	ArrayList<String> temp = new ArrayList<String>();
    	
    	if(nameList.contains(name)){
    		//do nothing
    		//System.out.println("Doing Nothing");
    	} else {
    		for(int k = 0; k < nameList.size(); k++){
    			
    			if(posList.get((nameList.get(k))) > posList.get(name)){
    				while(k != nameList.size()){
    					temp.add(nameList.get(k));
    					nameList.remove(k);
    				}
    				//System.out.println("Partitioning\n" + input + "\n" + temp);
    			} else {
    				//System.out.println("Skipping");
    			}
    		}
    		nameList.add(name);
    		nameList.addAll(temp);
			//System.out.println("Adding");
    	}
    	//System.out.print(input + "\n");
    }
    
    public static Map<String, Integer> getNamePos(){
    	Map<String, Integer> pos = new HashMap<String, Integer>();
    	
    	File file = new File("Test Spreadsheet.xls");//FIXME
    	//File file = new File(getClass().getClassLoader().getResource(".\\refDocs\\Test Spreadsheet.xls").getFile()); //TODO
        Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
	        
	        for (int i = 0; i < sheet.getRows(); i++) 
	        {
	        	Cell cell = sheet.getCell(3, i);
	        	pos.put(cell.getContents(), i+1);
	        }
			
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pos;
    }

    public static void delName(String name) 
    {
    	nameList.remove(name);
    	System.out.println(nameList);
    }

    /**
     * @throws IOExceptiono
     * @throws BiffException
     */
    public static void getAllEmails() throws IOException, BiffException 
    {	
    	emailList.clear();
    	File file = new File("Test Spreadsheet.xls");
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
    
    public static Boolean isEmpty() {
    	if (emailList.isEmpty()){
    		return true;
    	}
    	return false;
    }
    
    public static void clearList(){
    	emailList.clear();
    }
}
