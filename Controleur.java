import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controleur implements Initializable{
	@FXML private Button search;
	@FXML private Button convert;
	@FXML private Text label;
	private File f;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void choose(ActionEvent event){
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Fichier XML", ".xml"));
		f = fc.showOpenDialog(null);
		if(f!=null){
			label.setText("Fichier selectionné : "+f.getAbsolutePath());
			System.out.println(label.getText());
		}
	}
	
	@FXML
	public void conversion() throws IOException{
	}
}
