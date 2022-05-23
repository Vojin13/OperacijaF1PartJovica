/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sezona.Staza;

/**
 * FXML Controller class
 *
 * @author CJ
 */
public class StazeDodajSacuvajController implements Initializable {

    @FXML
    private TextField nazivTF; 
  
    @FXML
    private TextField lokacijaTF;
 
    @FXML
    private TextField drzavaTF;   
  
    @FXML
    private TextField tipStazeTF;
  
    @FXML
    private TextField duzinaTF;
   
    @FXML
    private TextField smerTF;
       
    
    @FXML
    private Button sacuvajBtn; 
  
     
    private ObjectOutputStream oos;    
    private ObjectInputStream ois;  
    
    
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    // metoda za dodavanje nove Staze u ListView iz TextFielda
    public void dodajStazu() {
        if ((!nazivTF.getText().equals("")) && (!lokacijaTF.getText().equals(""))
            && (!drzavaTF.getText().equals("")) && (!tipStazeTF.getText().equals(""))
            && (!duzinaTF.getText().equals("")) && (!smerTF.getText().equals("")))  {           
            Staza novaStaza = new Staza(nazivTF.getText(), lokacijaTF.getText(),
                                        drzavaTF.getText(),tipStazeTF.getText(),
                      Double.parseDouble(duzinaTF.getText()), smerTF.getText());        
           
           System.out.println(novaStaza.getNazivStaze());
           f1FXML.getStaze().add(novaStaza);
        //   f1FXML.prikaziStazeAL();
           
           f1FXML.getStazeNazivOL().add(novaStaza.getNazivStaze());
           
           nazivTF.clear(); lokacijaTF.clear(); drzavaTF.clear();
           tipStazeTF.clear(); duzinaTF.clear(); smerTF.clear();
        }
    }     
    
    
    // metoda za čuvanje ObservableList<String> stazeOL u fajl staze.ser
    public void sacuvajStaze() throws FileNotFoundException, IOException {
        oos = new ObjectOutputStream(new FileOutputStream("staze.ser"));     
        oos.writeObject(f1FXML.getStaze()); 
        
        System.out.println("\nWrite file staze.ser"); 
    }
    
}
