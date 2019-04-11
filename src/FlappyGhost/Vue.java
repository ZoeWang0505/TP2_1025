/**
 * Auteurs:     David Raby-Pepin, p0918119
 *              Zoe Wang, p20111352
 *
 * Date:        22/04/2019
 *
 * Description: Ce programme est un jeu en interface graphique avec la librairie JavaFX.
 *              Le joueur incarne un fantôme qui se promène dans un niveau rempli d’obstacles. Le seul objectif
 *              est d’avancer le plus longtemps possible dans le niveau sans toucher d’obstacle.
 */

package FlappyGhost;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Iterator;

public class Vue extends Application {
    private Controleur controleur;       // Contrôleur de l'application
    private int lrgFenetre = 640;        // largeur de la fenetre
    private int htrFenetre = 440;        // hauteur de la fenetre
    private int htrBarreTache = 40;      // hauteur de la barre de tache
    private GraphicsContext context;     // context graphique du canva
    private Boolean modeDebug = false;   // indique si le mode debug est active
    private String strPause = "Pause";   // affichage sur le bouton pause
    private String strResume = "Resume"; // affichage sur le bouton pause
    Text score;                          // score du joueur a afficher
    Canvas canvas;

    /**
     * Getter pour la largeur de la fenetre
     * @return largeur de la fenetre
     */
    public int getLrgFenetre() {
        return lrgFenetre;
    }

    /**
     * Getter pour la hauteur de la fenetre
     * @return hauteur de la fenetre
     */
    public int getHtrFenetre() {
        return htrFenetre;
    }

    /**
     * Getter pour la hauteur de la barre de tache
     * @return hauteur de la barre de tache
     */
    public int getHtrBarreTache() {
        return htrBarreTache;
    }

    /**
     * Getter pour l'etat du mode debug
     * @return etat du mode debug
     */
    public boolean getModeDebug(){
        return this.modeDebug;
    }

    /**
     * Fonction principale
     * @param args arguments entres en ligne de commande
     */
    public static void main(String[] args) {Vue.launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception{
        // creation de la vue initiale du jeu
        VBox root = new VBox(8);
        Scene scene = new Scene(root, lrgFenetre, htrFenetre);

        canvas = new Canvas(lrgFenetre, htrFenetre - htrBarreTache);

        HBox menuBoutons = new HBox(10);
        root.getChildren().add(canvas);
        root.getChildren().add(menuBoutons);
        Button pause = new Button(strPause);
        CheckBox modeDebug = new CheckBox("Mode debug");
        score = new Text("Score: 0");

        menuBoutons.getChildren().add(pause);
        menuBoutons.getChildren().add(modeDebug);
        menuBoutons.getChildren().add(score);
        menuBoutons.setAlignment(Pos.CENTER);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Flappy Ghost");
        primaryStage.getIcons().add(Resources.getGhost());
        primaryStage.show();

        context = canvas.getGraphicsContext2D();
        // Création du contrôleur
        controleur = new Controleur(this);
        controleur.start();
        canvas.requestFocus();


        // si l'utilisateur pese sur la barre d'espace, avertir le controleur
        scene.setOnKeyPressed((value) -> {
            switch (value.getCode()){
                case SPACE:
                    this.controleur.evenement(Jeu.actions.SAUTER);
                    break;
                case ESCAPE:
                    primaryStage.close();
                    break;
            }
            this.setFocus();
        });

        // si le jour clique sur le bouton pause, mettre le jeu sur pause et changer le texte du bouton a resume
        pause.setOnAction((event)-> {
            this.controleur.evenement(Jeu.actions.PAUSE);
            String str = pause.getText() == strPause ? strResume : strPause;
            pause.setText(str);
            this.setFocus();
        });

        // si le joueur clique sur la case du mode debug, activer le mode debug
        modeDebug.setOnAction((event)-> {
            this.modeDebug = modeDebug.isSelected();
            this.setFocus();
        });
    }

    private void setFocus(){
        // Après l’exécution de la fonction, le focus va automatiquement au canvas
        // Lorsqu’on clique ailleurs sur la scène, le focus retourne sur le canvas
        Platform.runLater(() -> {
            canvas.requestFocus();
        });
    }

    /**
     * Mets a jour la vue du jeu selon les changements apportes au modele
     * @param jeu modele
     */
    public void miseAJour(Jeu jeu) {
        // mettre a jour l'arriere-plan
        Background bg = jeu.getBackgroud();
        context.clearRect(0, 0, lrgFenetre, htrFenetre - htrBarreTache);
        Image image = bg.getImage();
        context.drawImage(image, bg.getCoordX(),0, image.getWidth(), image.getHeight() );
        context.drawImage(image, (bg.getCoordX() + lrgFenetre), 0,image.getWidth(), image.getHeight());

        // mettre a jour le score dujoueur
        score.setText("Score: " + jeu.getScore());

        // mettre a jour le fantome
        Fantome fantome = jeu.getFantome();
        if(!modeDebug) {
            context.drawImage(fantome.getImage(), fantome.getCoordX() - fantome.getRayon(),
                    fantome.getCoordY() - fantome.getRayon());
        } else {
            context.setFill(fantome.getCouleur());
            context.fillOval(fantome.getCoordX()- fantome.getRayon(),
                    fantome.getCoordY()- fantome.getRayon(),
                    fantome.getRayon() * 2, fantome.getRayon() * 2);
        }

        // mettre a jour les obstacles
        Iterator it = jeu.getListeObstacles().iterator();
        while(it.hasNext()){
            Obstacle obstacle = (Obstacle) it.next();
            if(!modeDebug) {
                context.drawImage(obstacle.getImage(),
                        obstacle.getCoordX() - obstacle.getRayon(),
                        obstacle.getCoordY() - obstacle.getRayon(),
                        obstacle.getRayon() * 2, obstacle.getRayon() * 2);
            } else {
                context.setFill(obstacle.getCouleur());
                context.fillOval(obstacle.getCoordX()- obstacle.getRayon(),
                        obstacle.getCoordY() - obstacle.getRayon(),
                        obstacle.getRayon() * 2, obstacle.getRayon() * 2);
            }
        }

    }
}

