package javaFiles;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.mail.MessagingException;

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
        

        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        MenuItem clear = new MenuItem("Clear All");
        Menu reset = new Menu("Reset...");
        Menu help = new Menu("Help");
        TabPane tabs = new TabPane();
        Tab unitStaff = new Tab("Unit Staff", chart.get(5));
        Tab bnStaff = new Tab("BN Staff", chart.get(0));
        Tab engineeringT = new Tab("Engineering", chart.get(2));
        Tab navigationT = new Tab("Navigation", chart.get(3)); 
        Tab weaponsT = new Tab("Weapons", chart.get(1));
        Tab mopltT = new Tab("Marine Options", chart.get(4));
        TextField subject = new TextField();
        TextArea body = new TextArea();
        TextArea addresses = new TextArea();
        VBox inputArea = new VBox();
        Button recall = new Button("MASS RECALL");
        Button send = new Button("Send");
        Button cancel = new Button("Cancel");
        HBox mainCtl = new HBox(cancel, send);
        VBox allBn = new VBox(recall, mainCtl);
        Pane spacer = new Pane();
        VBox rightside = new VBox(inputArea, allBn);
        GridPane root = new GridPane();
        BorderPane staging = new BorderPane();
        Scene rootOverhaul = new Scene(staging);
        Stage uiOverhaul = new Stage();
        
        exit.setOnAction(e -> Platform.exit());
        
        clear.setOnAction(v -> {
        	email.clearList();
        	diagram.clearSelection();
        	addresses.setText(null);
        	subject.setText(null);
        	body.setText(null);
        });
        
        file.getItems().addAll(exit);
        reset.getItems().add(clear);
        menuBar.getMenus().addAll(file, reset, help);
        
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabs.getTabs().addAll(unitStaff, bnStaff, weaponsT, engineeringT, navigationT, mopltT);
        
        tabs.setOnMouseClicked(g -> {
        	addresses.setText(Arrays.toString(email.outputEmailList()));
        	if(email.isEmpty()){
        		addresses.setText(null);
        	}
        });
        
        subject.setPromptText("Subject");
        
        body.setPromptText("Message Contents");
               
        addresses.setPromptText("No Emails Selected");
        addresses.setPrefHeight(75);
        addresses.setWrapText(true);
                
        inputArea.getChildren().addAll(addresses, subject, body);
        inputArea.setSpacing(5);
        
        recall.setPrefSize(205, 40);
        recall.setOnMouseClicked(fn -> 
        {	
        	Stage recallCheck = new Stage();
        	
        	Text statement = new Text("This will send a message to all users!");
        	
        	Button confirm = new Button("Confirm");
        	confirm.setOnMouseClicked(x -> {
        		try {
                email.getAllEmails();
                diagram.selectAll();
        		addresses.setText(Arrays.toString(email.outputEmailList()));
        		body.setText("This is a RECALL, all-hands must respond to this email by clicking the given link.");
        		} catch (BiffException | IOException e) 
        		{
                System.out.println("There was an error getting the correct email addresses.");
        		}
        		recallCheck.close();
        	});
        	
        	Button accident = new Button("Cancel");
        	accident.setOnMouseClicked(e -> recallCheck.close());
        	
        	HBox options = new HBox(confirm, accident);
        	options.setSpacing(20);
        	options.setAlignment(Pos.CENTER);
        	
        	VBox warningWindow = new VBox(statement, options);
        	warningWindow.setSpacing(15);
        	warningWindow.setAlignment(Pos.CENTER);
        	
        	Scene recallVerify = new Scene(warningWindow);
        	
        	recallCheck.setScene(recallVerify);
        	recallCheck.setMinHeight(200);
        	recallCheck.setMinWidth(400);
        	recallCheck.setTitle("WARNING");
        	recallCheck.show();
        });
               
        send.setPrefSize(100, 20);
        send.setOnMouseClicked(fn -> {
        	Stage passwordWindow = new Stage();
        	Text emailLabel = new Text("EMAIL:");
        	TextField usrEmail = new TextField();
        	usrEmail.setPromptText("EMAIL ADDRESS");
        	Text passLabel = new Text("PASSWORD:");
        	PasswordField password = new PasswordField();
        	password.setPromptText("PASSWORD");
        	
        	GridPane entryWin = new GridPane();
        	entryWin.add(emailLabel, 0,0);
        	entryWin.add(passLabel, 0, 1);
        	entryWin.add(usrEmail, 1, 0);
        	entryWin.add(password, 1, 1);
        	entryWin.setHgap(5);
        	entryWin.setAlignment(Pos.CENTER);
        	
        	Button ok = new Button("OK");
        	ok.setOnMouseClicked(sending -> {
        		Boolean sendSuccess = false;
        		try {
        			//for (int i = 0; i < 100; i++){ //Seriously...don't activate this...
        				MailSender.sendEmail(usrEmail.getText(), password.getCharacters().toString(), email.outputEmailList(), body.getText(), subject.getText());
        				sendSuccess = true;
        			//}
        		} catch(MessagingException me) {
        			Stage invPass = new Stage();
                	
                	Text error = new Text("Invalid User Name/Password");
                	
                	Button ack = new Button("OK");
                	ack.setAlignment(Pos.CENTER);
                	ack.setOnMouseClicked(x -> {
                		invPass.close();
                	});
                	
                	VBox warningWindow = new VBox(error, ack);
                	warningWindow.setSpacing(15);
                	warningWindow.setAlignment(Pos.CENTER);
                	warningWindow.setPrefSize(400, 200);
                	
                	Scene recallVerify = new Scene(warningWindow);
                	
                	invPass.setScene(recallVerify);
                	invPass.setMinHeight(100);
                	invPass.setMinWidth(200);
                	invPass.setTitle("WARNING");
                	invPass.show();
        		}
        		if (sendSuccess){
        			passwordWindow.close();
        			uiOverhaul.close();
        			//FIXME Put the call to your scene here
        		}
        	});
        	
        	Button backout = new Button("Cancel");
        	backout.setOnMouseClicked(func -> passwordWindow.close());
        	
        	HBox bnInput = new HBox(ok, backout);
        	bnInput.setAlignment(Pos.CENTER);
        	
        	VBox passWindow = new VBox(entryWin, bnInput);
        	passWindow.setAlignment(Pos.CENTER);
        	passWindow.setPrefSize(400, 100);
        	passWindow.setSpacing(5);
        	
        	Scene passPrompt = new Scene(passWindow);
        	
        	passwordWindow.setScene(passPrompt);
        	passwordWindow.setTitle("AUTHENTICATION CHECK");
        	passwordWindow.show();
        });
             
        cancel.setPrefSize(100, 20);
        cancel.setOnAction(y -> Platform.exit());
               
        mainCtl.setSpacing(5);
        mainCtl.setAlignment(Pos.TOP_CENTER);
               
        allBn.setSpacing(25);
        allBn.setAlignment(Pos.TOP_CENTER);
               
        spacer.setPrefSize(10, tabs.getHeight());
               
        rightside.setSpacing(10);
              
        root.add(tabs, 0, 0);
        root.add(spacer, 1, 0, 1, 2);
        root.add(rightside, 2, 0);
               
        staging.setTop(menuBar);
        staging.setCenter(root);
      
        uiOverhaul.setScene(rootOverhaul);
        uiOverhaul.setTitle("Quick Email Notification");
        uiOverhaul.show();
    }
    
    public static void main(String[] args){
    	Application.launch(args);
    }
}