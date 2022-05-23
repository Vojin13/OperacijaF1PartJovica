/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sezona.Staza;

/**
 *
 * @author CJ
 */

public class StazePrikaziController implements Initializable {
    
    @FXML
    private ListView stazeLW;   
    
    @FXML
    private Label stazaOpis; 
    
    @FXML
    private ImageView stazaImg;   
    
 
    // override metod initialize, koji se aktivira pri pokretanju aplikacije
    // StazeLW će automatski biti popunjen nazivima staza iz fajla staze.ser      
    @Override
    public void initialize(URL location, ResourceBundle resources) {      
        try {      
            ucitajStaze();             
           
            for(Staza staza : f1FXML.getStaze()) {
                if (staza.getNazivStaze().equals(stazeLW.getSelectionModel().getSelectedItem())) {
                    Image slika = new Image("/slike/staze/" + staza.getNazivStaze() + ".png");
                    stazaImg.setImage(slika);                
                    stazaOpis.setText(staza.getNazivStaze() + " - " +
                                         staza.getLokacija() + " - " +
                                         staza.getDrzava());                   
                }    
            }
           
               
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(StazePrikaziController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    //    System.out.println(f1FXML.getStaze().isEmpty());
        
    } 
   
    public void scenaDodajSacuvajStazu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLStazeDodajSacuvaj.fxml"));
   
        Scene secondScene = new Scene(root);
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage - Scene");
	newWindow.setScene(secondScene);
        
        
        newWindow.setX(secondScene.getX() + 200);
	newWindow.setY(secondScene.getY() + 500);
        
        newWindow.show();
    }    
       
    
    // metoda za učitavanje fajla staze.ser u ArrayList staze, i
    // smeštanje naziva staza u ListView stazeLW, sa prikazom prve stavke iz liste
    public void ucitajStaze() throws FileNotFoundException, IOException, ClassNotFoundException {        
       
        ucitajIzFajla(new ObjectInputStream(new FileInputStream("staze.ser")));
        
        ArrayList<String> stazeNazivAL = new ArrayList();
        f1FXML.getStaze().forEach(s -> stazeNazivAL.add(s.getNazivStaze()));
        
        f1FXML.setStazeNazivOL(stazeNazivAL);
        stazeLW.setItems(f1FXML.getStazeNazivOL());            
        stazeLW.getSelectionModel().selectFirst();
        System.out.println("Popunjen ListVew stazeLW");
        
       // System.out.println(f1FXML.getStaze().isEmpty());
            
    } 
    
    public void ucitajIzFajla(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ArrayList<Staza> ucitanaLista = (ArrayList<Staza>) ois.readObject();
        f1FXML.setStaze(ucitanaLista); 
        System.out.println("Read file staze.ser" );
    }

    public void nazad(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPocetnaScena.fxml"));
        Scene scene = new Scene(root);

        f1FXML.getStage().setScene(scene);
        f1FXML.setStage(f1FXML.getStage());

        scene.getStylesheets().add(f1FXML.class.getResource("F1.css").toExternalForm());

        System.out.println("Nazad na Početnu Scenu.");
    }
    

    // metoda za biranje staze iz ListView stazeLW i prikaz detalja te staze u
    // labelama i ImageView       
    public void izaberiStazu(Event event) {       
        //  System.out.println(f1FXML.getStaze().isEmpty());
        for (Staza staza : f1FXML.getStaze()) {         
            if (staza.getNazivStaze().equals(stazeLW.getSelectionModel().getSelectedItem())) {
                try
                {
                    Image slika = new Image("/slike/staze/" + staza.getNazivStaze() + ".png");
                    stazaImg.setImage(slika);
                }
                catch(Exception e)
                {
                    Image slika = new Image("/slike/noImage.png");
                    stazaImg.setImage(slika);
                }

                stazaOpis.setText(staza.getNazivStaze() + " - " +
                                  staza.getLokacija() + " - " +
                                  staza.getDrzava());              
                break;                                
            }
        }
    }    
}
