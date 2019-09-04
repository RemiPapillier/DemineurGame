package demineur;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Classe principale qui hérite de la classe Application
public class MainApps extends Application { 
	
	
	/*
	 * Initialisation de variables static final
	 */
	
	private static final int W = 450;
	private static final int H = 450;
	
	private static final int TILE1 = 10;
	private static final int TILE2 = 20;
	private static final int TILE3 = 30;
	
	private static final int TILE_S1 = W/TILE1;
	private static final int TILE_S2 = W/TILE2;
	private static final int TILE_S3= W/TILE3;
	
	private static Tile[][] grid1 = new Tile[TILE1][TILE1];
	private static Tile[][] grid2 = new Tile[TILE2][TILE2];
	private static Tile[][] grid3 = new Tile[TILE3][TILE3];
	
	
	/*
	 * Lancement du programme
	 */
	public static void main(String[] args) {		
		launch(args);
	}
	
	
	/*
	 * Affiche la page d'accueil, s'éxecute grâce à launch(args) du main
	 * C'est une particularite de la classe Application, elle démarre grace a la fonction start et cest le
	 * @Override qui nous indique qu'on va utiliser cette fonction de la classe hérité (Application)
	 * On place dans un objet parent le fichier fxml 
	 * On indique a notre stage son titre puis on y crée une nouvelle scene qui sera constitue de notre fichier fxml charge
	 * On affiche ensuite le stage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("PageAccueil.fxml"));
		primaryStage.setTitle("Demineur");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	
	/*
	 * On crée une nouvelle méthode qui renvoie un objet Parent et qui crée la grille de jeu en prenant la difficulte en parametre
	 * (1=facile 2=moyen 3=difficile)
	 * On crée un nouveau Pane et initialise sa taille avec les valeurs static final
	 * En fonction de la difficulte du paramètre, on réalise les étapes suivantes:
	 * Premiere boucle
	 * On parcourt tout le tableau grid (initialise au debut) 
	 * On crée une Tile (case) qui aura une bombe ou non aléatoirement qu'on ajoute ensuite dans le tableau pour former la grille 
	 * Deuxieme boucle
	 * On reparcourt tout le tableau (désormais une grille composé de case avec des bombes ou non)
	 * Si on a une bombe on continue le programme
	 * Sinon on calcul un type long bombs qui va appliquer la fonction getNeighbors ecrite plus bas
	 * La ligne de calcul de bombs observe tous les voisins de la case et compte combien il y a de bombes
	 * Si il y a au moins un bombe (>0) on écrit dans cette case le nombre de bombes
	 * La fonction renvoie finalement un objet Parent, qui contient la grille de jeu
	 */
	static Parent createContent(int difficulte) {
        Pane root = new Pane();
        root.setPrefSize(W, H);
        
        if (difficulte == 1) {
        	for (int y = 0; y < TILE1; y++) {
                for (int x = 0; x < TILE1; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.15, difficulte);
                    grid1[x][y] = tile;
                    root.getChildren().add(tile);
                }
            }

            for (int y = 0; y < TILE1; y++) {
                for (int x = 0; x < TILE1; x++) {
                    Tile tile = grid1[x][y];

                    if (tile.hasBomb)
                        continue;

                    long bombs = getNeighbors(tile, difficulte).stream().filter(t -> t.hasBomb).count();

                    if (bombs > 0)
                        tile.text.setText(String.valueOf(bombs));
                }
            }
        
        }
        else if (difficulte == 2) {
        	for (int y = 0; y < TILE2; y++) {
                for (int x = 0; x < TILE2; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.15, difficulte);
                    grid2[x][y] = tile;
                    root.getChildren().add(tile);
                }
            }

            for (int y = 0; y < TILE2; y++) {
                for (int x = 0; x < TILE2; x++) {
                    Tile tile = grid2[x][y];

                    if (tile.hasBomb)
                        continue;

                    long bombs = getNeighbors(tile, difficulte).stream().filter(t -> t.hasBomb).count();

                    if (bombs > 0)
                        tile.text.setText(String.valueOf(bombs));
                }
            }
        
        }
        else if (difficulte == 3){
        	for (int y = 0; y < TILE3; y++) {
                for (int x = 0; x < TILE3; x++) {
                    Tile tile = new Tile(x, y, Math.random() < 0.15, difficulte);

                    grid3[x][y] = tile;
                    root.getChildren().add(tile);
                }
            }

            for (int y = 0; y < TILE3; y++) {
                for (int x = 0; x < TILE3; x++) {
                    Tile tile = grid3[x][y];

                    if (tile.hasBomb)
                        continue;

                    long bombs = getNeighbors(tile, difficulte).stream().filter(t -> t.hasBomb).count();

                    if (bombs > 0)
                        tile.text.setText(String.valueOf(bombs));
                }
            }
        
        }
        return root;
    }
	

	/*
	 * Fonction qui renvoie une liste d'objet Tile (classe définie plus bas) et prend en parametre une tile et la difficulte
	 * On crée une nouvelle liste d'objet Tile
	 * On crée un tableau points comprenant des int et correspondant aux coordonnées pour avoir les voisins à partir d'une case donnée
	 * On crée une boucle for qui grace a dx et dy va prendre une coordonnée relative (ex: -1 ; -1)
	 * Puis on crée des nouvelles coordonnés newX newY en ajoutant les coordonnées relatives aux coordonnées de la case en question (tile.x et tile.y)
	 * Pour chaque difficulte on regarde si les cases voisines (cad coordonnés relatives ajoutés aux coordonnées de base) ne sont pas hors de la grille
	 * Si ils ne sortent pas de la grille, on ajoute à notre liste la case (type TILE) de coordonnées newX et newY dans la grille
	 * On retourne ensutie la liste créée
	 */
	private static List<Tile> getNeighbors(Tile tile, int difficulte) {
        List<Tile> neighbors = new ArrayList<>();
        
        int[] points = new int[] {
              -1, -1,
              -1, 0,
              -1, 1,
              0, -1,
              0, 1,
              1, -1,
              1, 0,
              1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;
            
            if (difficulte == 1) {
                if (newX >= 0 && newX < TILE1 && newY >= 0 && newY < TILE1) {
                    neighbors.add(grid1[newX][newY]);
                }
            }
            else if (difficulte == 2) {
                if (newX >= 0 && newX < TILE2 && newY >= 0 && newY < TILE2) {
                    neighbors.add(grid2[newX][newY]);
                }
            }
            else if (difficulte == 3){
                if (newX >= 0 && newX < TILE3 && newY >= 0 && newY < TILE3) {
                    neighbors.add(grid3[newX][newY]);
                }
            }
        }
        return neighbors;
    }

	
	/*
	 * On crée une nouvelle classe Tile qui hérite de StackPane, cette classe va représenter une case
	 * On initialise ses coordonnées propres x et y, le boolean hasBomb qui dit si il y a ou non une bombe dans la case 
	 * et le boolean isOpen qui dit si la case a deja ete ouverte ou non
	 * On crée les bordures des cases en fonction de la difficulté en utilisant la classe Rectangle (qui existe dans Java)
	 * On initialise également un nouveau Text
	 */
	public static class Tile extends StackPane {

		int x, y;
		boolean hasBomb;
		boolean isOpen= false;
		
		private Rectangle border1 = new Rectangle(TILE_S1 - 2, TILE_S1 -2);
		private Rectangle border2 = new Rectangle(TILE_S2 - 2, TILE_S2 -2);
		private Rectangle border3 = new Rectangle(TILE_S3 - 1, TILE_S3 -1);
		Text text = new Text();		
		
		
		/*
		 * Constructeur (Un constructeur prend le même nom que la classe)
		 * Lorsque l'on va créer un objet Tile, l'objet sera initialisé par la fonction suivante
		 * Le constructeur prend en parametre des coordonnes x, y le boolean hasBomb et la difficulte
		 * Le mot clé this, signifie qu'on se réfère à l'objet de la classe en cours, soit Tile
		 * Nous avions défini des variables dans cette classe 
		 * Donc la notation this.x = x signifie que le x défini plus tot dans l'objet, va prendre la valeur du parametre x
		 * Ainsi de suite avec les variables y et hasBomb
		 * On définie ensuite une police grasse et de taille 18
		 * setText(hasBomb ? "X" : "") signifie que si hasBomb=true on ecrit un X sinon hasBomb=false on ecrit rien
		 * setVisible(false) pour que la case reste caché tant qu'on ne clique pas dessus
		 * Ensuite, en fonction du niveau de difficulte indique dans le parametre:
		 * On donne une couleur à la bordure créée plus haut et on ajoute la bordure et le text à la case
		 * setOnMouseClicked signifie que lorsque qu'on effectue un click avec la souris, on effectue ce qu'il y a en parenthese
		 * (e -> open1()) donc quand il y a un click on va lancer la fonction open créée plus bas
		 * le try/catch correspond à une exception c'est a dire que le programme va essayer le try
		 * mais si il n'arrive pas a faire  ce qu'il y a dedans (lancer open() dans notre cas),
		 * le programme va aller dans le catch et donc lancer l'exception 
		 */
		public Tile(int x, int y, boolean hasBomb, int difficulte) {
			
			this.x = x;
			this.y = y;
			this.hasBomb = hasBomb;
			
			text.setFont(Font.font("",FontWeight.BOLD,18));
			text.setText(hasBomb ? "X" : "");
			text.setVisible(false);
			
			if(difficulte == 1) {
				border1.setStroke(Color.GREEN);
				getChildren().addAll(border1, text);
				setTranslateX(x * TILE_S1);
				setTranslateY(y * TILE_S1);
				setOnMouseClicked(e -> {
					try {
						open1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			}
			else if(difficulte == 2) {
				border2.setStroke(Color.ORANGE);
				getChildren().addAll(border2, text);
				setTranslateX(x * TILE_S2);
				setTranslateY(y * TILE_S2);
				setOnMouseClicked(e -> {
					try {
						open2();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			}
			else if (difficulte == 3){
				border3.setStroke(Color.RED);
				getChildren().addAll(border3, text);
				setTranslateX(x * TILE_S3);
				setTranslateY(y * TILE_S3);
				setOnMouseClicked(e -> {
					try {
						open3();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			}
				
		}
		
		
		/*
		 * Cette fonction charge le fichier fxml gameover dans un objet root de type Parent
		 * On crée la scene avec notre fichier fxml puis on l'ajoute a notre stage définie dans la classe Controller et on l'affiche
		 */
		
		static void throwLose() throws Exception {
			
			Parent root = FXMLLoader.load(FacileController.class.getResource("GameOver.fxml"));
			Controller.stage.setScene(new Scene(root));
			Controller.stage.show();
		}
		
		
		/*
		 * Cette fonction est semblable à throwLose() sauf qu'on charge le fichier fxml YouWin
		 */
		static void throwWin() throws Exception {
			
			Parent root = FXMLLoader.load(FacileController.class.getResource("YouWin.fxml"));
			Controller.stage.setScene(new Scene(root));
			Controller.stage.show();
		}
		
		/*
		 * Toutes les fonctions ci-dessous sont ecrites 3 fois, sensiblement de la meme facon en fonction de la difficulte
		 */

		
		/*
		 * Fonction open qui ne prend pas de paramètre (On rappelle que cette fonction s'execute lorsqu'on clique sur une case)
		 * if(isOpen) <=> if(isOpen == true) donc si la case est ouverte, on return (on sort de la fonction)
		 * si il y a une bombe, on envoie appelle la fonction throwLose de la classe "difficulte"controller
		 * On affiche le texte de la case et on enleve ce qui recouvre la case (setFill(null)) puis on sort de la fonction
		 * Si la case n'est pas ouverte et ne contient pas de bombe (cad on est rentre dans aucune des conditions)
		 * On indique que la case est maintenant ouverte, on affiche le text et on enleve le noir qui recouvre la case
		 * Ensuite on test la fonction TestWin() ecrite plus bas qui va vérifier si la condition de victoire est rempli
		 * Pour afficher tous les voisins possibles on crée une condition, si le texte est vide:
		 * on appelle la fonction getneigbors réalisé plus tot, avec comme parametre this (qui refere la case actuelle) et la difficulte
		 * On rappelle que cette fonction renvoie une liste de case et on y applique forEach(t -> t.open)
		 * cela signifie que pour chaque case, on va relancer la fonction open (celle dans laquelle nous sommes actuellement)
		 * On ajoute a cela la gestion des exception (try/catch)
		 */
		public void open1() throws Exception {
			
		
			if (isOpen)
				return;
			
			if(hasBomb) {
				throwLose();
				text.setVisible(true);
				border1.setFill(null);
				return;
			}
			
			isOpen = true;
			text.setVisible(true);
			border1.setFill(null);
			TestWin1();
			if (text.getText().isEmpty()) {
				getNeighbors(this, 1).forEach(t -> {
					try {
						t.open1();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			}
		}
		
		public void open2() throws Exception {
			if (isOpen)
				return;
			
			if(hasBomb) {
				throwLose();
				text.setVisible(true);
				border2.setFill(null);
				return;
			}
			
			isOpen = true;
			text.setVisible(true);
			border2.setFill(null);
			TestWin2();
			if (text.getText().isEmpty()) {
				getNeighbors(this, 2).forEach(t -> {
					try {
						t.open2();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			}
		}
		
		public void open3() throws Exception {
			if (isOpen)
				return;
			
			if(hasBomb) {
				throwLose();
				text.setVisible(true);
				border3.setFill(null);
				return;
			}
			
			isOpen = true;
			text.setVisible(true);
			border3.setFill(null);
			TestWin3();
			if (text.getText().isEmpty()) {
				getNeighbors(this, 3).forEach(t -> {
					try {
						t.open3();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			}
		}
		
		
		/*
		 * Fonction qui renvoie un entier (int) donnant le nombre total de mine dans la grille
		 * On parcourt toutes les cases de la grille et si une case possède la variable hasBomb sur true,
		 * c'est qu'il y a une bombe donc on incrémente notre compteur total de 1 (total++)
		 * On renvoie la valeur de total
		 */
		public int totalMines1() {
			int total = 0;
			for (int i = 0; i < TILE1; i++) {
				for (int j = 0; j < TILE1; j++) {
					if (grid1[i][j].hasBomb == true) {
						total++;
					}
				}
			}
			return total;
		}
		
		
		/*
		 * Fonction qui renvoie un entier donnant le nombre de case ouverte
		 * On parcourt toutes les cases de la grille et quand une case possede la variable isOpen sur true,
		 * c'est que la case est ouverte donc on incremente le compteur de 1
		 * On renvoie la valeur du compteur total
		 */
		public int totalOpened1() {
			int total = 0;
			for (int i = 0; i < TILE1; i++) {
				for (int j = 0; j < TILE1; j++) {
					if (grid1[i][j].isOpen == true) {
						total++;
					}
				}
			}
			return total;
		}
		
		
		/*
		 * Cette fonction permet de tester la victoire
		 * La partie est gagne lorsque le nombre de case ouverte + le nombre total de mine dans la grille
		 * vaut la totalité des cases dans la grille
		 * Donc quand cette condition est respecté, on peut appeler la fonction throwWin de "difficulte"Controller
		 */
		public void TestWin1() throws Exception {
			if (totalOpened1() + totalMines1() == TILE1 * TILE1) {
				throwWin();
			}
		}
		
		public int totalMines2() {
			int total = 0;
			for (int i = 0; i < TILE1; i++) {
				for (int j = 0; j < TILE2; j++) {
					if (grid2[i][j].hasBomb == true) {
						total++;
					}
				}
			}
			return total;
		}
		
		public int totalOpened2() {
			int total = 0;
			for (int i = 0; i < TILE2; i++) {
				for (int j = 0; j < TILE2; j++) {
					if (grid2[i][j].isOpen == true) {
						total++;
					}
				}
			}
			return total;
		}
		
		public void TestWin2() throws Exception {
			if (totalOpened2() + totalMines2() == TILE2 * TILE2) {
				throwWin();
			}
		}
		
		public int totalMines3() {
			int total = 0;
			for (int i = 0; i < TILE3; i++) {
				for (int j = 0; j < TILE3; j++) {
					if (grid3[i][j].hasBomb == true) {
						total++;
					}
				}
			}
			return total;
		}
		
		public int totalOpened3() {
			int total = 0;
			for (int i = 0; i < TILE3; i++) {
				for (int j = 0; j < TILE3; j++) {
					if (grid3[i][j].isOpen == true) {
						total++;
					}
				}
			}
			return total;
		}
		
		public void TestWin3() throws Exception {
			if (totalOpened3() + totalMines3() == TILE3 * TILE3) {
				throwWin();
			}
		}
		
	}

}