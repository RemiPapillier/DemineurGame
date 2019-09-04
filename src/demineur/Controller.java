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

public class Controller {
	
	//On definie un nouveau stage et AnchorPane public static
	public static Stage stage = new Stage();
	public static AnchorPane root = new AnchorPane();
	public static int flag = 0;
	
	
	public Controller() {
	}	
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void startRegles(ActionEvent event) throws Exception{

		Parent root = FXMLLoader.load(getClass().getResource("ReglesJeu.fxml"));
		Stage regles = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage = regles;
		regles.setScene(new Scene(root));
		regles.show();
	}

	
	/*
	 * Toutes ces fonctions start sont sensiblement identiques
	 * le @FXML au debut signifie qu'on va retrouver ces fonctions dans SceneBuilder pour pouvoir les associer a un bouton notamment
	 * On crée une fonction qui prend en parametre un objet event de type ActionEvent 
	 * cet event va etre identifie lors d'un click sur un bouton
	 * On crée un nouvelle objet root de type AnchorPane qui charge la page fxml voulue
	 * On crée notre Stage qui va prendre le meme sage que celui sur lequel le bouton est activé, cad on reste sur la meme fenetre
	 * (en effet, on getSource de event donc du bouton et on recupere la scene et window de ce bouton)
	 * On affecte ce Stage à notre variable stage initialise au debut
	 * On crée ensuite un nouveau objet jeu de type Pane qui va etre initialise a CreateContent,
	 * c'est la fonction que nous avons crée dans le MainApps pour créer la grille de jeu
	 * On ajoute le Pane a notre AnchorPane <-> on ajoute le jeu a la page fxml chargé
	 * On place les coordonnées du jeu
	 * On crée une scene qui devient l'objet root (donc la page fxml à laquelle on a ajouté le jeu) et on l'ajoute au stage
	 * On affiche le stage
	 */
	@FXML
	private void startFacile(ActionEvent event) throws Exception{
		
		root = FXMLLoader.load(Controller.class.getResource("NiveauFacile.fxml"));
		Stage facile = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage = facile;
		Pane jeu = new Pane(MainApps.createContent(1)); 
		root.getChildren().add(jeu);
		jeu.setLayoutX(100);
		jeu.setLayoutY(125);
		facile.setScene(new Scene(root)); 
		facile.show();
		flag = 1;
	}
	
	@FXML
	private void startMoyen(ActionEvent event) throws Exception{

		root = FXMLLoader.load(getClass().getResource("NiveauMoyen.fxml"));
		Stage moyen = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage = moyen;
		Pane jeu = new Pane(MainApps.createContent(2));
		root.getChildren().add(jeu);
		jeu.setLayoutX(100);
		jeu.setLayoutY(125);
		moyen.setScene(new Scene(root));
		moyen.show();
		flag = 2;
	}
	
	@FXML
	private void startDifficile(ActionEvent event) throws Exception{

		root = FXMLLoader.load(getClass().getResource("NiveauDifficile.fxml"));
		Stage hard = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage = hard;
		Pane jeu = new Pane(MainApps.createContent(3));
		root.getChildren().add(jeu);
		jeu.setLayoutX(100);
		jeu.setLayoutY(125);
		hard.setScene(new Scene(root));
		hard.show();
		flag = 3;
	}

}
