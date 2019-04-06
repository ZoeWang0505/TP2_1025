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

public class Vue extends Application {
    private Controleur controleur;  // Contrôleur de l'application
    private int lrgFenetre = 640;
    private int htrFenetre = 440;
    private int htrBarreTache = 40;
    int xFantome;
    int yFantome;

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
        GraphicsContext context = canvas.getGraphicsContext2D();
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


        // Création du contrôleur
        controleur = new Controleur(this);


        AnimationTimer timer = new AnimationTimer() { // Classe anonyme
            private long lastTime = 0;
            private double xBackground = 0;

            @Override
            public void start() {
                lastTime = System.nanoTime();
                super.start(); // Commence les appels de handle(...)
            }

            @Override
            public void handle(long now) {

                // Temps en sec = 10^(-9) * temps en nanosec
                double deltaTime = (now - lastTime) * 1e-9;
                xBackground -= deltaTime * 90; // 90 pixels/s


                context.clearRect(0, 0, lrgFenetre, htrFenetre - htrBarreTache);
                context.drawImage(Resources.getBg(), xBackground, 0, lrgFenetre, htrFenetre - htrBarreTache);
                context.drawImage(Resources.getBg(), (xBackground + lrgFenetre), 0, lrgFenetre,
                        htrFenetre - htrBarreTache);

                if (xBackground < -lrgFenetre){
                    xBackground = xBackground + lrgFenetre;
                }

                // afficher le fantome
                context.drawImage(Resources.getGhost(), xFantome, yFantome); //TODO:update ghost coords in real time

                lastTime = now;
            }
        };
        timer.start(); // demarrer le timer
    }

    public void miseAJour(int xFantome, int yFantome) {
        this.xFantome = xFantome;
        this.yFantome = yFantome;

        //TODO: update attributes depending on input from Controller
    }
}

