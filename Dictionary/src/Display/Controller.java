package Display;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import Management.DictionaryCommandline;

public class Menu {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void handleSceneMenu() {
        root = FXMLLoader.load(getClass().getResource("SMenu.fxml"));
        scene = new Scene(root);
        stage.setTitle("Dictionary");
        stage.setScene(new Scene(root, 400, 800));
        stage.show();
    }
    public void handleSceneSearch() {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        scene = new Scene(root);
        stage.setTitle("Dictionary");
        stage.setScene(new Scene(root, 400, 800));
        stage.show();
    }
    public void handleSceneEdit() {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        scene = new Scene(root);
        stage.setTitle("Dictionary");
        stage.setScene(new Scene(root, 400, 800));
        stage.show();
    }
    public void handleSceneAdvanceSearch() {
        root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        scene = new Scene(root);
        stage.setTitle("Dictionary");
        stage.setScene(new Scene(root, 400, 800));
        stage.show();
    }

}
