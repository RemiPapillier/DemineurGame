package demineur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GameOverController {
	
	public GameOverController() {
	}
	
	@FXML
	public void initialize(ActionEvent event) throws Exception{
	}

	
	/*
	 * @FXML pour interagir avec Scenebuilder, on associera cette fonction au bouton rejouer
	 * On charge la page d'accueil et on l'affiche dans une scene sur le stage initialise dans Controller
	 */
	@FXML
	private void Menu(ActionEvent event) throws Exception{

		Parent root = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
		Controller.stage.setScene(new Scene(root));
		Controller.stage.show();
	}
	
	/*
	 * En fonction de la valeur du flag (la variable initialise dans controller) il vaut 1 si on avait lance facile etc
	 * cela permet au programme de se souvenir quelle etait la derniere fenetre ouverte avant le game over
	 * On effectue ensuite le meme programme que StartFacile, StartMoyen etc
	 */
	@FXML
	private void Rejouer(ActionEvent event) throws Exception{
		
		if (Controller.flag == 1) {
			Controller.root = FXMLLoader.load(Controller.class.getResource("NiveauFacile.fxml"));
			Stage facile = (Stage)((Node)event.getSource()).getScene().getWindow();
			Controller.stage = facile;
			Pane jeu = new Pane(MainApps.createContent(1)); 
			Controller.root.getChildren().add(jeu);
			jeu.setLayoutX(100);
			jeu.setLayoutY(125);
			facile.setScene(new Scene(Controller.root)); 
			facile.show();
		}
		else if(Controller.flag == 2) {
			Controller.root = FXMLLoader.load(Controller.class.getResource("NiveauMoyen.fxml"));
			Stage moyen = (Stage)((Node)event.getSource()).getScene().getWindow();
			Controller.stage = moyen;
			Pane jeu = new Pane(MainApps.createContent(2)); 
			Controller.root.getChildren().add(jeu);
			jeu.setLayoutX(100);
			jeu.setLayoutY(125);
			moyen.setScene(new Scene(Controller.root)); 
			moyen.show();
		}
		else if (Controller.flag == 3) {
			Controller.root = FXMLLoader.load(Controller.class.getResource("NiveauDifficile.fxml"));
			Stage difficile = (Stage)((Node)event.getSource()).getScene().getWindow();
			Controller.stage = difficile;
			Pane jeu = new Pane(MainApps.createContent(3)); 
			Controller.root.getChildren().add(jeu);
			jeu.setLayoutX(100);
			jeu.setLayoutY(125);
			difficile.setScene(new Scene(Controller.root)); 
			difficile.show();
		}
	}
}
