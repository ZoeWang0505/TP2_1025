package FlappyGhost;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

import java.beans.EventHandler;
import java.util.Iterator;

public class Vue extends Application {
    private Controleur controleur;  // Contrôleur de l'application
    private int lrgFenetre = 640;
    private int htrFenetre = 440;
    private int htrBarreTache = 40;
    private GraphicsContext context;
    int xFantome;
    int yFantome;
    long lastTime = 0;

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
    }

    public void miseAJour(Jeu jeu) {
        //Afficher Background
        Background bg = jeu.getBackgroud();
        context.clearRect(0, 0, lrgFenetre, htrFenetre - htrBarreTache);
        Image image = bg.getImage();
        context.drawImage(image, bg.getCoordX(),0, image.getWidth(), image.getHeight() );
        context.drawImage(image, (bg.getCoordX() + lrgFenetre), 0,image.getWidth(), image.getHeight());

        score.setText((String.valueOf(bg.getCoordX())));

        // afficher le fantome
        Fantome fantome = jeu.getFantome();
        context.drawImage(fantome.getImage(), fantome.getCoordX(), fantome.getCoordY());

        // afficher les obstacles
        Iterator it = jeu.getListeObstacles().iterator();
        while(it.hasNext()){
            Obstacle obstacle = (Obstacle)it.next();
            context.drawImage(obstacle.getImage(),
                    obstacle.getCoordX(),obstacle.getCoordY(),
                    obstacle.getRayon(), obstacle.getRayon());
        }

        //TODO: update attributes depending on input from Controller
    }
}

