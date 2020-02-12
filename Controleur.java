import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.Normalizer;
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
			
		}
	}
	
	@FXML
	public void conversion() throws IOException, SAXException, ParserConfigurationException, TransformerException{
		FileChooser fileChooser = new FileChooser();
		 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);
        
        if (file != null) {
        	saveXMLfile(f,file);
        	valid.setText("Fichier converti et sauvegardé");
        }
        else{
			label.setText("Aucun fichier sélectionné.");
		}
		
	}
	
	public void saveXMLfile(File old_file, File dest){
		try
	    {
		    File nfile = new File(dest.getAbsolutePath());
		    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(old_file), "UTF8"));
		    String line = "", oldtext = "";
		    while((line = reader.readLine()) != null)
		        {
		        oldtext += line + "\r\n";
		    }
		    reader.close();
		    //Normalize
		    String newtext = toEAST(oldtext);
		    
		    //To replace a line in a file
		    //String newtext = oldtext.replaceAll("This is test string 20000", "blah blah blah");
		    
		    FileWriter writer = new FileWriter(nfile.getAbsolutePath());
		    writer.write(newtext);writer.close();
		}
		catch (IOException ioe)
		    {
		    ioe.printStackTrace();
		}
	}
	
	public String toEAST(String content){
		//Caractères spéciaux
		content = content.replaceAll("•", "?");
		content = content.replaceAll("–", "?");
		content = content.replaceAll("’", "'");
		content = content.replaceAll("&apos;", "'");
		content = content.replaceAll("«", "\"");
		content = content.replaceAll("»", "\"");
		
		//Balises
		/*content = content.replaceAll("<text:span text:style-name=\"T4\">", "<TITRE>");
		content = content.replaceAll("<text:span text:style-name=\"T5\">", "<TITRE>");
		content = content.replaceAll("<text:span text:style-name=\"T6\">", "<TITRE>");*/
		content = content.replaceAll("<draw:page ", "<PARTIE ");
		content = content.replaceAll("</draw:page>", "</PARTIE>");
		content = content.replaceAll("<draw:plugin ", "<video autoplay=\"true\" fichier =\"\" hauteur=\"50\" larger=\"50\" ");
		content = content.replaceAll("</draw:plugin>", "</video>");
		content = content.replaceAll("<text:list ", "<LISTE ");
		content = content.replaceAll("</text:list>", "</LISTE>");
		content = content.replaceAll("<text:list-item>", "<EL>");
		content = content.replaceAll("</text:list-item>", "</EL>");
		content = content.replaceAll("<table:table-cell>", "<LI><CI>");
		content = content.replaceAll("</table:table-cell>", "</CI></LI>");
		content = content.replaceAll("<table:table>", "<tableau>");
		content = content.replaceAll("</table:table>", "</tableau>");
		content = content.replaceAll("<text:span ", "<paragraphe ");
		content = content.replaceAll("</text:span>", "</paragraphe>");
		content = content.replaceAll("<draw:frame ", "<envimage ");
		content = content.replaceAll("</draw:frame>", "</envimage>");
		content = content.replaceAll("<dc:date>", "<date>");
		content = content.replaceAll("</dc:date>", "</date>");
		content = content.replaceAll("<text:p>", "<commentaire>");
		content = content.replaceAll("<text:p ", "<commentaire ");
		content = content.replaceAll("</text:p>", "</commentaire>");
		content = content.replaceAll("<draw:image ", "<image ");
		content = content.replaceAll("</draw:image>", "</image>");
		content = content.replaceAll("<text:a ", "<lien_ interne ");
		content = content.replaceAll("</text:a>", "</lien_ interne>");
		content = content.replaceAll("<draw:equation ", "<equation ");
		
		//Accents
		content = Normalizer.normalize(content, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
		
		return content;
	}
	
}
