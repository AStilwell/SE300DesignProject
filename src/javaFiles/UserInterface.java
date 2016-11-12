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
    public void start(Stage uiOverhaul) 
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
        chart.get(0).setOnMouseClicked(fn -> {
        	if(fn.getClickCount() == 3){
        		for(int i = 0; i < diagram.BNSTAFF.size(); i++){
        			diagram.BNSTAFF.get(i).setStyle("-fx-background-color: yellow; -fx-border-color: black");
        			email.addEmail(email.getEmailList(), diagram.BNSTAFFemails.get(i));
        			email.addName(email.getNameList(), diagram.BNSTAFFnames.get(i));
        		}
        	}
        });
        
        chart.get(1).setOnMouseClicked(fn -> {
        	if(fn.getClickCount() == 3){
        		for(int i = 0; i < diagram.WEPS.size(); i++){
        			diagram.WEPS.get(i).setStyle("-fx-background-color: yellow; -fx-border-color: black");
        			email.addEmail(email.getEmailList(), diagram.WEPSemails.get(i));
        			email.addName(email.getNameList(), diagram.WEPSnames.get(i));
        		}
        	}
        });
        
        chart.get(2).setOnMouseClicked(fn -> {
        	if(fn.getClickCount() == 3){
        		for(int i = 0; i < diagram.ENG.size(); i++){
        			diagram.ENG.get(i).setStyle("-fx-background-color: yellow; -fx-border-color: black");
        			email.addEmail(email.getEmailList(), diagram.ENGemails.get(i));
        			email.addName(email.getNameList(), diagram.ENGnames.get(i));
        		}
        	}
        });
        
        chart.get(3).setOnMouseClicked(fn -> {
        	if(fn.getClickCount() == 3){
        		for(int i = 0; i < diagram.NAV.size(); i++){
        			diagram.NAV.get(i).setStyle("-fx-background-color: yellow; -fx-border-color: black");
        			email.addEmail(email.getEmailList(), diagram.NAVemails.get(i));
        			email.addName(email.getNameList(), diagram.NAVnames.get(i));
        		}
        	}
        });
        
        chart.get(4).setOnMouseClicked(fn -> {
        	if(fn.getClickCount() == 3){
        		for(int i = 0; i < diagram.MOPLT.size(); i++){
        			diagram.MOPLT.get(i).setStyle("-fx-background-color: yellow; -fx-border-color: black");
        			email.addEmail(email.getEmailList(), diagram.MOPLTemails.get(i));
        			email.addName(email.getNameList(), diagram.MOPLTnames.get(i));
        		}
        	}
        });
        
        chart.get(5).setOnMouseClicked(fn -> {
        	if(fn.getClickCount() == 3){
        		for(int i = 0; i < diagram.USTAFF.size(); i++){
        			diagram.USTAFF.get(i).setStyle("-fx-background-color: yellow; -fx-border-color: black");
        			email.addEmail(email.getEmailList(), diagram.USTAFFemails.get(i));
        			email.addName(email.getNameList(), diagram.USTAFFnames.get(i));
        		}
        	}
        });
        
        TextField subject = new TextField();
        subject.setPromptText("Subject");
        
        TextArea body = new TextArea();
        body.setPromptText("Message Contents");
        
        TextArea addresses = new TextArea();
        addresses.setPromptText("No Emails Selected");
        addresses.setPrefHeight(75);
        addresses.setWrapText(true);
        
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> Platform.exit());
        
        Menu file = new Menu("File");
        file.getItems().addAll(exit);
        
        MenuItem clear = new MenuItem("Clear All");
        clear.setOnAction(v -> {
        	email.clearList();
        	diagram.clearSelection();
        	addresses.setText(null);
        	subject.setText(null);
        	body.setText(null);
        });
        
        Menu reset = new Menu("Reset...");
        reset.getItems().add(clear);
        
        Menu help = new Menu("Help");
        //TODO Create help menu
        
        Tab bnStaff = new Tab("BN Staff", chart.get(0));
        Tab weaponsT = new Tab("Weapons", chart.get(1));
        Tab engineeringT = new Tab("Engineering", chart.get(2));
        Tab navigationT = new Tab("Navigation", chart.get(3)); 
        Tab mopltT = new Tab("Marine Options", chart.get(4));
        Tab unitStaff = new Tab("Unit Staff", chart.get(5));
        
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabs.getTabs().addAll(unitStaff, bnStaff, weaponsT, engineeringT, navigationT, mopltT);
        tabs.setOnMouseClicked(g -> {
        	addresses.setText(Arrays.toString(email.outputEmailList()));
        	if(email.isEmpty()){
        		addresses.setText(null);
        	}
        });
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file, reset, help);

                
        VBox inputArea = new VBox();
        inputArea.getChildren().addAll(addresses, subject, body);
        inputArea.setSpacing(5);
        
        Button recall = new Button("MASS RECALL");
        recall.setPrefSize(205, 40);
        recall.setStyle("-fx-font-weight: bold; -fx-background-color: yellow; -fx-font-size: 20; -fx-border-color: black");
        recall.setOnMouseClicked(fn -> 
        {	
        	Stage recallCheck = new Stage();
        	
        	Text statement = new Text("THIS WILL SEND A MESSAGE TO ALL USERS!");
        	
        	Button confirm = new Button("Confirm");
        	confirm.setOnMouseClicked(x -> {
        		try {
                email.getAllEmails();
                diagram.selectAll();
        		addresses.setText(Arrays.toString(email.outputEmailList()));
        		body.setText("This is a RECALL, all-hands must respond to this email by clicking the given link.");
        		subject.setText("BATALLION RECALL");
        		} catch (BiffException | IOException e) 
        		{
                System.out.println("There was an error getting the correct email addresses.");
        		}
        		recallCheck.close();
        	});
        	confirm.setPrefSize(100, 20);
        	confirm.setStyle("-fx-background-color: green; -fx-font-weight: bold");
        	
        	Button accident = new Button("Cancel");
        	accident.setOnMouseClicked(e -> recallCheck.close());
        	accident.setPrefSize(100,  20);
        	accident.setStyle("-fx-background-color: red; -fx-font-weight: bold");
        	
        	HBox options = new HBox(confirm, accident);
        	options.setSpacing(10);
        	options.setAlignment(Pos.CENTER);
        	
        	VBox warningWindow = new VBox(statement, options);
        	warningWindow.setSpacing(10);
        	warningWindow.setAlignment(Pos.CENTER);
        	
        	Scene recallVerify = new Scene(warningWindow);
        	
        	recallCheck.setScene(recallVerify);
        	recallCheck.setMinHeight(200);
        	recallCheck.setMinWidth(400);
        	recallCheck.setTitle("WARNING");
        	recallCheck.show();
        });
               
        Button send = new Button("Send");
        send.setPrefSize(100, 20);
        send.setStyle("-fx-font-weight: bold");
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
        	entryWin.setHgap(10);
        	entryWin.setVgap(10);
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
                	
                	Text error = new Text("Authentication Error");
                	
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
        			ShowGui nextUI = new ShowGui();
        			
        			try {
						nextUI.start();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
        		}
        	});
        	ok.setPrefSize(100, 20);
        	
        	Button backout = new Button("Cancel");
        	backout.setOnMouseClicked(func -> passwordWindow.close());
        	backout.setPrefSize(100, 20);
        	
        	HBox bnInput = new HBox(ok, backout);
        	bnInput.setAlignment(Pos.CENTER);
        	bnInput.setSpacing(10);
        	
        	VBox passWindow = new VBox(entryWin, bnInput);
        	passWindow.setAlignment(Pos.CENTER);
        	passWindow.setPrefSize(400, 100);
        	passWindow.setSpacing(10);
        	
        	Scene passPrompt = new Scene(passWindow);
        	
        	passwordWindow.setScene(passPrompt);
        	passwordWindow.setTitle("AUTHENTICATION CHECK");
        	passwordWindow.show();
        });
        
        Button cancel = new Button("Cancel");
        cancel.setPrefSize(100, 20);
        cancel.setOnAction(y -> Platform.exit());
        cancel.setStyle("-fx-font-weight: bold");
        
        HBox mainCtl = new HBox(cancel, send);
        mainCtl.setSpacing(5);
        mainCtl.setAlignment(Pos.TOP_CENTER);
        
        VBox allBn = new VBox(recall, mainCtl);
        allBn.setSpacing(10);
        allBn.setAlignment(Pos.TOP_CENTER);
        
        Pane spacer = new Pane();
        spacer.setPrefSize(10, tabs.getHeight());
        
        VBox rightside = new VBox(inputArea, allBn);
        rightside.setSpacing(10);
        
        GridPane root = new GridPane();
        root.add(tabs, 0, 0);
        root.add(spacer, 1, 0, 1, 2);
        root.add(rightside, 2, 0);
        
        BorderPane staging = new BorderPane();
        staging.setTop(menuBar);
        staging.setCenter(root);
        
        Scene rootOverhaul = new Scene(staging);
        
        uiOverhaul.setScene(rootOverhaul);
        uiOverhaul.setTitle("Quick Email Notification");
        uiOverhaul.show();
    }
    
    public static void main(String[] args){
    	Application.launch(args);
    }
}