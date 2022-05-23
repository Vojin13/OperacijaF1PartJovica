package f1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tim.Sasija;
import tim.Tim;
import tim.Vozac;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class TimoviDodajSacuvajController implements Initializable {

    @FXML
    private TextField nazivTF;

    @FXML
    private TextField ime1TF;

    @FXML
    private TextField ime2TF;

    @FXML
    private TextField prezime1TF;

    @FXML
    private TextField prezime2TF;

    @FXML
    private TextField sasijaTF;

    @FXML
    private Button dodajBtn;

    private ObjectOutputStream oos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void dodajTim() throws IOException, FileNotFoundException, ClassNotFoundException {

        if (!nazivTF.getText().equals("") && !ime1TF.getText().equals("") && !prezime1TF.getText().equals("") &&
                !ime2TF.getText().equals("") && !prezime2TF.getText().equals("") && !sasijaTF.getText().equals("")) {
            Tim noviTim = new Tim(nazivTF.getText(), "1");
            Vozac v1 = new Vozac(ime1TF.getText(), prezime1TF.getText());
            Vozac v2 = new Vozac(ime2TF.getText(), prezime2TF.getText());
            tim.Tim.Konstruktor k = new tim.Tim.Konstruktor();
            Sasija s = new Sasija(sasijaTF.getText(), k);

            nazivTF.clear();
            ime1TF.clear();
            ime2TF.clear();
            prezime1TF.clear();
            prezime2TF.clear();
            sasijaTF.clear();

            f1FXML.getTimovi().add(noviTim);
            f1FXML.getTimoviNazivOL().add(noviTim.getNazivTima());
        }

    }
}