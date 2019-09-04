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

public class FacileController {

	public FacileController() {
	}
	
	@FXML
	private void initialize() {
	}
	
	
	/*
	 * @FXML pour faire un lien avec SceneBuilder, on lancera cette fonction en cliquant sur le bouton menu d'une page de niveau
	 * fonction qui prend un event en parametre
	 * On charge le fichier fxml pageaccueil pour revenir au menu
	 * On récupère le stage actuel
	 * Puis on affiche le stage comprenant la scene avec le fichier fxml
	 */
	@FXML
	private void backMenu (ActionEvent event) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
		Stage menu = (Stage)((Node)event.getSource()).getScene().getWindow();
		menu.setScene(new Scene(root));
		menu.show();
	}	
	
	
	/*
	 * Fonction qui reset le jeu, associé au bouton reset
	 * C'est la meme fonction que dans Controller, start"difficulte"
	 */
	@FXML
	private void reset(ActionEvent event) throws Exception {
		
		AnchorPane root = FXMLLoader.load(getClass().getResource("NiveauFacile.fxml"));
		Stage facile = (Stage)((Node)event.getSource()).getScene().getWindow();
		Pane jeu = new Pane(MainApps.createContent(1));
		root.getChildren().add(jeu);
		jeu.setLayoutX(100);
		jeu.setLayoutY(125);
		facile.setScene(new Scene(root)); 
		facile.show();
	}
}
