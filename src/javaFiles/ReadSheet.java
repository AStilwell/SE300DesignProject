package javaFiles;
import java.util.List;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

public class ReadSheet extends ShowGui implements Runnable  {
	

	

	String name;
	boolean flag =true;
	

	
	@Override
	public void run() {
		
		while(flag){
			try{
				
				conf.clear();
				// Build a new authorized API client service.
		        Sheets service = getSheetsService();

		        // Prints the names and majors of students in a sample spreadsheet:
		        // https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		        String spreadsheetId = "1o3k5Koml7QabFHP1w6ou4-UShjua2XPqjNvSJWhQSs4";
		        String range = "Sheet1!A:B";
		        
		        
		        ValueRange response = service.spreadsheets().values()
		            .get(spreadsheetId, range)
		            .execute();
		        List<List<Object>> values = response.getValues();
		        
		        System.out.printf("MIDN\t\t\tconformation\n");
		        
		        
		        if (values == null || values.size() == 0) {
		            System.out.println("No data found.");
		        } else {
		          for (List<Object> row : values) {
		        	  String status = (String)row.get(1); 
		        	  String confirmed = "1";
		        	  
		        	  for(String name : names){
		        		  if(row.get(0).toString().equals(name)){
		        			  System.out.printf("%s ", name);
		        			  if(status.equals(confirmed)){
		        				  System.out.printf("\thas confirmed\n");
		                          conf.add(1);
		        			  }else{
		        				  System.out.printf("\thas not confirmed\n");
		                      	  conf.add(0); 
		        			  }
		        		  }
		        		  
		        	  }
		          }
		          
		          
		     
	          Thread.sleep(3000);
	         
	          
	         
		          
		        }
			}catch(Exception e){
				
			}
		}
		
		 
		
	}
	

}
