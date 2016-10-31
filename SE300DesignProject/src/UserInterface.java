import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by stilw on 17-Oct-16. messed with by Peter
 */
public class UserInterface extends Application 
{

    /**
     * @param primaryStage Stage default for creation of GUI.
     */
    @Override
    public void start(Stage primaryStage) 
    {
        //Create new email list
        EmailList email = new EmailList();

        //Create new diagram handling class
        Diagram diagram = new Diagram();
        try 
        {
            diagram.read();
        } catch (IOException | BiffException ex) 
        {
            ex.printStackTrace();
        }

        ArrayList<VBox> chart = diagram.getDiagram();

        //Create various root panes for main GUI window
        BorderPane diagramHandler = new BorderPane();
        BorderPane root = new BorderPane();

        //Create sub-panes that contain controls and text fields
        GridPane controls = new GridPane();
        TextArea emails = new TextArea();
        emails.setPromptText("No Emails Selected");
        emails.setWrapText(true);
        TextArea message = new TextArea();
        message.setPromptText("Enter Message Here");
        Button massRecall = new Button("RECALL");

        //Method to select all email addresses and add them to the email list
        massRecall.setOnMouseClicked(fn -> 
        {
            try {
                email.getAllEmails();
            } catch (BiffException | IOException e) 
            {
                System.out.println("There was an error getting the correct email addresses.");
            }
            diagram.selectAll();
            emails.setText(email.outputEmailList().toString());
            message.setText("This is a RECALL, all-hands must respond to this email by clicking the given link.");
        });

        //Button declarations and functions
        Button select = new Button("SELECT");
        select.setOnMouseClicked(fn -> {
            emails.setText(Arrays.toString(email.outputEmailList()));
        });

        Button clear = new Button("CLEAR");
        clear.setOnMouseClicked(fn -> 
        {
            diagram.clearSelection();
            email.getEmailList().clear();
            emails.setText(null);
            message.setText(null);
        });
        
        Button send = new Button("SEND");
        send.setOnMouseClicked(fn -> {
        	PasswordField password = new PasswordField();
        	Button ok = new Button("OK");
        	ok.setOnMouseClicked(sending -> {
        		//change this email to yours for testing
        		MailSender.sendEmail("stilwell.andrewk@gmail.com", password.getCharacters().toString(), email.outputEmailList(), "Test", "This is a test");
        	});
        	
        	Button cancel = new Button("Cancel");
        	
        	GridPane passWindow = new GridPane();
        	passWindow.add(password, 0,0,2,1);
        	passWindow.add(ok, 0, 1);
        	passWindow.add(cancel, 1, 1);
        	
        	Scene passPrompt = new Scene(passWindow);
        	
        	Stage root2 = new Stage();
        	root2.setScene(passPrompt);
        	root2.show();
        });

        //Add buttons to the control sub-pane
        controls.add(emails, 0, 0, 2, 1);
        controls.add(message, 0, 1, 2, 1);
        controls.add(massRecall, 0, 2);
        controls.add(select, 1, 2);
        controls.add(clear, 0, 3);
        controls.add(send, 1, 3);
        controls.setMinWidth(250);
        controls.setHgap(20);
        controls.setVgap(20);

        //Create new menu
        MenuBar menuBar = new MenuBar();

        //Various menu item creation
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> Platform.exit());
        file.getItems().addAll(exit);

        Menu help = new Menu("Help");

        Menu department = new Menu("Switch View");
        MenuItem command = new MenuItem("BN STAFF");
        command.setOnAction(event -> diagramHandler.setCenter(chart.get(0)));
        MenuItem weapons = new MenuItem("WEAPONS");
        weapons.setOnAction(event -> diagramHandler.setCenter(chart.get(1)));
        MenuItem engineering = new MenuItem("ENGINEERING");
        engineering.setOnAction(event -> diagramHandler.setCenter(chart.get(2)));
        MenuItem navigation = new MenuItem("NAVIGATION");
        department.getItems().addAll(command, weapons, engineering, navigation);

        menuBar.getMenus().addAll(file, help, department);

        //Add default diagram to diagram handler
        diagramHandler.setCenter(chart.get(0));

        //Add elements to main root pane
        root.setTop(menuBar);
        root.setCenter(diagramHandler);
        root.setLeft(controls);

        //Create new scene
        Scene startScreen = new Scene(root);

        //Create new stage and assign scene/attributes
        primaryStage.setScene(startScreen);
        primaryStage.setTitle("User Interface");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    public static void main(String[] args){
    	Application.launch(args);
    }
}