package mongoclient.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        stage.setTitle("MongoCLient by Clockw0rk");
        stage.setScene(scene);
        stage.show();
    }

    //TODO: maybe a better failsave than an inherent IOException
    //for example: fall back to the main menu initial scene (private variable)
    public void changeScene(String fxmlScene) throws IOException {
        Parent scene = FXMLLoader.load(getClass().getResource(fxmlScene));
        primaryStage.getScene().setRoot(scene);
    }


    public static void main(String[] args) {
        launch();
    }
}

/*
adding stuff at run time

Scene scene = new Scene(new Group());
scene.getStylesheets().add(“test.css”);
Rectangle rect = new Rectangle(100,100);
rect.setLayoutX(50);
rect.setLayoutY(50);
rect.getStyleClass().add("my-rect");
((Group)scene.getRoot()).getChildren().add(rect);

 */