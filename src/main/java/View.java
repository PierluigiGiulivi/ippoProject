import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


/**
 * Class View.
 *
 * This class was taken from example number 8 out of the JavaFX Examples done by Paul Anderson.
 * https://groups.inf.ed.ac.uk/ippo/2020/website/code/ippo-fxexamples.zip [02/12/2020]
 *
 * @author Paul Anderson & Pierluigi Giulivi
 * @version 30/11/2020
 */

public class View extends Application {

	public void start(Stage stage) {
		
		try {

			String viewerFxml = "GUI.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(viewerFxml));
			AnchorPane page = (AnchorPane) fxmlLoader.load();
			Scene scene = new Scene(page);
			
			stage.setScene(scene);
			
			WorldController controller = (WorldController) fxmlLoader.getController();      			
			controller.Initialise();

			stage.show();
        
		} catch (IOException ex) {
		   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		   System.exit(1);
		}
	}
	
    public static void main(String args[]) {
     	launch(args);
     	System.exit(0);
    }

}
