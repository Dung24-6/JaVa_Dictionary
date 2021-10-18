package Display;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Objects;

import Management.DictionaryCommandline;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void handleSceneMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SceneMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dictionary");
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.show();
    }
    public void handleSceneSearch(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SceneSearch.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dictionary");
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.show();
    }
    public void handleSceneEdit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SceneEdit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dictionary");
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.show();
    }
    public void handleSceneHistory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SceneHistory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dictionary");
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.show();
    }
    public void handleSceneAdvancedSearch(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SceneAdvancedSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dictionary");
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.show();
    }
    public void handleExitButton(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void handleSearchButton() {

    }
    public void handleAddButton() {

    }
    public void handleModifyButton() {

    }
    public void handleDeleteButton() {

    }
    public void handleAdvanceSearchButton() {

    }


}
