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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Iterator;

public class Vue extends Application {
    private Controleur controleur;  // Contrôleur de l'application
    private int lrgFenetre = 640;
    private int htrFenetre = 440;
    private int htrBarreTache = 40;
    private GraphicsContext context;
    private Boolean modeDebug = false;

    Text score;


    public int getLrgFenetre() {
        return lrgFenetre;
    }

    public int getHtrFenetre() {
        return htrFenetre;
    }

    public int getHtrBarreTache() {
        return htrBarreTache;
    }

    public boolean getModeDebug(){
        return this.modeDebug;
    }


    public static void main(String[] args) {Vue.launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox(8);
        Scene scene = new Scene(root, lrgFenetre, htrFenetre);

        Canvas canvas = new Canvas(lrgFenetre, htrFenetre - htrBarreTache);

        HBox menuBoutons = new HBox(10);
        root.getChildren().add(canvas);
        root.getChildren().add(menuBoutons);
        Button pause = new Button("Pause");
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

        // Après l’exécution de la fonction, le focus va automatiquement au canvas
        Platform.runLater(() -> {
            canvas.requestFocus();
        });

        // Lorsqu’on clique ailleurs sur la scène, le focus retourne sur le canvas */
        scene.setOnMouseClicked((event) -> {
            canvas.requestFocus();
        });


        // si l'utilisateur pese sur la barre d'espace, avertir le controleur
        scene.setOnKeyPressed((value) -> {
            if (value.getCode() == KeyCode.SPACE) {
                this.controleur.evenement(Jeu.actions.SAUTER);
            }
        });

        pause.setOnAction((event)-> {
            this.controleur.evenement(Jeu.actions.PAUSE);
        });

        modeDebug.setOnAction((event)-> {
            this.modeDebug = modeDebug.isSelected();
        });

    }

    public void miseAJour(Jeu jeu) {
        //Afficher Background
        Background bg = jeu.getBackgroud();
        context.clearRect(0, 0, lrgFenetre, htrFenetre - htrBarreTache);
        Image image = bg.getImage();
        context.drawImage(image, bg.getCoordX(),0, image.getWidth(), image.getHeight() );
        context.drawImage(image, (bg.getCoordX() + lrgFenetre), 0,image.getWidth(), image.getHeight());

        score.setText("Score: " + jeu.getScore());

        // afficher le fantome
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

        // afficher les obstacles
        Iterator it = jeu.getListeObstacles().iterator();
        while(it.hasNext()){
            Obstacle obstacle = (Obstacle) it.next();
            if(!modeDebug) {
                context.drawImage(obstacle.getImage(),
                        obstacle.getCoordX() - obstacle.getRayon(), obstacle.getCoordY() - obstacle.getRayon(),
                        obstacle.getRayon() * 2, obstacle.getRayon() * 2);
            } else {
                context.setFill(obstacle.getCouleur());
                context.fillOval(obstacle.getCoordX()- obstacle.getRayon(), obstacle.getCoordY() - obstacle.getRayon(),
                        obstacle.getRayon() * 2, obstacle.getRayon() * 2);
            }
        }

    }
}

