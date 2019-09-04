package demineur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReglesController {

	public ReglesController() {
		
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void backMenu (ActionEvent event) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
		Stage regles = (Stage)((Node)event.getSource()).getScene().getWindow();;
		regles.setScene(new Scene(root));
		regles.show();
	}
	
	
}
