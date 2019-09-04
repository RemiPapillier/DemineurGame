package demineur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MoyenController {
	
	/*
	 * cf FacileController pour les explications des fonctions
	 */
	
	public MoyenController() {
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void backMenu (ActionEvent event) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
		Stage menu = (Stage)((Node)event.getSource()).getScene().getWindow();;
		menu.setScene(new Scene(root));
		menu.show();
	}
	
	@FXML
	private void reset(ActionEvent event) throws Exception {	
		
		AnchorPane root = FXMLLoader.load(getClass().getResource("NiveauMoyen.fxml"));
		Stage facile = (Stage)((Node)event.getSource()).getScene().getWindow();
		Pane jeu = new Pane(MainApps.createContent(2));
		root.getChildren().add(jeu);
		jeu.setLayoutX(100);
		jeu.setLayoutY(125);
		facile.setScene(new Scene(root)); 
		facile.show();
	}
	
}
