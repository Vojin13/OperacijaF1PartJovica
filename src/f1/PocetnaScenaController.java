/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author CJ
 */
public class PocetnaScenaController implements Initializable {

    /*  @FXML  
    private Hyperlink stazeHL;*/
    @FXML
    private MenuButton stazaMenuBtn;
    @FXML
    private MenuItem dodajStazuMBtn;
    @FXML
    private MenuItem promeniStazuMBtn;
    @FXML
    private MenuItem obrisiStazuMBtn;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    // postavljanje nove scene Staze
    @FXML
    private void scenaStaze(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLStazePrikazi.fxml"));
        Scene scene = new Scene(root);     
        
        f1FXML.getStage().setScene(scene);        
        f1FXML.setStage(f1FXML.getStage());   
        
     //   scene.getStylesheets().add(f1FXML.class.getResource("F1.css").toExternalForm());
    
        System.out.println("Scena Staze postavljena.");         
    } 
    
    // dodavanje nove staze u listu
    @FXML
    public void dodajStazu(Event event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDodajSacuvaj.fxml"));
        Scene scene = new Scene(root);     
        
        f1FXML.getStage().setScene(scene);        
        f1FXML.setStage(f1FXML.getStage());       
    
        System.out.println("Scena Dodaj/Sačuvaj Stazu postavljena."); 
    }
    
    // postavljanje nove scene Timovi
    @FXML
    private void scenaTimovi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTimoviPrikazi.fxml"));
        Scene scene = new Scene(root);     
        
        f1FXML.getStage().setScene(scene);        
        f1FXML.setStage(f1FXML.getStage());  
        
     //   scene.getStylesheets().add(f1FXML.class.getResource("F1.css").toExternalForm());
    
        System.out.println("Scena Timovi postavljena.");         
    } 
    
}
