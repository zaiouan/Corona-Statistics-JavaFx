/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationfxcovid;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Zaiouane
 */
public class StatController implements Initializable{
    @FXML
    private ComboBox<?> comboStat;
   @FXML
    private LineChart<?, ?> chart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    @FXML
    private LineChart<?, ?> chart1;
    @FXML
    private NumberAxis y1;
    @FXML
    private CategoryAxis x1;
    @FXML
    private LineChart<?, ?> chart2;
    @FXML
    private NumberAxis y2;
    @FXML
    private CategoryAxis x2;
    @FXML
    private LineChart<?, ?> char3;
    @FXML
    private NumberAxis y3;
    @FXML
    private CategoryAxis x3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLDocumentController.initCombo(comboStat);
    }
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void afficheChart(MouseEvent event) {
        chart.getData().clear();
        chart1.getData().clear();
        chart2.getData().clear();
        char3.getData().clear();
        String pays = (String) comboStat.getValue();
        XYChart.Series series = new XYChart.Series();
        List l = Covid.getTotalCasPerMounth(pays);
        for (int i = 0; i < l.size(); i++) {
            Object [] o = (Object[]) l.get(i);
            Double n = Double.parseDouble((String) o[0]);
            String t = Integer.toString((int) o[1]);
            String a = Integer.toString((int) o[2]);
            series.getData().add(new XYChart.Data<>(t+"/"+a,n));
        }
        series.setName("Cas");
        XYChart.Series series1 = new XYChart.Series();
        List l1 = Covid.getTotalDecesPerMounth(pays);
        for (int i = 0; i < l1.size(); i++) {
            Object [] o = (Object[]) l1.get(i);
            Double n = Double.parseDouble((String) o[0]);
            String t = Integer.toString((int) o[1]);
            String a = Integer.toString((int) o[2]);
            series1.getData().add(new XYChart.Data<>(t+"/"+a,n));
        }
        series1.setName("Deces");
        XYChart.Series series2 = new XYChart.Series();
        List l2 = Covid.getTotalTestPerMounth(pays);
        for (int i = 0; i < l2.size(); i++) {
            Object [] o = (Object[]) l2.get(i);
            Double n = Double.parseDouble((String) o[0]);
            String t = Integer.toString((int) o[1]);
            String a = Integer.toString((int) o[2]);
            series2.getData().add(new XYChart.Data<>(t+"/"+a,n));
        }
        series2.setName("Test");
        XYChart.Series series3 = new XYChart.Series();
        List l3 = Covid.getTotalVacPerMounth(pays);
        for (int i = 0; i < l2.size(); i++) {
            Object [] o = (Object[]) l3.get(i);
            Double n = Double.parseDouble((String) o[0]);
            String t = Integer.toString((int) o[1]);
            String a = Integer.toString((int) o[2]);
            series3.getData().add(new XYChart.Data<>(t+"/"+a,n));
        }
        series3.setName("Vaccination");
        chart.getData().add(series);
        chart1.getData().add(series1);
        chart2.getData().add(series2);
        char3.getData().add(series3);
    }
    
}
