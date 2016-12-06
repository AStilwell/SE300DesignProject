import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * @author Abdullah Alosaimi
 * @version 1.0
 * Once everyone has confirmed the showAllConf() method will
 * be called to show a window with"Everyone has confirmed" message.
 */
public class AllConfGUI extends UserInterface{
	
	/**
	 * This method show the sender a GUI window when 
	 * everyone has confirmed.
	 */
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
