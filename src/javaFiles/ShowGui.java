package javaFiles;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.GenericData.Flags;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import com.google.api.services.sheets.v4.Sheets;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//
public class ShowGui{
	

	
	public static ArrayList<Integer> conf = new ArrayList<>();
	
    public static String[] names = {"MIDN 1/C Murphy", "MIDN 1/C Krause", "MIDN 1/C Elliott"};

	
	/** Application name. */
    private static final String APPLICATION_NAME =
        "Google Sheets API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/sheets.googleapis.com-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            ShowGui.class.getResourceAsStream(".\\refDocs\\client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
       
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    public static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    

    
    BorderPane  borderPane = new BorderPane();
	//Layouts
	VBox NamesLayout = new VBox(10);
	VBox ConformationLayout = new VBox (10);
	VBox ConfCircles = new VBox(10);
	HBox bottomButtons = new HBox(10);
	
	Button button1;
	Stage window;
	Stage secondaryStage = new Stage();
	
	
	
	public void start() throws Exception {
		window = secondaryStage;
		window.setTitle("confirmation list");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
			System.exit(0);

		});
		
		
		Scene scene1 = new Scene (borderPane, 400, 600);
		//Exit Button 
		button1 = new Button("EXIT!");
		button1.setOnAction(e -> closeProgram());
		
		//RefreshButton
		Button refreshButton = new Button("Refresh");
		
		
		
		
		
		NamesLayout.setAlignment(Pos.TOP_CENTER);
		ConformationLayout.setAlignment(Pos.TOP_CENTER);
		ConfCircles.setAlignment(Pos.TOP_CENTER);
		bottomButtons.setAlignment(Pos.CENTER);
		
	    
		borderPane.setLeft(NamesLayout);
		borderPane.setCenter(ConformationLayout);
		borderPane.setRight(ConfCircles);
		borderPane.setBottom(bottomButtons);
		
		bottomButtons.getChildren().add(refreshButton);
		
		window.setScene(scene1);
		window.show();
		
		
		refreshButton.setOnAction(e -> {
			
		NamesLayout.getChildren().clear();
		ConfCircles.getChildren().clear();
		ConformationLayout.getChildren().clear();
		
		
		for(String name : names){
			Label label = new Label(name);
			NamesLayout.getChildren().add(label);
		}


		
		//Confirmation
		for(int value : conf){
			if (value ==0){
				Label label = new Label("has not confirmed");
				ConformationLayout.getChildren().add(label);
			}else{
				Label label = new Label("has confirmed");
				ConformationLayout.getChildren().add(label);
			}
		}
		
		

		
		//Circles
		for(int value : conf){
			if (value == 0){
				Circle c1 = new Circle();
				c1.setRadius(10);
				c1.setFill(Color.RED);
				ConfCircles.getChildren().add(c1);
			}else{
				
				Circle c1 = new Circle();
				c1.setRadius(10);
				c1.setFill(Color.GREEN);
				ConfCircles.getChildren().add(c1);
			}
		}
		

		
		
		
		
	});
		
		
		
}


	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Title", "Sure u wnat to exit?");
		if (answer){
			window.close();
		}
	}
    
    public void handoff() throws IOException {
    
    	Thread t1 = new Thread(new ReadSheet());
    
    	
        t1.start();
		//launch();
          
    }

    
  
	


}