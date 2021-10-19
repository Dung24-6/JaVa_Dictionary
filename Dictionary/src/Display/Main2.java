package Display;

import Management.DictionaryCommandline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main2  extends Application {

    public static void main(String[] args) throws IOException {
        DictionaryCommandline d = new DictionaryCommandline();
        d.dictionaryImport();
        launch(args);
        d.dictionaryExport();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
        primaryStage.setTitle("Dictionary");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}