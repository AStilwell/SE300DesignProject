import java.util.List;
import java.util.Random;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		          for (List row : values) {
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
