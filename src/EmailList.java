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
    private static ArrayList<String> emailList = new ArrayList<>();
    private static ArrayList<String> nameList = new ArrayList<>();
    private static Map<String, Integer> posList = getNamePos();
    
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
    	if(input.contains(email)){
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
    	if(input.contains(email)){
    		input.remove(email);
    	} else {
    		//Do nothing
    	}
    }

    public void addName(ArrayList<String> input, String name) 
    {
    	System.out.println("Entering Function");
    	ArrayList<String> temp = new ArrayList<String>();
    	
    	if(input.contains(name)){
    		//do nothing
    		System.out.println("Doing Nothing");
    	} else {
    		for(int k = 0; k < input.size(); k++){
    			if(posList.get((input.get(k))) > posList.get(name)){
    				for(int i = k; i < input.size(); i++){
    					temp.add(input.get(i));
    					input.remove(i);
    					System.out.println("Partitioning");
    				}
    				System.out.println("Adding");
    			} else {
    				System.out.println("Skipping");
    			}
    		}
    		input.add(name);
			input.addAll(temp);
    	}
    	System.out.print(input + "\n");
    }
    
    public static Map<String, Integer> getNamePos(){
    	Map<String, Integer> pos = new HashMap<String, Integer>();
    	
    	File file = new File("./src/Test Spreadsheet.xls");//FIXME
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

    public void delName(ArrayList<String> input, String name) 
    {
    	if(input.contains(name)){
    		input.remove(name);
    	} else {
    		
    	}
    }

    /**
     * @throws IOExceptiono
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
