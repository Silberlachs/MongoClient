package mongoclient.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private static HelloApplication instance;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        this.instance = this;

        stage.setTitle("MongoCLient by Clockw0rk");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxmlScene, String parameters) throws IOException {
        Parent scene = FXMLLoader.load(getClass().getResource(fxmlScene));
        scene.setUserData(parameters);
        primaryStage.getScene().setRoot(scene);
    }

    public static HelloApplication getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        launch();
    }
}