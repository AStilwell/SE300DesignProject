package javaFiles;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Andrew Stilwell on 24-Oct-16.
 */
public class Diagram
{

	private static final String filePath = ".\\src\\Test Spreadsheet.xls";
	ArrayList<VBox> storage = new ArrayList<>();
	ArrayList<VBox> nodeList = new ArrayList<>();

	/**
	 * @return Generates an array list of appropriate nodes for use in the main
	 *         GUI creation.
	 * @throws BiffException
	 * @throws IOException
	 */
	public ArrayList<VBox> read() throws BiffException, IOException
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

		VBox moAll = new VBox();
		VBox moCommand = new VBox();
		VBox sq1Staff = new VBox();
		VBox sq2Staff = new VBox();
		VBox sq1 = new VBox();
		VBox sq2 = new VBox();
		VBox f11 = new VBox();
		VBox f12 = new VBox();
		VBox f13 = new VBox();
		VBox f21 = new VBox();
		VBox f22 = new VBox();
		VBox f23 = new VBox();

		HBox sq1Personnel = new HBox();
		HBox sq2Personnel = new HBox();
		HBox moMain = new HBox();

		VBox moDiagram = new VBox();

		File file = new File(filePath);
		Workbook workbook = Workbook.getWorkbook(file);
		Sheet sheet = workbook.getSheet(0);

		for (int j = 0; j < sheet.getRows(); j++)
		{
			Cell cell = sheet.getCell(0, j);
			Cell workCenter = sheet.getCell(1, j);
			switch (cell.getContents())
			{
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
				// TODO
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

		caPersonnel.getChildren().addAll(ca01, ca02);
		caPersonnel.setSpacing(15);
		cgPersonnel.getChildren().addAll(cg01, cg02);
		cgPersonnel.setSpacing(15);
		caDiv.getChildren().addAll(caStaff, caPersonnel);
		caDiv.setSpacing(15);
		cgDiv.getChildren().addAll(cgStaff, cgPersonnel);
		cgDiv.setSpacing(15);
		wepsMain.getChildren().addAll(caDiv, cgDiv);
		wepsMain.setSpacing(15);

		wepsMain.setAlignment(Pos.CENTER);

		wepsAll.getChildren().addAll(wepsCommand, wepsMain);
		wepsAll.setSpacing(15);

		wepsDiagram.getChildren().addAll(wepsAll);

		eaPersonnel.getChildren().addAll(ea01, ea02);
		eaPersonnel.setSpacing(15);
		emPersonnel.getChildren().addAll(em01, em02);
		emPersonnel.setSpacing(15);
		eaDiv.getChildren().addAll(eaStaff, eaPersonnel);
		eaDiv.setSpacing(15);
		emDiv.getChildren().addAll(emStaff, emPersonnel);
		emDiv.setSpacing(15);
		engMain.getChildren().addAll(eaDiv, emDiv);
		engMain.setSpacing(15);
		engAll.getChildren().addAll(engCommand, engMain);
		engAll.setSpacing(15);

		engMain.setAlignment(Pos.CENTER);

		engDiagram.getChildren().addAll(engAll);

		/**sq1Personnel.getChildren().addAll(f11, f12, f13);
		sq1Personnel.setSpacing(15);
		sq2Personnel.getChildren().addAll(f21, f22, f23);
		sq2Personnel.setSpacing(15);
		eaDiv.getChildren().addAll(eaStaff, eaPersonnel); // TODO
		eaDiv.setSpacing(15);
		emDiv.getChildren().addAll(emStaff, emPersonnel);
		emDiv.setSpacing(15);
		engMain.getChildren().addAll(eaDiv, emDiv);
		engMain.setSpacing(15);
		engAll.getChildren().addAll(engCommand, engMain);
		engAll.setSpacing(15);

		engMain.setAlignment(Pos.CENTER);

		engDiagram.getChildren().addAll(engAll);
		**/
		
		storage.add(0, command);
		storage.add(1, wepsDiagram);
		storage.add(2, engDiagram);

		return storage;
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
		title.setText("TITLE: " + p);

		Text name = new Text();
		name.setText("NAME: " + n);

		Text email = new Text();
		email.setText("EMAIL: " + e);

		node.getChildren().addAll(title, name, email);

		nodeList.add(node);

		System.out.println("Node Created: " + p);

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
