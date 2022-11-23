/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controlador;

import java.util.Arrays;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javax.swing.JDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;

/**
 *
 * @author daviiid99
 */
public class FXMLDocumentController implements Initializable {
    
    public String currentNote = "Escribe algo";
    public String path = "";
        
    @FXML
    private TextField label;
    
    @FXML
    private ListView lista;
    
    @FXML
    private Button button;
    
    @FXML
    private Button delete;
    
    @FXML
    private MenuItem exportando;
    
    @FXML
    private MenuItem importando;
    
    @FXML
    private String contents = "";
    
    @FXML
    private File miNota;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    // Save current note name
    currentNote = label.getText();

    // Add note to listview
    if(!currentNote.isEmpty()){
    lista.getItems().add(currentNote);
    }

    // Clean text field for next iteration
     label.setText("");

    }
    
    @FXML
    private void addLineToListView(String line) {
        
    // Add note to listview
    if(line.equals("null") == false){
    lista.getItems().add(line);
    }

    // Clean text field for next iteration
     label.setText("");

    }

    @FXML
    private void handleDeleteAction(ActionEvent event){
        if (lista.getItems().size() > 0){
            // To prevent exception if list is empty
        final int selectedItem = lista.getSelectionModel().getSelectedIndex();
        if (selectedItem >= 0){
            // Prevent delete if no choosed item
        lista.getItems().remove(selectedItem);
        }
        }
    }
    
     private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
        }
    }
     
     private void readFile(File file){
         FileReader fileReader;
                  
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            for (String line = br.readLine(); line !=null;line = br.readLine()){
                addLineToListView(line);
            }
            // return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
     }
    
    @FXML
    private void handleExportAction(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Exportar Nota");
        chooser.getExtensionFilters().addAll(new ExtensionFilter(".txt", ".txt"));
        File miNota = chooser.showSaveDialog(new Stage());
        SaveFile(getFileContents(), miNota);
        this.contents = "";

    }
    
    @FXML
    private void handleImportAction(ActionEvent event){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Abrir Nota");
        File miNota = chooser.showOpenDialog(new Stage());
        readFile(miNota);
    }
    
    
    @FXML
    private String getFileContents(){
        // We'll get all contents inside listview and wrap them into an array in order to export it into a file later
        
        for (int i = 0; i < lista.getItems().size(); i++){
                if (!lista.getItems().get(i).equals(null)){
                     contents += lista.getItems().get(i) + "\n";
                }
            }
        
        return this.contents; 
    }
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
