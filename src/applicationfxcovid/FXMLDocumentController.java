/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationfxcovid;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author Zaiouane
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Covid> table;
    @FXML
    private TableColumn<?, ?> pays;
    @FXML
    private TableColumn<?, ?> cas;
    @FXML
    private TableColumn<?, ?> Vac;
    @FXML
    private TableColumn<?, ?> deces;
    @FXML
    private ComboBox<?> cmb;
    public ObservableList<Covid> Ob = FXCollections.observableArrayList();
    @FXML
    private Pane pane;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCombo(cmb);
        initTableu();
    } 
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void recherche(MouseEvent event) {
        table.getItems().clear();
        String Pays = (String) cmb.getValue();
        List<Covid> lp = Covid.rechercher(Pays);
        lp.forEach((c) -> {
            Ob.add(new Covid(c.getTotalCas(),c.getTotalDeces(),c.getTotalVac(),c.getPays()));
        });
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        cas.setCellValueFactory(new PropertyValueFactory<>("totalCas"));
        Vac.setCellValueFactory(new PropertyValueFactory<>("totalVac"));
        deces.setCellValueFactory(new PropertyValueFactory<>("totalDeces"));
        table.setItems(Ob);
    }

   public static void initCombo(ComboBox c){
       List Ob1 = Covid.AfficherPays();
       c.getItems().addAll(Ob1);
   }
   
   public void initTableu(){
        List<Covid> lp = Covid.Afficher();
        lp.forEach((c) -> {
            Ob.add(new Covid(c.getTotalCas(),c.getTotalDeces(),c.getTotalVac(),c.getPays()));
        });
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        cas.setCellValueFactory(new PropertyValueFactory<>("totalCas"));
        Vac.setCellValueFactory(new PropertyValueFactory<>("totalVac"));
        deces.setCellValueFactory(new PropertyValueFactory<>("totalDeces"));
        table.setItems(Ob);
   }

    @FXML
    private void pAcc(MouseEvent event) throws IOException {
       Node nd2 = FXMLLoader.load(getClass().getResource("Acc.fxml"));
         pane.getChildren().clear();
         pane.getChildren().add(nd2);
    }

    @FXML
    private void pPays(MouseEvent event) throws IOException {
         Node nd = FXMLLoader.load(getClass().getResource("Pays.fxml"));
         pane.getChildren().clear();
         pane.getChildren().add(nd);
    }

    @FXML
    private void pStat(MouseEvent event) throws IOException {
        Node nd1 = FXMLLoader.load(getClass().getResource("Stat.fxml"));
         pane.getChildren().clear();
         pane.getChildren().add(nd1);
    }
    
}
