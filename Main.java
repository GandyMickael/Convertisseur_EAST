import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Main extends Application {

	static String valeur = null;
	static Stage primaryStage=null;
	static Scene StaticScene;
	
	@Override
	public void start(Stage primaryStage) {
		new JFXPanel();
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {

				try {
		   			// Read file fxml and draw interface.
					FXMLLoader loader = new FXMLLoader();
			        loader.setLocation(Main.class.getResource("/Scene/Convertisseur.fxml"));
			        Parent content = loader.load(); 
			        
			        primaryStage.setTitle("Convertisseur EAST");
			        primaryStage.setScene(new Scene(content));
			        primaryStage.show();
			            
			    } catch(Exception err) {
			        err.printStackTrace();
			    }	     
		    }
		});    	 
	}

	public static String  getValue(){
		return valeur;	
	}
	public static Scene getScene(){
		return StaticScene;	
	}
	public static Stage getPrimaryStage(){
		return primaryStage;
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	

	
}