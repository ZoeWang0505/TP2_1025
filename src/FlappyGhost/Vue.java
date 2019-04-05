package FlappyGhost;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Vue extends Application {
    // Contrôleur de l'application
    private Controleur controleur;


    public static void main(String[] args) {Vue.launch(args);}

    private int WIDTH = 640;
    private int HEIGHT = 440;
    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox(8);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Canvas canvas = new Canvas(WIDTH, HEIGHT-40);
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

        // -----------temporaire ---------------
        GraphicsContext context = canvas.getGraphicsContext2D();
        //context.drawImage(Resources.getBg(), 100, 0, WIDTH, HEIGHT-40);
        //context.drawImage(Resources.getBg(), (100-WIDTH), 0, WIDTH, HEIGHT-40);


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


                context.clearRect(0, 0, WIDTH, HEIGHT-40);
                context.drawImage(Resources.getBg(), xBackground, 0, WIDTH, HEIGHT-40);
                context.drawImage(Resources.getBg(), (xBackground + WIDTH), 0, WIDTH, HEIGHT-40);

                if (xBackground < -WIDTH){
                    xBackground = xBackground + WIDTH;
                }

                lastTime = now;
            }
        };
        timer.start(); // démarrer le timer




        /*
        public void miseAJour() {
            context.drawImage(Resources.getBg(), 0, 0, 640, 400);
        }*/

    }

}

