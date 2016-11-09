package javaFiles;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Andrew Stilwell on 24-Oct-16.
 */
public class Diagram
{

	private static final String filePath = ".\\src\\refDocs\\Test Spreadsheet.xls"; //TODO
	//private URL jarPath = this.getClass().getClassLoader().getResource("refDocs/Test Spreadsheet.xls");
	ArrayList<VBox> storage = new ArrayList<>();
	ArrayList<VBox> nodeList = new ArrayList<>();

	/**
	 * @return Generates an array list of appropriate nodes for use in the main
	 *         GUI creation.
	 * @throws BiffException
	 * @throws IOException
	 */
	public void read() throws BiffException, IOException
	{

		VBox command = new VBox();

		VBox wepsAll = new VBox();
		VBox wepsCommand = new VBox();
		VBox caStaff = new VBox();
		VBox cgStaff = new VBox();
		VBox caDiv = new VBox();
		VBox cgDiv = new VBox();
		VBox ca01 = new VBox();
		VBox ca02 = new VBox();
		VBox cg01 = new VBox();
		VBox cg02 = new VBox();

		HBox caPersonnel = new HBox();
		HBox cgPersonnel = new HBox();
		HBox wepsMain = new HBox();

		VBox wepsDiagram = new VBox();

		VBox engAll = new VBox();
		VBox engCommand = new VBox();
		VBox emStaff = new VBox();
		VBox eaStaff = new VBox();
		VBox emDiv = new VBox();
		VBox eaDiv = new VBox();
		VBox em01 = new VBox();
		VBox em02 = new VBox();
		VBox ea01 = new VBox();
		VBox ea02 = new VBox();

		HBox emPersonnel = new HBox();
		HBox eaPersonnel = new HBox();
		HBox engMain = new HBox();

		VBox engDiagram = new VBox();
		
		VBox navAll = new VBox();
		VBox navCommand = new VBox();
		VBox odStaff = new VBox();
		VBox oiStaff = new VBox();
		VBox odDiv = new VBox();
		VBox oiDiv = new VBox();
		VBox od01 = new VBox();
		VBox od02 = new VBox();
		VBox oi01 = new VBox();
		VBox oi02 = new VBox();

		HBox oiPersonnel = new HBox();
		HBox odPersonnel = new HBox();
		HBox navMain = new HBox();

		VBox navDiagram = new VBox();

		VBox moAll = new VBox(); //mo everyone
		VBox moCommand = new VBox(); //mostaff
		VBox sq1Staff = new VBox(); //div1 staff
		VBox sq2Staff = new VBox(); //div2 staff
		HBox sq1 = new HBox(); //division
		HBox sq2 = new HBox(); //division	
		VBox f11 = new VBox(); //wc
		VBox f12 = new VBox(); //wc
		VBox f13 = new VBox(); //wc
		VBox f21 = new VBox(); //wc
		VBox f22 = new VBox(); //wc
		VBox f23 = new VBox(); //wc

		VBox sq1Personnel = new VBox();
		VBox sq2Personnel = new VBox();
		HBox moMain = new HBox();

		VBox moDiagram = new VBox();
		
		VBox unitStaff = new VBox();
		HBox allO3Staff = new HBox();
		HBox depStaff = new HBox();
		VBox marineStaff = new VBox();
		VBox seniorStaff = new VBox();

		File file = new File(filePath);
		Workbook workbook = Workbook.getWorkbook(file);
		Sheet sheet = workbook.getSheet(0);

		for (int j = 0; j < sheet.getRows(); j++)
		{
			Cell cell = sheet.getCell(0, j);
			Cell workCenter = sheet.getCell(1, j);
			switch (cell.getContents())
			{
			case "UNIT STAFF":
				switch (workCenter.getContents()){
				case "WEPS":
					depStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "ENG":
					depStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "NAV":
					depStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "MOI":
					marineStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "AMOI":
					marineStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "CO":
					seniorStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "XO":
					seniorStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				}
				break;
			case "BN STAFF":
				command.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
						sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
			case "WEPS":
				switch (workCenter.getContents())
				{
				case "STAFF":
					wepsCommand.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "CA":
					caStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "CA-01":
					ca01.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "CA-02":
					ca02.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "CG":
					cgStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "CG-01":
					cg01.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "CG-02":
					cg02.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				}
				break;
			case "ENG":
				switch (workCenter.getContents())
				{
				case "STAFF":
					engCommand.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "EA":
					eaStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "EA-01":
					ea01.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "EA-02":
					ea02.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "EM":
					emStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "EM-01":
					em01.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "EM-02":
					em02.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				}
				break;
			case "NAV":
				switch (workCenter.getContents())
				{
				case "STAFF":
					navCommand.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "OI":
					oiStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "OI-01":
					oi01.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "OI-02":
					oi02.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "OD":
					odStaff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "OD-01":
					od01.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "OD-02":
					od02.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				}
				break;
			case "MO":
				switch (workCenter.getContents())
				{
				case "STAFF":
					moCommand.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "SQUAD 1":
					sq1Staff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "1-1":
					f11.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "1-2":
					f12.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "1-3":
					f13.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "SQUAD 2":
					sq2Staff.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "2-1":
					f21.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "2-2":
					f22.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				case "2-3":
					f23.getChildren().add(createPane(sheet.getCell(2, j).getContents(),
							sheet.getCell(3, j).getContents(), sheet.getCell(4, j).getContents()));
					break;
				}
				break;
			}
		}
		wepsCommand.setSpacing(10);
		caPersonnel.getChildren().addAll(ca01, ca02);
		caPersonnel.setSpacing(10);
		cgPersonnel.getChildren().addAll(cg01, cg02);
		cgPersonnel.setSpacing(10);
		caDiv.getChildren().addAll(caStaff, caPersonnel);
		caDiv.setSpacing(10);
		cgDiv.getChildren().addAll(cgStaff, cgPersonnel);
		cgDiv.setSpacing(10);
		wepsMain.getChildren().addAll(caDiv, cgDiv);
		wepsMain.setSpacing(10);

		wepsMain.setAlignment(Pos.CENTER);

		wepsAll.getChildren().addAll(wepsCommand, wepsMain);
		wepsAll.setSpacing(10);

		wepsDiagram.getChildren().addAll(wepsAll);
		
		engCommand.setSpacing(10);
		eaPersonnel.getChildren().addAll(ea01, ea02);
		eaPersonnel.setSpacing(10);
		emPersonnel.getChildren().addAll(em01, em02);
		emPersonnel.setSpacing(10);
		eaDiv.getChildren().addAll(eaStaff, eaPersonnel);
		eaDiv.setSpacing(10);
		emDiv.getChildren().addAll(emStaff, emPersonnel);
		emDiv.setSpacing(10);
		engMain.getChildren().addAll(eaDiv, emDiv);
		engMain.setSpacing(10);
		engAll.getChildren().addAll(engCommand, engMain);
		engAll.setSpacing(10);

		engMain.setAlignment(Pos.CENTER);

		engDiagram.getChildren().addAll(engAll);
		
		navCommand.setSpacing(10);
		oiPersonnel.getChildren().addAll(oi01, oi02);
		oiPersonnel.setSpacing(10);
		odPersonnel.getChildren().addAll(od01, od02);
		odPersonnel.setSpacing(10);
		oiDiv.getChildren().addAll(oiStaff, oiPersonnel);
		oiDiv.setSpacing(10);
		odDiv.getChildren().addAll(odStaff, odPersonnel);
		odDiv.setSpacing(10);
		navMain.getChildren().addAll(oiDiv, odDiv);
		navMain.setSpacing(10);
		navAll.getChildren().addAll(navCommand, navMain);
		navAll.setSpacing(10);

		navMain.setAlignment(Pos.CENTER);

		navDiagram.getChildren().addAll(navAll);
		
		moCommand.setSpacing(10);
		sq1.getChildren().addAll(f11, f12, f13);
		sq1.setSpacing(10);
		sq2.getChildren().addAll(f21, f22, f23);
		sq2.setSpacing(10);
		
		sq1Personnel.getChildren().addAll(sq1Staff, sq1);
		sq1Personnel.setSpacing(10);
		sq2Personnel.getChildren().addAll(sq2Staff, sq2);
		sq2Personnel.setSpacing(10);
		
		moMain.getChildren().addAll(sq1Personnel, sq2Personnel);
		moMain.setAlignment(Pos.CENTER);
		moMain.setSpacing(10);
		
		moAll.getChildren().addAll(moCommand, moMain);
		moAll.setSpacing(10);
		
		moDiagram.getChildren().add(moAll);
		
		command.setSpacing(10);
		
		seniorStaff.setSpacing(10);
		
		allO3Staff.getChildren().addAll(depStaff, marineStaff);
		allO3Staff.setAlignment(Pos.CENTER);
		allO3Staff.setSpacing(10);
		unitStaff.getChildren().addAll(seniorStaff, allO3Staff);
		unitStaff.setSpacing(10);
		
		command.setAlignment(Pos.TOP_CENTER);
		wepsDiagram.setAlignment(Pos.TOP_CENTER);
		engDiagram.setAlignment(Pos.TOP_CENTER);
		moDiagram.setAlignment(Pos.TOP_CENTER);
		
		
		storage.add(0, command);
		storage.add(1, wepsDiagram);
		storage.add(2, engDiagram);
		storage.add(3, navDiagram);
		storage.add(4, moDiagram);
		storage.add(5, unitStaff);
	}

	/**
	 * @param p
	 *            The position of the row being read.
	 * @param n
	 *            The name of the row being read.
	 * @param e
	 *            The email of the row being read.
	 * @return The created graphic node that contains name, position, and email
	 */
	private VBox createPane(String p, String n, String e)
	{

		VBox node = new VBox();

		Text title = new Text();
		title.setText(p);
		title.setTextAlignment(TextAlignment.CENTER);

		Text name = new Text();
		name.setText(n);
		name.setTextAlignment(TextAlignment.CENTER);

		Text email = new Text();
		email.setText(e);
		email.setTextAlignment(TextAlignment.CENTER);

		node.getChildren().addAll(title, name, email);
		node.setAlignment(Pos.CENTER);

		nodeList.add(node);
		
		node.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			EmailList temp = new EmailList();

			@Override
			public void handle(MouseEvent event)
			{
				MouseButton button = event.getButton();
				if (button == MouseButton.PRIMARY)
				{
					node.setStyle("-fx-background-color: yellow; -fx-border-color: black");
					temp.addEmail(temp.getEmailList(), e);
					temp.addName(temp.getNameList(), n);
					
				} else if (button == MouseButton.SECONDARY)
				{
					node.setStyle("-fx-background-color: transparent; -fx-border-color: black");
					temp.delEmail(temp.getEmailList(), e);
					temp.delName(temp.getEmailList(), n);
				}
			}
		});

		node.setStyle("-fx-border-color: black");
		node.setSpacing(5);
		return node;
	}

	/**
	 * @return The final diagram created by this class
	 */
	public ArrayList<VBox> getDiagram()
	{
		return storage;
	}

	public void clearSelection()
	{
		for (int i = 0; i < nodeList.size(); i++)
		{
			nodeList.get(i).setStyle("-fx-background-color: transparent; -fx-border-color: black");
		}
	}

	public void selectAll()
	{
		for (int i = 0; i < nodeList.size(); i++)
		{
			nodeList.get(i).setStyle("-fx-background-color: yellow; -fx-border-color: black");
		}
	}
}
