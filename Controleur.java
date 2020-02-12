import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
	@FXML private Text valid;
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
			//System.out.println(xmltoString());
		}
	}
	
	@FXML
	public void conversion() throws IOException, SAXException, ParserConfigurationException, TransformerException{
		String st = xmltoString();
		//st = st.replaceAll("\\baxe\\b", "sword");
		
		FileChooser fileChooser = new FileChooser();
		 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);
        
        if (file != null) {
        	try {
                Files.copy(f.toPath(), file.toPath());
            } catch (IOException ex) {
                // handle exception...
            }
        	valid.setText("Fichier converti et sauvegardé");
        }
		
	}
	
	public String xmltoString(){
		String st=null;
		if(f==null){
			label.setText("Aucun fichier sélectionné.");
		}
		else{
			try{
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				InputSource is = new InputSource(f.getAbsolutePath());
				Document document = docBuilderFactory.newDocumentBuilder().parse(is);
				StringWriter sw = new StringWriter();
				Transformer serializer = TransformerFactory.newInstance().newTransformer();
				serializer.transform(new DOMSource(document), new StreamResult(sw));
				st=sw.toString();
			}
			catch (Exception e) {
				e.printStackTrace();
			}		
		}
		return st;	
	}
	
	private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
