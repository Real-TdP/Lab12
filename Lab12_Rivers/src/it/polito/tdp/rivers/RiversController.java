package it.polito.tdp.rivers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Analisi;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Statistiche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleSimula(ActionEvent event) {
    	txtResult.clear();
    	if(!(boxRiver.getValue() instanceof River)) {
    		txtResult.appendText("Scegliere un fiume\n");
    		return;
    	}
    	River river=boxRiver.getValue();
    	float k;
		try {
			k = Float.parseFloat(txtK.getText());
		} catch (NumberFormatException e) {
			txtResult.appendText("Inserire un numero valido compreso fra 0 ed 1\n");
			e.printStackTrace();
			return;
		}
		if(k>1||k<0) {
			txtResult.appendText("Inserire un numero fra 0 ed 1\n");
			return;
		}
    	Statistiche sta=model.simula(river, k);
    	txtResult.appendText("Giorni senza erogazione minima: "+sta.getNoWat()+"\nOccupazione media bacino: "+sta.getCapMedia());

    }
    
    @FXML
    void handleAnalyze(ActionEvent event) {
    	txtResult.clear();
    	txtStartDate.clear();
    	txtEndDate.clear();
    	txtNumMeasurements.clear();
    	txtFMed.clear();
    	if(!(boxRiver.getValue() instanceof River)) {
    		txtResult.appendText("Scegliere un fiume\n");
    		return;
    	}
    	Analisi a=model.analyze(boxRiver.getValue());
    	if(a!=null) {
    		txtStartDate.setText(a.getDataS().toString());
    		txtEndDate.setText(a.getDataE().toString());
    		txtNumMeasurements.appendText(String.valueOf(a.getnMisure()));
    		txtFMed.appendText(String.valueOf(a.getfMedia()));
    	}
    	else
    		txtResult.appendText("Operazione non riuscita\n");
    }


    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    	List<River> river=this.model.getRiver();
    	if(river==null) {
    		txtResult.appendText("Errore nell'inizializzazione, controllare stato DB\n");
    		return;
    	}
    	boxRiver.getItems().addAll(river);
    }
}
