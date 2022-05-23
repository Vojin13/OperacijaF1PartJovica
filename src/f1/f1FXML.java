/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f1;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sezona.Staza;
import tim.Tim;

/**
 *
 * @author CJ
 */
public class f1FXML extends Application {
    
    private static Stage newStage;     // static field
    
    private static ArrayList<Staza> staze = new ArrayList<Staza>();
    private static ObservableList stazeNazivOL;
    
    private static ArrayList<Tim> timovi = new ArrayList<Tim>();
    private static ObservableList timoviNazivOL;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPocetnaScena.fxml"));
        stage.setTitle("F1 - JavaFXML - Осма гимназија");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        f1FXML.newStage = stage;
        System.out.println("Stage F1 postavljen.");
        
        scene.getStylesheets().add(f1FXML.class.getResource("F1.css").toExternalForm());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void setStage(Stage newStage) { // set metod
        f1FXML.newStage = newStage;
        f1FXML.newStage.show();
    }
    
    public static Stage getStage() {             // get metod
        return f1FXML.newStage;
    }
    
    // Staze    
    public static ArrayList<Staza> getStaze(){
        return staze;
    }
    
    public static void setStaze(ArrayList<Staza> lista){
        staze = lista;
    }
    
    public static void setStazeNazivOL(ArrayList<String> nazivi) {
         if(stazeNazivOL == null){
             stazeNazivOL = FXCollections.observableList(nazivi);
         }
         else {
            stazeNazivOL.add(nazivi);
            stazeNazivOL = FXCollections.observableList(nazivi);
         }
    }
     
    public static ObservableList getStazeNazivOL() {
        return stazeNazivOL;
    }
    
     public static void dodajPojedinacno(String naziv) {
        stazeNazivOL.add(naziv);
    }
     
     public static void prikaziStazeAL() {
       staze.forEach((s) -> System.out.println(s));
    } 
     
    // Timovi
     
    public static ArrayList<Tim> getTimovi(){
        return timovi;
    }
    
    public static void setTimovi(ArrayList<Tim> lista){
        timovi = lista;
    }
     
    public static void setTimoviNazivOL(ArrayList<String> nazivi) {
         if(timoviNazivOL == null){
             timoviNazivOL = FXCollections.observableList(nazivi);
         }
         else {
            timoviNazivOL.add(nazivi);
            timoviNazivOL = FXCollections.observableList(nazivi);
         }
    }
     
    public static ObservableList getTimoviNazivOL() {
        return timoviNazivOL;
    }
    
}