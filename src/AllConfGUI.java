import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class AllConfGUI extends UserInterface{
	
	public void showAllConf(){
		
		 Platform.setImplicitExit(false);
		 StackPane pane1 = new StackPane();
		 Scene Conscene = new Scene (pane1, 400, 100);
		 Text tx = new Text();
		 tx.setText("Everyone has confirmed!!\n You can close this window");
		 pane1.getChildren().add(tx);
		 uiOverhaul.hide();
		 uiOverhaul.setScene(Conscene);
		 uiOverhaul.show();
	}
}
