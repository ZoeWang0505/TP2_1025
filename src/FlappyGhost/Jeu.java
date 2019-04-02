package FlappyGhost;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Jeu extends Application {

    private int WIDTH = 480;
    private int HEIGHT = 300;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
