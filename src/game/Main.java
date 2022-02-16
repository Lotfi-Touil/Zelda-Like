package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View/view.fxml"));
    	Parent root = loader.load();

        Scene scene = new Scene(root, Constants.PX_WIDTH, Constants.PX_HEIGHT);
        scene.getRoot().requestFocus();

        primaryStage.setTitle("LWL");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:assets/icon/icon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
         MediaPlayer player = new MediaPlayer(new Media(new File("assets/music/theme.mp3").toURI().toString()));
         player.setVolume(0.05);
         player.setAutoPlay(true);
        launch(args);
    }
}
