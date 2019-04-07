package FlappyGhost;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.beans.EventHandler;

public class Vue extends Application {
    private Controleur controleur;  // Contrôleur de l'application
    private int lrgFenetre = 640;
    private int htrFenetre = 440;
    private int htrBarreTache = 40;
    private GraphicsContext context;
    int xFantome;
    int yFantome;
    long lastTime = 0;


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
        Text score = new Text("Score: 0");
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

        context.clearRect(0, 0, lrgFenetre, htrFenetre - htrBarreTache);

        //Afficher Background
        Background bg = jeu.getBackgroud();
        context.drawImage(bg.getImage(), bg.getCoordX(),0);
        context.drawImage(Resources.getBg(), (bg.getCoordX() + lrgFenetre), 0);

        // afficher le fantome
        Fantome fantome = jeu.getFantome();
        context.drawImage(fantome.getImage(), fantome.getCoordX(), fantome.getCoordY()); //TODO:update ghost coords in real time

        //this.xFantome = xFantome;
        //this.yFantome = yFantome;

        //TODO: update attributes depending on input from Controller
    }
}

