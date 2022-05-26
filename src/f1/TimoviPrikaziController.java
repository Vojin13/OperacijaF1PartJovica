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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tim.Tim;
import tim.Vozac;

/**
 * FXML Controller class
 *
 * @author CJ
 */
public class TimoviPrikaziController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ListView timoviLV;
    @FXML
    private ImageView vozac1Img;
    @FXML
    private ImageView vozac2Img;
    @FXML
    private ImageView bolidImg;
    @FXML
    private Button dodajTim;
    @FXML
    private Label timOpis;    
    @FXML
    private Label prviVozac;    
    @FXML
    private Label drugiVozac;    
    @FXML
    private Label bolid;
    @FXML
    private Button izbrisiBtn;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            ucitajTimove();              
             
            for(Tim tim : f1FXML.getTimovi()) {
                if (tim.getNazivTima().equals(timoviLV.getSelectionModel().getSelectedItem())) {

                    List<Vozac> aktivniVozaci = new ArrayList<>();
                    aktivniVozaci = tim.getVozaci().stream().filter(vozac -> vozac.getUloga1().equals("DRIVERFIRST") || vozac.getUloga1().equals("DRIVERSECOND")).collect(Collectors.toList());
                                   
                    int c = 0;
                        for(Vozac vozac : aktivniVozaci){
                            if(vozac.getUloga1().equals("DRIVERFIRST") && (c !=0)){
                                Collections.swap(aktivniVozaci,0,c);
                            break;
                            }
                        c++;
                    }                    
                    try {
                        Image slika1 = new Image("/slike/vozaci/" + aktivniVozaci.get(0).getImeIPrezime() + ".png");
                        Image slika2 = new Image("/slike/vozaci/" + aktivniVozaci.get(1).getImeIPrezime() + ".png");
                        Image slika3 = new Image("/slike/bolidi/" + tim.bolid.getBolid() + ".png");
                        vozac1Img.setImage(slika1);
                        vozac2Img.setImage(slika2);
                        bolidImg.setImage(slika3);
                    }
                    catch (Exception e)
                    {
                        Image slika = new Image("/slike/noImage.png");
                        vozac1Img.setImage(slika);
                        vozac2Img.setImage(slika);
                        bolidImg.setImage(slika);
                    }
                    
                    timOpis.setText(tim.getNazivTima()); 
                    prviVozac.setText(aktivniVozaci.get(0).getIme() + " " + aktivniVozaci.get(0).getPrezime());
                    drugiVozac.setText(aktivniVozaci.get(1).getIme() + " " + aktivniVozaci.get(1).getPrezime());
                    bolid.setText(tim.bolid.getBolid());
                    
                  //  System.out.println(tim.bolid.getBolid());
                    
                }
            }               
        } catch (IOException | ClassNotFoundException ex) {
        Logger.getLogger(TimoviPrikaziController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    

    // metoda za učitavanje fajla staze.ser u ArrayList staze, i
    // smeštanje naziva staza u ListView stazeLW, sa prikazom prve stavke iz liste
    public void ucitajTimove() throws FileNotFoundException, IOException, ClassNotFoundException {        
       
        ucitajIzFajla(new ObjectInputStream(new FileInputStream("timovi.ser")));
        
        ArrayList<String> timoviNazivAL = new ArrayList();
        f1FXML.getTimovi().forEach(t -> timoviNazivAL.add(t.getNazivTima()));
        
        f1FXML.setTimoviNazivOL(timoviNazivAL);
        timoviLV.setItems(f1FXML.getTimoviNazivOL());
        timoviLV.getSelectionModel().selectFirst();
        System.out.println("Popunjen ListVew timoviLW");
        
       // System.out.println(f1FXML.getStaze().isEmpty());
            
    } 
    
    public void ucitajIzFajla(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ArrayList<Tim> ucitanaLista = (ArrayList<Tim>) ois.readObject();
        f1FXML.setTimovi(ucitanaLista); 
        System.out.println("Read file timovi.ser" );
    } 
    
    
    public void izaberiTim(Event event) { 
        for(Tim tim : f1FXML.getTimovi()) {
            if (tim.getNazivTima().equals(timoviLV.getSelectionModel().getSelectedItem())) {               
                    
                List<Vozac> aktivniVozaci = new ArrayList();
                aktivniVozaci = tim.getVozaci().stream().filter(vozac -> vozac.getUloga1().equals("DRIVERFIRST") || vozac.getUloga1().equals("DRIVERSECOND")).collect(Collectors.toList());
                                  
                int c = 0;
                for(Vozac vozac : aktivniVozaci){
                    if(vozac.getUloga1().equals("DRIVERFIRST") && (c !=0)){
                        Collections.swap(aktivniVozaci, 0, c);
                        break;
                    }
                    c++;
                }                    
                try
                {
                    Image slika1 = new Image("/slike/vozaci/" + aktivniVozaci.get(0).getImeIPrezime() + ".png");
                    Image slika2 = new Image("/slike/vozaci/" + aktivniVozaci.get(1).getImeIPrezime() + ".png");
                    Image slika3 = new Image("/slike/bolidi/" + tim.bolid.getBolid() + ".png");
                    vozac1Img.setImage(slika1);
                    vozac2Img.setImage(slika2);
                    bolidImg.setImage(slika3);
                }
                catch (Exception e)
                {
                    Image slika = new Image("/slike/noImage.png");
                    vozac1Img.setImage(slika);
                    vozac2Img.setImage(slika);
                    bolidImg.setImage(slika);
                }

                
                timOpis.setText(tim.getNazivTima()); 
                prviVozac.setText(aktivniVozaci.get(0).getIme() + " " + aktivniVozaci.get(0).getPrezime());
                drugiVozac.setText(aktivniVozaci.get(1).getIme() + " " + aktivniVozaci.get(1).getPrezime());
                bolid.setText(tim.bolid.getBolid());
                
                break;
            }    
        }
    }
    
    public void nazad(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPocetnaScena.fxml"));
        Scene scene = new Scene(root);     
        
        f1FXML.getStage().setScene(scene);        
        f1FXML.setStage(f1FXML.getStage());  
        
        scene.getStylesheets().add(f1FXML.class.getResource("F1.css").toExternalForm());
    
        System.out.println("Nazad na Početnu Scenu.");
    }

    public void scenaDodajSacuvajTim() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTimoviDodajSacuvaj.fxml"));

        Scene secondScene = new Scene(root);
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage - Scene");
        newWindow.setScene(secondScene);


        newWindow.setX(secondScene.getX() + 200);
        newWindow.setY(secondScene.getY() + 500);

        newWindow.show();
    }

    public void izbrisiTim()
    {
            if(f1FXML.getTimovi().get(timoviLV.getSelectionModel().getSelectedIndex()).getNazivTima().equals(timoviLV.getSelectionModel().getSelectedItem().toString()));
            {
                System.out.println(timoviLV.getSelectionModel().getSelectedItem().toString());
                f1FXML.getTimovi().remove(timoviLV.getSelectionModel().getSelectedIndex());
                f1FXML.getTimoviNazivOL().remove(timoviLV.getSelectionModel().getSelectedIndex());
            }


    }
}
