import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//
public class ShowGui extends UserInterface {
	

	
	public static ArrayList<Integer> conf = new ArrayList<>();
	
    public static String[] names = EmailList.outputNameList();
   

    

    //Pane
    static BorderPane  borderPane = new BorderPane();
    
	//Layouts
    static VBox NamesLayout = new VBox(10);
    static VBox ConformationLayout = new VBox (10);
    static VBox ConfCircles = new VBox(10);
    static HBox bottomButtons = new HBox(10);
	
    //Button
    static Button button1;
	
   
	
	
	public static void showConf() {
		
		Platform.setImplicitExit(false);
		
		
		//start the thread
		Thread t1 = new Thread(new ReadSheet());
		t1.start();
		
		uiOverhaul.setOnCloseRequest(e -> {
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
		
		uiOverhaul.hide();
		uiOverhaul.setScene(scene1);
		uiOverhaul.show();
		
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
			}else if (value == 1){
				Label label = new Label("has confirmed");
				ConformationLayout.getChildren().add(label);
			} else {
				Label label = new Label("invalid input");
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
			}else if (value == 1){
				
				Circle c1 = new Circle();
				c1.setRadius(10);
				c1.setFill(Color.GREEN);
				ConfCircles.getChildren().add(c1);
			} else {
				Circle c1 = new Circle();
				c1.setRadius(10);
				c1.setFill(Color.YELLOW);
				ConfCircles.getChildren().add(c1);
			}
		}
		

		
		
		
		
	});
		
		
		
}


	public static String[] getNammes(){
		String [] array = EmailList.outputNameList();
		
		
		return array;
	}
	
	//Changed to static
	private static void closeProgram(){
		Boolean answer = ConfirmBox.display("Title", "Sure u wnat to exit?");
		if (answer){
			uiOverhaul.close();
		}
	}
    

	

    
  
	


}