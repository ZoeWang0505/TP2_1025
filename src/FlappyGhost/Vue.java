package FlappyGhost;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Text titre = new Text("Flappy Ghost\n\n");

        Canvas canvas = new Canvas(WIDTH, HEIGHT-40);
        HBox menuBoutons = new HBox();
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
        primaryStage.show();



        // Création du contrôleur
        controleur = new Controleur(this);
    }

    public void miseAJour(String text) {
        output.setText(text);
}

