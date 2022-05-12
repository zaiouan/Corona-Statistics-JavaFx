/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationfxcovid;

import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Zaiouane
 */
public class PaysController implements Initializable {
     @FXML
    private ComboBox<String> ComboPays2;

    @FXML
    private DatePicker date1;
    @FXML
    private Label labelcas;
    @FXML
    private Label labeldeces;
    @FXML
    private Label lblncas;
    @FXML
    private Label lblndeces;
    @FXML
    private Label labelvac;
    @FXML
    private Label lblnvac;
    @FXML
    private Label labeltest;
    @FXML
    private Label lblntest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLDocumentController.initCombo(ComboPays2);
    }
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void confirmer(MouseEvent event) {
        String pays = (String) ComboPays2.getValue();
        LocalDate localDate = date1.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List <Covid> l = Covid.rechercher(pays,date);
        l.forEach((c) -> {
            if(c.getTotalCas().isEmpty()){
               labelcas.setText("-"); 
            }else{
            labelcas.setText(c.getTotalCas());
            }
            if(c.getTotalVac().isEmpty()){
               labelvac.setText("-"); 
            }else{
            labelvac.setText(c.getTotalVac());
            }
            if(c.getTotalDeces().isEmpty()){
               labeldeces.setText("-"); 
            }else{
            labeldeces.setText(c.getTotalDeces());
            }
            if(c.getTotalTest().isEmpty()){
               labeltest.setText("-"); 
            }else{
            labeltest.setText(c.getTotalTest());
            }
            if(c.getNewCas().isEmpty()){
               lblncas.setText("-"); 
            }else{
            lblncas.setText("+"+c.getNewCas());
            }       
            if(c.getNewDeces().isEmpty()){
               lblndeces.setText("-"); 
            }else{
            lblndeces.setText("+"+c.getNewDeces());
            }
            if(c.getNewTest().isEmpty()){
               lblntest.setText("-"); 
            }else{
            lblntest.setText("+"+c.getNewTest());
            }
            if(c.getNewVac().isEmpty()){
               lblnvac.setText("-"); 
            }else{
            lblnvac.setText("+"+c.getNewVac());
            }
            
        });
        
    }
    
}
